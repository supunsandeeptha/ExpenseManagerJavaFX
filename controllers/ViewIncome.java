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

public class ViewIncome {
    //date picker variable
    public DatePicker datePickerFrom;
    public DatePicker datePickerTo;
    //table
    public TableView<Income> tableView;
    //columns
    public TableColumn<Income, String> incomeType;
    public TableColumn<Income, String> description;
    public TableColumn<Income, Double> amount;
    public TableColumn<Income, String> incomeId;
    public TableColumn<Income, String> date;
    public TableColumn<Income, String> enteredby;
    public TableColumn<Income, String> paymentMode;

    //labels
    public Label airTickets;
    public Label visas;
    public Label tours;
    public Label otherIncome;
    public Label totalIncomeLabel;



    //Observable list
    ObservableList<Income> incomeList = FXCollections.observableArrayList();


    //instance of the Database Connection class
     DatabaseConnection databaseConnection = new DatabaseConnection();


    //view income records method
    public void view(ActionEvent actionEvent) {

        String dateFrom = "";
        String dateTo = "";

        //getting the date from

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = datePickerFrom.getValue();
        LocalDate date2 = datePickerTo.getValue();

        //converting the date
        if (date1 != null || date2 != null) {
            dateFrom = dateTimeFormatter.format(date1);
            dateTo = dateTimeFormatter.format(date2);
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
            String airTicketQuery = "select sum(amount) as amount from income where incomeType = 'Air Tickets' and dateEntered >= ? AND dateEntered <= ?";

            //query to get total purchases
            String visaQuery = "select sum(amount)as amount from income where incomeType = 'Visas' and dateEntered >= ? AND dateEntered <= ?";

            //query to get total petty cash
            String tourQuery = "select sum(amount) as amount from income where incomeType = 'Tours' and dateEntered >= ? AND dateEntered <= ?";

            //query to get the total other
            String otherQuery = "select sum(amount) as amount from income where incomeType = 'Other' and dateEntered >= ? AND dateEntered <= ?";

            //query for the total
            String totalIncomeQuery = "select sum(amount) as amount from income where dateEntered >= ? AND dateEntered <= ?";


            //date range query
            String query = "SELECT * FROM income WHERE dateEntered >= ? AND dateEntered <= ? ";
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement(query);

            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                incomeList.add(new Income(rs.getString("id"),rs.getString("incomeType"), rs.getString("incomeDescription"),
                        rs.getDouble("amount"),rs.getString("paymentMode"), rs.getString("dateEntered"), rs.getString("enteredBy")));


            }

            for(int i =0 ; i <incomeList.size();i++){
                System.out.println(incomeList.get(i));
            }

            //total administration
            preparedStatement = databaseConnection.con.prepareStatement(airTicketQuery);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            ResultSet rs2 = preparedStatement.executeQuery();
            rs2.next();
            airTickets.setText(rs2.getString("amount"));

            //total purchases
            preparedStatement = databaseConnection.con.prepareStatement(visaQuery);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            visas.setText(rs2.getString("amount"));

            //total petty cash
            preparedStatement = databaseConnection.con.prepareStatement(tourQuery);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            tours.setText(rs2.getString("amount"));

            //total other cost
            preparedStatement = databaseConnection.con.prepareStatement(otherQuery);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            otherIncome.setText(rs2.getString("amount"));

            //total cost
            preparedStatement = databaseConnection.con.prepareStatement(totalIncomeQuery);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);
            rs2 = preparedStatement.executeQuery();
            rs2.next();
            totalIncomeLabel.setText(rs2.getString("amount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        incomeType.setCellValueFactory(new PropertyValueFactory<>("incomeType"));
        description.setCellValueFactory(new PropertyValueFactory<>("incomeDescription"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentMode.setCellValueFactory(new PropertyValueFactory<>("paymentMode"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateEntered"));
        enteredby.setCellValueFactory(new PropertyValueFactory<>("enteredBy"));
        incomeId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableView.setItems(incomeList);


    }
}
