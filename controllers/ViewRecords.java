package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewRecords {

    //date picker variable
    public DatePicker datePickerFrom;
    public DatePicker datePickerTo;
    //table column
    public TableColumn<Expense, String> administrationColumn;
    public TableColumn<Expense, String> TravellingColumn;
    public TableColumn<Expense, Double> pettyCashColumn;
    public TableColumn<Expense, String> otherColumn;
    public TableColumn<Expense, String> totalColumn;
    public TableColumn<Expense, String> expenseId;

    //table
    public TableView<Expense> tableView;

    //labels
    public Label administrativeCost;
    public Label pettyCashCost;
    public Label purchasingCost;
    public Label otherCost;
    public Label totalCostLabel;


    //Observable list
    ObservableList<Expense> expenseList = FXCollections.observableArrayList();


    //instance of the Database Connection class
   // DatabaseConnection databaseConnection = new DatabaseConnection();

    //view expense records method
    public void view(ActionEvent actionEvent) {
        String dateFrom = "";
        String dateTo = "";

        //getting the date from
        LocalDate date = datePickerFrom.getValue();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date1 = datePickerTo.getValue();

        //converting the date
        if (date != null || date1 != null) {
            dateFrom = dateTimeFormatter.format(date);
            dateTo = dateTimeFormatter.format(date1);
            //debugging
            System.out.println(dateFrom);
            System.out.println(dateTo);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a date from both");
            a.show();
        }

        try {
            //query to get the sum of administration
            String administrationQuery = "select sum(amount) as amount from expenses where expenseType = 'administration' and dateEntered >= ? AND dateEntered <= ?";

            //query to get total purchases
            String purchasingQuery = "select sum(amount)as amount from expenses where expenseType = 'purchasing' and dateEntered >= ? AND dateEntered <= ?";

            //query to get total petty cash
            String totalPettyCash = "select sum(amount) as amount from expenses where expenseType = 'petty cash' and dateEntered >= ? AND dateEntered <= ?";

            //query to get the total other
            String totalOther = "select sum(amount) as amount from expenses where expenseType = 'other' and dateEntered >= ? AND dateEntered <= ?";

            //query for the total
            String totalCost = "select sum(amount) as amount from expenses where dateEntered >= ? AND dateEntered <= ?";


            //date range query
            String query = "SELECT * FROM expenses WHERE dateEntered >= ? AND dateEntered <= ? ";
            //instance of the database connection class
            DatabaseConnection obj = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = obj.getCon().prepareStatement(query);

            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                expenseList.add(new Expense(rs.getString("id"),rs.getString("expenseType"), rs.getString("expenseDescription"),
                        rs.getDouble("amount"), rs.getString("dateEntered"), rs.getString("enteredBy")));


            }

            //total administration
            preparedStatement = obj.getCon().prepareStatement(administrationQuery);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            ResultSet rs2 = preparedStatement.executeQuery();
            rs2.next();
            administrativeCost.setText(rs2.getString("amount"));

            //total purchases
            preparedStatement = obj.getCon().prepareStatement(purchasingQuery);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            purchasingCost.setText(rs2.getString("amount"));

            //total petty cash
            preparedStatement = obj.getCon().prepareStatement(totalPettyCash);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            pettyCashCost.setText(rs2.getString("amount"));

            //total other cost
            preparedStatement = obj.getCon().prepareStatement(totalOther);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            otherCost.setText(rs2.getString("amount"));

            //total cost
            preparedStatement = obj.getCon().prepareStatement(totalCost);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            totalCostLabel.setText(rs2.getString("amount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        administrationColumn.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        TravellingColumn.setCellValueFactory(new PropertyValueFactory<>("expenseDescription"));
        pettyCashColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        otherColumn.setCellValueFactory(new PropertyValueFactory<>("dateEntered"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("enteredBy"));
        expenseId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableView.setItems(expenseList);


    }
}
