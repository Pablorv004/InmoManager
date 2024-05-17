package controllers;

import java.sql.SQLException;

import util.ConnectionDB;
import views.GUIConnect;
import views.GUILogin;

public class ControllerConnect {
    GUIConnect connect;

    public ControllerConnect(GUIConnect connect) {
        this.connect = connect;
        makeConnection();

    }

    public void makeConnection(){
        try{
            ConnectionDB.connect();
            new GUILogin();
            connect.dispose();
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    
}
