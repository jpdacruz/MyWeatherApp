package com.example.openweatherapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.openweatherapp.R;

public class SplashActivity extends AppCompatActivity {

    //vars
    Animation firstAnim;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //animations

        firstAnim = AnimationUtils.loadAnimation(this, R.anim.fistanimation);
        imageView1 = findViewById(R.id.imageViewAnim1);
        imageView1.setAnimation(firstAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}