/*
 * DAOFactory.java
 */
package dao;

import daoimplements.*;
import singleconnection.ConnectionBD;

import java.sql.Connection;

/**
 * Factory pour les objets de l'application SamPharma
 * @author Samuel Sicard
 * @version 0.9
 */
public class DAOFactory {

    protected static final Connection connexion = ConnectionBD.ouvrirConnection();

    public static ClientDAO getClientDAO() { return new ClientDAO(connexion); }

    public static CommandeDAO getCommandeDAO() { return new CommandeDAO(connexion); }

    public static LigneCommandeDAO getLigneCommandeDAO() {
        return new LigneCommandeDAO(connexion);
    }

    public static MedicamentDAO getMedicamentDAO() {
        return new MedicamentDAO(connexion);
    }

    public static PharmacienDAO getPharmacienDAO() {
        return new PharmacienDAO(connexion);
    }
}
