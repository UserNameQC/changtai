package com.android.barcodescandemo;

import java.io.IOException;
import com.android.barcodescandemo.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainDemo extends Activity {
	public Handler mHandler = new MainHandler();

	public EditText lv;

	public boolean btn_scan = false;
	protected MediaPlayer mediaPlayer = null;
	HadwareControll Controll = new HadwareControll();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Controll.Open();
		Controll.m_handler = mHandler;
		lv = (EditText) findViewById(R.id.lv);
		
		
		
		
		
		Button buttonClear = (Button) findViewById(R.id.btn_disable);
		initBeepSound();

		buttonClear.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				lv.setText("");
				
			}
		});

		Button buttonScan = (Button) findViewById(R.id.btn_scan);
		buttonScan.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				Controll.scan_start();

			}
		});
	}

	private class MainHandler extends Handler {

		public void handleMessage(Message msg) {
			switch (msg.what) {

			case HadwareControll.BARCODE_READ: {
				String result = msg.obj + "\n";
				//byte[]buffer=(byte[])msg.obj;
				
				lv.append(result);
				playBeepSound();
				break;
			}
			default:
				break;
			}
		}
	};

	
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_F9 || keyCode == KeyEvent.KEYCODE_F10
				|| keyCode == KeyEvent.KEYCODE_F11)&&event.getRepeatCount()==0) {
			Controll.scan_start();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_F9 || keyCode == KeyEvent.KEYCODE_F10
				|| keyCode == KeyEvent.KEYCODE_F11) {
			Controll.scan_stop();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private void playBeepSound() {
		if (mediaPlayer != null) {
			mediaPlayer.start();
		}
		
		//Controll.playbeep();
		

	}

	private void initBeepSound() {
		if (mediaPlayer == null) {
			mediaPlayer = new MediaPlayer();
			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				if (mediaPlayer != null) {
					mediaPlayer.setDataSource(file.getFileDescriptor(),
							file.getStartOffset(), file.getLength());
					file.close();

					mediaPlayer.prepare();
				}
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	protected void onDestroy() {
		Controll.Close();
		Controll.m_handler = null;

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_demo, menu);
		return true;
	}

}
