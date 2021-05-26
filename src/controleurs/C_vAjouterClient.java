/*
 * C_vAjouterClient.java
 */
package controleurs;

import dao.DAOFactory;
import exceptions.ErrIdentifiantDejaExistant;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modeles.Client;
import vues.MyAlert;

import static ressources.txt.Textes_application.*;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class C_vAjouterClient {

    @FXML
    public TextField txtf_ajouterclient_nom;
    @FXML
    public TextField txtf_ajouterclient_prenom;
    @FXML
    public TextField txtf_ajouterclient_gsm;
    @FXML
    public TextField txtf_ajouterclient_email;
    @FXML
    public Button btn_ajouterclient_confirm;

    public void validerAjoutClient() {
        MyAlert myAlert;
        if (allChampsOk()) {
            try {
                Client client = new Client(txtf_ajouterclient_nom.getText(), txtf_ajouterclient_prenom.getText(), txtf_ajouterclient_gsm.getText(), txtf_ajouterclient_email.getText());
                DAOFactory.getClientDAO().create(client);
                myAlert = new MyAlert(Alert.AlertType.CONFIRMATION, BOITE_INSCRIPTION_CLIENT_OK, CONTENU_BOITE_CLIENT_OK);
                myAlert.afficherAlert();
            } catch (ErrIdentifiantDejaExistant e) {
                myAlert = new MyAlert(Alert.AlertType.CONFIRMATION, ERR_TITRE_BOITE_IDENFIANT_EXISTANT, ERR_CONTENU_BOITE_IDENTIFIANT_EXISTANT);
                myAlert.afficherAlert();
            }
        } else {
            myAlert = new MyAlert(Alert.AlertType.ERROR, ERR_TITRE_BOITE_INSCRIPTION, ERR_CONTENU_BOITE_AJOUT_OBJET);
            myAlert.afficherAlert();
        }
    }

    public boolean allChampsOk() {
        boolean nomOk = !txtf_ajouterclient_nom.getText().isEmpty();
        boolean prenomOk = !txtf_ajouterclient_prenom.getText().isEmpty();
        boolean gsmOk = !txtf_ajouterclient_gsm.getText().isEmpty();
        boolean emailOk = !txtf_ajouterclient_email.getText().isEmpty();
        return (nomOk && prenomOk && gsmOk && emailOk);
    }
}
