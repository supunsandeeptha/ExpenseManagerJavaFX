package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnterIncome {
    public TextField amount;
    public TextField enteredby;
    public DatePicker dateentered;
    public TextField description;

    //combo box
    public ComboBox select;
    public ComboBox selectPaymentMode;

    //instance of the database connection class
    //DatabaseConnection databaseConnection = new DatabaseConnection();

    public void save(ActionEvent actionEvent) {


        //getting the values from the user (payment category)
        String income = select.getValue().toString();
        System.out.println(income);

        //getting the payment payment
        String paymentMethod = selectPaymentMode.getValue().toString();
        System.out.println(paymentMethod);

        //variabale to store the
        double correctAmount = 0.0;

        //getting the amount
        String amt = amount.getText();
        //validation
        boolean value = isDouble(amount, amt);
        if (!value) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter a double value");
            a.show();

        } else {
            correctAmount = Double.parseDouble(amt);
            System.out.println(correctAmount);
        }

        String dateDB = "";
        //date time formatter (giving the database preferred patter)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //getting the date from the datepicker
        LocalDate date = dateentered.getValue();
        //converting
        if (date != null) {
            dateDB = formatter.format(date);
            System.out.println(dateDB);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a date");
            a.show();
        }



        //getting the entered name
        String  enteredPerson = enteredby.getText();

        //getting the description
        String  DBdescription = description.getText();


        //validation for all
        if (income.isEmpty() || enteredPerson.isEmpty() || amt.isEmpty() || dateentered.getEditor().getText().isEmpty()
                || DBdescription.isEmpty() || !value || paymentMethod.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please fill the values");
            a.show();
        } else {

            //query
            String query = "insert into income(incomeType,incomeDescription,amount,dateEntered,enteredBy,paymentMode) values (?,?,?,?,?,?)";
            //prepare statement

            DatabaseConnection obj = DatabaseConnection.getInstance();
            try {
                PreparedStatement preparedStatement = obj.getCon().prepareStatement(query);

                preparedStatement.setString(1, income);
                preparedStatement.setString(2,DBdescription);
                preparedStatement.setDouble(3, correctAmount);
                preparedStatement.setString(4, dateDB);
                preparedStatement.setString(5, enteredPerson);
                preparedStatement.setString(6,paymentMethod);

                //executing the statement
                int status = -1;

                status = preparedStatement.executeUpdate();

                //checking the status of the query
                if (status == 1) {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Successfully added the record");
                    a.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Failed Adding the Record");
                    a.show();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        //resetting the textfield
        amount.clear();
        dateentered.getEditor().clear();
        enteredby.clear();
        description.clear();
        select.getEditor().clear();
        selectPaymentMode.getEditor().clear();

        
    }



    //double input validation
    public boolean isDouble(TextField textField, String input) {
        try {
            double amount = Double.parseDouble(input);
            System.out.println("User has entered a double " + amount);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }
}
