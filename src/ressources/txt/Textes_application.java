/*
 * Textes_application.java
 */
package ressources.txt;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class Textes_application {

    /*************************************************
     *  General - Application et nom des fenetres
     *************************************************/

    public static final String NOM_APPLICATION = "SamPharma - ";

    public static final String NOM_FENETRE_CONNEXION = NOM_APPLICATION + "Connexion";

    public static final String NOM_FENETRE_INSCRIPTION_PHARMACIEN = "Inscription Pharmacien";

    public static final String NOM_FENETRE_ACCUEIL = NOM_APPLICATION + "Accueil";

    public static final String NOM_FENETRE_AJOUTER_CLIENT = NOM_APPLICATION + "Ajouter un nouveau client";

    public static final String NOM_FENETRE_AJOUTER_COMMANDE = NOM_APPLICATION +  "Ajouter une commande";

    public static final String NOM_FENETRE_AJOUTER_MEDICAMENT = NOM_APPLICATION + "Ajouter un medicament";

    public static final String NOM_FENETRE_GESTION_CLIENT = NOM_APPLICATION + "Gestion Client";

    public static final String NOM_FENETRE_GESTION_MEDICAMENT = NOM_APPLICATION + "Gestion Medicament";

    public static final String NOM_FENETRE_GESTION_COMMANDE = NOM_APPLICATION + "Gestion Commande";

    public static final String NOM_FENETRE_GESTION_PHARMACIEN = NOM_APPLICATION + "Gestion Pharmacien";

    public static final String TITRE_CONFIRMATION_SUPPRESSION = "Confirmation de la suppression";

    public static final String CONTENU_BOITE_CONFIRMATION_SUPPRESSION = "Confirmation de la suppression";

    public static final String MESSAGE_FERMETURE_APPLICATION = "Merci d'avoir utilise SamPharma\n A bientot";

    public static final String NOM_FENETRE_FERMETURE_APPLICATION = "Fermeture de SamPharma";

    public static final String MESSAGE_CONSOLE_FERMETURE_APPLICATION = "Fermeture de l'application reussie";



    /*************************************************
     *  ERREURS Generales
     *************************************************/

    public static final String ERR_CONTENU_BOITE_IDENTIFIANT_EXISTANT = "L'identifiant issue de vos donnees existe deja";

    public static final String ERR_TITRE_BOITE_IDENFIANT_EXISTANT = "Erreur, il y a un conflit pour cet identifiant";

    public static final String ERR_CONTENU_BOITE_AJOUT_OBJET = "Vérifiez que les champs sont corrects !\nProbleme de valeur ou champ vide";

    public static final String ERR_COMMANDE_CONCERNEE = "Suprresion impossible, l'objet' par des commandes existantes.|\nSupprimer d'abord les commandes.";

    public static final String ERR_OBJET_INEXISTANT = "L'objet selectionne n'existe pas";

    /*************************************************
     *  Fenetre Connexion (Start Application)
     *************************************************/
    public static final String ERR_TITRE_BOITE_CONNEXION = "Erreur : Connexion";

    public static final String ERR_CONTENU_BOITE_CONNEXION = "Les donnees saisies n'ont pas permis de vous authentifier.";

    public static final String ERR_CHAMPS_VIDES = "Veuillez remplir les champs";

    /*************************************************
     *  Fenetre Inscription Pharmacien
     *************************************************/

    public static final String ERR_TITRE_BOITE_INSCRIPTION = "Erreur : Inscription";

    public static final String BOITE_INSCRIPTION_PHARMACIEN_OK = "Ajout du pharmacien confirme !";

    public static final String CONTENU_BOITE_PHARMACIEN_OK = "Le pharmacien a ete ajoute avec success";


    /*************************************************
     *  Fenetre Ajouter Client
     *************************************************/


    public static final String ERR_TITRE_BOITE_AJOUTCLIENT = "Erreur : Ajout client";

    public static final String ERR_CLIENT_INEXISTANT = "Erreur, le client n'existe pas";

    public static final String BOITE_INSCRIPTION_CLIENT_OK = "Ajout du client confirme !";

    public static final String CONTENU_BOITE_CLIENT_OK = "Le client a ete ajoute avec success";

    /*************************************************
     *  Fenetre Ajouter Medicament
     *************************************************/

    public static final String BOITE_INSCRIPTION_MEDICAMENT_OK = "Ajout du medicament confirme !";

    public static final String CONTENU_BOITE_MEDICAMENT_OK = "Le medicament a ete ajoute avec success";







}
