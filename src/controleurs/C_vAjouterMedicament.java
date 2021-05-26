/*
 * C_vAjouterMedicament.java
 */
package controleurs;

import dao.DAOFactory;
import exceptions.ErrIdentifiantDejaExistant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modeles.Medicament;
import vues.MyAlert;

import java.net.URL;
import java.util.ResourceBundle;

import static ressources.txt.Textes_application.*;

/**
 * Controller de la vue vAjouterMedicament
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class C_vAjouterMedicament implements Initializable {

    @FXML
    public TextField txtf_ajoutermedicament_idmedicament;
    @FXML
    public TextField txtf_ajoutermedicament_denomination;
    @FXML
    public TextField txtf_ajoutermedicament_formepharmaceutique;
    @FXML
    public TextField txtf_ajoutermedicament_molecule;
    @FXML
    public ComboBox cmbx_ajoutermedicament_voieadministration;
    @FXML
    public TextField txtf_ajoutermedicament_pvente;
    @FXML
    public TextField txtf_ajoutermedicament_stockdisopnible;


    public void validerAjoutMedicament() {
        MyAlert myAlert;
        if (allChampsOk()) {
            try {
                Medicament medicament = new Medicament(txtf_ajoutermedicament_idmedicament.getText(),
                        txtf_ajoutermedicament_denomination.getText(),
                        txtf_ajoutermedicament_formepharmaceutique.getText(),
                        cmbx_ajoutermedicament_voieadministration.getSelectionModel().getSelectedItem().toString(),
                        txtf_ajoutermedicament_molecule.getText(),
                        Double.parseDouble(txtf_ajoutermedicament_pvente.getText()));
                DAOFactory.getMedicamentDAO().create(medicament);
                myAlert = new MyAlert(Alert.AlertType.CONFIRMATION, BOITE_INSCRIPTION_MEDICAMENT_OK, CONTENU_BOITE_MEDICAMENT_OK);
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

        boolean idOk = !txtf_ajoutermedicament_idmedicament.getText().isEmpty();
        boolean denominationOk = !txtf_ajoutermedicament_denomination.getText().isEmpty();
        boolean formepharmaceutiqueOk = !txtf_ajoutermedicament_formepharmaceutique.getText().isEmpty();
        boolean moleculeOk = !txtf_ajoutermedicament_molecule.getText().isEmpty();
        boolean voieadministrationOk = !cmbx_ajoutermedicament_voieadministration.getSelectionModel().isEmpty();
        boolean pventeOk = !txtf_ajoutermedicament_pvente.getText().isEmpty() && (Double.parseDouble(txtf_ajoutermedicament_pvente.getText()) > 0.0);


        return (idOk && denominationOk && formepharmaceutiqueOk && moleculeOk && voieadministrationOk && pventeOk);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list_voieadministration = FXCollections.observableArrayList("orale", "inhalee", "sous-cutanee");
        cmbx_ajoutermedicament_voieadministration.setItems(list_voieadministration);

    }
}
