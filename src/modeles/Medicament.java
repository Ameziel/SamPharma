/*
 * Medicament.java
 */
package modeles;

/**
 * Classe pour représenter un médicament.
 * Le médicament est le produit central de l'application.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class Medicament {
    /**
     * Identifiant médicament
     */
    private String idMedicament;

    /**
     * Dénomination courante
     */
    private String denomination;

    /**
     * Forme pharmaceutique
     *
     * @see <a href="https://fr.wikipedia.org/wiki/Forme_gal%C3%A9nique#Formes_solides">Informations complémentaires</a>
     */
    private String formePharmaceutique;

    /**
     * Précise la voie utilisé pour administrer le médicament. (Orale, sous-cutanée...)
     */
    private String voieAdministration;

    /**
     * Dénomination du principe actif contenu dans le médicament
     */
    private String molecule;

    /**
     * Prix auquel le médicament est revendu au client
     */
    private double prixVente;

    /**
     * Nombre d'exemplaire possédés dans le stock
     */
    private int stockDisponible;

    /**
     * Stock par défaut au départ
     */
    public static final int STOCK_INITIAL = 100;

    public Medicament() {

    }

    /**
     * @param arg_idMedicament        : Une série unique de 10 chiffres.
     * @param arg_denomination        : Appellation courante.
     * @param arg_formePharmaceutique : Décrit la forme sous laquelle se présente le médicament.
     * @param arg_voieAdministration  : Décrit la manière d'administrer le médicament.
     * @param arg_molecule            : Nom de la molécule du principe actif.
     * @param arg_prixVente           : Prix à la revente au client.
     */
    public Medicament(String arg_idMedicament, String arg_denomination, String arg_formePharmaceutique,
                      String arg_voieAdministration, String arg_molecule, double arg_prixVente) {
        this.idMedicament = arg_idMedicament;
        this.denomination = arg_denomination;
        this.formePharmaceutique = arg_formePharmaceutique;
        this.voieAdministration = arg_voieAdministration;
        this.molecule = arg_molecule;
        this.stockDisponible = STOCK_INITIAL;
        if (checkMontantCorrect(arg_prixVente)) {
            this.prixVente = arg_prixVente;
        } else {
            this.prixVente = 0.0;
        }
    }

    public String getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(String idMedicament) {
        this.idMedicament = idMedicament;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getFormePharmaceutique() {
        return formePharmaceutique;
    }

    public void setFormePharmaceutique(String formePharmaceutique) {
        this.formePharmaceutique = formePharmaceutique;
    }

    public String getVoieAdministration() {
        return voieAdministration;
    }

    public void setVoieAdministration(String voieAdministration) {
        this.voieAdministration = voieAdministration;
    }

    public String getMolecule() {
        return molecule;
    }

    public void setMolecule(String molecule) {
        this.molecule = molecule;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "idMedicament='" + idMedicament + '\'' +
                ", denomination='" + denomination + '\'' +
                ", formePharmaceutique='" + formePharmaceutique + '\'' +
                ", voieAdministration='" + voieAdministration + '\'' +
                ", molecule='" + molecule + '\'' +
                ", prixVente=" + prixVente +
                ", stockDisponible=" + stockDisponible +
                '}';
    }

    /* La valeur d'un produit a l'achat ou a la vente doit être un nombre positif */
    private boolean checkMontantCorrect(double arg_pvente) {
        return arg_pvente >= 0.0;
    }

}