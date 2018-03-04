package com.rythmgame.chicsign.dropthebeat;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Chicsign on 2018-03-04.
 */

public class Note extends ImageView {

    float position;

    public Note(Context context) {
        super(context);
        setLayoutParams(new RelativeLayout.LayoutParams(350, 150));
        setBackgroundResource(R.drawable.note);
    }



}
