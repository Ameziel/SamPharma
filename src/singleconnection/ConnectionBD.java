/*
 * ConnectionBD.java
 */
package singleconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connection à la base de données avec emploi du pattern singleton
 * @author Samuel Sicard
 * @version 0.9
 */
public class ConnectionBD {

    private static volatile ConnectionBD co_unique = null;

    private String url = "jdbc:mysql://localhost:3306/sampharmacie?autoReconnect=true&useSSL=false";
    private String user = "root";
    private String password = "";

    private static Connection connexion;

    private ConnectionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("L'initialisation de la connexion a echouee");
        }
    }

    public static Connection ouvrirConnection() {
        if (co_unique == null) {
            synchronized (Connection.class) {
                if(co_unique == null) {
                    co_unique = new ConnectionBD();
                }
            }
        }
        return connexion;
    }
}
