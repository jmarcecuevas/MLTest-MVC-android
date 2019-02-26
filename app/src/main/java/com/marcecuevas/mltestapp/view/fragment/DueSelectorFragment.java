package com.marcecuevas.mltestapp.view.fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.controller.PaymentController;
import com.marcecuevas.mltestapp.model.MLError;
import com.marcecuevas.mltestapp.model.MLResultListener;
import com.marcecuevas.mltestapp.model.SelectionModel;
import com.marcecuevas.mltestapp.model.dto.BankDTO;
import com.marcecuevas.mltestapp.model.dto.InstallmentOptionDTO;
import com.marcecuevas.mltestapp.model.dto.PaymentMethodDTO;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;
import com.marcecuevas.mltestapp.view.activity.HomeActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class DueSelectorFragment extends GenericFragment implements DetailDialogFragment.Listener{

    @BindView(R.id.numberPicker)
    protected NumberPicker numberPicker;

    @BindView(R.id.finishBTN)
    protected Button finishBTN;

    private String[] installmentNumbers = {"1","3","6","9","12"};
    private SelectionModel selectionModel;

    @Override
    protected int getLayout() {
        return R.layout.fragment_due_selector;
    }

    @Override
    protected void init() {
        loadExtras();

        numberPicker.setDisplayedValues(installmentNumbers);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(installmentNumbers.length - 1);

        finishBTN.setTextColor(Color.WHITE);
        finishBTN.setTextSize(18);
        finishBTN.setText(getString(R.string.finish_label));
        MLFont.applyFont(getContext(),finishBTN, MLFontVariable.light);
    }

    private void loadExtras(){
        if(getActivity() != null && getActivity().getIntent() != null){
            selectionModel = (SelectionModel) getActivity().getIntent().
                    getSerializableExtra("SELECTION_MODEL");
        }
    }

    @OnClick(R.id.finishBTN)
    public void onClick(View view){
        callService();
    }

    private void callService(){
        PaymentController controller = new PaymentController(getContext());
        controller.installmentOptions(new MLResultListener<List<InstallmentOptionDTO>>() {
            @Override
            public void success(List<InstallmentOptionDTO> result) {
                String installments = installmentNumbers[numberPicker.getValue()];
                String message = controller.getRecommendedMessage(result.get(0),
                        installments);
                showDetailPopup(message);
            }

            @Override
            public void error(MLError error) {
                showErrorMessage(error);
            }
        },selectionModel.getTotal(),selectionModel.getPaymentID(),
                selectionModel.getBankID());
    }

    private void showDetailPopup(String recommendedMessage){
        FragmentManager fm = Objects.requireNonNull(getActivity()).getFragmentManager();
        DetailDialogFragment fragment = DetailDialogFragment.newInstance(selectionModel,recommendedMessage);
        fragment.setListener(this);
        fragment.show(fm,"DIALOG");
    }

    @Override
    public void onFinish() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
