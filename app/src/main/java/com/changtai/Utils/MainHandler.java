package com.changtai.Utils;

import android.os.Looper;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by SAMSUNG on 2018/5/23.
 */

public class MainHandler extends android.os.Handler {

    private static volatile MainHandler instance;

    public static MainHandler getInstance() {
        if (null == instance) {
            synchronized (MainHandler.class) {
                if (null == instance) {
                    instance = new MainHandler();
                }
            }
        }
        return instance;
    }
    private MainHandler() {
        super(Looper.getMainLooper());
    }
}
