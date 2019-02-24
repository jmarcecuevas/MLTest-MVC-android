package com.marcecuevas.mltestapp.utils;

import android.content.Context;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

public class MLFont {

    private static final MLFont ourInstance = new MLFont();

    public static MLFont getInstance() {
        return ourInstance;
    }

    private String familyName;

    private Map<String, String> configurations;

    private MLFont() {
        this.familyName = "Montserrat";
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName, Context context) {
        this.configurations = new HashMap<>();
        this.familyName = familyName;
        int resId = context.getResources().getIdentifier(this.familyName, "array", context.getPackageName());
        if (resId == 0){
            this.familyName = null;
            return;
        }
        String[] fonts = context.getResources().getStringArray(resId);
        for (String font : fonts) {
            String[] parts = font.split("\\|");
            if (parts.length > 0){
                this.configurations.put(parts[0],parts[1]);
            }
        }
    }

    public String defaultPath(){
        return this.pathForVariable(MLFontVariable.regular);
    }

    public String pathForVariable(MLFontVariable variable){
        return configurations.get(variable.name());
    }

    public static void applyFont(Context context, TextView textView, MLFontVariable variable){
        CalligraphyUtils.applyFontToTextView(context, textView, MLFont.getInstance().pathForVariable(variable));
    }

    public static void applyNumberFont(Context context, TextView textView){
        CalligraphyUtils.applyFontToTextView(context, textView, "fonts/numbers.otf");
    }
}