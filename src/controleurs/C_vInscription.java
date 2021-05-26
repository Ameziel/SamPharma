/*
 * C_vInscription.java
 */
package controleurs;

import dao.DAOFactory;
import daoimplements.PharmacienDAO;
import exceptions.ErrIdentifiantDejaExistant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modeles.Pharmacien;
import vues.MyAlert;

import static ressources.txt.Textes_application.*;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class C_vInscription {



    @FXML
    public TextField txtf_inscription_nom;
    @FXML
    public TextField txtf_inscription_prenom;
    @FXML
    public PasswordField passwdf_inscription_password;
    @FXML
    public PasswordField passwdf_inscription_passwordconfirm;
    @FXML
    public TextField txtf_inscription_gsm;
    @FXML
    public TextField txtf_inscription_email;
    @FXML
    public Button btn_inscription_raz;
    @FXML
    public Button btn_inscription_confirm;

    public void viderChamps(ActionEvent actionEvent) {
        txtf_inscription_nom.setText("");
        txtf_inscription_prenom.setText("");
        passwdf_inscription_password.setText("");
        passwdf_inscription_passwordconfirm.setText("");
        txtf_inscription_gsm.setText("");
        txtf_inscription_email.setText("");


    }

    public void validerInscription(ActionEvent actionEvent) {
        MyAlert myAlert;
        if (allChampsOk()) {
            try {
                Pharmacien pharmacien = new Pharmacien(txtf_inscription_prenom.getText(),txtf_inscription_nom.getText(),
                        passwdf_inscription_password.getText(), txtf_inscription_gsm.getText(), txtf_inscription_email.getText());
                PharmacienDAO pharmacienDao = DAOFactory.getPharmacienDAO();
                pharmacienDao.create(pharmacien);
                myAlert = new MyAlert(Alert.AlertType.CONFIRMATION,BOITE_INSCRIPTION_PHARMACIEN_OK,CONTENU_BOITE_PHARMACIEN_OK);
                myAlert.setContentText("L'identifiant de votre compte est : " + pharmacien.getIdPharmacien());
                myAlert.afficherAlert();
            } catch (ErrIdentifiantDejaExistant e) {
                myAlert = new MyAlert(Alert.AlertType.CONFIRMATION,ERR_TITRE_BOITE_IDENFIANT_EXISTANT,ERR_CONTENU_BOITE_IDENTIFIANT_EXISTANT);
                myAlert.afficherAlert();
            }
        } else {
             myAlert = new MyAlert(Alert.AlertType.ERROR,ERR_TITRE_BOITE_INSCRIPTION,ERR_CONTENU_BOITE_AJOUT_OBJET);
             myAlert.afficherAlert();
        }
    }

    /**
     * Verifie que tous les champs ne sont pas vides
     * @return vrai si les champs contiennent des valeurs et que les mots de passes saisies sont egaux.
     */
    public boolean allChampsOk() {
        boolean nomOk               = !txtf_inscription_nom.getText().isEmpty();
        boolean prenomOk            = !txtf_inscription_prenom.getText().isEmpty();
        boolean passwordOk          = !passwdf_inscription_password.getText().isEmpty();
        boolean passwordOkConfirm   = !passwdf_inscription_passwordconfirm.getText().isEmpty();
        boolean gsmOk               = !txtf_inscription_gsm.getText().isEmpty();
        boolean emailOk             = !txtf_inscription_email.getText().isEmpty();
        return (nomOk && prenomOk && passwordOk && passwordOkConfirm && gsmOk && emailOk) && (passwdf_inscription_password.getText().equals(passwdf_inscription_passwordconfirm.getText()));
    }
}
