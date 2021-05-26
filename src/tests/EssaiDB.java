package tests;

import singleconnection.ConnectionBD;

import java.sql.*;

public class EssaiDB {

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver chargé avec succès");
            String url = "jdbc:mysql://localhost:3306/sampharmacie?autoReconnect=true&useSSL=false";
            String user = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("BD OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection c = ConnectionBD.ouvrirConnection();
        PreparedStatement s = c.prepareStatement("");


    }
}
