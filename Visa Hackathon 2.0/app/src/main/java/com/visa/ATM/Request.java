package com.visa.ATM;

public class Request {


    String userName;
    double rate;
    double amount;


    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public double getRate() {
        return rate;
    }

    public double getAmount() {
        return amount;
    }

}
