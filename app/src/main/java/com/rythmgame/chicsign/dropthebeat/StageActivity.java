package com.rythmgame.chicsign.dropthebeat;

import android.app.Activity;
import android.app.Notification;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    RelativeLayout track;
    ArrayList<Pair> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage);

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
        final Note note = new Note(getBaseContext());
        list = new ArrayList<>();
        list.add(noteInfo(new Note(getBaseContext()),0));
        list.add(noteInfo(new Note(getBaseContext()),100));
        list.add(noteInfo(new Note(getBaseContext()),0));
        list.add(noteInfo(new Note(getBaseContext()),100));
        list.add(noteInfo(new Note(getBaseContext()),0));
        list.add(noteInfo(new Note(getBaseContext()),100));
        list.add(noteInfo(new Note(getBaseContext()),0));
        list.add(noteInfo(new Note(getBaseContext()),100));
    }

    public static Pair<ImageView, Integer> noteInfo(ImageView src1, int src2) {
        ImageView v1 = src1;
        int posX = src2;

        return Pair.create(v1, posX);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            dropNotes((ImageView) list.get(msg.arg1).first,(Integer) list.get(msg.arg1).second);
//            Log.d("BEB","msg" + msg.arg1);
        }
    };


    void dropNotes(ImageView noteview, int positionX) {
        final ImageView v2 = noteview;
        track = (RelativeLayout) findViewById(R.id.track1);
        track.removeAllViews();
        track.addView(v2);
        final TranslateAnimation anim = new TranslateAnimation(positionX, positionX, 0, 2460);
        anim.setDuration(1640);
        anim.setFillAfter(true);
        v2.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Log.d("BEB","remove v2 ID " + v2.getId());
                track.removeView(v2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                anim.cancel();
                track.removeView(v2);
            }
        });

    }

}
