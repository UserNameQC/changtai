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
import java.util.Date;
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
    //原年度购水量是否已经超出范围
    public boolean isMoreFirstTotal = false, isMoreSecTotal = false;

    //水费
    public BigDecimal firstCost, secondCost, thirdCost;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(demo1443A.this, R.layout.rfid_1443a);
        utils = new RfidUtils();
        rfid.OpenComm();
        initView();
        //initData();
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
                BigDecimal bigDecimal = new BigDecimal(s.toString());
                BigDecimal max = new BigDecimal("99999999");
                BigDecimal min = new BigDecimal("0");
                if (bigDecimal.compareTo(max) > 0 || bigDecimal.compareTo(min) < 0){
                    Entity.toastMsg(demo1443A.this, "您输入的内容超出规定范围，请重新输入！");
                    return;
                }
                setResultFromTotal(s.toString());
            }
        });

        binding.rfidOverdraft.addTextChangedListener(textWatcher);
        binding.rfidAlarmValue.addTextChangedListener(textWatcher);
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int result = Integer.parseInt(s.toString());
            if (result < 0 || result > 99){
                Entity.toastMsg(demo1443A.this, "您输入的内容不再范围内，请重新输入！");
                return;
            }
        }
    };

    public void setResultFromTotal(String s) {
        BigDecimal secLimit = new BigDecimal(userModel.getLimitSj2());
        BigDecimal firLimit = new BigDecimal(userModel.getLimitSj1());
        //购水量
        BigDecimal purTotal = new BigDecimal(s);
        //累计购水量
        BigDecimal bigTotal = new BigDecimal(s).add(new BigDecimal(userModel.getPurchaseTotal()));
        //年度购水量
        BigDecimal bigTotalYear =
                new BigDecimal(s).add(new BigDecimal(userModel.getPurchaseTotalThisYear()));
        //一级水价
        BigDecimal sj1 = new BigDecimal(priceModel.getSj1());
        BigDecimal sj2 = new BigDecimal(priceModel.getSj2());
        BigDecimal sj3 = new BigDecimal(priceModel.getSj3());

        binding.rfidPurchaseTotal.setText(String.valueOf(bigTotal));
        binding.rfidBuyYearAmount.setText(String.valueOf(bigTotalYear));
        /**
         * 1. isMoreSecTotal 为真：年度购水量超过二级水量上限；且肯定超过一级水量上限；
         *    此时不在计算与一级水量相关的结果。
         *
         * 2.若isMoreSecTotal 为假：年度购水量小于或等于二级水量上限；
         *  再判断通过isMoreFirstTotal的结果来判定是否超过一级水量上限；
         *  若isMoreFirstTotal为真：年度购水量超过一级购水量上限；
         *  若为假，则小于等于一级水量上限。
         *
         *  以上年度购水量的判断均以原年度购水量为准
         *
         * 3.增加本次购水量结果后，再次与水量上限进行比较，动态更改水量的数据，然后计算三种级别的水费。
         *  并计算购水金额。
         */
        if (isMoreSecTotal) {//此时所有购买的水量，均为三级水量
            BigDecimal thirdTotal = bigTotalYear.subtract(secLimit);
            //三级水量
            binding.rfidThirdAmount.setText(String.valueOf(thirdTotal));
            //三级水费
            /**
             * 标注：此时水量全为三级水量，与一级、二级水量无关，一级水费、二级水费是否为零？
             */
            thirdCost = sj3.multiply(thirdTotal);
            binding.rfidThirdCost.setText(String.valueOf(thirdCost));
           //购水金额
            binding.rfidBuyWaterAmount.setText(String.valueOf(thirdCost));
        } else {
            /**
             * 此时要计算增加购水量后，是否超过二级水量上限
             */
            if (isMoreFirstTotal){
                if (bigTotalYear.compareTo(secLimit) > 0){
                    //超过二级水量部分按三级水价计算
                    //三级水量 = 总年度购水量 - 二级水量上限
                    thirdCost = sj3.multiply(bigTotalYear.subtract(secLimit));
                    binding.rfidThirdCost.setText(String.valueOf(thirdCost));
                    binding.rfidThirdAmount.setText(String.valueOf(bigTotalYear.subtract(secLimit)));
                    //二级水量 = 购水量 - 三级水量
                    secondCost = sj2.multiply(purTotal.subtract(bigTotalYear.subtract(secLimit)));
                    binding.rfidSecondCost.setText(String.valueOf(secondCost));
                    binding.rfidSecondAmount.setText(String.valueOf(secLimit.subtract(firLimit)));
                    binding.rfidFirstCost.setText("0");

                    binding.rfidBuyWaterAmount.setText(String.valueOf(secondCost.add(thirdCost)));
                } else {
                    //此时购水量全为二级水量
                    secondCost = sj2.multiply(purTotal);
                    binding.rfidSecondAmount.setText(String.valueOf(bigTotalYear.subtract(firLimit)));
                    binding.rfidBuyWaterAmount.setText(String.valueOf(secondCost));
                    binding.rfidSecondCost.setText(String.valueOf(secondCost));
                    binding.rfidFirstCost.setText("0");
                    binding.rfidThirdCost.setText("0");
                }
            } else {
                /**
                 * 此时要判断总购水量是否超过一级购水量上限，以至于超过二级购水量上限
                 * 若超过一级水量上限且超过了二级水量上限：
                 * 三级水量 = 总购水量 - 二级上限；
                 * 二级水量 = 二级上限 - 一级上限；
                 * 一级水量 = 本次购水量 - （三级水量 + 二级水量）
                 *
                 * 若只是超过一级上限，未能超过二级上限：
                 * 二级水量 = 总购水量 - 一级上限
                 * 一级水量 = 本次购水量 - 二级水量
                 *
                 * 若未能超过一级水量：
                 * 一级水量 = 总购水量
                 * 一级水费 = 一级水价 * 本次购水量
                 */
                if (bigTotalYear.compareTo(firLimit) > 0){
                    if (bigTotalYear.compareTo(secLimit) > 0){
                        BigDecimal thirdTotal = bigTotalYear.subtract(secLimit);
                        thirdCost = sj3.multiply(thirdTotal);
                        BigDecimal secTotal = secLimit.subtract(firLimit);
                        secondCost = sj2.multiply(secTotal);
                        firstCost = sj1.multiply(purTotal.subtract(thirdTotal.add(secTotal)));

                        binding.rfidFirstCost.setText(String.valueOf(firstCost));
                        binding.rfidSecondCost.setText(String.valueOf(secondCost));
                        binding.rfidThirdCost.setText(String.valueOf(thirdCost));
                        binding.rfidThirdAmount.setText(String.valueOf(thirdTotal));
                        binding.rfidSecondAmount.setText(String.valueOf(secTotal));
                        binding.rfidFirstAmount.setText(String.valueOf(firLimit));

                        binding.rfidBuyWaterAmount.setText(String.valueOf(thirdCost.add(secondCost).add(firstCost)));
                    } else {
                        BigDecimal secTotal = bigTotalYear.subtract(firLimit);
                        secondCost = sj2.multiply(secTotal);
                        firstCost = sj1.multiply(purTotal.subtract(secTotal));

                        binding.rfidFirstAmount.setText(String.valueOf(firLimit));
                        binding.rfidSecondAmount.setText(String.valueOf(secTotal));
                        binding.rfidThirdAmount.setText("0");
                        binding.rfidFirstCost.setText(String.valueOf(firstCost));
                        binding.rfidSecondCost.setText(String.valueOf(secondCost));
                        binding.rfidThirdCost.setText("0");

                        binding.rfidBuyWaterAmount.setText(String.valueOf(secondCost.add(firstCost)));
                    }
                } else {
                    firstCost = sj1.multiply(purTotal);
                    binding.rfidFirstCost.setText(String.valueOf(firstCost));
                    binding.rfidFirstAmount.setText(String.valueOf(bigTotalYear));
                    binding.rfidSecondCost.setText("0");
                    binding.rfidSecondAmount.setText("0");
                    binding.rfidThirdCost.setText("0");
                    binding.rfidThirdAmount.setText("0");

                    binding.rfidBuyWaterAmount.setText(String.valueOf(firstCost));
                }
            }
        }

    }

    public void initData() {
        PriceModelDao priceModelDao = MyApplication.getInstance().getDaoSession().getPriceModelDao();
        QueryBuilder<PriceModel> queryBuilder = priceModelDao.queryBuilder();
//        List<PriceModel> priceModels = queryBuilder.where(queryBuilder.and(PriceModelDao.Properties
//                .StationNo.eq(userModel.getStationNo()), PriceModelDao.Properties.SjId.eq(userModel.getSjId()))).list();
        queryBuilder.and(PriceModelDao.Properties
                .StationNo.eq(userModel.getStationNo()), PriceModelDao.Properties.SjId.eq(userModel.getSjId()));
        List<PriceModel> priceModels =  queryBuilder.build().list();
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
                //水量设置
                if (yearTotal > sl1) {
                    isMoreFirstTotal = true;
                    binding.rfidFirstAmount.setText(String.valueOf(sl1));
                    if (yearTotal > sl2) {
                        isMoreSecTotal = true;
                        binding.rfidSecondAmount.setText(String.valueOf(sl2));
                        binding.rfidThirdAmount.setText(String.valueOf(yearTotal - sl2));
                    } else {
                        isMoreSecTotal = false;
                        binding.rfidSecondAmount.setText(String.valueOf(yearTotal - sl1));
                        binding.rfidThirdAmount.setText(String.valueOf(0));
                    }
                } else {
                    isMoreFirstTotal = false;
                    binding.rfidFirstAmount.setText(String.valueOf(yearTotal));
                    binding.rfidSecondAmount.setText(String.valueOf(0));
                    binding.rfidThirdAmount.setText(String.valueOf(0));
                }
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
                    } else {
                        Entity.toastMsg(this, "无此用户数据，非本水站用户");
                        return;
                    }

                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
                    String date = format.format(userModel.getLastDatetime() == null ? "0" : userModel.getLastDatetime());
                    if (Entity.GetNowTime().compareTo(date) < 0) {
                        Entity.toastMsg(this, "本机时间小于最后购水时间，不允许购水！");
                        return;
                    }
                    if (userFromCardBean.getFlag().equals("20")) {
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
                    if (userFromCardBean.getFlag().equals("01") && Integer.parseInt(userFromCardBean.getToal()) > 0) {
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

                    initData();

                    if (userFromCardBean.getFlag().equals("00") && userFromCardBean.getPurchaseDate().compareTo(userModel.getUsedTotal()) > 0) {
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

                if (Entity.editIsNull(binding.rfidAlarmValue)) return;
                if (Entity.editIsNull(binding.rfidOverdraft)) return;
                if (Entity.editIsNull(binding.rfidPurchaseTotal)) return;
                if (Entity.editIsNull(binding.rfidBuyPurchase)) return;

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
                Date date = new Date();
                String dates = format.format(new Date());
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(binding.rfidUserNo.getText().toString())
                        .append("01")
                        .append(binding.rfidPurchaseTotal.getText().toString())
                        .append(binding.rfidOverdraft.getText().toString())
                        .append(binding.rfidAlarmValue.getText().toString())
                        .append(dates);

                if (utils.writeToCard(stringBuffer.toString())) {
                    Entity.toastMsg(demo1443A.this, "写卡成功！");
                    //handler.sendEmptyMessage(300);
                }

                userModel.setAlarmValue(binding.rfidAlarmValue.getText().toString());
                userModel.setOverdraft(binding.rfidOverdraft.getText().toString());
                userModel.setLastDatetime(date);
                userModel.setPurchaseTotal(binding.rfidPurchaseTotal.getText().toString());
                userModel.setPurchaseTotalThisYear(binding.rfidBuyYearAmount.getText().toString());
                userModel.setServerVersion(0L);
                userModel.setClientVersion(Long.parseLong(dates));
                userModelDao.insertOrReplace(userModel);

                PurchaseRecordModel purchaseRecordModel = new PurchaseRecordModel();
                purchaseRecordModel.setStationNo(userModel.getStationNo());
                purchaseRecordModel.setDeviceNo(userModel.getDeviceNo());
                purchaseRecordModel.setUserNo(userModel.getUserNo());
                purchaseRecordModel.setUserName(userModel.getUserName());
                purchaseRecordModel.setPurchaseTotalThisTime(binding.rfidBuyPurchase.getText().toString());
                purchaseRecordModel.setPurchaseaMountThisTime(binding.rfidBuyWaterAmount.getText().toString());
                purchaseRecordModel.setPurchaseDatetimeThisTime(date);
                purchaseRecordModel.setPurchaseYear(new Date().getYear());
                purchaseRecordModel.setPurchaseTotal(userModel.getPurchaseTotal());
                purchaseRecordModel.setPurchaseTotalThisYear(userModel.getPurchaseTotalThisYear());
                purchaseRecordModel.setPriceSj1(priceModel.getSj1());
                purchaseRecordModel.setPriceSj2(priceModel.getSj2());
                purchaseRecordModel.setPriceSj3(priceModel.getSj3());
                purchaseRecordModel.setAmountSj1(binding.rfidFirstCost.getText().toString());
                purchaseRecordModel.setAmountSj2(binding.rfidSecondCost.getText().toString());
                purchaseRecordModel.setAmountSj3(binding.rfidThirdCost.getText().toString());
                purchaseRecordModel.setTotalSj1(binding.rfidFirstAmount.getText().toString());
                purchaseRecordModel.setTotalSj2(binding.rfidSecondAmount.getText().toString());
                purchaseRecordModel.setTotalSj3(binding.rfidThirdAmount.getText().toString());
                purchaseRecordModel.setAdministratorName(Entity.loginModel.getUserName());
                purchaseRecordModel.setServerVersion(0L);
                purchaseRecordModel.setClientVersion(Long.parseLong(dates));

                PurchaseRecordModelDao purchaseRecordModelDao = MyApplication.getInstance().getDaoSession().getPurchaseRecordModelDao();
                purchaseRecordModelDao.insertOrReplace(purchaseRecordModel);
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