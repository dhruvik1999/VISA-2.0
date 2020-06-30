package com.visa.ATM.VisaDirect;

public class PullPojo {


    private String transactionIdentifier;

    private String actionCode;

    private String approvalCode;

    private String responseCode;

    private String transmissionDateTime;

    private String cavvResultCode;

    private String cpsAuthorizationCharacteristicsIndicator;

    public void setTransactionIdentifier(String transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setTransmissionDateTime(String transmissionDateTime) {
        this.transmissionDateTime = transmissionDateTime;
    }

    public void setCavvResultCode(String cavvResultCode) {
        this.cavvResultCode = cavvResultCode;
    }

    public void setCpsAuthorizationCharacteristicsIndicator(String cpsAuthorizationCharacteristicsIndicator) {
        this.cpsAuthorizationCharacteristicsIndicator = cpsAuthorizationCharacteristicsIndicator;
    }


    public String getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public String getActionCode() {
        return actionCode;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getTransmissionDateTime() {
        return transmissionDateTime;
    }

    public String getCavvResultCode() {
        return cavvResultCode;
    }

    public String getCpsAuthorizationCharacteristicsIndicator() {
        return cpsAuthorizationCharacteristicsIndicator;
    }



}