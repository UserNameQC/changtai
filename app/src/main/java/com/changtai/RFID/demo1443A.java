package com.changtai.RFID;

import com.android.RfidControll;
import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.HelloWorldController;
import com.changtai.application.MyApplication;
import com.changtai.databinding.Rfid1443aBinding;
import com.changtai.sqlModel.PriceModel;
import com.changtai.sqlModel.PurchaseRecordModel;
import com.changtai.sqlModel.UserModel;
import com.changtai.sqlModelDao.PriceModelDao;
import com.changtai.sqlModelDao.PurchaseRecordModelDao;
import com.changtai.sqlModelDao.UserModelDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

import org.greenrobot.greendao.query.QueryBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("all")
public class demo1443A extends Activity implements OnClickListener {

    RfidControll rfid = new RfidControll();
    private ArrayAdapter<String> adapter;
    private static final String[] mBLOCK = new String[64 * 4];
    public UserModelDao userModelDao;
    public UserModel userModel;
    public RfidUtils utils;
    public String cardNum = "";

    public PriceModel priceModel;
    public PriceModelDao priceModelDao;
    public int res = -1;
    public Rfid1443aBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(demo1443A.this, R.layout.rfid_1443a);
        utils = new RfidUtils();
        rfid.OpenComm();
        initView();
        initData();
    }

    public void initView() {
        binding.btnReadstring.setOnClickListener(this);
        binding.btnWritestring.setOnClickListener(this);

        //点击查看更多
        binding.rfidClickMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.rfidClickMore.setVisibility(View.GONE);
                binding.linearLayout52.setVisibility(View.VISIBLE);
            }
        });

        binding.rfidBuyPurchase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                BigDecimal bigTotal = BigDecimal.valueOf(Double.parseDouble(s.toString())
                        + Double.parseDouble(userModel.getPurchaseTotal()));
                BigDecimal bigTotalYear = BigDecimal.valueOf(Double.parseDouble(s.toString())
                        + Double.parseDouble(userModel.getPurchaseTotalThisYear()));
                binding.rfidPurchaseTotal.setText(String.valueOf(bigTotal));
                binding.rfidBuyYearAmount.setText(String.valueOf(bigTotalYear));
                //binding.rfidBuyWaterAmount.setText();
            }
        });
    }

    public void initData() {
        PriceModelDao priceModelDao = MyApplication.getInstance().getDaoSession().getPriceModelDao();
        QueryBuilder<PriceModel> queryBuilder = priceModelDao.queryBuilder();
        List<PriceModel> priceModels = queryBuilder.where(queryBuilder.and(PriceModelDao.Properties
                .StationNo.eq(userModel.getStationNo()), PriceModelDao.Properties.SjId.eq(userModel.getSjId()))).list();
        if (priceModels != null && priceModels.size() > 0) {
            PriceModel priceModel = priceModels.get(0);
            binding.rfidFirstPrice.setText(priceModel.getSj1());
            binding.rfidSecondPrice.setText(priceModel.getSj2());
            binding.rfidThirdPrice.setText(priceModel.getSj3());
        }
    }

    public Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 200) {
                if (userModel == null) return;
                binding.rfidBuyYearAmount.setText(userModel.getPurchaseTotalThisYear());
                binding.rfidFirst.setText(userModel.getLimitSj1());
                binding.rfidSecond.setText(userModel.getLimitSj2());

                //判断累计购水量是否相同
                String purchaseTotal = userModel.getPurchaseTotal();
                String card_purchaseTotal = binding.rfidPurchaseTotal.getText().toString();
                if (!TextUtils.isEmpty(card_purchaseTotal) && !TextUtils.isEmpty(purchaseTotal)) {
                    if (!purchaseTotal.equals(card_purchaseTotal)) {
                        Entity.toastMsg(demo1443A.this, "数据不一致，请重新同步数据");
                        return;
                    }
                }
                int sl1 = Integer.parseInt(userModel.getLimitSj1());
                int sl2 = Integer.parseInt(userModel.getLimitSj2());
                int yearTotal = Integer.parseInt(binding.rfidBuyYearAmount.getText().toString());
                binding.rfidFirstAmount.setText(yearTotal > sl1 ? String.valueOf(sl1) : String.valueOf(yearTotal));
                binding.rfidSecondAmount.setText(yearTotal > sl2 ? String.valueOf(sl2) : String.valueOf(yearTotal - sl1));
            }

            if (msg.what == 300) {
                int thisPurchase = Integer.parseInt(binding.rfidBuyPurchase.getText().toString());//本次购水量
                String year = binding.rfidBuyYearAmount.getText().toString();//原年度购水量
                String total = binding.rfidPurchaseTotal.getText().toString();//原累计购水量
                int resultYear = Integer.parseInt(year) + thisPurchase;
                int resultTotal = Integer.parseInt(total) + thisPurchase;
                binding.rfidBuyYearAmount.setText(String.valueOf(resultYear));
                binding.rfidPurchaseTotal.setText(String.valueOf(resultTotal));
                //更新数据库
                userModel.setPurchaseTotalThisYear(String.valueOf(resultYear));
                userModel.setPurchaseTotal(String.valueOf(resultTotal));
                userModel.setAdministratorName(Entity.spres.getString(Entity.LOGIN_NAME, ""));
                userModelDao.insertOrReplace(userModel);
            }
        }
    };


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_readstring:
                byte buffer1[] = new byte[256];
                if (getPassWord() == null) return;
                res = rfid.API_MF_Read(0x00, 0x01, 57, 1, getPassWord(), buffer1);
                if (res == 0) {
                    UserFromCardBean userFromCardBean = utils.getCardBean(buffer1);
                    if (userFromCardBean == null) return;
                    String code = userFromCardBean.getFlag();
                    if (!code.equals("01")) {
                        Entity.toastMsg(this, "非用户卡，请更换卡片后重试");
                        return;
                    }
                    userModelDao = MyApplication.getInstance().getDaoSession().getUserModelDao();
                    List<UserModel> userModels = userModelDao.queryBuilder()
                            .where(UserModelDao.Properties.UserNo.eq(userFromCardBean.getUserCardNo())).list();
                    if (!userModels.isEmpty()) {
                        userModel = userModels.get(0);
                        if (!userModel.getCardNo().equals(this.cardNum)) {
                            Entity.toastMsg(this, "卡号不一致，不允许售水");
                            return;
                        }
                    }

                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
                    String date = format.format(userModel.getLastDatetime());
                    if (Entity.GetNowTime().compareTo(date) < 0){
                        Entity.toastMsg(this, "本机时间小于最后购水时间，不允许购水！");
                        return;
                    }
                    if (userFromCardBean.getFlag().equals("20")){
                        Entity.toastMsg(this, "此卡为水量设置卡，不允许购水！");
                        return;
                    }

                    QueryBuilder<PurchaseRecordModel> queryBuilder = MyApplication.getInstance().getDaoSession().getPurchaseRecordModelDao().queryBuilder();
                    List<PurchaseRecordModel> purModels =
                            queryBuilder.where(PurchaseRecordModelDao.Properties.UserNo.eq(userFromCardBean.getUserCardNo())).list();
                    if (purModels != null && purModels.size() > 0) {
                        PurchaseRecordModel purchaseRecordModel = purModels.get(0);
                        if (Long.parseLong(purchaseRecordModel.getPurchaseTotal()) > Long.parseLong(userFromCardBean.getToal())) {
                            Entity.toastMsg(this, "该用户已补卡，本卡是丢失的原用户卡，不允许继续使用");
                            return;
                        } else if (Long.parseLong(purchaseRecordModel.getPurchaseTotal()) < Long.parseLong(userFromCardBean.getToal())) {
                            Entity.toastMsg(this, "本卡数据缺失， 请同步数据后再操作");
                            return;
                        }
                    }

                    /**
                     * 放在最后判断
                     */
                    if (userFromCardBean.getFlag().equals("01") && Integer.parseInt(userFromCardBean.getToal()) > 0){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setTitle("提示：");
                        dialog.setMessage("此用户上次购水后未插卡，是否继续购水？");
                        dialog.setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        dialog.setPositiveButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                return;
                            }
                        });
                        dialog.show();
                    }

                    if (userFromCardBean.getFlag().equals("00") && userFromCardBean.getPurchaseDate().compareTo(userModel.getUsedTotal()) > 0){
                        UserModel userModel = new UserModel();
                        userModel.setServerVersion(0L);
                        userModel.setClientVersion(Long.parseLong(Entity.GetNowTime()));
                        userModel.setUsedTotal(userFromCardBean.getPurchaseDate());
                        userModelDao.insertOrReplace(userModel);
                    }



                    binding.rfidUserNo.setText(userFromCardBean.getUserCardNo());//用户号
                    //binding.rfidBz.setText(userFromCardBean.getFlag());//标志
                    binding.rfidPurchaseTotal.setText(userFromCardBean.getToal());//累计购水量
                    binding.rfidOverdraft.setText(String.valueOf(Integer.parseInt(userModel.getOverdraft()) / 100));//透支限量
                    binding.rfidAlarmValue.setText(String.valueOf(Integer.parseInt(userModel.getAlarmValue()) / 100));//报警水量
                    binding.rfidBuyDate.setText(userFromCardBean.getPurchaseDate());//购水日期
                    binding.rfidUseWaterAmount.setText(userModel.getUsedTotal());
                    Log.d("012", toHexString(buffer1, 1 * 16));
                    handler.sendEmptyMessage(200);
                } else {
                    Entity.toastMsg(demo1443A.this, "当前用户非本地区用户，不可购水");
                }
                break;
            case R.id.btn_writestring:

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(binding.rfidUserNo.getText().toString())
                        //.append(binding.rfidBz.getText().toString())
                        .append(binding.rfidPurchaseTotal.getText().toString())
                        .append(binding.rfidOverdraft.getText().toString())
                        .append(binding.rfidAlarmValue.getText().toString())
                        .append(binding.rfidBuyDate.getText().toString());

                if (utils.writeToCard(stringBuffer.toString())) {
                    Entity.toastMsg(demo1443A.this, "写卡成功！");
                    handler.sendEmptyMessage(300);
                }
                break;
        }
    }


    public byte[] getPassWord() {
        byte[] uid = new byte[4];
        byte[] pdata = new byte[1];
        pdata[0] = 0x00;
        byte buffer[] = new byte[256];
        res = rfid.API_MF_Request(0, 0x26, buffer);
        if (res == 0) {
            res = rfid.API_MF_Anticoll(0, pdata, buffer);
            if (res == 0) {
                System.arraycopy(buffer, 0, uid, 0, 4);
            }
            if (res == 0) {
                res = rfid.API_MF_Select(0, uid, buffer);
                byte[] n = {buffer[0], buffer[1], buffer[2], buffer[3]};
                cardNum = toHexString(n, 4);
                Log.e("0-carNum", cardNum);
                StringBuffer result = new StringBuffer("");
                for (int i = cardNum.length(); i >= 0; i -= 2) {
                    if (i - 2 >= 0)
                        result.append(cardNum.substring(i - 2, i));
//					else
//						result.append(cardNum.substring(0,2));
                }
                cardNum = result.toString();
            }
        }
        if (TextUtils.isEmpty(cardNum)) {
            Log.e("Password", "getPassWord: " + res);
            return null;
        }
        long cardL = Long.parseLong(cardNum, 16);
        Log.e("ReCard", cardNum);
        HelloWorldController controller = new HelloWorldController();
        //Log.e("Test1",toHexString(controller.GetPass(cardL), 6));
        int a = rfid.API_MF_Request(0x00, 0x52, new byte[2]);
        //Log.e("RFID", a + "");
        byte[] snr = controller.GetPass(cardL);
        return snr;
    }

    private String toHexString(byte[] byteArray, int size) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException(
                    "this byteArray must not be null or empty");
        final StringBuilder hexString = new StringBuilder(2 * size);
        for (int i = 0; i < size; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
            if (i != (byteArray.length - 1))
                hexString.append("");
        }
        return hexString.toString().toUpperCase();
    }

    private byte[] toByteArray(String hexString) {
        hexString = hexString.toUpperCase();
        final byte[] byteArray = new byte[(hexString.length() + 1) / 3];
        int k = 0;

        for (int i = 0; i < byteArray.length; i++) {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 3;
        }
        return byteArray;

    }

    protected void onDestroy() {
        rfid.CloseComm();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (binding.linearLayout52.getVisibility() == View.VISIBLE) {
            binding.linearLayout52.setVisibility(View.GONE);
            binding.rfidClickMore.setVisibility(View.VISIBLE);
        } else
            super.onBackPressed();
    }
}