/*
 * Pharmacien.java
 */
package modeles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Classe pour représenter un pharmacien dans notre application.
 * Il est l'objet principal puisque c'est lui qui est en charge de la majorité des fonctionnalités
 * de l'application :
 * <ul>
 *     <li>passer des commandes</li>
 *     <li>d'imprimer une facture</li>
 * </ul>
 * passer des commandes
 * d'imprimer une facture
 * </p>
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class Pharmacien {

    /**
     * Préfixe associé par défaut à l'identifiant d'un pharmacien
     */
    private final static String PREFIXE_PHARMACIEN = "pha";

    /**
     * Valeur utilisée pour extraire le nombre de caractères du nom et prénom afin de construire l'identifiant
     */
    private final static int TAILLE_DECOUPAGE = 4;

    /**
     * Stub pour le role de responsable
     */
    public final static String ROLE_DEFAULT = "responsable";

    /**
     * Identifiant pharmacien
     */
    private String idPharmacien = "";

    /**
     * Nom pharmacien
     */
    private String nomPharmacien;

    /**
     * Prénom pharmacien.
     */
    private String prenomPharmacien;

    /**
     * Mot de passe du pharmacien
     */
    private String mdpPharmacien;

    /**
     * Numero de telephone mobile du pharmacien
     */
    private String gsmPharmacien;

    /**
     * Adresse email de contact pour un pharmacien
     **/
    private String emailPharmacien;

    /**
     * La liste des commandes qu'un pharmacien a enregistre.
     */
    private ArrayList<Commande> commandesPharmacien;

    public Pharmacien() {

    }

    /**
     * Constructeur par défaut pour un Pharmacien.
     *
     * @param arg_prenomPharmacien : Prenom attribue pharmacien.
     * @param arg_nomPharmacien    : Nom attribue au pharmacien.
     * @param arg_mdp              : Mot de passe attribué au compte du pharmacien.
     * @param arg_gsm              : Numero de telephone associe au pharmacien.
     * @param arg_email            : E-mail du pharmacien.
     */
    public Pharmacien(String arg_prenomPharmacien, String arg_nomPharmacien, String arg_mdp, String arg_gsm, String arg_email) {
        this.prenomPharmacien    = arg_prenomPharmacien;
        this.nomPharmacien       = arg_nomPharmacien;
        this.mdpPharmacien       = arg_mdp;
        this.gsmPharmacien       = arg_gsm;
        this.emailPharmacien     = arg_email;
        this.idPharmacien        = constructionIdentifiantPharmacien();
        this.commandesPharmacien = new ArrayList<>();
    }

    /**
     * @return L'identifiant du pharmacien.
     */
    public String getIdPharmacien() {
        return this.idPharmacien;
    }


    public void setIdPharmacien(String idPharmacien) {
        this.idPharmacien = idPharmacien;
    }


    /**
     * @param arg_prenomPharmacien : Le nouveau prenom
     */
    public void setPrenomPharmacien(String arg_prenomPharmacien) {
        this.prenomPharmacien = arg_prenomPharmacien;
    }

    /**
     * @return Le prénom du pharmacien
     */
    public String getPrenomPharmacien() {
        return this.prenomPharmacien;
    }

    /**
     * @param arg_nomPharmacien : Le nouveau nom
     */
    public void setNomPharmacien(String arg_nomPharmacien) {
        this.nomPharmacien = arg_nomPharmacien;
    }

    /**
     * @return Le nom du pharmacien
     */
    public String getNomPharmacien() {
        return this.nomPharmacien;
    }

    /**
     * @param arg_mdpPharmacien : Nouveau mot de passe
     */
    public void setMdpPharmacien(String arg_mdpPharmacien) {
        this.mdpPharmacien = arg_mdpPharmacien;
    }

    /**
     * @return Le mot de passe du pharmacien.
     */
    public String getMdpPharmacien() {
        return this.mdpPharmacien;
    }

    /**
     * @param arg_gsm : Le nouvel e-mail
     */
    public void setGsmPharmacien(String arg_gsm) {
        this.gsmPharmacien = arg_gsm;
    }

    /**
     * @return L'adresse e-mail du pharmacien
     */
    public String getGsmPharmacien() {
        return this.gsmPharmacien;
    }

    /**
     * @param arg_email : Le nouvel e-mail
     */
    public void setEmailPharmacien(String arg_email) {
        this.emailPharmacien = arg_email;
    }

    /**
     * @return L'adresse e-mail du pharmacien
     */
    public String getEmailPharmacien() {
        return this.emailPharmacien;
    }

    //---------------------------------------------------
    //                  COMMANDES
    //---------------------------------------------------

    /**
     * Supprime l'entierete des commandes du pharmacien
     */
    public void viderCommande() {
        commandesPharmacien.clear();
    }

    /**
     * @param arg_commande : Est ajoute a la liste des commandes du pharmacien
     */
    public void ajouterCommande(Commande arg_commande) {
        commandesPharmacien.add(arg_commande);
    }

    /**
     * @param arg_commande : Est retire de la liste des commandes du pharmacien
     */
    public void supprimerCommande(Commande arg_commande) {
        commandesPharmacien.remove(arg_commande);
    }

    @Override
    public String toString() {
        return "Pharmacien{" +
                "idPharmacien='" + idPharmacien + '\'' +
                ", nomPharmacien='" + nomPharmacien + '\'' +
                ", prenomPharmacien='" + prenomPharmacien + '\'' +
                ", gsm='" + gsmPharmacien + '\'' +
                ", emailPharmacien='" + emailPharmacien + '\'' +
                '}';
    }

    /* Génère l'identifiant à partir des informations saisies */
    public String constructionIdentifiantPharmacien() {
        StringBuilder resultat = new StringBuilder(PREFIXE_PHARMACIEN);
        resultat.append(this.prenomPharmacien.subSequence(0, TAILLE_DECOUPAGE));
        resultat.append(this.nomPharmacien.subSequence(0, TAILLE_DECOUPAGE));
        resultat.append(this.gsmPharmacien.subSequence(0, 5));
        return resultat.toString();
    }
}
