package controllers;

public class Income {


    //variables
    private  String Id;
    private String incomeType;
    private String incomeDescription;
    private double amount;
    private String paymentMode;
    private String dateEntered;
    private String enteredBy;


    //constructor
    public Income(String id, String incomeType, String incomeDescription, double amount,String paymentMode, String dateEntered, String enteredBy) {
        Id = id;
        this.incomeType = incomeType;
        this.incomeDescription = incomeDescription;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.dateEntered = dateEntered;
        this.enteredBy = enteredBy;
    }

    //getters and setters
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getIncomeDescription() {
        return incomeDescription;
    }

    public void setIncomeDescription(String incomeDescription) {
        this.incomeDescription = incomeDescription;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }



}
