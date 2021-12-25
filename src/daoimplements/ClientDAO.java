/*
 * ClientDAO.java
 */
package daoimplements;

import dao.DAO;
import exceptions.*;
import modeles.Client;

import java.sql.*;
import java.util.ArrayList;


/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class ClientDAO implements DAO<Client, String> {

    /**
     * Connexion utilise
     */
    private Connection connexion;

    /**
     * Constante statique d'une valeur erronee
     */
    private static final int ERROR_VALUE = -1;

    public ClientDAO(Connection connexion) {
        this.connexion = connexion;
    }

    /**
     * Créer un client en base de données à partir d'un client temporaire tout en vérifiant que ce client
     * est bien unique, et donc n'existe pas déjà.
     * @param arg_obj Un Client
     */
    @Override
    public void create(Client arg_obj) throws ErrIdentifiantDejaExistant {
        if (!isClientExist(arg_obj.getIdClient())) {
            String requete = "INSERT INTO client (idclient,nom,prenom,gsm,email)" +
                    "VALUES (?,?,?,?,?);";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_obj.getIdClient());
                ps.setString(2, arg_obj.getNomClient());
                ps.setString(3, arg_obj.getPrenomClient());
                ps.setString(4, arg_obj.getNumGSM());
                ps.setString(5, arg_obj.getEmail());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace(); //TODO Faire exception car ici quun seul affichage
            }
        } else {
            throw new ErrIdentifiantDejaExistant();
        }
    }

    /**
     * Met à jours les donnees relatives à un client.
     * @param arg_obj Un client
     */
    @Override
    public void update(Client arg_obj) throws ErrObjetInexistant {
        if (isClientExist(arg_obj.getIdClient())) {
            String requete = "UPDATE client SET nom = ?,    prenom = ?,    gsm = ?,    email = ?   WHERE idclient = ?;";
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_obj.getNomClient());
                ps.setString(2, arg_obj.getPrenomClient());
                ps.setString(3, arg_obj.getNumGSM());
                ps.setString(4, arg_obj.getEmail());
                ps.setString(5, arg_obj.getIdClient());
                int nb_result = ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new ErrObjetInexistant();
        }
    }

    /**
     * @param arg_id : L'identifiant du client
     * @return True si le client existe, False sinon
     */
    private boolean isClientExist(String arg_id) {
        String requete = "SELECT count(*)  AS clienttotal FROM client WHERE idclient = ?;";
        int retour = 0;
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setString(1, arg_id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                retour = result.getInt("clienttotal");
            }
            result.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (retour == 1);
    }


    @Override
    public void delete(String arg_id) throws ErrObjetInexistant, ErrCommandeConcernee {
        if (isClientExist(arg_id)) {
            int nb_commandes = nombreCommandePassees(arg_id);
            if (nb_commandes > 0) {
                throw new ErrCommandeConcernee();
            } else {
                String requete = "DELETE FROM client WHERE idclient = ?";
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


    /**
     * Retourne le nombre de commande passé pour un idcliant.
     * @param arg_id L'identifiant du client
     * @return Un entier qui est le nombre de commande passees par le client
     */
    public int nombreCommandePassees(String arg_id) throws ErrObjetInexistant {
        if (isClientExist(arg_id)) {
            String requete = "SELECT count(*)  AS total FROM commande WHERE refclient = ?;";
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
    public Client find(String arg_id) throws ErrObjetInexistant {
        Client client;
        if (isClientExist(arg_id)) {
            String requete = "SELECT * FROM client where idclient = ? ;";
            client = new Client();
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.setString(1, arg_id);
                ResultSet result = ps.executeQuery();
                ResultSetMetaData result_meta = result.getMetaData();
                while (result.next()) {
                    client.setIdClient(result.getString("idclient"));
                    client.setNomClient(result.getString("nom"));
                    client.setPrenomClient(result.getString("prenom"));
                    client.setNumGSM(result.getString("gsm"));
                    client.setEmail(result.getString("email"));
                }
                result.close();
                ps.close();
                return client;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new ErrObjetInexistant();
        }
        return client;
    }

    /**
     * Retourne les clients de l'application.
     *
     * @return Une liste de client
     */
    public ArrayList<Client> findAll() {
        String requete = "SELECT * FROM client;";
        ArrayList<Client> clients = new ArrayList<Client>();
        try {
            Statement s = connexion.createStatement();
            ResultSet result = s.executeQuery(requete);
            while (result.next()) {
                Client c = new Client();
                c.setIdClient(result.getString("idclient"));
                c.setNomClient(result.getString("nom"));
                c.setPrenomClient(result.getString("prenom"));
                c.setNumGSM(result.getString("gsm"));
                c.setEmail(result.getString("email"));
                clients.add(c);
            }
            result.close();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}