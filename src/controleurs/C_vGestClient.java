/*
 * C_vGestClient.java
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
public class C_vGestClient implements Initializable {


    @FXML
    public TextField txtf_gestclient_modifnom;
    @FXML
    public TextField txtf_gestclient_modifprenom;
    @FXML
    public TextField txtf_gestclient_modifemail;
    @FXML
    public TextField txtf_gestclient_modifgsm;


    @FXML
    private TableView<Client> tblview_gestClient;

    @FXML
    private TableColumn<Client, String> tblcolumn_gestclient_idclient;

    @FXML
    private TableColumn<Client, String> tblcolumn_gestclient_nom;

    @FXML
    private TableColumn<Client, String> tblcolumn_gestclient_prenom;

    @FXML
    private TableColumn<Client, String> tblcolumn_gestclient_gsm;

    @FXML
    private TableColumn<Client, String> tblcolumn_gestclient_email;

    @FXML
    private Button btn_gestclient_ajouterclient;

    public void afficherToutLesClients() {
        ArrayList<Client> clients = DAOFactory.getClientDAO().findAll();
        ObservableList<Client> clients_list = FXCollections.observableArrayList(clients);
        for (int i = 0; i < clients.size(); i++) {
            tblcolumn_gestclient_idclient.setCellValueFactory((new PropertyValueFactory<>("idClient")));
            tblcolumn_gestclient_nom.setCellValueFactory((new PropertyValueFactory<>("nomClient")));
            tblcolumn_gestclient_prenom.setCellValueFactory((new PropertyValueFactory<>("prenomClient")));
            tblcolumn_gestclient_gsm.setCellValueFactory((new PropertyValueFactory<>("numGSM")));
            tblcolumn_gestclient_email.setCellValueFactory((new PropertyValueFactory<>("email")));
        }
        tblview_gestClient.setItems(clients_list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initBtnAjouterClient();
    }

    private void initBtnAjouterClient() {
        ouvrirFenetreAjouterClient();
    }

    @FXML
    private void ouvrirFenetreAjouterClient() {
        btn_gestclient_ajouterclient.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("./vues/vAjouterClient.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(NOM_FENETRE_AJOUTER_CLIENT);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String id_client_selectionne;

    public void getItemSelected() {
        id_client_selectionne = tblview_gestClient.getSelectionModel().getSelectedItem().getIdClient(); // peut renvoyer null si aucun client. / ! \
    }
    public void supprimerClient() throws ErrObjetInexistant {
        MyAlert alert = new MyAlert(Alert.AlertType.CONFIRMATION, TITRE_CONFIRMATION_SUPPRESSION, CONTENU_BOITE_CONFIRMATION_SUPPRESSION);
        alert.afficherAlert();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                DAOFactory.getClientDAO().delete(id_client_selectionne);
                alert.close();
            } catch (ErrCommandeConcernee e) {
                alert.setHeaderText(ERR_COMMANDE_CONCERNEE);
            }
        } else {
            alert.close();
        }
    }

    /**
     * Modifie un client selectionne dans le resultat.
     */
    public void modifierClient() throws ErrObjetInexistant {
        Client c;
        if (allChampsOk() && id_client_selectionne != null) {
            c = new Client(txtf_gestclient_modifnom.getText(), txtf_gestclient_modifprenom.getText(), txtf_gestclient_modifgsm.getText(),
                    txtf_gestclient_modifemail.getText());
            c.setIdClient(id_client_selectionne);
            DAOFactory.getClientDAO().update(c);
        } else {
            System.out.println("Erreur"); // TODO faire une alert pop up
        }
    }

    public boolean allChampsOk() {
        boolean nomOk = !txtf_gestclient_modifnom.getText().isEmpty();
        boolean prenomOk = !txtf_gestclient_modifprenom.getText().isEmpty();
        boolean gsmOk = !txtf_gestclient_modifemail.getText().isEmpty();
        boolean emailOk = !txtf_gestclient_modifgsm.getText().isEmpty();
        return (nomOk && prenomOk && gsmOk && emailOk);
    }

    public void retourAccueil(ActionEvent actionEvent) {
        Parent root;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
