package com.cc.geekband.timertoy;

/**
 * Created by think on 2016-02-08.
 */
public class TimeNumber {

    private static float mMillSecond;

    private static float mSecond;

    private static float mMinute;

    public static float getmSecond() {
        return mSecond;
    }

    public static float getmMillSecond() {
        return mMillSecond;
    }

    public static float getmMinute() {
        return mMinute;
    }

    public TimeNumber(){
    }


    public static void setTimeNumber(float timeNumber){
        mMillSecond = ( timeNumber % 1000.0f) / 10.0f;
        mSecond = (timeNumber/1000.0f) % 60.0f;
        mMinute =  timeNumber/60000;
    }


}
