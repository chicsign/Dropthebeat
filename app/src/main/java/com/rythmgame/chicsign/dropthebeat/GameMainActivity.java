package com.rythmgame.chicsign.dropthebeat;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chicsign on 2018-01-08.
 */

public class GameMainActivity extends Activity {

    private static MediaPlayer mp;
    private boolean onoff = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        ImageView img = (ImageView) findViewById(R.id.sangrila);

        TextView tx = (TextView) findViewById(R.id.easy_btn);

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameMainActivity.this, StageActivity.class);
                startActivity(i);
                if(onoff){
                    onoff = false;
                    mp.stop();
                }
            }
        });




        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!onoff){
                    mp = MediaPlayer.create(GameMainActivity.this,R.raw.vixx);
                    mp.setLooping(true);
                    onoff = true;
                    mp.start();
                }else {
                    onoff = false;
                    mp.stop();
                }


            }
        });

    }
}
