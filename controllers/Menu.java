package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    //open enter expenses window
    public void enterExpenses(ActionEvent actionEvent) {
        //parent root
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("expenseenter.fxml"));
            root = (Parent)loader.load();
            //new scene
            Scene scene = new Scene(root,600,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("View Records - Edit Records");
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //open view expenses window
    public void viewExpenses(ActionEvent actionEvent) {
        //parent root
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewrecords.fxml"));
            root = (Parent)loader.load();
            //new scene
            Scene scene = new Scene(root,1024,720);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("View Records - Edit Records");
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //open enter income window
    public void enterIncome(ActionEvent actionEvent) {

        //parent root
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("enterincome.fxml"));
            root = (Parent)loader.load();
            //new scene
            Scene scene = new Scene(root,600,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("View Records - Edit Records");
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //open view income window
    public void viewIncome(ActionEvent actionEvent) {

        //parent root
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewincome.fxml"));
            root = (Parent)loader.load();
            //new scene
            Scene scene = new Scene(root,1024,720);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("View Records - Edit Records");
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
