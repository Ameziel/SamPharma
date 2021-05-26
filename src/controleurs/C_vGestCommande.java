/*
 * C_vGestCommande.java
 */
package controleurs;

import dao.DAOFactory;
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
import modeles.Commande;
import vues.MyAlert;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class C_vGestCommande implements Initializable {

    @FXML
    public Button btn_gestcommande_ajoutercommande;
    @FXML
    public TableView<Commande> tblview_gestCommande;
    @FXML
    public TableColumn<Commande, Integer> tblcolumn_gestccommande_idcommande;
    @FXML
    public TableColumn<Commande, String> tblcolumn_gestcommande_refclient;
    @FXML
    public TableColumn<Commande, String> tblcolumn_gestcommande_refpharmacien;
    @FXML
    public TableColumn<Commande, LocalDate> tblcolumn_gestcommande_date;
    @FXML
    public TableColumn<Commande, Double> tblcolumn_gestcommande_montant;


    @FXML
    public Button btn_gestcommande_affichercommandes;
    @FXML
    public Button btn_gestcommande_supprimercommande;
    @FXML
    public Button btn_gestcommande_retourAccueil;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initBtnOuvrirFenetreAjouterCommande();
    }

    public void initBtnOuvrirFenetreAjouterCommande() {
        btn_gestcommande_ajoutercommande.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vAjouterCommande.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(NOM_APPLICATION + NOM_FENETRE_AJOUTER_COMMANDE);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void afficherToutesLesCommandes(ActionEvent actionEvent) {
        ArrayList<Commande> commandes = DAOFactory.getCommandeDAO().findAll();
        ObservableList<Commande> commandes_list = FXCollections.observableArrayList(commandes);
        for (int i = 0; i < commandes.size(); i++) {
            tblcolumn_gestccommande_idcommande.setCellValueFactory((new PropertyValueFactory<>("idCommande")));
            tblcolumn_gestcommande_refclient.setCellValueFactory((new PropertyValueFactory<>("refClient")));
            tblcolumn_gestcommande_refpharmacien.setCellValueFactory((new PropertyValueFactory<>("refPharmacien")));
            tblcolumn_gestcommande_date.setCellValueFactory((new PropertyValueFactory<>("date")));
            tblcolumn_gestcommande_montant.setCellValueFactory((new PropertyValueFactory<>("montanttotal")));
        }
        tblview_gestCommande.setItems(commandes_list);
    }

    public void rechercherCommande(ActionEvent actionEvent) {
    }

    private Integer id_commande_selectionne;

    public void getItemSelected(MouseEvent mouseEvent) {
        id_commande_selectionne = tblview_gestCommande.getSelectionModel().getSelectedItem().getIdCommande();
    }

    public void supprimerCommande(ActionEvent actionEvent) throws SQLException, ErrObjetInexistant {
        MyAlert alert = new MyAlert(Alert.AlertType.CONFIRMATION, TITRE_CONFIRMATION_SUPPRESSION, CONTENU_BOITE_CONFIRMATION_SUPPRESSION);
        alert.afficherAlert();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
                DAOFactory.getCommandeDAO().delete(id_commande_selectionne);
                alert.close();
        } else {
            alert.close();
        }
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
}
