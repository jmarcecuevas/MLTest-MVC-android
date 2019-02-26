package com.marcecuevas.mltestapp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.controller.PaymentController;
import com.marcecuevas.mltestapp.model.MLError;
import com.marcecuevas.mltestapp.model.MLResultListener;
import com.marcecuevas.mltestapp.model.SelectionModel;
import com.marcecuevas.mltestapp.model.dto.BankDTO;
import com.marcecuevas.mltestapp.view.activity.DuesSelectorActivity;
import com.marcecuevas.mltestapp.view.adapter.BankAdapter;

import java.util.List;

import butterknife.BindView;

public class BankFragment extends GenericFragment implements BankAdapter.Listener {

    @BindView(R.id.banksRV)
    protected RecyclerView methodsRV;

    private BankAdapter adapter;
    private SelectionModel selectionModel;

    @Override
    protected int getLayout() {
        return R.layout.fragment_bank;
    }

    @Override
    protected void init() {
        loadExtras();

        adapter = new BankAdapter(getContext(), this);
        methodsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        methodsRV.setAdapter(adapter);

        callService();
    }

    private void callService() {
        PaymentController controller = new PaymentController(getContext());
        controller.banks(new MLResultListener<List<BankDTO>>() {
            @Override
            public void success(List<BankDTO> result) {
                adapter.update(result);
            }

            @Override
            public void error(MLError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        },selectionModel.getPaymentID());
    }

    private void loadExtras(){
        if(getActivity() != null && getActivity().getIntent() != null){
            selectionModel = (SelectionModel) getActivity().getIntent().
                    getSerializableExtra("SELECTION_MODEL");
        }
    }

    @Override
    public void onBankSelected(BankDTO bank) {
        selectionModel.setBankName(bank.getName());
        selectionModel.setBankID(bank.getId());
        Intent intent = new Intent(getContext(), DuesSelectorActivity.class);
        intent.putExtra("SELECTION_MODEL",selectionModel);
        startActivity(intent);
    }
}
