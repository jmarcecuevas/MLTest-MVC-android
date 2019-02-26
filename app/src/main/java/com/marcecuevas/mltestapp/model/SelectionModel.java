package com.marcecuevas.mltestapp.model;

import java.io.Serializable;

public class SelectionModel implements Serializable {

    private String total;
    private int installmentsAmount;
    private String paymentName;
    private String paymentID;
    private String bankName;
    private String bankID;

    public SelectionModel(){

    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getInstallmentsAmount() {
        return installmentsAmount;
    }

    public void setInstallmentsAmount(int installmentsAmount) {
        this.installmentsAmount = installmentsAmount;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
