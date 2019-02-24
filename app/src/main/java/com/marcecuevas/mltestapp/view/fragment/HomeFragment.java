package com.marcecuevas.mltestapp.view.fragment;

import android.widget.TextView;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;

import butterknife.BindView;

public class HomeFragment extends GenericFragment {

    @BindView(R.id.titleTV)
    protected TextView titleTV;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        titleTV.setText("ML Test");
        MLFont.applyFont(getContext(),titleTV, MLFontVariable.bold);
    }
}
