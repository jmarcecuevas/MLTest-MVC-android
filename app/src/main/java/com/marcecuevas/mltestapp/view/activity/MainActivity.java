package com.marcecuevas.mltestapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.fragment.HomeFragment;

public class MainActivity extends GenericActivity {

    @Override
    protected void init() {
        addFragmentWithBackStack(HomeFragment.class,false);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.id.fragment;
    }
}
