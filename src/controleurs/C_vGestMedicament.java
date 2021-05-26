/*
 * C_vGestMedicament.java
 */
package controleurs;

import dao.DAOFactory;
import exceptions.ErrCommandeConcernee;
import exceptions.ErrObjetInexistant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modeles.Client;
import modeles.Medicament;
import vues.MyAlert;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static ressources.txt.Textes_application.*;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class C_vGestMedicament implements Initializable {

    @FXML
    public Button btn_gestmedicament_ajoutermedicament;

    @FXML
    public TableView<Medicament> tblview_gestMedicament;
    @FXML
    public TableColumn<Medicament, String> tblcolumn_gestmedicament_idmedicament;
    @FXML
    public TableColumn<Medicament, String> tblcolumn_gestmedicament_denomination;
    @FXML
    public TableColumn<Medicament, String> tblcolumn_gestmedicament_formepharmaceutique;
    @FXML
    public TableColumn<Medicament, String> tblcolumn_gestmedicament_voieadministration;
    @FXML
    public TableColumn<Medicament, String> tblcolumn_gestmedicament_molecule;
    @FXML
    public TableColumn<Medicament, Double> tblcolumn_gestmedicament_pvente;
    @FXML
    public TableColumn<Medicament, Integer> tblcolumn_gestmedicament_stockdisponible;





    public void afficherTousLesMedicaments(ActionEvent actionEvent) {
        ArrayList<Medicament> medicaments = DAOFactory.getMedicamentDAO().findAll();
        ObservableList<Medicament> medicaments_list = FXCollections.observableArrayList(medicaments);
        for (int i = 0; i < medicaments.size(); i++) {
            tblcolumn_gestmedicament_idmedicament.setCellValueFactory((new PropertyValueFactory<>("idMedicament")));
            tblcolumn_gestmedicament_denomination.setCellValueFactory((new PropertyValueFactory<>("denomination")));
            tblcolumn_gestmedicament_formepharmaceutique.setCellValueFactory((new PropertyValueFactory<>("formePharmaceutique")));
            tblcolumn_gestmedicament_voieadministration.setCellValueFactory((new PropertyValueFactory<>("voieAdministration")));
            tblcolumn_gestmedicament_molecule.setCellValueFactory((new PropertyValueFactory<>("molecule")));
            tblcolumn_gestmedicament_pvente.setCellValueFactory((new PropertyValueFactory<>("prixVente")));
            tblcolumn_gestmedicament_stockdisponible.setCellValueFactory((new PropertyValueFactory<>("stockDisponible")));
        }
        tblview_gestMedicament.setItems(medicaments_list);
    }

    public void rechercherMedicament(ActionEvent actionEvent) {
    }



    public void retourAccueil(ActionEvent actionEvent) {
        Parent root;
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vAccueil.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(NOM_FENETRE_ACCUEIL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ouvrirFenetreAjouterMedicament();
    }

    public void ouvrirFenetreAjouterMedicament() {
        btn_gestmedicament_ajoutermedicament.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vAjouterMedicament.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(NOM_APPLICATION + NOM_FENETRE_AJOUTER_MEDICAMENT);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    String id_medicament_selectionne;
    public void getItemSelected(MouseEvent mouseEvent) {
        id_medicament_selectionne = tblview_gestMedicament.getSelectionModel().getSelectedItem().getIdMedicament();
    }

    public void supprimerMedicament(ActionEvent actionEvent) {
        MyAlert alert = new MyAlert(Alert.AlertType.CONFIRMATION, TITRE_CONFIRMATION_SUPPRESSION, CONTENU_BOITE_CONFIRMATION_SUPPRESSION);
        alert.afficherAlert();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                DAOFactory.getMedicamentDAO().delete(id_medicament_selectionne);
                alert.close();
            } catch (ErrCommandeConcernee | ErrObjetInexistant e) {
                alert.setHeaderText(ERR_COMMANDE_CONCERNEE);
            }
        } else {
            alert.close();
        }
    }
}
