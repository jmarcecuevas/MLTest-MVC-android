package com.marcecuevas.mltestapp.view.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.model.SelectionModel;
import com.marcecuevas.mltestapp.utils.DimensionUtil;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;

import java.util.Objects;


public class DetailDialogFragment extends DialogFragment {

    private SelectionModel selectionModel;
    private String message;
    private Listener listener;

    private TextView titleTV, totalTV, recommendMessageTV,
            paymentMethodTV,bankTV;
    private Button continueBTN;

    public static DetailDialogFragment newInstance(SelectionModel selectionModel,String message){

        DetailDialogFragment dialogFragment = new DetailDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("SELECTION_MODEL",selectionModel);
        args.putString("MESSAGE",message);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_detail,container);
        initViews(rootView);
        setupViews();
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(root);

        int width = DimensionUtil.getWidth(1032 / 1080, getActivity());
        Objects.requireNonNull(dialog.getWindow()).
                setLayout(width, width);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadArguments();
    }

    private void loadArguments() {
        if(getArguments() != null){
            selectionModel = (SelectionModel) getArguments().
                    getSerializable("SELECTION_MODEL");
            message = getArguments().getString("MESSAGE");
        }
    }

    private void initViews(View rootView) {
        titleTV = rootView.findViewById(R.id.titleTV);
        totalTV = rootView.findViewById(R.id.totalTV);
        recommendMessageTV = rootView.findViewById(R.id.recommendedMessageTV);
        paymentMethodTV = rootView.findViewById(R.id.paymentMethodTV);
        bankTV = rootView.findViewById(R.id.bankTV);
        continueBTN = rootView.findViewById(R.id.continueBTN);

        continueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onFinish();
                }
            }
        });

        setupFonts();
    }

    private void setupFonts(){
        MLFont.applyFont(getActivity(),titleTV, MLFontVariable.bold);
        MLFont.applyFont(getActivity(),totalTV, MLFontVariable.bold);
        MLFont.applyFont(getActivity(),recommendMessageTV, MLFontVariable.bold);
        MLFont.applyFont(getActivity(),paymentMethodTV, MLFontVariable.light);
        MLFont.applyFont(getActivity(),bankTV, MLFontVariable.light);
        MLFont.applyFont(getActivity(),continueBTN, MLFontVariable.bold);
    }

    @SuppressLint("SetTextI18n")
    private void setupViews() {
        if(selectionModel != null){
            totalTV.setText("$" + selectionModel.getTotal());
            recommendMessageTV.setText(message);
            paymentMethodTV.setText(selectionModel.getPaymentName());
            bankTV.setText(selectionModel.getBankName());
        }
    }

    public interface Listener {
        void onFinish();
    }
}
