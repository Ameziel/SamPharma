/*
 * C_vGestPharmacien.java
 */
package controleurs;

import dao.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modeles.Client;
import modeles.Pharmacien;

import java.io.IOException;
import java.util.ArrayList;

import static ressources.txt.Textes_application.NOM_FENETRE_ACCUEIL;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class C_vGestPharmacien {


    @FXML
    public TableView<Pharmacien> tblview_gestPharmacien;
    @FXML
    public TableColumn<Pharmacien,String> tblcolumn_gestpharmacien_idpharmacien;
    @FXML
    public TableColumn<Pharmacien,String> tblcolumn_gestpharmacien_nom;
    @FXML
    public TableColumn<Pharmacien,String> tblcolumn_gestpharmacien_prenompharmacien;
    @FXML
    public TableColumn<Pharmacien,String> tblcolumn_gestpharmacien_mdppharmacien;
    @FXML
    public TableColumn<Pharmacien,String> tblcolumn_gestpharmacien_gsmpharmacien;
    @FXML
    public TableColumn<Pharmacien,String> tblcolumn_gestpharmacien_emailpharmacien;

    public void getItemSelected(MouseEvent mouseEvent) {
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

    public void rechercherPharmacien(ActionEvent actionEvent) {
    }

    public void afficherTousLesPharmaciens(ActionEvent actionEvent) {
        ArrayList<Pharmacien> pharmaciens = DAOFactory.getPharmacienDAO().findAll();
        ObservableList<Pharmacien> pharmaciens_list = FXCollections.observableArrayList(pharmaciens);
        for (int i = 0; i < pharmaciens.size(); i++) {
            tblcolumn_gestpharmacien_idpharmacien.setCellValueFactory((new PropertyValueFactory<>("idPharmacien")));
            tblcolumn_gestpharmacien_nom.setCellValueFactory((new PropertyValueFactory<>("nomPharmacien")));
            tblcolumn_gestpharmacien_prenompharmacien.setCellValueFactory((new PropertyValueFactory<>("prenomPharmacien")));
            tblcolumn_gestpharmacien_mdppharmacien.setCellValueFactory((new PropertyValueFactory<>("mdpPharmacien")));
            tblcolumn_gestpharmacien_gsmpharmacien.setCellValueFactory((new PropertyValueFactory<>("gsmPharmacien")));
            tblcolumn_gestpharmacien_emailpharmacien.setCellValueFactory((new PropertyValueFactory<>("emailPharmacien")));
        }
        tblview_gestPharmacien.setItems(pharmaciens_list);
    }

    public void ouvrirFenetreAjouterPharmacien(ActionEvent actionEvent) {
    }

    public void modifierPharmacien(ActionEvent actionEvent) {
    }

    public void supprimerPharmacien(ActionEvent actionEvent) {

    }
}
