package com.haseebahmed.covid19tracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cuberto.liquid_swipe.LiquidPager;

public class onBoarding extends AppCompatActivity {




    LiquidPager pager;
    viewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pager = findViewById(R.id.pager);
        viewPager = new viewPager(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);



    }

    public  void skip(View view)
    {
        startActivity(new Intent(this,Dashboard.class));
    }


}