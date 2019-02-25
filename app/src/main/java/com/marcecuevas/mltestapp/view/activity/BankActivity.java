package com.marcecuevas.mltestapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.fragment.BankFragment;

public class BankActivity extends ToolbarActivity {

    @Override
    protected void init() {
        super.init();
        addFragmentWithBackStack(BankFragment.class,false);
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.id.fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_bank;
    }

    @Override
    protected String title() {
        return getString(R.string.toolbar_bank);
    }
}
