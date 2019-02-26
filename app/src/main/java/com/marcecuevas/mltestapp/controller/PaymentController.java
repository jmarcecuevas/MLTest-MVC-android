package com.marcecuevas.mltestapp.controller;

import android.content.Context;
import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.dao.PaymentDAOImpl;
import com.marcecuevas.mltestapp.model.MLError;
import com.marcecuevas.mltestapp.model.MLResultListener;
import com.marcecuevas.mltestapp.model.dto.BankDTO;
import com.marcecuevas.mltestapp.model.dto.InstallmentOptionDTO;
import com.marcecuevas.mltestapp.model.dto.PaymentMethodDTO;
import com.marcecuevas.mltestapp.model.dto.InstallmentOptionDTO.PayerCostDTO;
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

        PaymentDAOImpl paymentDAO = new PaymentDAOImpl(getContext());
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

    public void banks(final MLResultListener<List<BankDTO>> listener,String paymentMethodID){
        if(!this.isNetworkingOnline(getContext())){
            listener.error(MLError.errorFromMessageAndTitle(getContext().getString(R.string.no_internet),
                    getContext().getString(R.string.error)));
        }

        PaymentDAOImpl paymentDAO = new PaymentDAOImpl(getContext());
        paymentDAO.banks(new MLResultListener<List<BankDTO>>() {

            @Override
            public void success(List<BankDTO> result) {
                listener.success(result);
            }

            @Override
            public void error(MLError error) {
                listener.error(error);
            }
        },paymentMethodID);
    }

    public void installmentOptions(final MLResultListener<List<InstallmentOptionDTO>> listener, String amount,
                                    String paymentMethodID,String issuerID){
        if(!this.isNetworkingOnline(getContext())){
            listener.error(MLError.errorFromMessageAndTitle(getContext().getString(R.string.no_internet),
                    getContext().getString(R.string.error)));
        }

        PaymentDAOImpl paymentDAO = new PaymentDAOImpl(getContext());
        paymentDAO.installmentOptions(new MLResultListener<List<InstallmentOptionDTO>>() {

            @Override
            public void success(List<InstallmentOptionDTO> result) {
                List<InstallmentOptionDTO> items = new ArrayList<>();
                items.add(getInstallmentOptionsBy(issuerID,result));
                listener.success(items);
            }

            @Override
            public void error(MLError error) {
                listener.error(error);
            }
        },amount,paymentMethodID,issuerID);
    }

    private InstallmentOptionDTO getInstallmentOptionsBy(String issuerID,
                                                         List<InstallmentOptionDTO> items){
        InstallmentOptionDTO installmentOption = new InstallmentOptionDTO();
        for(InstallmentOptionDTO installment : items){
            if(installment.getIssuer().getId().equals(issuerID)){
                return installment;
            }
        }
        return installmentOption;
    }

    public String getRecommendedMessage(InstallmentOptionDTO items, String installments){
        for(PayerCostDTO payerCost : items.getPayerCosts()){
            if(payerCost.getInstallments() == Integer.valueOf(installments)){
                return payerCost.getRecommendedMessage();
            }
        }
        return "";
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
