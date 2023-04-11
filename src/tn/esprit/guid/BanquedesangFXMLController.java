/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.guid;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entity.banquedesang;
import tn.esprit.service.ServiceBanqueDeSang;

public class BanquedesangFXMLController implements Initializable {

    banquedesang b = new banquedesang();
    ServiceBanqueDeSang sb = new ServiceBanqueDeSang();
    private ObservableList<banquedesang> banqueData = FXCollections.observableArrayList();

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tflongitude;
    @FXML
    private TextField tfaltitude;
    @FXML
    private Button ajouter;
    @FXML
    private Label listlabel;
    @FXML
    private TableView<banquedesang> tablebanquedesang;
    @FXML
    private TableColumn<banquedesang, String> nameColumn;
    @FXML
    private TableColumn<banquedesang, String> addressColumn;
    @FXML
    private TableColumn<banquedesang, Integer> telColumn;
    @FXML
    private TableColumn<banquedesang, Float> longitudeColumn;
    @FXML
    private TableColumn<banquedesang, Float> latitudeColumn;
    @FXML
    private Button supprimer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        // Load the data from the database and add it to the table
        List<banquedesang> banque = sb.afficher();
        banqueData.addAll(banque);
        tablebanquedesang.setItems(banqueData);

    }

    @FXML
    private void ajouter(ActionEvent event) {
        b.setNom(tfnom.getText());
        b.setAdresse(tfadresse.getText());
        b.setTel(Integer.parseInt(tftel.getText()));
        b.setLongitude(Float.parseFloat(tflongitude.getText()));
        b.setLatitude(Float.parseFloat(tfaltitude.getText()));
        sb.ajouter(b);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        banquedesang crt = tablebanquedesang.getSelectionModel().getSelectedItem();

        if (crt == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une banque de sang dans le tableau!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette banque de sang : " + crt.getNom() + " ?");
            alert.setHeaderText(null);
            // Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            // Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ServiceBanqueDeSang sbds = new ServiceBanqueDeSang();
                sbds.supprimer(crt.getId(), crt);
                //updating user data after closing popup
                banqueData = FXCollections.observableList(sbds.afficher());
                tablebanquedesang.setItems(banqueData);

            }

        }
    }

}
