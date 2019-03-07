package com.changtai.Utils;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

public class VoiceUtils {

    public MediaPlayer mediaPlayer;

    public MediaPlayer failedPlayer;

    public VoiceUtils() {

    }

    public void initSuccessMedia() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //注册事件。当播放完毕一次后，重新指向流文件的开头，以准备下次播放。

        // When the beep has finished playing, rewind to queue up another one.

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.seekTo(0);
            }
        });
    }

    public void playSuccessVoice(Activity context, int raw) {
        AssetFileDescriptor file;
        file = context.getResources().openRawResourceFd(raw);
        if (mediaPlayer == null) {
            initSuccessMedia();
        }

        try {
            mediaPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
            file.close();
            mediaPlayer.setVolume(1.0f, 1.0f);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ioe) {
            //Log.w(TAG, ioe);
            mediaPlayer = null;
        }

    }

}
