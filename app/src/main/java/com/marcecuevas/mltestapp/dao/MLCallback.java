package com.marcecuevas.mltestapp.dao;

import com.marcecuevas.mltestapp.model.MLError;
import com.marcecuevas.mltestapp.model.MLResultListener;

import java.lang.annotation.Annotation;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MLCallback<T> implements retrofit2.Callback<T> {

    private MLResultListener<T> listener;

    private Retrofit retrofit;

    public MLCallback(MLResultListener<T> listener, Retrofit retrofit) {
        this.listener = listener;
        this.retrofit = retrofit;
    }

    @Override
    public void onResponse(retrofit2.Call<T> call, Response<T> response) {
        if (!response.isSuccessful()){
            try {
                Converter<ResponseBody, MLError> errorConverter = retrofit.responseBodyConverter(MLError.class, new Annotation[0]);
                MLError error = errorConverter.convert(response.errorBody());
                listener.error(error);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        listener.success(response.body());
    }

    @Override
    public void onFailure(retrofit2.Call<T> call, Throwable t) {
        listener.error(new MLError());
    }
}