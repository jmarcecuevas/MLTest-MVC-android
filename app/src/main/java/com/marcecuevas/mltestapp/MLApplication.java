package com.marcecuevas.mltestapp;

import android.app.Application;
import com.marcecuevas.mltestapp.utils.MLFont;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MLApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MLFont.getInstance().setFamilyName("Montserrat",this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(MLFont.getInstance().defaultPath())
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
