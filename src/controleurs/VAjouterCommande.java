/*
 * VAjouterCommande.java
 */
package controleurs;

import dao.DAOFactory;
import exceptions.ErrObjetInexistant;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modeles.Client;
import modeles.Commande;
import modeles.Medicament;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class description goes here.
 *
 * @author Samuel Sicard
 * @version 0.9
 */
public class VAjouterCommande implements Initializable {


    @FXML
    public ComboBox cmbbox_ajoutercommande_idclient;
    @FXML
    public ComboBox cmbbox_ajoutercommande_idmedicament;
    @FXML
    public ComboBox cmbbox_ajoutercommande_quantitemedicament;
    @FXML
    public Button btn_ajoutercommande_ajouterlignecommande;

    @FXML
    public TableView<LigneTableViewCommande> tblview_ajoutercommande;

    @FXML
    public TableColumn<LigneTableViewCommande, String> tblcolumn_gestCommande_idmedicament;
    @FXML
    public TableColumn<LigneTableViewCommande, String> tblcolumn_gestCommande_denomination;
    @FXML
    public TableColumn<LigneTableViewCommande, String> tblcolumn_gestCommande_quantite;
    @FXML
    public TableColumn<LigneTableViewCommande, String> tblcolumn_gestCommande_prixunitaire;
    @FXML
    public TableColumn<LigneTableViewCommande, String> tblcolumn_gestCommande_soustotal;


    private ArrayList<Medicament> medicaments;

    private ArrayList<Client> clients;

    private LigneTableViewCommande ltvc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chargerMedicaments();
        chargerIdClients();
        chargerQuantite();
    }

    private void chargerQuantite() {
        for(int i = 1 ; i < 10; i++) {
            cmbbox_ajoutercommande_quantitemedicament.getItems().add(i);
        }
    }

    private void chargerIdClients() {
        clients = DAOFactory.getClientDAO().findAll();
        for(Client item : clients) {
            cmbbox_ajoutercommande_idclient.getItems().add(item.getIdClient());
        }
    }

    private void chargerMedicaments() {
        medicaments = DAOFactory.getMedicamentDAO().findAll();
        for(Medicament item : medicaments) {
            cmbbox_ajoutercommande_idmedicament.getItems().add(item.getIdMedicament() + " " + item.getDenomination());
        }
    }

    @FXML
    public Label lbl_ajoutercommande_total;

    Double total_toutes_lignes = 0.0d;

    public void ajouterLigneCommande() throws ErrObjetInexistant {
        //idmedicament  denomination quantite prixunitaire soustotal
        // il faut la créer et les garder en stock pour ensuite juste ajouter la commande quand on click
        //sur ok TODO (penser à la vidéo sur excel)
        String idmedicament_combo       = cmbbox_ajoutercommande_idmedicament.getValue().toString().substring(0,8);
        String denomination             = DAOFactory.getMedicamentDAO().find(idmedicament_combo).getDenomination();
        Integer quantite_combo          = Integer.parseInt(cmbbox_ajoutercommande_quantitemedicament.getValue().toString());
        Double prix_unitaire            = DAOFactory.getMedicamentDAO().find(idmedicament_combo).getPrixVente();
        Double soustotal                = quantite_combo * prix_unitaire;

        //calcul du sous total et affichage
        total_toutes_lignes += soustotal;
        lbl_ajoutercommande_total.setText(total_toutes_lignes.toString());

        ltvc = new LigneTableViewCommande(idmedicament_combo,denomination,quantite_combo.toString(),prix_unitaire.toString(),soustotal.toString());

        tblcolumn_gestCommande_idmedicament.setCellValueFactory((new PropertyValueFactory<>("idmedicament")));
        tblcolumn_gestCommande_denomination.setCellValueFactory((new PropertyValueFactory<>("denomination")));
        tblcolumn_gestCommande_quantite.setCellValueFactory((new PropertyValueFactory<>("quantite")));
        tblcolumn_gestCommande_prixunitaire.setCellValueFactory((new PropertyValueFactory<>("prixunitaire")));
        tblcolumn_gestCommande_soustotal.setCellValueFactory((new PropertyValueFactory<>("soustotal")));

        tblview_ajoutercommande.getItems().add(ltvc);
    }

    public void ajouterCommande() {
        //Creer la commande
        Commande commande = new Commande((String) cmbbox_ajoutercommande_idclient.getValue(),C_vConnexion.id_pharmacien);
        //Creer les lignes de commandes


    }
}
