package com.android.barcodescandemo;

import java.io.UnsupportedEncodingException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.BarcodeJNI;


public class HadwareControll implements BarcodeJNI.ScanCallBack {

	public Handler m_handler = null;
	static public final int BARCODE_READ = 10;
	private static Handler mHandler = null;
	private BarcodeJNI barcodeControll = new BarcodeJNI(this);

	

	public void Open() {
		barcodeControll.Barcode_open();

	}

	public void Close() {
		barcodeControll.Barcode_Close();
	}

	public void scan_start() {
		barcodeControll.Barcode_StartScan();

	}

	

	public void scan_stop() {
		barcodeControll.Barcode_StopScan();

	}


	public void onScanResults(byte[] data) {
		// TODO Auto-generated method stub
		if (m_handler != null && data != null) {
			System.out.println("code :" + data);
			Message msg = new Message();
			try {
				String info = null;
				info = new String(data, "GBK");
				msg.what = BARCODE_READ;
				msg.obj = info;
				System.out.println("msg.obj=" + msg.obj);
				
				Log.d("012","msg.obj=" + msg.obj);
				m_handler.sendMessage(msg);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
