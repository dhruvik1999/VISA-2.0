package com.visa.ATM.VisaDirect;

public class PushPojo {
    private String transactionIdentifier;
    private String actionCode;

    private String responseCode;

    private String transmissionDateTime;

    public void setTransactionIdentifier(String transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setTransmissionDateTime(String transmissionDateTime) {
        this.transmissionDateTime = transmissionDateTime;
    }



    public String getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public String getActionCode() {
        return actionCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getTransmissionDateTime() {
        return transmissionDateTime;
    }



}
