package com.haseebahmed.covid19tracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.haseebahmed.covid19tracker.Fragments.OnBoardingFragment1;
import com.haseebahmed.covid19tracker.Fragments.OnBoardingFragment2;
import com.haseebahmed.covid19tracker.Fragments.OnBoardingFragment3;
import com.haseebahmed.covid19tracker.Fragments.OnBoardingFragment4;

public class viewPager extends FragmentPagerAdapter {


    public viewPager(@NonNull FragmentManager fm, int behaviour) {
        super(fm, behaviour);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OnBoardingFragment1();

            case 1:
                return new OnBoardingFragment2();

            case 2:
                return new OnBoardingFragment3();

            case 3:
                return new OnBoardingFragment4();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }







}
