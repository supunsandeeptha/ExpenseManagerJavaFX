package controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {


    public TextField username;
    public PasswordField password;
    //Stage variable
    public Stage window;
    public Scene scene1;
    //login button
    @FXML
    public Button button;

    //instance of the database connection class
    DatabaseConnection connection = new DatabaseConnection();


    //main method
    public static void main(String[] args) {

        launch(args);

    }

    //starting the main window (login)
    @Override
    public void start(Stage primaryStage) throws Exception {
        //instantiating the Stage variable;
        this.window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        scene1 = new Scene(root, 600, 400);
        window.setTitle("Orinway Leisure - Expense Manager");
        window.setScene(scene1);
        window.show();
    }


    public void login(ActionEvent actionEvent) {
        //getting the username
        String userName = username.getText();
        //getting the password
        String pass = password.getText();

        //query
        String query = "select * from users where username = '" + userName + "'" + "and pass = '" + pass + "'";

        try {
            Statement st = connection.con.createStatement();
            //result set
            ResultSet resultSet;
            resultSet = st.executeQuery(query);

            //if the credentials are wrong result set will be empty //issue a error
            if (!resultSet.next()) {
                //error (alert)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter Correct Credentials");
                alert.show();
            } else {
                String dbUsername = resultSet.getString("username");
                String dbPass = resultSet.getString("pass");

                if (dbUsername.equals(userName) && dbPass.equals(pass)) {
                    //else open the next window
                    Parent root;
                    try {
                        //setting the fxml file
                        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                        //new scene
                        Scene scene = new Scene(root, 600, 400);
                        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        app_stage.setScene(scene);
                        app_stage.show();


                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter Correct Credentials");
                    alert.show();

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
