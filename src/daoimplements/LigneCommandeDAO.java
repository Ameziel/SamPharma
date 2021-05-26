/*
 * LigneCommandeDAO.java
 */
package daoimplements;

import dao.DAO;
import exceptions.ErrCommandeConcernee;
import exceptions.ErrIdentifiantDejaExistant;
import exceptions.ErrObjetInexistant;
import modeles.Commande;
import modeles.LigneCommande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class LigneCommandeDAO implements DAO<LigneCommande, Integer> {

    private Connection connexion;

    public LigneCommandeDAO(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void create(LigneCommande arg_obj) throws ErrIdentifiantDejaExistant {
        String requete = "INSERT INTO lignedecommande (idrefcommande, idrefmedicament, quantite) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setInt(1, arg_obj.getRefCommande());
            ps.setString(2, arg_obj.getRefMedicament());
            ps.setInt(3, arg_obj.getQuantite());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * @param arg_idlignecommande : L'identifiant de la ligne de commande
     * @return True si la ligne existe, False sinon
     */
    private boolean isLigneCommandeExist(Integer arg_idlignecommande) {
        String requete = "SELECT count(*) AS lignecommandetotal FROM lignedecommande WHERE idlignecommande = ?;";
        int retour = 0;
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setInt(1, arg_idlignecommande);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                retour = result.getInt("lignecommandetotal");
            }
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (retour == 1);
    }

    public void createMultipleLigneCommande(ArrayList<LigneCommande> arg_list_obj, Integer idCommande) throws SQLException {
        String requete = "INSERT INTO lignedecommande (idrefcommande, idrefmedicament, quantite) VALUES (?, ?, ?)";
        connexion.setAutoCommit(false);
        try {
            for (LigneCommande lignecommande : arg_list_obj) {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setInt(1, idCommande);
                ps.setString(2, lignecommande.getRefMedicament());
                ps.setInt(3, lignecommande.getQuantite());
                ps.executeUpdate();
                ps.close();
            }
            connexion.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connexion.setAutoCommit(true);
    }

    @Override
    public void update(LigneCommande arg_obj)  {
        //inutile au vue de l'IHM
    }

    @Override
    public void delete(Integer arg_obj) {
        if (isLigneCommandeExist(arg_obj)) {
            String requete = "DELETE FROM lignedecommande WHERE idlignecommande = ?;";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setInt(1, arg_obj);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public LigneCommande find(Integer arg_id) throws ErrObjetInexistant {
        String requete = "SELECT * FROM lignedecommande WHERE idlignecommande = ?;";
        LigneCommande lignecommande = null;
        try {
            lignecommande = new LigneCommande();
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setInt(1, arg_id);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                lignecommande.setIdlignecommande(result.getInt("idlignecommande"));
                lignecommande.setRefCommande(result.getInt("idrefcommande"));
                lignecommande.setRefMedicament(result.getString("idrefmedicament"));
                lignecommande.setQuantite(result.getInt("quantite"));
            }
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lignecommande;
    }

    @Override
    public ArrayList<LigneCommande> findAll() {
        String requete = "SELECT * FROM lignedecommande;";
        ArrayList<LigneCommande> ligneCommandes = null;
        try {
            ligneCommandes = new ArrayList<>();
            PreparedStatement ps = connexion.prepareStatement(requete);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                LigneCommande lignecommande = new LigneCommande();
                lignecommande.setIdlignecommande(result.getInt("idlignecommande"));
                lignecommande.setRefCommande(result.getInt("idrefcommande"));
                lignecommande.setRefMedicament(result.getString("idrefmedicament"));
                lignecommande.setQuantite(result.getInt("quantite"));
                ligneCommandes.add(lignecommande);
            }
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ligneCommandes;
    }
}
