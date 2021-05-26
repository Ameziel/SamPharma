/*
 * MedicamentDAO.java
 */
package daoimplements;

import dao.DAO;
import exceptions.ErrCommandeConcernee;
import exceptions.ErrIdentifiantDejaExistant;
import exceptions.ErrObjetInexistant;
import modeles.Medicament;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class MedicamentDAO implements DAO<Medicament,String> {

    /** Connexion utilise */
    private Connection connexion;

    /** Constante statique d'une valeur erronee */
    private static final int ERROR_VALUE = -1;

    public MedicamentDAO(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void create(Medicament arg_obj) throws ErrIdentifiantDejaExistant {
        if (!isMedicamentExist(arg_obj.getIdMedicament())) {
            String requete = "INSERT INTO medicament (idmedicament,denomination,formepharmaceutique,voieadministration,molecule,pvente,stockdisponible)" +
                    "VALUES (?,?,?,?,?,?,?);";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_obj.getIdMedicament());
                ps.setString(2, arg_obj.getDenomination());
                ps.setString(3, arg_obj.getFormePharmaceutique());
                ps.setString(4, arg_obj.getVoieAdministration());
                ps.setString(5, arg_obj.getMolecule());
                ps.setDouble(6, arg_obj.getPrixVente());
                ps.setInt(7, arg_obj.getStockDisponible());
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
    public void update(Medicament arg_obj) {
        //Impossible, un medicament doit etre encode valide. Un changement dans le produit / packaging
        //implique un changement d'identifiant / code barre / autorisation sur le marche.
    }

    public int nombreCommandeConcernee(String arg_id) throws ErrObjetInexistant {
        if (isMedicamentExist(arg_id)) {
            String requete = "SELECT count(*)  AS total FROM lignedecommande WHERE idrefmedicament = ?;";
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
    public void delete(String arg_obj) throws ErrCommandeConcernee, ErrObjetInexistant {
        if (isMedicamentExist(arg_obj)) {
            int nb_commandes = nombreCommandeConcernee(arg_obj);
            if (nb_commandes > 0) {
                throw new ErrCommandeConcernee();
            } else {
                String requete = "DELETE FROM medicament WHERE idmedicament = ?";
                try {
                    PreparedStatement ps = connexion.prepareStatement(requete);
                    ps.setString(1, arg_obj);
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

    /**
     *
     * @param arg_id : L'identifiant du medicament
     * @return True si le medicament existe, False sinon
     */
    private boolean isMedicamentExist(String arg_id) {
        String requete = "SELECT count(*)  AS medicamenttotal FROM medicament WHERE idmedicament = ?;";
        int retour = 0;
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setString(1,arg_id);
            ResultSet result     = ps.executeQuery();
            while(result.next()) {
                retour = result.getInt("medicamenttotal");
            }
            result.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (retour == 1);
    }

    @Override
    public Medicament find(String arg_id) throws ErrObjetInexistant {
        if(isMedicamentExist(arg_id)) {
            String requete                      = "SELECT * FROM medicament where idmedicament = ?;";
            Medicament medicament = new Medicament();
            try {
                PreparedStatement ps            =  connexion.prepareStatement(requete);
                ps.setString(1, arg_id);
                ResultSet result                = ps.executeQuery();
                ResultSetMetaData result_meta   = result.getMetaData();
                while (result.next()) {
                    medicament.setIdMedicament(result.getString("idmedicament"));
                    medicament.setDenomination(result.getString("denomination"));
                    medicament.setFormePharmaceutique(result.getString("formepharmaceutique"));
                    medicament.setVoieAdministration(result.getString("voieadministration"));
                    medicament.setMolecule(result.getString("molecule"));
                    medicament.setPrixVente(result.getDouble("pvente"));
                    medicament.setStockDisponible(result.getInt("stockdisponible"));
                }
                result.close();
                ps.close();
                return medicament;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new ErrObjetInexistant();
        }
        return new Medicament();
    }

    /**
     * Retourne les clients de l'application.
     * @return Une liste de client
     */
    public ArrayList<Medicament> findAll() {
        String requete = "SELECT * FROM medicament;";
        ArrayList<Medicament> medicaments   = new ArrayList<Medicament>();
        try {
            Statement s                   = connexion.createStatement();
            ResultSet result              = s.executeQuery(requete);
            ResultSetMetaData result_meta = result.getMetaData();
            while (result.next()) {
                Medicament m = new Medicament();
                m.setIdMedicament(result.getString("idmedicament"));
                m.setDenomination(result.getString("denomination"));
                m.setFormePharmaceutique(result.getString("formepharmaceutique"));
                m.setVoieAdministration(result.getString("voieadministration"));
                m.setMolecule(result.getString("molecule"));
                m.setPrixVente(result.getDouble("pvente"));
                m.setStockDisponible(result.getInt("stockdisponible"));
                medicaments.add(m);
            }
            result.close();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;
    }
}
