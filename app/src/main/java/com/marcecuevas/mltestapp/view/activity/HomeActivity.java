package com.marcecuevas.mltestapp.view.activity;

import android.view.View;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.fragment.HomeFragment;

public class HomeActivity extends ToolbarActivity {

    @Override
    protected void init() {
        super.init();
        setBackButtonVisibility(View.GONE);
        addFragmentWithBackStack(HomeFragment.class,false);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected String title() {
        return getString(R.string.toolbar_how_much);
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.id.fragment;
    }
}
