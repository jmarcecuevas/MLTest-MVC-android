package com.marcecuevas.mltestapp.model;

import java.io.Serializable;

public class MLError implements Serializable {

    private String title;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static MLError errorFromMessage(String message){
        MLError result = new MLError();
        result.setMessage(message);
        return result;
    }

    public static MLError errorFromMessageAndTitle(String message, String title){
        MLError result = MLError.errorFromMessage(message);
        result.setTitle(title);
        return result;
    }
}
