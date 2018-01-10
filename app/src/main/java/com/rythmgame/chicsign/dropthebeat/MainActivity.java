package com.rythmgame.chicsign.dropthebeat;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start_btn = (Button) findViewById(R.id.StartButton);

        mp = MediaPlayer.create(MainActivity.this,R.raw.bgm);
        mp.setLooping(true);

        mp.start();
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, GameMainActivity.class);
                startActivity(i);
                mp.stop();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }
}
