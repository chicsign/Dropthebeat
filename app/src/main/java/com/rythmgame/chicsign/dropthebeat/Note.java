package com.rythmgame.chicsign.dropthebeat;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Chicsign on 2018-03-04.
 */

public class Note extends android.support.v7.widget.AppCompatImageView implements Runnable {

    float position;

    boolean isCancel;
    TranslateAnimation anim;

    public Note(Context context) {
        super(context);
        setLayoutParams(new RelativeLayout.LayoutParams(350, 150));
        setBackgroundResource(R.drawable.note);

        position = this.getY();
        run();
    }


    @Override
    public void run() {
        anim = new TranslateAnimation(100, 100, 0, 2460);

        anim.setDuration(1640);
        anim.setFillAfter(true);
        startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Log.d("BEB","remove v2 ID " + v2.getId());
//                track.removeView(v2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void cancel() {
        Log.d("BEB", "test");
        anim.cancel();
//        Thread.interrupted();
    }
}

