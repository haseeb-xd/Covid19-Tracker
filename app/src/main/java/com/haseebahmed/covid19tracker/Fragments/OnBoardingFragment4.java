package com.haseebahmed.covid19tracker.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.haseebahmed.covid19tracker.Dashboard;
import com.haseebahmed.covid19tracker.R;

public class OnBoardingFragment4 extends Fragment {


    FloatingActionButton floatingActionButton;
    Animation animation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
    {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding4,container,false);

        floatingActionButton= root.findViewById(R.id.fab);
        animation= AnimationUtils.loadAnimation(getActivity(), R.anim.side_animation);
        floatingActionButton.setAnimation(animation);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), Dashboard.class);
                startActivity(intent);
            }
        });

        return root;

    }

}
