package com.cc.geekband.timertoy;

/**
 * Created by think on 2016-02-11.
 */
public class Timer {

    private long startTime;

    private long pauseTime;

    public Timer() {

    }

    public void setStartTime(){
        startTime = System.currentTimeMillis();
    }

    public long getNowTime() {

        return System.currentTimeMillis();
    }

    public long getUpdateTime() {

        long nowTime = getNowTime();

        return (nowTime - startTime);
    }

    public long setPauseTime() {
        pauseTime = System.currentTimeMillis();

        return pauseTime;
    }

    public void startAgain(){
        startTime = startTime + System.currentTimeMillis() - pauseTime;
    }
}
