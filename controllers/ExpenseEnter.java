package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseEnter {

    //text fields
//    public TextField expensetype;
    public TextField amount;
    public TextField enteredby;
    public TextField description;
    //date picker
    public DatePicker dateentered;
    //Combo Box
    public ComboBox select;



    //instance of the DatabaseConnection class
   // public DatabaseConnection databaseConnection = new DatabaseConnection();


    //save method
    public void save(ActionEvent actionEvent) {


        //getting the values from the user
        String expense = select.getValue().toString();
        System.out.println(expense);


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
        if (expense.isEmpty() || enteredPerson.isEmpty() || amt.isEmpty() || dateentered.getEditor().getText().isEmpty()
        || DBdescription.isEmpty() || !value) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please fill the values");
            a.show();
        } else {

            //query
            String query = "insert into expenses(expenseType,expenseDescription,amount,dateEntered,enteredBy) values (?,?,?,?,?)";
            //prepare statement

            //database connection obj
            DatabaseConnection obj = DatabaseConnection.getInstance();
            try {
                PreparedStatement preparedStatement = obj.getCon().prepareStatement(query);

                preparedStatement.setString(1, expense);
                preparedStatement.setString(2,DBdescription);
                preparedStatement.setDouble(3, correctAmount);
                preparedStatement.setString(4, dateDB);
                preparedStatement.setString(5, enteredPerson);

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

        //resetting the textfield after filling
        amount.clear();
        enteredby.clear();
        description.clear();
        dateentered.getEditor().clear();
        select.getEditor().clear();
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
