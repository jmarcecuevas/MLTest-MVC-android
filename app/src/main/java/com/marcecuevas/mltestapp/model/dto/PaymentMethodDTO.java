package com.marcecuevas.mltestapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PaymentMethodDTO implements Serializable {

    private String id;
    private String name;
    @SerializedName("payment_type_id")
    private String paymentTypeID;
    private String status;
    @SerializedName("secure_thumbnail")
    private String secureThumbnail;
    private String thumbnail;
    @SerializedName("deferred_capture")
    private String deferredCapture;
    private List<SettingsDTO> settings;
    @SerializedName("additional_info_needed")
    private List<String> additionalInfoNeeded;
    @SerializedName("min_allowed_amount")
    private String minAllowedAmount;
    @SerializedName("max_allowed_amount")
    private String maxAllowedAmount;
    @SerializedName("accreditation_time")
    private String accreditationTime;
    @SerializedName("financial_institutions")
    private List<String> financialInstitutions;
    @SerializedName("processing_modes")
    private List<String> processingModes;

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

    public String getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setPaymentTypeID(String paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDeferredCapture() {
        return deferredCapture;
    }

    public void setDeferredCapture(String deferredCapture) {
        this.deferredCapture = deferredCapture;
    }

    public List<SettingsDTO> getSettings() {
        return settings;
    }

    public void setSettings(List<SettingsDTO> settings) {
        this.settings = settings;
    }

    public List<String> getAdditionalInfoNeeded() {
        return additionalInfoNeeded;
    }

    public void setAdditionalInfoNeeded(List<String> additionalInfoNeeded) {
        this.additionalInfoNeeded = additionalInfoNeeded;
    }

    public String getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public void setMinAllowedAmount(String minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public String getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(String maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public String getAccreditationTime() {
        return accreditationTime;
    }

    public void setAccreditationTime(String accreditationTime) {
        this.accreditationTime = accreditationTime;
    }

    public List<String> getFinancialInstitutions() {
        return financialInstitutions;
    }

    public void setFinancialInstitutions(List<String> financialInstitutions) {
        this.financialInstitutions = financialInstitutions;
    }

    public List<String> getProcessingModes() {
        return processingModes;
    }

    public void setProcessingModes(List<String> processingModes) {
        this.processingModes = processingModes;
    }

    public boolean isCreditCard(){
        return paymentTypeID.equals("credit_card");
    }

    public class SettingsDTO implements Serializable {

        @SerializedName("card_number")
        private CardNumberDTO cardNumber;
        private BinDTO bin;
        @SerializedName("security_code")
        private SecurityCodeDTO securityCode;

        public CardNumberDTO getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(CardNumberDTO cardNumber) {
            this.cardNumber = cardNumber;
        }

        public BinDTO getBin() {
            return bin;
        }

        public void setBin(BinDTO bin) {
            this.bin = bin;
        }

        public SecurityCodeDTO getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(SecurityCodeDTO securityCode) {
            this.securityCode = securityCode;
        }

        public class CardNumberDTO implements Serializable {

            private String validation;
            private String length;

            public String getValidation() {
                return validation;
            }

            public void setValidation(String validation) {
                this.validation = validation;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }
        }

        public class BinDTO implements Serializable {

            private String pattern;
            @SerializedName("installments_pattern")
            private String installmentsPattern;
            @SerializedName("exclusion_pattern")
            private String exclusionPattern;

            public String getPattern() {
                return pattern;
            }

            public void setPattern(String pattern) {
                this.pattern = pattern;
            }

            public String getInstallmentsPattern() {
                return installmentsPattern;
            }

            public void setInstallmentsPattern(String installmentsPattern) {
                this.installmentsPattern = installmentsPattern;
            }

            public String getExclusionPattern() {
                return exclusionPattern;
            }

            public void setExclusionPattern(String exclusionPattern) {
                this.exclusionPattern = exclusionPattern;
            }
        }

        public class SecurityCodeDTO implements Serializable {

            private String length;
            @SerializedName("card_location")
            private String cardLocation;
            private String mode;

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getCardLocation() {
                return cardLocation;
            }

            public void setCardLocation(String cardLocation) {
                this.cardLocation = cardLocation;
            }

            public String getMode() {
                return mode;
            }

            public void setMode(String mode) {
                this.mode = mode;
            }
        }

    }
}

