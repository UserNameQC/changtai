package com.changtai.RFID;

import java.io.UnsupportedEncodingException;

import com.android.RfidControll;
import com.changtai.R;
import com.changtai.Utils.HelloWorldController;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class demo1443A extends Activity implements OnClickListener {

	private Button btn_clr1, btn_clr2, btn_clr3;
	private Button btn_readuid;
	private Button btn_read;
	private Button btn_write;
	RfidControll rfid = new RfidControll();
	//private EditText mRecetxet;
	private EditText msendtext;
	private EditText mUidxet;
	private EditText mrfid_purchaseTotal;
	private EditText rfid_overdraft;
	private EditText rfid_alarm_value;
	private EditText rfid_buy_date;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private static final String[] mBLOCK = new String[64*4];
	int m_readblock = 0;
	int m_writeblock = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rfid_1443a);
		btn_read = (Button) findViewById(R.id.btn_readstring);
		btn_write = (Button) findViewById(R.id.btn_writestring);

		rfid.OpenComm();
		btn_read.setOnClickListener(this);
		btn_write.setOnClickListener(this);

		msendtext = findViewById(R.id.rfid_user_no);
		mUidxet = findViewById(R.id.rfid_bz);
		mrfid_purchaseTotal = findViewById(R.id.rfid_purchaseTotal);
		rfid_overdraft = findViewById(R.id.rfid_overdraft);
		rfid_alarm_value = findViewById(R.id.rfid_alarm_value);
		rfid_buy_date = findViewById(R.id.rfid_buy_date);
	}

 
	
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Object tag = arg0.getTag();
			switch (Integer.parseInt(String.valueOf(tag))) {
			case 10:
				m_readblock = arg2;
				break;
			/*case 11:
				m_writeblock = arg2;
				break;*/
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	int res = 0;
      

	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btn_readstring:

			//byte[] snr = new byte[6];
			//System.arraycopy(cc, 0 , snr, 0, 6);
			//Log.d("011", "btn_readstring");
			//byte snr[] = { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,(byte) 0xFF, (byte) 0xFF };
			byte buffer1[] = new byte[256];
			res = rfid.API_MF_Read(0x00, 0x01, 57/*m_readblock*/, 1, getPassWord(), buffer1);
			Log.e("011", "btn_readstring   "+res);
			Log.e("011", "buffer   "+  buffer1[0]);
			Log.e("011", "snr   "+  toHexString(getPassWord(), 6));

			if (res == 0) {
				//Log.e("012", toHexString(snr, 4));
				String result = toHexString(buffer1, 16);
				msendtext.setText(result.substring(0,10));//用户号
				mUidxet.setText(result.substring(10,12));//标志
				mrfid_purchaseTotal.setText(result.substring(12,20));//累计购水量
				rfid_overdraft.setText(result.substring(20,22));//透支限量
				rfid_alarm_value.setText(result.substring(22,24));//报警水量
				rfid_buy_date.setText(result.substring(24));//购水日期
				Log.d("012", toHexString(buffer1, 1 * 16));
			}
			break;
		case R.id.btn_writestring:
			
			byte buffer2[] = new byte[256];
			byte buffer3[] = new byte[32];

			StringBuffer stringBuffer = new StringBuffer("");
			stringBuffer.append(msendtext.getText().toString())
                    .append(mUidxet.getText().toString())
                    .append(mrfid_purchaseTotal.getText().toString())
                    .append(rfid_overdraft.getText().toString())
                    .append(rfid_alarm_value.getText().toString())
                    .append(rfid_buy_date.getText().toString());
			//buffer2=stringBuffer.toString().getBytes();
			buffer2 = toBytes(stringBuffer.toString());
			//buffer2 = toByteArray(msendtext.getText().toString());
			
		
			Log.e("write", "XXXXX"+msendtext.getText().toString());
			
			Log.e("write", "XXXXX"+buffer2);
			byte snr1[] = { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
					(byte) 0xFF, (byte) 0xFF };
			Log.e("write", "XXXXX"+snr1);
			if(buffer2.length<=32){
				
				System.arraycopy(buffer2, 0, buffer3, 0, buffer2.length);
				res = rfid.API_MF_Write(0x00, 0x01, 57, 2, getPassWord(), buffer3);
				Toast.makeText(this, "" + res,Toast.LENGTH_SHORT).show();
				if (res == 0) {
					//msendtext.setText(toHexString(buffer3, 4));

					Toast.makeText(this, "写卡成功",Toast.LENGTH_SHORT).show();
				}
				
			}		
			else	
			{
			res = rfid.API_MF_Write(0x00, 0x01, 57, 1, getPassWord(), buffer2);

			Log.d("011", "res--->"+res);
			if (res == 0) {
				//msendtext.setText(toHexString(buffer2, 4));
				Toast.makeText(this, "写卡成功--1",Toast.LENGTH_SHORT).show();
			}
			}
			break;
		}
	}

	public static byte[] toBytes(String str) {
		if(str == null || str.trim().equals("")) {
			return new byte[0];
		}

		byte[] bytes = new byte[str.length() / 2];
		for(int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}

		return bytes;
	}

	public byte[] getPassWord(){
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
				for (int i = cardNum.length(); i >=0; i-=2){
					if (i - 2 >=0)
						result.append(cardNum.substring(i-2,i));
//					else
//						result.append(cardNum.substring(0,2));
				}
				cardNum = result.toString();
			}
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
		// TODO Auto-generated method stub
		rfid.CloseComm();
		super.onDestroy();
	}

}