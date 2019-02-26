package com.marcecuevas.mltestapp.dao;

import com.marcecuevas.mltestapp.model.MLResultListener;
import com.marcecuevas.mltestapp.model.dto.BankDTO;
import com.marcecuevas.mltestapp.model.dto.InstallmentOptionDTO;
import com.marcecuevas.mltestapp.model.dto.PaymentMethodDTO;

import java.util.List;

public interface PaymentDAO {

    void paymentMethods(final MLResultListener<List<PaymentMethodDTO>> listener);

    void banks(final MLResultListener<List<BankDTO>> listener, String paymentMethodID);

    void installmentOptions(final MLResultListener<List<InstallmentOptionDTO>> listener, String amount,
                            String paymentMethodID, String issuerID);

}
