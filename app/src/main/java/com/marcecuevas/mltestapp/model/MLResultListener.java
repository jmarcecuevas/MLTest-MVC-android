package com.marcecuevas.mltestapp.model;

public interface MLResultListener<T> {

    void success(T result);
    void error(MLError error);
}
