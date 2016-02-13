package com.cc.geekband.timertoy;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mPlay;

    private Button mReset;

    private int PLAY = 0;

    private int PAUSE = 1;

    private int STOP = 2;

    public final static int MSG_CODE = 123;

    private int mState;

    private MyHandler mMyHandler = new MyHandler(this);

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlay = (Button) findViewById(R.id.paly);
        mPlay.setOnClickListener(this);
        mReset = (Button) findViewById(R.id.reset);
        mReset.setOnClickListener(this);
        mReset.setVisibility(View.INVISIBLE);

        mState = STOP;

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paly:
                if (mState == STOP) {
                    mReset.setVisibility(View.VISIBLE);
                    mPlay.setBackground(getDrawable(R.drawable.pause));


                    mTimer = new Timer();
                    mTimer.setStartTime();
                    Message message = mMyHandler.obtainMessage();
                    message.what = MSG_CODE;
                    mMyHandler.sendMessageDelayed(message, 10);

                    mState = PLAY;

                } else if (mState == PLAY) {
                    mReset.setVisibility(View.VISIBLE);
                    mPlay.setBackground(getDrawable(R.drawable.play));
                    mState = PAUSE;
                    mTimer.setPauseTime();

                } else if (mState == PAUSE) {
                    mReset.setVisibility(View.VISIBLE);
                    mPlay.setBackground(getDrawable(R.drawable.pause));

                    mTimer.startAgain();
                    Message message = mMyHandler.obtainMessage();
                    message.what = MSG_CODE;
                    mMyHandler.sendMessageDelayed(message, 10);
                    mState = PLAY;

                }
                break;

            case R.id.reset:
                if(mState == PAUSE) {
                    TimeNumber.setTimeNumber(0);
                    mState = STOP;
                    mReset.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    private static class MyHandler extends Handler{
        private WeakReference<MainActivity> mMainActivityWeakReference;

        private float sumTime = 0;

        public MyHandler(MainActivity mainActivity) {
            mMainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            MainActivity mainActivity = mMainActivityWeakReference.get();

            switch (msg.what) {
                case MSG_CODE:

                    sumTime = mainActivity.mTimer.getUpdateTime() ;
                    Log.d("sunTime", "" + sumTime);
                    TimeNumber.setTimeNumber(sumTime);

                    Message message2 = Message.obtain();
                    message2.what = MSG_CODE;
                    message2.obj = 10;
                    if (mainActivity.mState == mainActivity.PLAY) {
                        sendMessageDelayed(message2, 10);
                        Log.d("message2", "456");
                        break;
                    }
            }

        }
    }
}
