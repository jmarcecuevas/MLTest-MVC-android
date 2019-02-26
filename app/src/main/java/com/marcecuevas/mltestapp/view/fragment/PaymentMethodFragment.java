package com.marcecuevas.mltestapp.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.controller.PaymentController;
import com.marcecuevas.mltestapp.model.MLError;
import com.marcecuevas.mltestapp.model.MLResultListener;
import com.marcecuevas.mltestapp.model.SelectionModel;
import com.marcecuevas.mltestapp.model.dto.PaymentMethodDTO;
import com.marcecuevas.mltestapp.utils.MLFont;
import com.marcecuevas.mltestapp.utils.MLFontVariable;
import com.marcecuevas.mltestapp.view.activity.BankActivity;
import com.marcecuevas.mltestapp.view.adapter.PaymentMethodAdapter;

import java.util.List;

import butterknife.BindView;

public class PaymentMethodFragment extends GenericFragment implements PaymentMethodAdapter.Listener {

    @BindView(R.id.methodsRV)
    protected RecyclerView methodsRV;

    @BindView(R.id.totalTV)
    protected TextView totalTV;

    @BindView(R.id.totalAmountTV)
    protected TextView totalAmountTV;

    private SelectionModel selectionModel;
    private PaymentMethodAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_payment_method;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void init() {
        loadExtra();

        totalTV.setText(getString(R.string.total_label));
        totalTV.setTextSize(14);
        totalTV.setTextColor(getResources().getColor(R.color.colorDark));
        MLFont.applyFont(getContext(),totalTV, MLFontVariable.light);

        totalAmountTV.setText("$" + selectionModel.getTotal());
        totalAmountTV.setTextSize(16);
        totalAmountTV.setTextColor(getResources().getColor(R.color.colorDark));
        MLFont.applyFont(getContext(),totalAmountTV,MLFontVariable.bold);

        adapter = new PaymentMethodAdapter(getContext(),this);
        methodsRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        methodsRV.setAdapter(adapter);

        callService();
    }

    private void callService() {
        PaymentController controller = new PaymentController(getContext());
        controller.paymentMethods(new MLResultListener<List<PaymentMethodDTO>>() {
            @Override
            public void success(List<PaymentMethodDTO> result) {
                adapter.update(result);
            }

            @Override
            public void error(MLError error) {
                showErrorMessage(error);
            }
        });
    }

    @Override
    public void onPaymentMethodSelected(PaymentMethodDTO paymentMethod) {
        selectionModel.setPaymentName(paymentMethod.getName());
        selectionModel.setPaymentID(paymentMethod.getId());
        Intent intent = new Intent(getContext(), BankActivity.class);
        intent.putExtra("SELECTION_MODEL",selectionModel);
        startActivity(intent);
    }

    private void loadExtra(){
        if(getActivity() != null && getActivity().getIntent() != null){
            selectionModel = (SelectionModel) getActivity().getIntent().
                    getSerializableExtra("SELECTION_MODEL");
        }
    }
}
