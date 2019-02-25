package com.marcecuevas.mltestapp.view.activity;

import android.os.Bundle;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.fragment.HomeFragment;
import com.marcecuevas.mltestapp.view.fragment.PaymentMethodFragment;

public class PaymentMethodActivity extends ToolbarActivity {

    @Override
    protected void init() {
        super.init();
        addFragmentWithBackStack(PaymentMethodFragment.class,false);
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.id.fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_payment_method;
    }

    @Override
    protected String title() {
        return getString(R.string.toolbar_payment_method);
    }
}
