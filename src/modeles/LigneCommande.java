/*
 * LigneCommande.java
 */
package modeles;

/**
 * Classe ligne de commande. Une ligne de commande se rattache à une commande.
 * Plusieurs lignes de commandes peuvent faire partie d'une même commande si elle ne concerne pas le même medicament
 * @author Samuel Sicard
 * @version 0.9
 */
public class LigneCommande {

    /** Idenfiant de la commande a laquelle la ligne est attribuee */
    private Integer idlignecommande;

    /** Idenfiant de la commande a laquelle la ligne est attribuee */
    private Integer refCommande;

    /** Identifiant du medicament concerne pour la ligne */
    private String refMedicament;

    /** Quantite desire pour ce medicament */
    private int quantite;

    /** Prix de la ligne */
    private double prix;

    /**
     * Constructeur par defaut
     */
    public LigneCommande() {

    }

    /**
     * Constructeur pour inserer une ligne de commande
     * @param arg_refCommande   Reference de la commande a laquelle la ligne est associe
     * @param arg_refMedicament Reference du medicament concernee par la ligne de comande
     * @param arg_quantite      Quantite du medicament selectionne
     */
    public LigneCommande(Integer arg_refCommande, String arg_refMedicament, int arg_quantite) {
        this.refCommande        = arg_refCommande;
        this.refMedicament      = arg_refMedicament;
        this.quantite           = arg_quantite;
    }

    /**
     * Constructeur pour récupérer une ligne de commande
     * @param arg_idlignecommande : identifiant de la ligne de commande
     * @param arg_refCommande     : Reference de la commande a laquelle la ligne est associe
     * @param arg_refMedicament   : Reference du medicament concernee par la ligne de comande
     * @param arg_quantite        : Quantite du medicament selectionne
     */
    public LigneCommande(Integer arg_idlignecommande, Integer arg_refCommande, String arg_refMedicament, int arg_quantite) {
        this.idlignecommande    = arg_idlignecommande;
        this.refCommande        = arg_refCommande;
        this.refMedicament      = arg_refMedicament;
        this.quantite           = arg_quantite;
    }

    /** @param arg_refCommande Reference de l'objet commande associe. */
    public void setRefCommande(Integer arg_refCommande) {
        this.refCommande = arg_refCommande;
    }

    /** @return : La reference de l'objet commande */
    public Integer getRefCommande() {
        return refCommande;
    }

    /** @param refMedicament : Medicament concerne par la ligne de commande */
    public void setRefMedicament(String refMedicament) {
        this.refMedicament = refMedicament;
    }

    /** @return Le medicament correspondant */
    public String getRefMedicament() {
        return refMedicament;
    }

    /** @param quantite : Nombre d'occurence du medicaments choisit */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /** @return Retourne la quantite desire du medicament */
    public int getQuantite() {
        return quantite;
    }


    /** @return Retourne l'identifiant de la ligne commande */
    public Integer getIdlignecommande() { return idlignecommande; }

    /** @param : Lidentifiant de la ligne commande */
    public void setIdlignecommande(Integer idlignecommande) {
        this.idlignecommande = idlignecommande;
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "idlignecommande=" + idlignecommande +
                ", refCommande=" + refCommande +
                ", refMedicament=" + refMedicament +
                ", quantite=" + quantite +
                '}';
    }
}
