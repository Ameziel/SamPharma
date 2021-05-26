/*
 * ScenariosTests.java
 */
package tests;

import dao.DAOFactory;
import exceptions.ErrCommandeConcernee;
import exceptions.ErrIdentifiantDejaExistant;
import exceptions.ErrObjetInexistant;
import modeles.Client;
import modeles.Medicament;
import modeles.Pharmacien;
import singleconnection.ConnectionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe de test de l'application. Sert à vérifier que le comportement de toutes les fonctions DAO sont correctes.
 * Note : Il faut lancer les tests etapes par etapes car il sera impossible de modifier un client qui n'est pas encore
 * mis a jours dans la base de donnees par exemple/
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class ScenariosTests {

    public static void main(String[] args) throws ErrIdentifiantDejaExistant, ErrObjetInexistant, ErrCommandeConcernee, SQLException {

        /**
         *  CLIENT
         * - 1.Création d'un client
         * - 1# Creation d'un client dans la BD
         * - 2.Modification de son nom et numéros de GSM
         * - 3.Suppression du client s'il n'a pas de commandes
         * - 4.Afficher un client par son ID
         * - 5.Afficher tous les clients
         *    ALL CHECKED
         */

        //1
//        Client client = new Client("samuel","sicard","0123456789","email@email.be");
//        System.out.println(client);
        //1#
//        Client client = new Client("samuel","sicard","0123456789","mail@be");
//        DAOFactory.getClientDAO().create(client);
        //2
//        Client client2 = DAOFactory.getClientDAO().find(client.getIdClient());
//        client2.setNomClient("sicardo"); client2.setNumGSM("0987654321");
//        DAOFactory.getClientDAO().update(client2);

        //3
//        DAOFactory.getClientDAO().delete("cliaurolefe01234");

        //4
//        Client client4 = DAOFactory.getClientDAO().find(client.getIdClient());
//        System.out.println(client2.toString());

        //5
        ArrayList<Client> clients = DAOFactory.getClientDAO().findAll();
        for(Client item : clients) {
            System.out.println(item);
        }


        System.out.println("---------------- FIN TEST CLIENTS ----------------");

        System.out.println("---------------- TEST PHARMACIEN ----------------");

        /**
         *  Pharmacien
         * - 1.Création d'un Pharmacien
         * - 1# Creation d'un client dans la BD
         * - 2.Modification de son nom et numéros de GSM
         * - 3.Suppression du Pharmacien s'il n'a pas de commandes
         * - 4.Afficher un Pharmacien par son ID
         * - 5.Afficher tous les Pharmacien
         *       ALL CHECKED !
         */

        //1
//        Pharmacien pharmacien = new Pharmacien("dumont", "antoine", "mdpantoine", "0456789123", "mail@antoine");
//        System.out.println(pharmacien);
        //1#
//        DAOFactory.getPharmacienDAO().create(pharmacien);

        //2
//        Pharmacien pharmacien2 = DAOFactory.getPharmacienDAO().find(pharmacien.getIdPharmacien());
//        pharmacien2.setNomPharmacien("sicardo"); pharmacien2.setGsmPharmacien("9999999999");
//        DAOFactory.getPharmacienDAO().update(pharmacien2);


        //3
//        DAOFactory.getPharmacienDAO().delete("phadumoanto04567");
//

        //4
//        Pharmacien pharmacien4 = DAOFactory.getPharmacienDAO().find("phasamusica01234");
//        System.out.println(pharmacien4.toString());

        //5
        ArrayList<Pharmacien> pharmaciens = DAOFactory.getPharmacienDAO().findAll();
        for (Pharmacien item : pharmaciens) {
            System.out.println(item);
        }


        System.out.println("---------------- FIN TEST PHARMACIEN ----------------");


        System.out.println("----------------  TEST Medicament ----------------");
        /**
         *  Medicament
         * - 1.Création d'un Medicament
         * - 1# Creation d'un client dans la BD
         * - 2.Suppression du Medicament s'il n'a pas de commandes
         * - 3.Afficher un Medicament par son ID
         * - 4.Afficher tous les Medicament
         *       ALL CHECKED !
         */

        //1
        Medicament medicament = new Medicament("62128152", "DOMPERIDONE ARROW 10 mg, comprimé orodispersible",
                "comprimé orodispersible", "orale", "atycyclique", 7.20);
//        System.out.println(medicament);
        //1#
//        DAOFactory.getMedicamentDAO().create(medicament);


        //3
//        DAOFactory.getMedicamentDAO().delete("67436923");
//

        //4
//        Medicament medicament4 = DAOFactory.getMedicamentDAO().find(medicament.getIdMedicament());
//        System.out.println(medicament4.toString());

        //5
//        ArrayList<Medicament> medicaments = DAOFactory.getMedicamentDAO().findAll();
//        for (Medicament item : medicaments) {
//            System.out.println(item);
//        }


        System.out.println("---------------- FIN TEST Medicament ----------------");

        System.out.println("---------------- TEST Commande ----------------");
        /**
         *  Commande
         * - 1.Création d'une Commande
         * - 2.Suppression de la Commande
         * - 3.Afficher une Commande par son ID
         * - 4.Afficher toutes les Commandes
         */
//        ajouterligne();
//        testrecupdate();
        //1
//        Commande commande = new Commande("cliarnoarna01234","phasamusica01234");
//        DAOFactory.getCommandeDAO().create(commande);

        //System.out.println(DAOFactory.getCommandeDAO().getLastIdCommande());
        //System.out.println(DAOFactory.getCommandeDAO().isCommandeExist(5));



        //2
        //DAOFactory.getCommandeDAO().delete(4);

        //3
        //DAOFactory.getCommandeDAO().find(4);

        //3#
        System.out.println(DAOFactory.getCommandeDAO().calculTotalCommande(3));


        //4
//        ArrayList<Commande> commandes = DAOFactory.getCommandeDAO().findAll();
//        for(Commande item : commandes) {
//            System.out.println(item);
//        }


        System.out.println("---------------- TEST Ligne Commande ----------------");
        /**
         *  Commande
         * - 1.Création d'une Ligne commande
         * - 2.Suppression de la Ligne Commande
         * - 3.Afficher une Ligne Commande par son ID
         * - 4.Afficher toutes les Commandes
         */
        //1
//        LigneCommande lignecommande = new LigneCommande(4, "62128302",2);
//        DAOFactory.getLigneCommandeDAO().create(lignecommande);

        //3
        //System.out.println(DAOFactory.getLigneCommandeDAO().find(1));



    }


    public static void ajouterligne() throws SQLException {
        Connection co = ConnectionBD.ouvrirConnection();
        String requete = "INSERT INTO tabletest (nom,date)" +
                "VALUES(?,SYSDATE());";
        PreparedStatement ps = co.prepareStatement(requete);
        ps.setString(1,"sam4");
        ps.executeUpdate();
        ps.close();
    }

    public static void testrecupdate() throws SQLException {
        Connection co = ConnectionBD.ouvrirConnection();
        String requete = "SELECT nom,date FROM tabletest ";
        Statement s = co.createStatement();
        ResultSet rs = s.executeQuery(requete);
        if(rs.first()) {
            LocalDate madate = rs.getObject("date", LocalDate.class);
            System.out.println(madate);
        }
    }
}
