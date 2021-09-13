package com.haseebahmed.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {


    private int SPLASH_SECONDS=3500;
    LottieAnimationView lottieAnimationView;
    ImageView logo;
    Animation topAnim,bottomAnim;
    SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo= (ImageView)  findViewById(R.id.logo);
        lottieAnimationView= (LottieAnimationView) findViewById(R.id.animationView);


        lottieAnimationView.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {

//            onBoardingScreen= getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
//            boolean isFirstTime= onBoardingScreen.getBoolean("firstTime",true);

//            if(isFirstTime) {
//                SharedPreferences.Editor editor=onBoardingScreen.edit();
//                editor.putBoolean("firstTime",false);
//                editor.commit();

                Intent intent = new Intent(MainActivity.this, onBoarding.class);
                startActivity(intent);
                finish();
//            }

//            else {
//                Intent intent= new Intent(getApplicationContext(),Dashboard.class);
//                startActivity(intent);
//                finish();
//            }



        }, SPLASH_SECONDS);


    }
}