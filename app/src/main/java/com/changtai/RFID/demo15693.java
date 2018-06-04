//package com.changtai.RFID;
//
//import com.android.RfidControll;
//import com.changtai.R;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//public class demo15693 extends Activity implements OnClickListener {
//
//	private Button btn_clr1, btn_clr2, btn_clr3;
//	private Button btn_readuid;
//	private Button btn_read;
//	private Button btn_write;
//	RfidControll rfid = new RfidControll();
//	private EditText mRecetxet;
//	private EditText msendtext;
//	private EditText mUidxet;
//	private Spinner[] spinner = new Spinner[2];
//	private ArrayAdapter<String> adapter;
//	private static final String[] mBLOCK = new String[64];
//	private static final byte[] NULL = null;
//	public String s = null, s1 = null;
//	public int intsp1, intsp2;
//
//	int len =0;
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.rfid_15693);
//		btn_clr1 = (Button) findViewById(R.id.btn_clear1);
//		btn_clr2 = (Button) findViewById(R.id.btn_clear2);
//		btn_clr3 = (Button) findViewById(R.id.btn_clear3);
//		btn_readuid = (Button) findViewById(R.id.btn_readuid);
//		btn_read = (Button) findViewById(R.id.btn_readstring);
//		btn_write = (Button) findViewById(R.id.btn_writestring);
//
//
//		mRecetxet = (EditText) findViewById(R.id.edit_recetext);
//		msendtext = (EditText) findViewById(R.id.edit_sendtext);
//		mUidxet = (EditText) findViewById(R.id.edit_uidtext);
//		spinner[0] = (Spinner) findViewById(R.id.spinner1);
//		spinner[1] = (Spinner) findViewById(R.id.spinner2);
//		rfid.OpenComm();
//		btn_clr1.setOnClickListener(this);
//		btn_clr2.setOnClickListener(this);
//		btn_clr3.setOnClickListener(this);
//		btn_readuid.setOnClickListener(this);
//		btn_read.setOnClickListener(this);
//		btn_write.setOnClickListener(this);
//		for (int i = 0; i < 64; i++) {
//			mBLOCK[i] = String.format("%d", i);
//		}
//		adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_spinner_item, mBLOCK);
//
//		for (int i = 0; i < 2; i++) {
//			// ���������б�ķ��
//			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//			spinner[i].setAdapter(adapter);
//			spinner[i].setTag(i + 10);
//			spinner[i].setVisibility(View.VISIBLE);
//
//			// spinner[i].setSelection(0);
//			// ����¼�Spinner�¼�����
//			spinner[i].setOnItemSelectedListener(new SpinnerSelectedListener());
//		}
//
//	}
//
//	class SpinnerSelectedListener implements OnItemSelectedListener {
//
//		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
//				long arg3) {
//
//			s = spinner[0].getSelectedItem().toString();
//			s1 = spinner[1].getSelectedItem().toString();
//			intsp1 = Integer.parseInt(s);
//			intsp2 = Integer.parseInt(s1);
//
//		}
//		public void onNothingSelected(AdapterView<?> arg0) {
//		}
//	}
//
//	byte buffer[] = new byte[256];
//	byte buffer1[] = new byte[256];
//
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//
//		switch (v.getId()) {
//		case R.id.btn_clear1:
//			mUidxet.setText("");
//			break;
//		case R.id.btn_clear2:
//			mRecetxet.setText("");
//			break;
//		case R.id.btn_clear3:
//			msendtext.setText("");
//
//			break;
//
//		case R.id.btn_readuid:
//			byte[] pBuffer = new byte[30];
//			byte[] Bufferread = new byte[10];
//			byte[] nrOfCard = new byte[1];
//			byte[] pdata = new byte[1];
//			int res = -1;
//			pdata[0] = 0x00;
//			mUidxet.setText("");
//			res = rfid.API_ISO15693_Inventory(0, 0x02, 0, 0, pdata, nrOfCard,
//					pBuffer);
//			if(res==0)
//			{
//				int num=nrOfCard[0];
//				for(int i=0;i<num;i++)
//				{
//
//					System.arraycopy(pBuffer, 10*i+2, Bufferread, 0, 8);
//
//					mUidxet.append(toHexString(Bufferread, 8)+"\n");
//				}
//
//			}
//			else
//				Toast.makeText(demo15693.this, "δ�ҵ���",
//						Toast.LENGTH_SHORT).show();
//
//			break;
//		case R.id.btn_readstring:
//			int res1;
//			byte[] pBufferread = new byte[32];
//			byte[] returnlen = new byte[1];
//			mRecetxet.setText("");
//			res1 = rfid.API_ISO15693_ReadString(0, 0, pBufferread, 32);
//			if (res1 !=0) {
//				Toast.makeText(demo15693.this, "����ʧ��",
//						Toast.LENGTH_SHORT).show();
//			} else {
//				mRecetxet.setText(new String(pBufferread));//(toHexString(pBufferread, returnlen[0]));
//			}
//			break;
//
//		case R.id.btn_writestring:
//
//			byte buftest[] = new byte[4*64];
//			String sendstr = msendtext.getText().toString();
//			if (sendstr == null || sendstr.length() <= 0) {
//				Toast t1 = Toast.makeText(demo15693.this, "������Ҫ���͵��ַ�",
//						Toast.LENGTH_LONG);
//				t1.show();
//				break;
//			}
//			buftest =sendstr.getBytes(); //toByteArray(sendstr);// �ַ���ת16����
//			len =buftest.length;
//			if (buftest.length >= 4*64) {
//				Toast.makeText(demo15693.this, "��������ȷ�ĳ���",
//						Toast.LENGTH_SHORT).show();
//			}
//			// ��uid
//
//			Log.d("012","buftest.length:"+buftest.length);
//			int res2 = rfid.API_ISO15693_WriteString(0, 0x42, intsp2,
//					buftest);
//			if (res2  != 0) {
//				Toast.makeText(demo15693.this, "д��ʧ��",
//						Toast.LENGTH_SHORT).show();
//
//			} else {
//				 Toast.makeText(demo15693.this, "д��ɹ�",
//						Toast.LENGTH_SHORT).show();
//
//			}
//			break;
//
//		}
//	};
//
//	private String toHexString(byte[] byteArray, int size) {
//		if (byteArray == null || byteArray.length < 1)
//			throw new IllegalArgumentException(
//					"this byteArray must not be null or empty");
//		final StringBuilder hexString = new StringBuilder(2 * size);
//		for (int i = 0; i < size; i++) {
//			if ((byteArray[i] & 0xff) < 0x10)//
//				hexString.append("0");
//			hexString.append(Integer.toHexString(0xFF & byteArray[i]));
//			if (i != (byteArray.length - 1))
//				hexString.append("");
//		}
//		return hexString.toString().toUpperCase();
//	}
//
//
//	private byte[] toByteArray(String hexString) {
//		hexString = hexString.toUpperCase();
//		final byte[] byteArray = new byte[(hexString.length() + 1) / 3];
//		int k = 0;
//
//		for (int i = 0; i < byteArray.length; i++) {
//			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
//			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
//			byteArray[i] = (byte) (high << 4 | low);
//			k += 3;
//		}
//		return byteArray;
//
//	}
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		rfid.CloseComm();
//		super.onDestroy();
//	}
//
//}
