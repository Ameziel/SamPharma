/*
 * LigneTableViewCommande.java
 */
package controleurs;


/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class LigneTableViewCommande {


    private String idmedicament;

    private String denomination;

    private String quantite;

    private String prixunitaire;

    private String soustotal;

    public LigneTableViewCommande(String idmedicament, String denomination, String quantite, String prixunitaire, String soustotal) {
        this.idmedicament = idmedicament;
        this.denomination = denomination;
        this.quantite = quantite;
        this.prixunitaire = prixunitaire;
        this.soustotal = soustotal;
    }

    public String getIdmedicament() {
        return idmedicament;
    }

    public void setIdmedicament(String idmedicament) {
        this.idmedicament = idmedicament;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(String prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public String getSoustotal() {
        return soustotal;
    }

    public void setSoustotal(String soustotal) {
        this.soustotal = soustotal;
    }
}
