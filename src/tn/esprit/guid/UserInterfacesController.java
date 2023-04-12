/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.guid;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entity.banquedesang;
import tn.esprit.service.ServiceBanqueDeSang;

/**
 * FXML Controller class
 *
 * @author Fahd
 */
public class UserInterfacesController implements Initializable {
        banquedesang b = new banquedesang();
    ServiceBanqueDeSang sb = new ServiceBanqueDeSang();
    private ObservableList<banquedesang> banqueData = FXCollections.observableArrayList();

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
    private Button bb;
    @FXML
    private TableView<banquedesang> tablebanquedesang;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
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
    private void shownotifications(ActionEvent event) {
        try {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserNotifications.fxml"));
            root = loader.load();
            bb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
