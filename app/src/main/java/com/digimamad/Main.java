package com.digimamad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class Main extends AppCompatActivity {
    private Button letsgo;
    Animation fromleft;
    ImageView photo;
    String j;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery);
        photo = (ImageView) findViewById(R.id.photo_delivery);
        fromleft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        photo.setAnimation(fromleft);
        final Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        letsgo = findViewById(R.id.start_btn_delivery);
        Timer timer = new Timer();
        timer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                letsgo.setVisibility(View.VISIBLE);
                                letsgo.setAnimation(slideUp);
                            }
                        });
                    }
                },
                2000
        );
        letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToLogin();
            }
        });
//        jbjlkljkjol
    }


    public void GoToLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
