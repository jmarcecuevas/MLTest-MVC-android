package com.marcecuevas.mltestapp.controller;

import android.content.Context;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.dao.PaymentDAO;
import com.marcecuevas.mltestapp.model.MLError;
import com.marcecuevas.mltestapp.model.MLResultListener;
import com.marcecuevas.mltestapp.model.dto.PaymentMethodDTO;

import java.util.ArrayList;
import java.util.List;

public class PaymentController extends GenericController {

    public PaymentController(Context context) {
        super(context);
    }

    public void paymentMethods(final MLResultListener<List<PaymentMethodDTO>> listener){
        if(!this.isNetworkingOnline(getContext())){
            listener.error(MLError.errorFromMessageAndTitle(getContext().getString(R.string.no_internet),
                    getContext().getString(R.string.error)));
        }

        PaymentDAO paymentDAO = new PaymentDAO(getContext());
        paymentDAO.paymentMethods(new MLResultListener<List<PaymentMethodDTO>>() {

            @Override
            public void success(List<PaymentMethodDTO> result) {
                listener.success(filterCreditCards(result));
            }

            @Override
            public void error(MLError error) {
                listener.error(error);
            }
        });
    }

    private List<PaymentMethodDTO> filterCreditCards(List<PaymentMethodDTO> paymentMethods){
        List<PaymentMethodDTO> creditCards = new ArrayList<>();
        for(PaymentMethodDTO item : paymentMethods){
            if(item.isCreditCard()){
                creditCards.add(item);
            }
        }
        return creditCards;
    }
}
