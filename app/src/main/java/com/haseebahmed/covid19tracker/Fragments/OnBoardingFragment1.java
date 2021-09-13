package com.haseebahmed.covid19tracker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.haseebahmed.covid19tracker.R;


public class OnBoardingFragment1 extends Fragment {

    Animation fadeIn;
    LottieAnimationView lottieAnimationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
    {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding1,container,false);
        return root;

    }

}
