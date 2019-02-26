package com.marcecuevas.mltestapp.view.fragment;

import android.content.Intent;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.view.activity.HomeActivity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends GenericFragment {

    private static final long SPLASH_SCREEN_DELAY = 3500;

    @Override
    protected int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void init() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                next();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    private void next() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }
}

