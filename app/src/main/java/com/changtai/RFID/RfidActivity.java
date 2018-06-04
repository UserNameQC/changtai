//package com.changtai.RFID;
//import android.app.Activity;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.nfc.NfcAdapter;
//import android.nfc.Tag;
//import android.nfc.tech.IsoDep;
//import android.nfc.tech.MifareClassic;
//import android.nfc.tech.NfcA;
//import android.nfc.tech.NfcF;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.changtai.BaiduMap.BaiduMapActivity;
//import com.changtai.R;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class RfidActivity extends Activity  implements OnClickListener{
//	private Button btn_1443A;
//	private Button btn_15693;
//	private Button map;
//	public NfcAdapter mAdapter;
//	public PendingIntent mPendingIntent;
//	public IntentFilter[] mFilters;
//	public String[][] mTechLists;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.rfid_activity);
////		btn_1443A = (Button) findViewById(R.id.btn_1443A);
////		btn_15693 = (Button) findViewById(R.id.btn_15693);
////		map = (Button) findViewById(R.id.baidu_map);
////		btn_1443A.setOnClickListener(this);
////		btn_15693.setOnClickListener(this);
////		map.setOnClickListener(this);
//
//		mAdapter = NfcAdapter.getDefaultAdapter(this);
//		mPendingIntent = PendingIntent.getActivity(this, 0,
//				new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
//		try {
//			ndef.addDataType("*/*");
//
//		} catch (IntentFilter.MalformedMimeTypeException e) {
//			throw new RuntimeException("fail", e);
//		}
//		mFilters = new IntentFilter[]{ndef,};
//		mTechLists = new String[][]{{IsoDep.class.getName()}, {NfcA.class.getName()},};
//		Log.d(" mTechLists", NfcF.class.getName() + mTechLists.length);
//
//		if (mAdapter == null) {
//			Toast.makeText(this, "设备不支持NFC！", Toast.LENGTH_LONG).show();
//			return;
//		}
//		if (!mAdapter.isEnabled()) {
//			Toast.makeText(this, "请在系统设置中先启用NFC功能！", Toast.LENGTH_LONG).show();
//			return;
//		}
//	}
//
//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//	}
//
//	// 读卡
//	String resolveIntent(Intent intent) {
//		String action = intent.getAction();
//		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
//			Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//			MifareClassic mfc = MifareClassic.get(tagFromIntent);
//			MifareClassCard mifareClassCard = null;
//			String authKey = "a2xQ61";//这是卡片的1扇区的密码（实际根据自己的卡片所设置的扇区和密码）
//			try {
//				mfc.connect();
//				boolean auth = false;
//				int secCount = mfc.getSectorCount();
//				mifareClassCard = new MifareClassCard(secCount);
//				int bCount = 0;
//				int bIndex = 0;
//				//连接NFC卡后到开始循环读取扇区
//				for (int j = 0; j < secCount; j++) {
//					MifareSector mifareSector = new MifareSector();
//					mifareSector.sectorIndex = j;
//					if (j == 1 && !TextUtils.isEmpty(authKey)) {
//						auth = mfc.authenticateSectorWithKeyA(j, hexStringToByte(str2HexStr(authKey)));//需用str2HexStr方法 将服务器传的卡密码string转16进制
//					} else {
//						auth = mfc.authenticateSectorWithKeyA(j, MifareClassic.KEY_DEFAULT);//默认密码
//					}
//					if (j >= 2) { //这里因为我只需要1扇区的，所以后面直接break了的遍历直接break了
//						break;
//					}
//					mifareSector.authorized = auth;
//					if (auth) {
//						bCount = mfc.getBlockCountInSector(j);
//						bCount = Math.min(bCount, MifareSector.BLOCKCOUNT);
//						bIndex = mfc.sectorToBlock(j);
//						for (int i = 0; i < bCount; i++) {
//							byte[] data = mfc.readBlock(bIndex);
//							MifareBlock mifareBlock = new MifareBlock(data);
//							mifareBlock.blockIndex = bIndex;
//							bIndex++;
//							mifareSector.blocks[i] = mifareBlock;
//
//						}
//						mifareClassCard.setSector(mifareSector.sectorIndex, mifareSector);
//					} else {
//
//					}
//				}
//				ArrayList<String> blockData = new ArrayList<String>();
//				int blockIndex = 0;
//				//遍历每个扇区及对应的区块
//				for (int i = 0; i < secCount; i++) {
//
//					MifareSector mifareSector = mifareClassCard.getSector(i);
//					for (int j = 0; j < MifareSector.BLOCKCOUNT; j++) {
//						MifareBlock mifareBlock = mifareSector.blocks[j];
//						byte[] data = mifareBlock.getData();
//						if (i == 1 && j == 0) {//我使用的卡内数据 写在1扇区 0块 ,拿到所需值返回即可
////                            Log.e("扇区：" + blockIndex, "" + new String(data).trim());
//							return new String(data).trim();
//						}
//						blockData.add("Block " + blockIndex++ + " : "
//								+ Converter.getHexString(data, data.length));
//					}
//				}
//
//			} catch (IOException e) {
//				Log.e("IOException", e.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				if (mifareClassCard != null) {
//					mifareClassCard.debugPrint();
//				}
//			}
//		}// End of method
//		return "";
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		//开启前台调度系统
//		if (mAdapter != null) {
//			mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
//		}
//	}
//
//	@Override
//	protected void onPause() {
//		super.onPause();
//		//关闭前台调度系统
//		mAdapter.disableForegroundDispatch(this);
//	}
//
//	public static byte[] hexStringToByte(String hex) {
//		int len = (hex.length() / 2);
//		byte[] result = new byte[len];
//		char[] achar = hex.toCharArray();
//		for (int i = 0; i < len; i++) {
//			int pos = i * 2;
//			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
//		}
//		return result;
//	}
//
//	private static int toByte(char c) {
//		byte b = (byte) "0123456789ABCDEF".indexOf(c);
//		return b;
//	}
//
//
//	public static String str2HexStr(String str) {
//
//		char[] chars = "0123456789ABCDEF".toCharArray();
//		StringBuilder sb = new StringBuilder("");
//		byte[] bs = str.getBytes();
//		int bit;
//
//		for (int i = 0; i < bs.length; i++) {
//			bit = (bs[i] & 0x0f0) >> 4;
//			sb.append(chars[bit]);
//			bit = bs[i] & 0x0f;
//			sb.append(chars[bit]);
//		}
//		return sb.toString().trim();
//	}
//
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//
//		switch (v.getId()) {
//		case R.id.btn_1443A:
//
//			Intent intent1 = new Intent();
//			intent1.setClass(RfidActivity.this, demo1443A.class);
//			startActivity(intent1);
//			break;
//
//		case R.id.btn_15693:
//			Intent intent2 = new Intent();
//			//intent2.setClass(RfidActivity.this, demo15693.class);
//			startActivity(intent2);
//			break;
//			case R.id.baidu_map:
//				Intent intent3 = new Intent(RfidActivity.this, BaiduMapActivity.class);
//				startActivity(intent3);
//		}
//	};
//}
