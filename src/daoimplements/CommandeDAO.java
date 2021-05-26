/*
 * CommandeDAO.java
 */
package daoimplements;

import dao.DAO;
import exceptions.ErrObjetInexistant;
import modeles.Commande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class description goes here.
 * @author Samuel Sicard
 * @version 0.9
 */
public class CommandeDAO implements DAO<Commande, Integer> {

    /**
     * Connexion utilise
     */
    private Connection connexion;

    /**
     * Constante statique d'une valeur erronee
     */
    private static final int ERROR_VALUE = -1;

    public CommandeDAO(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void create(Commande arg_obj) {
        String requete = "INSERT INTO commande (refclient, refpharmacien, date) VALUES (?, ?, SYSDATE());";
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setString(1, arg_obj.getRefClient());
            ps.setString(2, arg_obj.getRefPharmacien());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Integer getLastIdCommande() {
        String requete = "SELECT MAX(idcommande) as idcommande FROM commande;";
        Integer idCommande = -1;
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ResultSet result = ps.executeQuery();
            result.next();
            idCommande = result.getInt("idcommande");
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idCommande;
    }

    /**
     * @param arg_id : L'identifiant de la commande
     * @return True si la commande existe, False sinon
     */
    private boolean isCommandeExist(Integer arg_id) {
        String requete = "SELECT count(*)  AS commandetotal FROM commande WHERE idcommande = ?;";
        Integer retour = 0;
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setInt(1, arg_id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                retour = result.getInt("commandetotal");
            }
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (retour == 1);
    }

    @Override
    public void update(Commande arg_obj) throws ErrObjetInexistant {
        if (isCommandeExist(arg_obj.getIdCommande())) {
            String requete = "UPDATE commande SET date = ? WHERE idcommande = ? ;";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                //ps.setDate(1, new java.sql.Date.valueOf(arg_obj.getDate().toString()));
                ps.setInt(2, arg_obj.getIdCommande());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new ErrObjetInexistant();
        }
    }


    @Override
    public void delete(Integer arg_id) throws SQLException, ErrObjetInexistant {
        connexion.setAutoCommit(false);
        if (isCommandeExist(arg_id)) {
            String requeteLigneCommande = "DELETE FROM lignedecommande WHERE idrefcommande = ? ;";
            String requeteCommande = "DELETE FROM commande WHERE idcommande = ? ;";
            try {
                PreparedStatement ps = connexion.prepareStatement(requeteLigneCommande);
                ps.setInt(1, arg_id);
                ps.executeUpdate();
                ps.close();

                ps = connexion.prepareStatement(requeteCommande);
                ps.setInt(1, arg_id);
                ps.executeUpdate();
                ps.close();

                connexion.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            throw new ErrObjetInexistant();
        }
        connexion.setAutoCommit(true);
    }

    @Override
    public Commande find(Integer arg_id) {
        String requete = "SELECT * FROM commande WHERE idcommande = ?;";
        Commande commande = null;
        try {
            commande = new Commande();
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setInt(1, arg_id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                commande.setIdCommande(result.getInt("idcommande"));
                commande.setRefClient(result.getString("refclient"));
                commande.setRefPharmacien(result.getString("refpharmacien"));
                commande.setDate(result.getObject("date", LocalDate.class));
                commande.setMontanttotal(calculTotalCommande(arg_id));
            }
            result.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

    public double calculTotalCommande(Integer arg_id) throws SQLException {
        String requete = "SELECT SUM((medicament.pvente * lignedecommande.quantite)) prixtotal " +
                "FROM commande " +
                "JOIN lignedecommande " +
                "ON commande.idcommande = lignedecommande.idrefcommande " +
                "JOIN medicament " +
                "ON lignedecommande.idrefmedicament = medicament.idmedicament " +
                "WHERE idcommande = ?";
        double montanttotal = 0.0d;
        PreparedStatement ps = connexion.prepareStatement(requete);
        ps.setInt(1, arg_id);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            montanttotal = result.getDouble("prixtotal");
        }
        result.close();
        ps.close();
        return montanttotal;
    }


    @Override
    public ArrayList<Commande> findAll() {
        String requete = "SELECT * FROM commande;";
        ArrayList<Commande> commandes = null;
        try {
            commandes = new ArrayList<>();
            PreparedStatement ps = connexion.prepareStatement(requete);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Commande commande = new Commande();
                commande.setIdCommande(result.getInt("idcommande"));
                commande.setRefClient(result.getString("refclient"));
                commande.setRefPharmacien(result.getString("refpharmacien"));
                commande.setDate(result.getObject("date", LocalDate.class));
                commande.setMontanttotal(calculTotalCommande(commande.getIdCommande()));
                commandes.add(commande);
            }
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commandes;
    }
}
