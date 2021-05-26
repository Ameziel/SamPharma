/*
 * Commande.java
 */
package modeles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Classe qui implemente l'utilisation des commandes.
 * Une commande est passe par un client, enregistree par un pharmacien et comprends des lignes
 * qui representent quel medicament a ete commande et en quelle quentite
 * @author Samuel Sicard
 * @version 0.9
 */

public class Commande {

    /** Prefixe associé par défaut à l'identifiant d'une commande */
    private final static String PREFIXE_COMMANDE = "com";

    /** Valeur utilisée pour extraire le nombre de caractères du nom et prénom afin de construire l'identifiant */
    private final static int TAILLE_DECOUPAGE = 3;

    /** Numéro de commande unique */
    private Integer idCommande;

    /** Référence au client associé */
    private String refClient;

    /** Référence au pharmacien qui a validé la commande */
    private String refPharmacien;

    /** Date à laquelle la commande a été passée */
    private LocalDate date;

    /** Lignes de commandes associees */
    private ArrayList<LigneCommande> ensembleDesLignes;

    /** Montant total de la commande */
    private double montanttotal;

    /**
     * Constructeur par defaut
     */
    public Commande() {

    }

    /**
     * Constructeur pour insérer une commande dans la base de données
     * @param arg_refClient      : Reference du client qui ordonne la commande
     * @param arg_refPharmacien  : Reference du pharmacien qui enregistre la commande
     */
    public Commande(String arg_refClient, String arg_refPharmacien) {
        this.refClient          = arg_refClient;
        this.refPharmacien      = arg_refPharmacien;
        ensembleDesLignes       = new ArrayList<>();
    }

    /**
     * Constructeur pour récupérer une commande dans la base de données
     * @param arg_refClient      : Reference du client qui ordonne la commande
     * @param arg_refPharmacien  : Reference du pharmacien qui enregistre la commande
     */
    public Commande(Integer arg_idcommande, String arg_refClient, String arg_refPharmacien, LocalDate arg_date) {
        this.idCommande         = arg_idcommande;
        this.refClient          = arg_refClient;
        this.refPharmacien      = arg_refPharmacien;
        this.date               = arg_date;
        ensembleDesLignes       = new ArrayList<>();
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public String getRefClient() {
        return refClient;
    }

    public void setRefClient(String refClient) {
        this.refClient = refClient;
    }

    public String getRefPharmacien() {
        return refPharmacien;
    }

    public void setRefPharmacien(String refPharmacien) {
        this.refPharmacien = refPharmacien;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(double montanttotal) {
        this.montanttotal = montanttotal;
    }

    public ArrayList<LigneCommande> getEnsembleDesLignes() {
        return ensembleDesLignes;
    }

    public void setEnsembleDesLignes(ArrayList<LigneCommande> ensembleDesLignes) {
        this.ensembleDesLignes = ensembleDesLignes;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", refClient='" + refClient + '\'' +
                ", refPharmacien='" + refPharmacien + '\'' +
                ", date=" + date +
                '}';
    }

    //---------------------------------------------------


}
