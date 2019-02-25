package com.marcecuevas.mltestapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcecuevas.mltestapp.R;

public class DuesSelectorActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dues_selector);
    }

    @Override
    protected String title() {
        return getString(R.string.toolbar_dues_amount);
    }
}
