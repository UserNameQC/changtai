package com.changtai.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.changtai.R;
import com.changtai.SynchronizationWithPC.DownloadFromPcActivity;
import com.changtai.SynchronizationWithWeb.DownloadFromWebActivity;

public class SynchronizationMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronization_main);
    }

    public void onSynchronizationWithPC(View view) {
        startActivity(new Intent(this, DownloadFromPcActivity.class));
    }

    public void onSynchronizationWithWeb(View view) {
        startActivity(new Intent(this, DownloadFromWebActivity.class));
    }

    public void onBackPressed(View view) {
        super.onBackPressed();
    }
}
