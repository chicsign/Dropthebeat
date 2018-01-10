package com.rythmgame.chicsign.dropthebeat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Chicsign on 2018-01-08.
 */

public class StageActivity extends Activity {

    private ImageView note1;
    private ImageView note2;
    private ImageView note3;
    private ImageView note4;
    private int i =0;
    private int speed = 30;
    private int delay = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage);

        note1 = (ImageView) findViewById(R.id.note1);
        note2 = (ImageView) findViewById(R.id.note2);
        note3 = (ImageView) findViewById(R.id.note3);
        note4 = (ImageView) findViewById(R.id.note4);


    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        Thread myThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(delay);
                    } catch (Throwable t) {
                    }
                }
            }
        });

        myThread.start();
    }


    private void updateThread() {
        i += speed;
        note1.setY(i);
    }


}
