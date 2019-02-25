package com.marcecuevas.mltestapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BankDTO implements Serializable {

    private String id;
    private String name;
    @SerializedName("secure_thumbnail")
    private String secureThumbnail;
    private String thumbnail;
    @SerializedName("processing_mode")
    private String processingMode;
    @SerializedName("merchant_account_id")
    private String merchantAccountID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String processingMode) {
        this.processingMode = processingMode;
    }

    public String getMerchantAccountID() {
        return merchantAccountID;
    }

    public void setMerchantAccountID(String merchantAccountID) {
        this.merchantAccountID = merchantAccountID;
    }
}