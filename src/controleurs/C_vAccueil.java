/*
 * C_vAccueil.java
 */
package controleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import vues.MyAlert;

import java.io.IOException;

import static ressources.txt.Textes_application.*;

/**
 * Controleur de la vue Accueil
 * @author Samuel Sicard
 * @version 0.9
 */
public class C_vAccueil {


    @FXML
    public Button btn_gest_client;
    @FXML
    public Button btn_gest_medicament;
    @FXML
    public Button btn_gest_pharmacien;
    @FXML
    public Button btn_gest_commande;
    @FXML
    public Button btn_gest_fermerapplication;


    /**
     * Remplace la scene d' /Accueil/ par celle de la gestion client
     * @param actionEvent evenement recu
     */
    public void ouvrirGestClient(ActionEvent actionEvent) {
        Parent root;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vGestClient.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(NOM_FENETRE_GESTION_CLIENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ouvrirGestMedicament(ActionEvent actionEvent) {
        Parent root;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vGestMedicament.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(NOM_FENETRE_GESTION_MEDICAMENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ouvrirGestPharmacien(ActionEvent actionEvent) {
        Parent root;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vGestPharmacien.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(NOM_FENETRE_GESTION_PHARMACIEN);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ouvrirGestCommande(ActionEvent actionEvent) {
        Parent root;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vGestCommande.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(NOM_FENETRE_GESTION_COMMANDE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void retourEcranConnecion(ActionEvent actionEvent) {
        Stage stage = ((Stage)((Node)actionEvent.getSource()).getScene().getWindow());
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vConnexion.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(NOM_FENETRE_CONNEXION);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void fermerApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        MyAlert myalert = new MyAlert(Alert.AlertType.CONFIRMATION, NOM_FENETRE_FERMETURE_APPLICATION, MESSAGE_FERMETURE_APPLICATION);
        if (myalert.showAndWait().get() == ButtonType.OK) {
            System.out.println(MESSAGE_CONSOLE_FERMETURE_APPLICATION);
            stage.close();
        }
    }
}
