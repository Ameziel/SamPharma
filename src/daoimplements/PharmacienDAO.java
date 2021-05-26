/*
 * PharmacienDAO.java
 */
package daoimplements;

import dao.DAO;
import exceptions.ErrCommandeConcernee;
import exceptions.ErrIdentifiantDejaExistant;
import exceptions.ErrIdentificationPharmacien;
import exceptions.ErrObjetInexistant;
import modeles.Client;
import modeles.Pharmacien;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe de methodes pour les objets de type 'PharmacienDAO'
 * @author Samuel Sicard
 * @version 0.9
 */
public class PharmacienDAO implements DAO<Pharmacien, String> {

    /**
     * Connexion utilise
     */
    private Connection connexion;

    /**
     * Constante statique d'une valeur erronee
     */
    private static final int ERROR_VALUE = -1;

    public PharmacienDAO(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void create(Pharmacien arg_obj) throws ErrIdentifiantDejaExistant {
        if (!isPharmacienExist(arg_obj.getIdPharmacien())) {
            String requete = "INSERT INTO pharmacien (idpharmacien,nom,prenom,mdppharmacien,gsm,email)" +
                    "VALUES (?,?,?,?,?,?);";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_obj.getIdPharmacien());
                ps.setString(2, arg_obj.getNomPharmacien());
                ps.setString(3, arg_obj.getPrenomPharmacien());
                ps.setString(4, arg_obj.getMdpPharmacien());
                ps.setString(5, arg_obj.getGsmPharmacien());
                ps.setString(6, arg_obj.getEmailPharmacien());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new ErrIdentifiantDejaExistant();
        }
    }

    @Override
    public void update(Pharmacien arg_obj) throws ErrObjetInexistant {
        if (isPharmacienExist(arg_obj.getIdPharmacien())) {
            String requete = "UPDATE pharmacien SET nom = ?,    prenom = ?, mdppharmacien = ?,    gsm = ?,    email = ?   WHERE idpharmacien = ?;";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_obj.getNomPharmacien());
                ps.setString(2, arg_obj.getPrenomPharmacien());
                ps.setString(3, arg_obj.getMdpPharmacien());
                ps.setString(4, arg_obj.getGsmPharmacien());
                ps.setString(5, arg_obj.getEmailPharmacien());
                ps.setString(6, arg_obj.getIdPharmacien());
                int nb_result = ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new ErrObjetInexistant();
        }

    }

    private boolean isPharmacienExist(String arg_id) {
        String requete = "SELECT count(*)  AS pharmacienTotal FROM pharmacien WHERE idpharmacien = ?;";
        int retour = 0;
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setString(1, arg_id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                retour = result.getInt("pharmacienTotal");
            }
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (retour == 1);
    }

    @Override
    public void delete(String arg_id) throws ErrCommandeConcernee, ErrObjetInexistant {
        if (isPharmacienExist(arg_id)) {
            int nb_commandes = nombreCommandePassees(arg_id);
            if (nb_commandes > 0) {
                throw new ErrCommandeConcernee();
            } else {
                String requete = "DELETE FROM pharmacien WHERE idpharmacien = ?";
                try {
                    PreparedStatement ps = connexion.prepareStatement(requete);
                    ps.setString(1, arg_id);
                    int nb_result = ps.executeUpdate();
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } else {
            throw new ErrObjetInexistant();
        }

    }

    public int nombreCommandePassees(String arg_id) throws ErrObjetInexistant {
        if (isPharmacienExist(arg_id)) {
            String requete = "SELECT count(*)  AS total FROM commande WHERE refpharmacien = ?;";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_id);
                ResultSet result = ps.executeQuery();
                int nombre = 0;
                while (result.next()) {
                    nombre = result.getInt("total");
                }
                result.close();
                ps.close();
                return nombre;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return ERROR_VALUE;
            }
        } else {
            throw new ErrObjetInexistant();
        }
    }

    @Override
    public Pharmacien find(String arg_id) throws ErrObjetInexistant {
        Pharmacien pharmacien;
        if (isPharmacienExist(arg_id)) {
            String requete = "SELECT * FROM pharmacien where idpharmacien = ? ;";
            pharmacien = new Pharmacien();
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_id);
                ResultSet result = ps.executeQuery();
                ResultSetMetaData result_meta = result.getMetaData();
                while (result.next()) {
                    pharmacien.setIdPharmacien(result.getString("idpharmacien"));
                    pharmacien.setNomPharmacien(result.getString("nom"));
                    pharmacien.setPrenomPharmacien(result.getString("prenom"));
                    pharmacien.setMdpPharmacien(result.getString("mdppharmacien"));
                    pharmacien.setGsmPharmacien(result.getString("gsm"));
                    pharmacien.setEmailPharmacien(result.getString("email"));
                }
                result.close();
                ps.close();
                return pharmacien;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new ErrObjetInexistant();
        }
        return  pharmacien;
    }

    @Override
    public ArrayList<Pharmacien> findAll() {
        String requete = "SELECT * FROM pharmacien;";
        ArrayList<Pharmacien> pharmaciens = new ArrayList<Pharmacien>();
        try {
            Statement s = connexion.createStatement();
            ResultSet result = s.executeQuery(requete);
            ResultSetMetaData result_meta = result.getMetaData();
            while (result.next()) {
                Pharmacien p = new Pharmacien();
                p.setIdPharmacien(result.getString("idpharmacien"));
                p.setNomPharmacien(result.getString("nom"));
                p.setPrenomPharmacien(result.getString("prenom"));
                p.setMdpPharmacien(result.getString("mdppharmacien"));
                p.setGsmPharmacien(result.getString("gsm"));
                p.setEmailPharmacien(result.getString("email"));
                pharmaciens.add(p);
            }
            result.close();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pharmaciens;
    }

    /**
     * Permet l'authentification à l'application à l'aide d'un identifiant et d'un mot de passe
     */
    public String login(String arg_id, String arg_password) throws ErrIdentificationPharmacien {
        String requete = "Select idpharmacien FROM pharmacien WHERE idpharmacien=? AND mdppharmacien=?;";
        String resultat = "wrong";
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setString(1, arg_id);
            ps.setString(2, arg_password);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                resultat = result.getString("idpharmacien");
            }
            result.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (resultat == null || resultat.equals("wrong")) {
            throw new ErrIdentificationPharmacien("Exception message");
        }
        return resultat;
    }
}
