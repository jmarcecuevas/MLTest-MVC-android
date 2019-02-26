package com.marcecuevas.mltestapp.view.activity;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.fragment.SplashFragment;

public class SplashActivity extends GenericActivity {


    @Override
    protected void init() {
        addFragmentWithBackStack(SplashFragment.class,false);
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.id.fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }
}
