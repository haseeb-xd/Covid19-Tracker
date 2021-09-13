package com.haseebahmed.covid19tracker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.haseebahmed.covid19tracker.R;

public class OnBoardingFragment3 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
    {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3,container,false);
        return root;

    }
}
