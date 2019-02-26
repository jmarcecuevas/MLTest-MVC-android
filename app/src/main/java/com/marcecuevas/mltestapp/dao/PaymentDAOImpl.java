package com.marcecuevas.mltestapp.dao;

import android.content.Context;

import com.marcecuevas.mltestapp.model.MLError;
import com.marcecuevas.mltestapp.model.MLResultListener;
import com.marcecuevas.mltestapp.model.dto.BankDTO;
import com.marcecuevas.mltestapp.model.dto.InstallmentOptionDTO;
import com.marcecuevas.mltestapp.model.dto.PaymentMethodDTO;

import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PaymentDAOImpl extends GenericDAO implements PaymentDAO {

    public PaymentDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void paymentMethods(final MLResultListener<List<PaymentMethodDTO>> listener){
        PaymentREST paymentREST = getRetrofit().create(PaymentREST.class);
        Call<List<PaymentMethodDTO>> call = paymentREST.paymentMethods(getPublicKey());

        call.enqueue(new MLCallback<>(new MLResultListener<List<PaymentMethodDTO>>() {
            @Override
            public void success(List<PaymentMethodDTO> result) {
                listener.success(result);
            }

            @Override
            public void error(MLError error) {
                listener.error(error);
            }
        },getRetrofit()));
    }

    @Override
    public void banks(final MLResultListener<List<BankDTO>> listener, String paymentMethodID){
        PaymentREST paymentREST = getRetrofit().create(PaymentREST.class);
        Call<List<BankDTO>> call = paymentREST.banks(getPublicKey(),paymentMethodID);

        call.enqueue(new MLCallback<>(new MLResultListener<List<BankDTO>>() {
            @Override
            public void success(List<BankDTO> result) {
                listener.success(result);
            }

            @Override
            public void error(MLError error) {
                listener.error(error);
            }
        },getRetrofit()));
    }

    @Override
    public void installmentOptions(final MLResultListener<List<InstallmentOptionDTO>> listener, String amount,
                                   String paymentMethodID,String issuerID){
        PaymentREST paymentREST = getRetrofit().create(PaymentREST.class);
        Call<List<InstallmentOptionDTO>> call = paymentREST.installmentOptions(getPublicKey(),amount,
                paymentMethodID,issuerID);

        call.enqueue(new MLCallback<>(new MLResultListener<List<InstallmentOptionDTO>>() {
            @Override
            public void success(List<InstallmentOptionDTO> result) {
                listener.success(result);
            }

            @Override
            public void error(MLError error) {
                listener.error(error);
            }
        },getRetrofit()));
    }


    private interface PaymentREST {

        @GET("payment_methods")
        Call<List<PaymentMethodDTO>> paymentMethods(@Query("public_key") String publicKey);

        @GET("payment_methods/card_issuers")
        Call<List<BankDTO>> banks(@Query("public_key") String publicKey,
                                                    @Query("payment_method_id") String paymentMethodID);

        @GET("payment_methods/installments")
        Call<List<InstallmentOptionDTO>> installmentOptions(@Query("public_key") String publicKey,
                                  @Query("amount") String amount,
                                  @Query("payment_method_id") String paymentMethodID,
                                  @Query("issuer_id") String issuerID);
    }
}
