package com.changtai.RFID;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.changtai.R;

public class NfcDemoActivity extends Activity implements OnClickListener {

	// NFC������
	private NfcAdapter nfcAdapter = null;
	// ������ͼ
	private PendingIntent pi = null;
	// �˵�����޷���Ӧ�ʹ����Intent
	private IntentFilter tagDetected = null;
	// �ı��ؼ�
	private TextView promt = null;
	// �Ƿ�֧��NFC���ܵı�ǩ
	private boolean isNFC_support = false;
	// ����д��ɾ��ť�ؼ�
	private Button readBtn, writeBtn, deleteBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfc_demo);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		setupViews();
		initNFCData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (isNFC_support == false) {
			// ����豸��֧��NFC����NFC����û��������return��
			return;
		}
		// ��ʼ����NFC�豸�Ƿ�����
		startNFC_Listener();

		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(this.getIntent()
				.getAction())) {
			// ע�����if�еĴ��뼸�������������Ϊ�ո�����һ�д��뿪���˼���NFC���ӣ���һ�д������Ͼ��յ���NFC���ӵ�intent�����ּ��ʺ�С
			// �����intent
			processIntent(this.getIntent());
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isNFC_support == true) {
			// ��ǰActivity��������ֻ�����ǰ�ˣ���ֹͣNFC�豸���ӵļ���
			stopNFC_Listener();
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		// ��ǰapp����ǰ�˽������У����ʱ����intent���͹�������ôϵͳ�ͻ����onNewIntent�ص���������intent���͹���
		// ����ֻ��Ҫ������������intent�Ƿ���NFC��ص�intent������ǣ��͵��ô�����
		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
			processIntent(intent);
		}
	}

	@Override
	public void onClick(View v) {

		// �������ť��
		if (v.getId() == R.id.read_btn) {
			try {
				String content = read(tagFromIntent);
				if (content != null && !content.equals("")) {
					promt.setText(promt.getText() + "nfc��ǩ���ݣ�\n" + content
							+ "\n");
				} else {
					promt.setText(promt.getText() + "nfc��ǩ���ݣ�\n" + "����Ϊ��\n");
				}
			} catch (IOException e) {
				promt.setText(promt.getText() + "����:" + e.getMessage() + "\n");
				Log.e("myonclick", "��ȡnfc�쳣", e);
			} catch (FormatException e) {
				promt.setText(promt.getText() + "����:" + e.getMessage() + "\n");
				Log.e("myonclick", "��ȡnfc�쳣", e);
			}
			// ���д��д��
		} else if (v.getId() == R.id.write_btn) {
			try {
				write(tagFromIntent);
			} catch (IOException e) {
				promt.setText(promt.getText() + "����:" + e.getMessage() + "\n");
				Log.e("myonclick", "дnfc�쳣", e);
			} catch (FormatException e) {
				promt.setText(promt.getText() + "����:" + e.getMessage() + "\n");
				Log.e("myonclick", "дnfc�쳣", e);
			}
		} else if (v.getId() == R.id.delete_btn) {
			try {
				delete(tagFromIntent);
			} catch (IOException e) {
				promt.setText(promt.getText() + "����:" + e.getMessage() + "\n");
				Log.e("myonclick", "ɾ��nfc�쳣", e);
			} catch (FormatException e) {
				promt.setText(promt.getText() + "����:" + e.getMessage() + "\n");
				Log.e("myonclick", "ɾ��nfc�쳣", e);
			}
		}
	}

	private void setupViews() {
		// �ؼ��İ�
		promt = (TextView) findViewById(R.id.promt);
		readBtn = (Button) findViewById(R.id.read_btn);
		writeBtn = (Button) findViewById(R.id.write_btn);
		deleteBtn = (Button) findViewById(R.id.delete_btn);
		// ���ı��ؼ���ֵ��ʼ�ı�
		promt.setText("�ȴ�RFID��ǩ");
		// ��������д��ɾ��ť�ؼ�
		readBtn.setOnClickListener(this);
		writeBtn.setOnClickListener(this);
		deleteBtn.setOnClickListener(this);
	}

	private void initNFCData() {
		// ��ʼ���豸֧��NFC����
		isNFC_support = true;
		// �õ�Ĭ��nfc������
		//nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		// ��ʾ��Ϣ����
		String metaInfo = "";
		// �ж��豸�Ƿ�֧��NFC������NFC
		if (nfcAdapter == null) {
			metaInfo = "设备不支持NFC";
			Toast.makeText(this, metaInfo, Toast.LENGTH_SHORT).show();
			isNFC_support = false;
			return;
		}
		if (!nfcAdapter.isEnabled()) {
			metaInfo = "请在系统设置中先启用NFC功能！";
			Toast.makeText(this, metaInfo, Toast.LENGTH_SHORT).show();
			isNFC_support = false;
		}

		if (isNFC_support == true) {
			init_NFC();
		} else {
			promt.setTextColor(Color.RED);
			promt.setText(metaInfo);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nfc_demo, menu);
		return true;
	}

	// �ַ�����ת��Ϊ16�����ַ���
	private String bytesToHexString(byte[] src) {
		return bytesToHexString(src, true);
	}

	private String bytesToHexString(byte[] src, boolean isPrefix) {
		StringBuilder stringBuilder = new StringBuilder();
		if (isPrefix == true) {
			stringBuilder.append("0x");
		}
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] buffer = new char[2];
		for (int i = 0; i < src.length; i++) {
			buffer[0] = Character.toUpperCase(Character.forDigit(
					(src[i] >>> 4) & 0x0F, 16));
			buffer[1] = Character.toUpperCase(Character.forDigit(src[i] & 0x0F,
					16));
			System.out.println(buffer);
			stringBuilder.append(buffer);
		}
		return stringBuilder.toString();
	}

	private Tag tagFromIntent;

	/**
	 * Parses the NDEF Message from the intent and prints to the TextView
	 */
	public void processIntent(Intent intent) {
		if (isNFC_support == false)
			return;

		// ȡ����װ��intent�е�TAG
		tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

		promt.setTextColor(Color.BLUE);
		String metaInfo = "";
		metaInfo += "��ƬID��" + bytesToHexString(tagFromIntent.getId()) + "\n";
		Toast.makeText(this, "�ҵ���Ƭ", Toast.LENGTH_SHORT).show();

		// Tech List
		String prefix = "android.nfc.tech.";
		String[] techList = tagFromIntent.getTechList();

		//����NFC�������ͣ� Mifare Classic/UltraLight Info
		String CardType = "";
		for (int i = 0; i < techList.length; i++) {
			if (techList[i].equals(NfcA.class.getName())) {
				// ��ȡTAG
				NfcA mfc = NfcA.get(tagFromIntent);
				try {
					if ("".equals(CardType))
						CardType = "MifareClassic��Ƭ���� \n ��֧��NDEF��Ϣ \n";
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (techList[i].equals(MifareUltralight.class.getName())) {
				MifareUltralight mifareUlTag = MifareUltralight
						.get(tagFromIntent);
				String lightType = "";
				// Type Info
				switch (mifareUlTag.getType()) {
				case MifareUltralight.TYPE_ULTRALIGHT:
					lightType = "Ultralight";
					break;
				case MifareUltralight.TYPE_ULTRALIGHT_C:
					lightType = "Ultralight C";
					break;
				}
				CardType = lightType + "��Ƭ����\n";

				Ndef ndef = Ndef.get(tagFromIntent);
				CardType += "������ݳߴ�:" + ndef.getMaxSize() + "\n";

			}
		}
		metaInfo += CardType;
		promt.setText(metaInfo);
	}

	// ��ȡ����
	private String read(Tag tag) throws IOException, FormatException {
		if (tag != null) {
			//����Tag��ȡ��NDEFʵ��
			Ndef ndef = Ndef.get(tag);
			//������
			ndef.connect();
			//��ȡNDEF��Ϣ
			NdefMessage message = ndef.getNdefMessage();
			//����Ϣת�����ֽ�����
			byte[] data = message.toByteArray();
			//���ֽ�����ת�����ַ���
			String str = new String(data, Charset.forName("UTF-8"));
			//�ر�����
			ndef.close();
			return str;
		} else {
			Toast.makeText(NfcDemoActivity.this, "设备与nfc卡连接断开，请重新连接...",
					Toast.LENGTH_SHORT).show();
		}
		return null;
	}

	// д�뷽��
	private void write(Tag tag) throws IOException, FormatException {
		if (tag != null) {
			//�½�NdefRecord���飬����������ֻ��һ��Ԫ��
			NdefRecord[] records = { createRecord() };
			//�½�һ��NdefMessageʵ��
			NdefMessage message = new NdefMessage(records);
			// ����TAG��ȡ��NDEFʵ��
			Ndef ndef = Ndef.get(tag);
			// ������
			ndef.connect();
			// д��NDEF��Ϣ
			ndef.writeNdefMessage(message);
			// �ر�����
			ndef.close();
			promt.setText(promt.getText() + "д�����ݳɹ���" + "\n");
		} else {
			Toast.makeText(NfcDemoActivity.this, "�豸��nfc�����ӶϿ�������������...",
					Toast.LENGTH_SHORT).show();
		}
	}

	// ɾ������
	private void delete(Tag tag) throws IOException, FormatException {
		if (tag != null) {
			//�½�һ���������κ���Ϣ��NdefRecordʵ��
			NdefRecord nullNdefRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
					new byte[] {}, new byte[] {}, new byte[] {});
			NdefRecord[] records = { nullNdefRecord };
			NdefMessage message = new NdefMessage(records);
			// ����TAG��ȡ��NDEFʵ��
			Ndef ndef = Ndef.get(tag);
			// ������
			ndef.connect();
			// д����Ϣ
			ndef.writeNdefMessage(message);
			// �ر�����
			ndef.close();
			promt.setText(promt.getText() + "ɾ�����ݳɹ���" + "\n");
		} else {
			Toast.makeText(NfcDemoActivity.this, "�豸��nfc�����ӶϿ�������������...",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	//����һ��NdefRecordʵ��
	private NdefRecord createRecord() throws UnsupportedEncodingException {
		//��װ�ַ�����׼������Ҫд�����Ϣ
		String msg = "BEGIN:VCARD\n" + "VERSION:2.1\n" + "�й�����ʡ�人��\n"
				+ "�人��ѧ�����ѧԺ\n" + "END:VCARD";
		//���ַ���ת�����ֽ�����
		byte[] textBytes = msg.getBytes();
		//���ֽ������װ��һ��NdefRecordʵ����ȥ
		NdefRecord textRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
				"text/x-vCard".getBytes(), new byte[] {}, textBytes);
		return textRecord;
	}

	private MediaPlayer ring() throws Exception, IOException {
		// TODO Auto-generated method stub
		Uri alert = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		MediaPlayer player = new MediaPlayer();
		player.setDataSource(this, alert);
		final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		if (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) != 0) {
			player.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
			player.setLooping(false);
			player.prepare();
			player.start();
		}
		return player;
	}

	private void startNFC_Listener() {
		// ��ʼ����NFC�豸�Ƿ����ӣ�������Ӿͷ�pi��ͼ
		nfcAdapter.enableForegroundDispatch(this, pi,
				new IntentFilter[] { tagDetected }, null);
	}

	private void stopNFC_Listener() {
		// ֹͣ����NFC�豸�Ƿ�����
		nfcAdapter.disableForegroundDispatch(this);
	}

	private void init_NFC() {
		// ��ʼ��PendingIntent������NFC�豸�����ϵ�ʱ�򣬾ͽ�����ǰActivity����
		pi = PendingIntent.getActivity(this, 0, new Intent(this, getClass())
				.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		// �½�IntentFilter��ʹ�õ��ǵڶ��ֵĹ��˻���
		tagDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
		tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
	}

}
