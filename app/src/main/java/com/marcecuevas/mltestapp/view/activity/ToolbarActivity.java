package com.marcecuevas.mltestapp.view.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class ToolbarActivity extends GenericActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.toolbarBack)
    protected ImageView toolbarBack;

    @BindView(R.id.toolbarTitle)
    protected TextView toolbarTitle;

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbarTitle.setTextSize(16);
        toolbarTitle.setTextColor(Color.WHITE);
        toolbarTitle.setText(title());
        MLFont.applyFont(this,toolbarTitle, MLFontVariable.light);
    }

    @OnClick(R.id.toolbarBack)
    public void onClick(View view){
        onBackPressed();
    }

    protected void setBackButtonVisibility(int visibility){
        this.toolbarBack.setVisibility(visibility);
    }

    @Override
    protected int getFragmentLayoutID() {
        return 0;
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    protected abstract String title();
}
