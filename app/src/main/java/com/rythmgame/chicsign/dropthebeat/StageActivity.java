package com.rythmgame.chicsign.dropthebeat;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Chicsign on 2018-01-08.
 */

public class StageActivity extends Activity {

    private int y = 0;
    private int speed = 30;
    private int delay = 1000;
    private boolean note_continuous = true;


    private int height;
    private int width;

    ImageView note;
    RelativeLayout track1;
    RelativeLayout track2;
    RelativeLayout track3;
    RelativeLayout track4;
    ArrayList<RelativeLayout> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage);
        track1 = (RelativeLayout) findViewById(R.id.track1);
        track2 = (RelativeLayout) findViewById(R.id.track2);
        track3 = (RelativeLayout) findViewById(R.id.track3);
        track4 = (RelativeLayout) findViewById(R.id.track4);
        setMap();
        Thread myThread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        Message msg = handler.obtainMessage();
                        msg.arg1 = i;
                        handler.sendMessage(msg);
                        Thread.sleep(delay);

                    } catch (Throwable t) {
                    }
                }
            }
        });
        myThread.start();

    }

    private void setMap() {

        list = new ArrayList<>();
        list.add(track1);
        list.add(track2);
        list.add(track4);
        list.add(track2);
        list.add(track1);
        list.add(track3);
        list.add(track4);
        list.add(track1);
        list.add(track2);
        list.add(track3);
        list.add(track4);
        list.add(track3);
        list.add(track3);

    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            dropNotes(list.get(msg.arg1));
//            Log.d("BEB","msg" + msg.arg1);
        }
    };


    void dropNotes(final RelativeLayout l) {
        final ImageView v2 = new ImageView(getBaseContext());
        v2.setLayoutParams(new RelativeLayout.LayoutParams(350, 150));
        v2.setBackgroundResource(R.drawable.note);
        l.addView(v2);
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, 2460);
        anim.setDuration(2000);
        anim.setFillAfter(true);
        v2.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Log.d("BEB","remove note");
                l.removeView(v2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}
