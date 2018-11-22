package com.changtai.RFID;

import com.android.RfidControll;
import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.HelloWorldController;
import com.changtai.application.MyApplication;
import com.changtai.databinding.Rfid1443aBinding;
import com.changtai.sqlModel.PurchaseRecordModel;
import com.example.john.greendaodemo.gen.PurchaseRecordModelDao;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class demo1443A extends Activity implements OnClickListener {

    RfidControll rfid = new RfidControll();
    private ArrayAdapter<String> adapter;
    private static final String[] mBLOCK = new String[64 * 4];

    public int res = -1;

    public Rfid1443aBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(demo1443A.this, R.layout.rfid_1443a);
        rfid.OpenComm();
        initView();
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
    }

    public void initData() {

    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_readstring:
                byte buffer1[] = new byte[256];
                if (getPassWord() == null) return;
                res = rfid.API_MF_Read(0x00, 0x01, 57/*m_readblock*/, 1, getPassWord(), buffer1);

                if (res == 0) {
                    String result = toHexString(buffer1, 16);
                    String code = result.substring(10, 12);
                    if (!code.equals("01")) {
                        Entity.toastMsg(this, "非用户卡，请更换卡片后重试");
                        return;
                    }

                    QueryBuilder<PurchaseRecordModel> queryBuilder =  MyApplication.getInstance().getDaoSession().getPurchaseRecordModelDao().queryBuilder();
                    List<PurchaseRecordModel> purModels =
                            queryBuilder.where(PurchaseRecordModelDao.Properties.Id.eq(result.substring(0, 10))).list();
                    if (purModels != null && purModels.size() > 0){
                        PurchaseRecordModel purchaseRecordModel = purModels.get(0);
                        if (Long.parseLong(purchaseRecordModel.getPurchaseTotal()) > Long.parseLong(result.substring(12, 20))) {
                            Entity.toastMsg(this, "该用户已补卡，本卡是丢失的原用户卡，不允许继续使用");
                            return;
                        } else if (Long.parseLong(purchaseRecordModel.getPurchaseTotal()) < Long.parseLong(result.substring(12, 20))) {
                            Entity.toastMsg(this, "本卡数据缺失， 请同步数据后再操作");
                            return;
                        }
                    }

                    binding.rfidUserNo.setText(result.substring(0, 10));//用户号
                    binding.rfidBz.setText(result.substring(10, 12));//标志
                    binding.rfidPurchaseTotal.setText(result.substring(12, 20));//累计购水量
                    binding.rfidOverdraft.setText(result.substring(20, 22));//透支限量
                    binding.rfidAlarmValue.setText(result.substring(22, 24));//报警水量
                    binding.rfidBuyDate.setText(result.substring(24));//购水日期
                    Log.d("012", toHexString(buffer1, 1 * 16));
                }
                break;
            case R.id.btn_writestring:

                byte buffer2[] = new byte[256];
                byte buffer3[] = new byte[32];

                StringBuffer stringBuffer = new StringBuffer("");
                stringBuffer.append(binding.rfidUserNo.getText().toString())
                        .append(binding.rfidBz.getText().toString())
                        .append(binding.rfidPurchaseTotal.getText().toString())
                        .append(binding.rfidOverdraft.getText().toString())
                        .append(binding.rfidAlarmValue.getText().toString())
                        .append(binding.rfidBuyDate.getText().toString());
                //buffer2=stringBuffer.toString().getBytes();
                buffer2 = toBytes(stringBuffer.toString());
                //buffer2 = toByteArray(msendtext.getText().toString());


                Log.e("write", "XXXXX" + buffer2);
                byte snr1[] = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                        (byte) 0xFF, (byte) 0xFF};
                Log.e("write", "XXXXX" + snr1);
                if (buffer2.length <= 32) {

                    System.arraycopy(buffer2, 0, buffer3, 0, buffer2.length);
                    res = rfid.API_MF_Write(0x00, 0x01, 57, 2, getPassWord(), buffer3);
                    Toast.makeText(this, "" + res, Toast.LENGTH_SHORT).show();
                    if (res == 0) {
                        //msendtext.setText(toHexString(buffer3, 4));

                        Toast.makeText(this, "写卡成功", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    res = rfid.API_MF_Write(0x00, 0x01, 57, 1, getPassWord(), buffer2);

                    Log.d("011", "res--->" + res);
                    if (res == 0) {
                        //msendtext.setText(toHexString(buffer2, 4));
                        Toast.makeText(this, "写卡成功--1", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    public byte[] getPassWord() {
        String cardNum = "";
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