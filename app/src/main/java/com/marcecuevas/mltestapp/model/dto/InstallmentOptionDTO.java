package com.marcecuevas.mltestapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InstallmentOptionDTO implements Serializable {

    @SerializedName("payment_method_id")
    private String paymentMethodId;
    @SerializedName("payment_type_id")
    private String paymentTypeId;
    private IssuerDTO issuer;
    @SerializedName("processing_mode")
    private String processingMode;
    @SerializedName("merchant_account_id")
    private String merchantAccountID;
    @SerializedName("payer_costs")
    private List<PayerCostDTO> payerCosts;

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public IssuerDTO getIssuer() {
        return issuer;
    }

    public void setIssuer(IssuerDTO issuer) {
        this.issuer = issuer;
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

    public List<PayerCostDTO> getPayerCosts() {
        return payerCosts;
    }

    public void setPayerCosts(List<PayerCostDTO> payerCosts) {
        this.payerCosts = payerCosts;
    }

    public class PayerCostDTO implements Serializable {

        @SerializedName("total_amount")
        private double totalAmount;
        @SerializedName("installment_amount")
        private double installmentAmount;
        @SerializedName("recommended_message")
        private String recommendedMessage;
        @SerializedName("max_allowed_amount")
        private int maxAllowedAmount;
        @SerializedName("min_allowed_amount")
        private int minAllowedAmount;
        @SerializedName("installment_rate_collector")
        private List<String> installmentRateCollector;
        private List<String> labels;
        @SerializedName("discount_rate")
        private int discountRate;
        @SerializedName("installment_rate")
        private double installmentRate;
        private int installments;

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public double getInstallmentAmount() {
            return installmentAmount;
        }

        public void setInstallmentAmount(int installmentAmount) {
            this.installmentAmount = installmentAmount;
        }

        public String getRecommendedMessage() {
            return recommendedMessage;
        }

        public void setRecommendedMessage(String recommendedMessage) {
            this.recommendedMessage = recommendedMessage;
        }

        public int getMaxAllowedAmount() {
            return maxAllowedAmount;
        }

        public void setMaxAllowedAmount(int maxAllowedAmount) {
            this.maxAllowedAmount = maxAllowedAmount;
        }

        public int getMinAllowedAmount() {
            return minAllowedAmount;
        }

        public void setMinAllowedAmount(int minAllowedAmount) {
            this.minAllowedAmount = minAllowedAmount;
        }

        public List<String> getInstallmentRateCollector() {
            return installmentRateCollector;
        }

        public void setInstallmentRateCollector(List<String> installmentRateCollector) {
            this.installmentRateCollector = installmentRateCollector;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public int getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(int discountRate) {
            this.discountRate = discountRate;
        }

        public double getInstallmentRate() {
            return installmentRate;
        }

        public void setInstallmentRate(int installmentRate) {
            this.installmentRate = installmentRate;
        }

        public int getInstallments() {
            return installments;
        }

        public void setInstallments(int installments) {
            this.installments = installments;
        }
    }

    public class IssuerDTO implements Serializable {

        private String thumbnail;
        @SerializedName("secure_thumbnail")
        private String secureThumbnail;
        private String name;
        private String id;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getSecureThumbnail() {
            return secureThumbnail;
        }

        public void setSecureThumbnail(String secureThumbnail) {
            this.secureThumbnail = secureThumbnail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

//https://api.mercadopago.com/v1/payment_methods/installments?public_key=444a9ef5-8a6b-429f-abdf-587639155d88&amount=1600&payment_method_id=visa&issuer_id=288#json
