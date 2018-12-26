package com.changtai.RFID;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.RfidControll;
import com.changtai.Utils.HelloWorldController;

/**
 * Created by qjcjob on 2018/12/26.
 */

public class RfidUtils {

    public String TAG = "WaterSettings";
    RfidControll rfid = new RfidControll();
    public int res = -1;
    public int read = -1;
    public byte buffer[] = new byte[256];
    public byte buffer1[] = new byte[256];

    public RfidUtils() {
        rfid.OpenComm();
    }

    public int readCard(byte[] password) {
        if (password == null) return -1;
        read = rfid.API_MF_Read(0x00, 0x01, 57/*m_readblock*/, 1, password, buffer);
        Log.e(TAG, "readCard: " + read);
        if (read == 0) {
            String result = toHexString(buffer, 16);
            if (result.equals("0000000000000000")) {
                return 0;
            } else {
                return 1;
            }
        }
        return 2;
    }

    public boolean writeToCard(String data) {
        byte buffer[] = toBytes(data);
        byte buffer3[] = new byte[32];
        System.arraycopy(buffer, 0, buffer3, 0, buffer.length);
        res = rfid.API_MF_Write(0x00, 0x01, 57, 2, getPassWord(), buffer3);
        if (res == 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否是新卡
     */
    public boolean isnNewCard() {
        byte buffer[] = new byte[256];
        byte snr[] = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        int res = rfid.API_MF_Read(0x00, 0x01, 57, 1, snr, buffer);
        if (res == 0) {
            Log.e(TAG, "isEmptyCard: " + "新卡");
            return true;
        }
        Log.e(TAG, "isEmptyCard: " + res);
        return false;
    }

    /**
     * 是否是空卡
     */
    public int isEmptyCard() {
        byte[] buffer = new byte[256];
        if (getPassWord() == null) {
            Log.e(TAG, "isEmptyCard: NULL");
            return -1;
        }
        int res = rfid.API_MF_Read(0x00, 0x01, 57, 1, getPassWord(), buffer);
        if (res == 0) {
            Log.e(TAG, "isEmptyCard: " + "空卡");
            Log.e(TAG, "isEmptyCard: " + toHexString(buffer, 16));
            String hexString = toHexString(buffer, 16);
            if (hexString.equals("00000000000000000000000000000000")) {
                return 0;
            }
            return 1;
        }
        Log.e(TAG, "isEmptyCard: " + res);
        return -1;
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
}
