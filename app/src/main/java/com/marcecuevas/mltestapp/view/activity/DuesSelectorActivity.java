package com.marcecuevas.mltestapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.fragment.BankFragment;
import com.marcecuevas.mltestapp.view.fragment.DueSelectorFragment;

public class DuesSelectorActivity extends ToolbarActivity {

    @Override
    protected void init() {
        super.init();
        addFragmentWithBackStack(DueSelectorFragment.class,false);
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.id.fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dues_selector;
    }


    @Override
    protected String title() {
        return getString(R.string.toolbar_dues_amount);
    }
}
