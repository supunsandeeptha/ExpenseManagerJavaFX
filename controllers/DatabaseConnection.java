package controllers;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//singleton design class
public class DatabaseConnection {

    //credentials for the database connection
    private  String host ;
    private  String uName ;
    private  String password ;

    //connection variable
    private  Connection con;


    //private constructor
    private DatabaseConnection(String host,String uName, String password,Connection con){
         this.host = host;
         this.uName = uName;
         this.password = password;
         this.con = con;
    }

    //private instance of the class
    private static DatabaseConnection databaseConnection;

    static {
        try {
            databaseConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/orinway","root","",DriverManager.getConnection("jdbc:mysql://localhost:3306/orinway","root",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //getters
    public  String getHost() {
        return host;
    }

    public  String getuName() {
        return uName;
    }

    public  String getPassword() {
        return password;
    }

    public  Connection getCon() {
        return con;
    }



    // get instance of the class
    public static DatabaseConnection getInstance(){
        return databaseConnection;
    }











}

