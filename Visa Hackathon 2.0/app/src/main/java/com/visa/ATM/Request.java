package com.visa.ATM;

public class Request {


    String name;
    double rate;
    double amount;



    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getAmount() {
        return amount;
    }

}
