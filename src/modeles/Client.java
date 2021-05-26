/*
 * Client.java
 */
package modeles;

import java.util.ArrayList;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class Client {

    /**
     * Préfixe associé par défaut à l'identifiant d'un client
     */
    private final static String PREFIXE_CLIENT = "cli";

    /**
     * Taille de découpage pour le nom et prénom afin de construire l'identifiant
     */
    private final static int TAILLE_DECOUPAGE = 4;

    /**
     * Identifiant client
     */
    public String idClient = "";

    /**
     * Nom client
     */
    private String nomClient;

    /**
     * Prénom client
     */
    private String prenomClient;

    /**
     * Numéro de contact téléphonique
     */
    private String numGSM;

    /**
     * Email de contact
     */
    private String email;

    /**
     * La liste des commandes qu'un client a passe.
     */
    private ArrayList<Commande> commandesClients;


    /**
     * Contructeur par defaut
     */
    public Client() {
    }

    /**
     * Constructeur parametre d'un client
     *
     * @param arg_nomClient    : Nom a donner au client
     * @param arg_prenomClient : Prénom a donner au client
     * @param arg_numGSM       : Numéro de contact téléphonique
     * @param arg_email        : Email de contact client
     */
    public Client(String arg_nomClient, String arg_prenomClient,
                  String arg_numGSM, String arg_email) {
        this.nomClient = arg_nomClient;
        this.prenomClient = arg_prenomClient;
        this.numGSM = arg_numGSM;
        this.email = arg_email;
        constructionIdentifiantClient();
        this.commandesClients = new ArrayList<>();
    }

    /**
     * @param arg_nomClient : Nouveau nom du client
     */
    public void setNomClient(String arg_nomClient) {
        this.nomClient = arg_nomClient;
    }

    /**
     * @return Le nom du client
     */
    public String getNomClient() {
        return nomClient;
    }
    //---------------------------------------------------

    /**
     * @param arg_prenomClient : Nouveau prenom du client
     */
    public void setPrenomClient(String arg_prenomClient) {
        this.prenomClient = arg_prenomClient;
    }

    /**
     * @return Prénom du client
     */
    public String getPrenomClient() {
        return this.prenomClient;
    }
    //---------------------------------------------------

    /**
     * @param arg_numGSM : Attribue un nouveau numéro de gsm
     */
    public void setNumGSM(String arg_numGSM) {
        this.numGSM = arg_numGSM;
    }

    /**
     * @return Le numéro du gsm client
     */
    public String getNumGSM() {
        return this.numGSM;
    }
    //---------------------------------------------------

    /**
     * @param arg_email : Attribue une nouvelle adresse email de contact
     */
    public void setEmail(String arg_email) {
        this.email = arg_email;
    }

    /**
     * @return L'adresse email pour contacter le client
     */
    public String getEmail() {
        return this.email;
    }
    //---------------------------------------------------

    /**
     * @param arg_idclient Attribue un nouveau identifiant client
     */
    public void setIdClient(String arg_idclient) {
        this.idClient = arg_idclient;
    }

    /**
     * @return L'identifiant du clib sous forme de chaine de caracteres
     */
    public String getIdClient() {
        return this.idClient;
    }

    /**
     * @param arg_commande : Est ajoute a la liste des commandes du client
     */
    public void ajouterCommande(Commande arg_commande) {
        commandesClients.add(arg_commande);
    }

    /**
     * @param arg_commande : Est retire de la liste des commandes du client
     */
    public void supprimerCommande(Commande arg_commande) {
        commandesClients.remove(arg_commande);
    }

    /**
     * Supprime l'entierete des commandes du clients
     */
    public void viderCommandeClient() {
        commandesClients.clear();
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient='" + idClient + '\'' +
                ", nomClient='" + nomClient + '\'' +
                ", prenomClient='" + prenomClient + '\'' +
                ", numGSM='" + numGSM + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Génère l'identifiant à partir des informations saisies
     *
     * @return Un identifiant pour le client
     */
    private void constructionIdentifiantClient() {
        StringBuilder resultat = new StringBuilder(PREFIXE_CLIENT);
        resultat.append(this.prenomClient.subSequence(0, TAILLE_DECOUPAGE));
        resultat.append(this.nomClient.subSequence(0, TAILLE_DECOUPAGE));
        resultat.append(this.numGSM.subSequence(0, 5));
        this.idClient = resultat.toString();
    }
}
