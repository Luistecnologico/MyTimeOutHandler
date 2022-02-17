package com.example.mycountdowntimer;

import android.os.CountDownTimer;

import java.util.concurrent.atomic.AtomicBoolean;

public class TimeOutHandler extends CountDownTimer {

    private final AtomicBoolean atomicFlag;
    private static TimeOutHandler mySelf;

    public static TimeOutHandler getInstance(long millisUntilFinished) {
        if (mySelf == null) {
            mySelf = new TimeOutHandler(millisUntilFinished);
        }
        return mySelf;
    }

    private TimeOutHandler(Long millisUntilFinished) {
        super(millisUntilFinished, millisUntilFinished);
        atomicFlag = new AtomicBoolean(false);
    }

    public void run() {
        atomicFlag.set(true);
        start();
    }

    public void stop() {
        cancel();
        atomicFlag.set(false);
        mySelf = null;
    }

    public boolean isOnTime() {
        return atomicFlag.get();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // Do nothing
    }

    @Override
    public void onFinish() {
        atomicFlag.set(false);
    }
}
