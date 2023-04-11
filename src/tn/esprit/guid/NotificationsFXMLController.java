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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entity.notifications;
import tn.esprit.service.Servicenotifications;

/**
 * FXML Controller class
 *
 * @author Fahd
 */
public class NotificationsFXMLController implements Initializable {

    notifications n = new notifications();
    Servicenotifications sn = new Servicenotifications();
    private ObservableList<notifications> notificationsData = FXCollections.observableArrayList();
    private String[] typesang = {"Type A", "Type O", "Type B"};

    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfmessage;
    @FXML
    private TextField tfrecipient;
    @FXML
    private TextField tfsender;
    @FXML
    private ChoiceBox<notifications> cbtypesang;
    @FXML
    private TableColumn<notifications, String> titleColumn;
    @FXML
    private TableColumn<notifications, String> messageColumn;
    @FXML
    private TableColumn<notifications, String> recipientColumn;
    @FXML
    private TableColumn<notifications, String> senderColumn;
    @FXML
    private TableColumn<notifications, String> typesangColumn;
    @FXML
    private TableView<notifications> tablenotifications;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        recipientColumn.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        typesangColumn.setCellValueFactory(new PropertyValueFactory<>("typesang"));

        // Load the data from the database and add it to the table
        List<notifications> notifications = sn.afficher();
        notificationsData.addAll(notifications);
        tablenotifications.setItems(notificationsData);

    }

    @FXML
    private void ajouter(ActionEvent event) {
        n.setTitle(tftitle.getText());
        n.setMessage(tfmessage.getText());
        n.setRecipient(tfrecipient.getText());
        n.setSender(tfsender.getText());
        n.setTypesang(cbtypesang.getValue().toString());
        sn.ajouter(n);
    }

    @FXML
    private void supprimer(ActionEvent event) {
notifications notif = tablenotifications.getSelectionModel().getSelectedItem();

    if (notif == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Cliquez sur une notification dans le tableau!");
        alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette notification : " + notif.getId() + " ?");
        alert.setHeaderText(null);
        //Getting Buttons
        Optional<ButtonType> result = alert.showAndWait();
        //Testing if the user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Servicenotifications sn = new Servicenotifications();
            sn.supprimer(notif);
            //updating notification data after closing popup
            notificationsData = FXCollections.observableList(sn.afficher());
            tablenotifications.setItems(notificationsData);
        }
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

}
