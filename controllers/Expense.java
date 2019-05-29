package controllers;

public class Expense {



    //variables
    private String expenseType;
    private String expenseDescription;
    private double amount;
    private String dateEntered;
    private String enteredBy;
    private  String Id;



    //constructor
    public Expense(String Id,String expenseType, String expenseDescription,double amount, String dateEntered, String enteredBy){
        this.Id = Id;
        this.expenseType = expenseType;
        this.expenseDescription = expenseDescription;
        this.amount = amount;
        this.dateEntered = dateEntered;
        this.enteredBy = enteredBy;
    }

    //getters and setters
    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }


}
