package com.marcecuevas.mltestapp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;
import com.marcecuevas.mltestapp.view.activity.PaymentMethodActivity;
import com.marcecuevas.mltestapp.view.custom.AutoResizeEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends GenericFragment {

    @BindView(R.id.amountET)
    protected AutoResizeEditText amountET;

    @BindView(R.id.continueBTN)
    protected Button continueBTN;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        MLFont.applyFont(getContext(),amountET, MLFontVariable.bold);

        continueBTN.setTextColor(Color.WHITE);
        continueBTN.setTextSize(18);
        MLFont.applyFont(getContext(),continueBTN, MLFontVariable.light);
    }

    @OnClick(R.id.continueBTN)
    public void onClick(View view){
        Intent intent = new Intent(getContext(), PaymentMethodActivity.class);
        startActivity(intent);
    }
}
