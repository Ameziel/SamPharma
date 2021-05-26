/**
 * C_vConnexion.java
 */
package controleurs;

import dao.DAOFactory;
import daoimplements.PharmacienDAO;
import exceptions.ErrIdentificationPharmacien;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modeles.Pharmacien;
import vues.MyAlert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ressources.txt.Textes_application.*;

/**
 * Controleur de la vue vConnexion
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class C_vConnexion extends Application implements Initializable {


    @FXML
    public TextField txtf_connexion_indentifiant;
    @FXML
    public PasswordField passwdf_connexion_password;
    @FXML
    public Button btn_connexion_inscription;
    @FXML
    public Button btn_connexion_connexion;

    public static String id_pharmacien;

    /**
     * Methode principale de l'application
     *
     * @param args non utilise
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initBtnouvrirFenetreInscription();
    }


    private void initBtnouvrirFenetreInscription() {
        btn_connexion_inscription.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vInscription.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(NOM_APPLICATION + NOM_FENETRE_INSCRIPTION_PHARMACIEN);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../vues/vConnexion.fxml"));
        stage.setTitle(NOM_FENETRE_CONNEXION);
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void verifLogin(ActionEvent actionEvent) {
        //ouvrirFenetreAccueil((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()); // pour zapper la co
        MyAlert monalerte;
        if (saisieLoginOk()) {
            try {
                PharmacienDAO pharmacien = DAOFactory.getPharmacienDAO();
                pharmacien.login(txtf_connexion_indentifiant.getText().toLowerCase().trim(), passwdf_connexion_password.getText());
                id_pharmacien = txtf_connexion_indentifiant.getText().toLowerCase().trim();
                ouvrirFenetreAccueil((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
            } catch (ErrIdentificationPharmacien e) {
                monalerte = new MyAlert(Alert.AlertType.INFORMATION, ERR_TITRE_BOITE_CONNEXION, ERR_CONTENU_BOITE_CONNEXION);
                monalerte.afficherAlert();
            }
        } else {
            monalerte = new MyAlert(Alert.AlertType.WARNING, ERR_TITRE_BOITE_CONNEXION, ERR_CHAMPS_VIDES);
            monalerte.afficherAlert();
        }
    }

    public void ouvrirFenetreAccueil(Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vAccueil.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(NOM_FENETRE_ACCUEIL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Verifie que les champs contiennent au moins une valeur.
     */
    private boolean saisieLoginOk() {
        String id = txtf_connexion_indentifiant.getText().toLowerCase().trim();
        String password = passwdf_connexion_password.getText().toLowerCase().trim();
        System.out.println(!id.isEmpty() && !password.isEmpty());
        return !id.isEmpty() && !password.isEmpty();
    }
}
