package com.rythmgame.chicsign.dropthebeat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.View;
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
        Note note = new Note(getBaseContext());
        list = new ArrayList<>();
        list.add(noteInfo(note,0));
        note = new Note(getBaseContext());
        list.add(noteInfo(note,100));
        note = new Note(getBaseContext());
        list.add(noteInfo(note,300));
        note = new Note(getBaseContext());
        list.add(noteInfo(note,400));
        note = new Note(getBaseContext());
        list.add(noteInfo(note,500));
    }

    public static Pair<ImageView, Integer> noteInfo(ImageView src1, int src2) {
        ImageView v1 = src1;
        int posX = src2;

        return Pair.create(v1, posX);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            dropNotes((ImageView) list.get(msg.arg1).first,(Integer) list.get(msg.arg1).second , msg.arg1);
//            Log.d("BEB","msg" + msg.arg1);
        }
    };


    void dropNotes(ImageView noteview, int positionX , int index) {
        final Note v2 = (Note) noteview;
        track = (RelativeLayout) findViewById(R.id.track1);
        track.addView(v2);
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v2.cancel();
                track.removeView(v2);
            }
        });

    }

}
