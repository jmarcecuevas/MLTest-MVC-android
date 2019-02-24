package com.marcecuevas.mltestapp.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhy.http.okhttp.request.CountingRequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenericDAO {

    private Context context;
    private Retrofit retrofit;

    public GenericDAO(Context context) {
        this.context = context;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        httpClient.readTimeout(600, TimeUnit.SECONDS);
        httpClient.connectTimeout(600, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        .method(original.method(), original.body());
                try{
                    Map<String, String> headers = getHeaders();
                    for (String key : headers.keySet()) {
                        builder.addHeader(key, headers.get(key));
                    }
                }catch (Exception e){
                }
                Request request = builder.build();
                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl.Builder builder= originalHttpUrl.newBuilder();
                Map<String, String> parameters = getParameters();
                for (String key : parameters.keySet()) {
                    builder.addEncodedQueryParameter(key, parameters.get(key));
                    builder.addQueryParameter(key, parameters.get(key));
                }
                HttpUrl url = builder.build();
                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        httpClient.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                if (originalRequest.body() == null) {
                    return chain.proceed(originalRequest);
                }

                Request progressRequest = originalRequest.newBuilder()
                        .method(originalRequest.method(),
                                new CountingRequestBody(originalRequest.body(), new CountingRequestBody.Listener() {
                                    @Override
                                    public void onRequestProgress(long bytesWritten, long contentLength) {
                                        double progress = (1.0 * bytesWritten) / contentLength;
                                        Log.v("REQUEST PROGRESS", progress + "");
                                    }
                                }))
                        .build();

                return chain.proceed(progressRequest);
            }
        });

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(getBaseURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(new GsonConverterFactory.create(new Gson()))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    protected Map<String, String> getParameters(){
        return new HashMap<>();
    }

    protected Map<String, String> getHeaders(){
        return new HashMap<>();
    }

    @NonNull
    protected String getBaseURL() {
        return "https://reqres.in/api/";
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Context getContext() {
        return context;
    }

}
