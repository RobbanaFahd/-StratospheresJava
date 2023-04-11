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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
public void modifier(ActionEvent event) {
    notifications selectedNotification = tablenotifications.getSelectionModel().getSelectedItem();

    if (selectedNotification == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Cliquez sur une notification dans le tableau!");
        alert.showAndWait();
    } else {
        Dialog<notifications> dialog = new Dialog<>();
        dialog.setTitle("Modifier la notification");
        dialog.setHeaderText("Modifier les informations de la notification");

        // Set the button types.
        ButtonType modifierButtonType = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

        // Create the notification fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField titleField = new TextField();
        titleField.setText(selectedNotification.getTitle());
        TextField messageField = new TextField();
        messageField.setText(selectedNotification.getMessage());
        TextField recipientField = new TextField();
        recipientField.setText(selectedNotification.getRecipient());
        TextField senderField = new TextField();
        senderField.setText(selectedNotification.getSender());
        ChoiceBox<String> typesangField = new ChoiceBox<>(FXCollections.observableArrayList(typesang));
        typesangField.setValue(selectedNotification.getTypesang());

        grid.add(new Label("Titre:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Message:"), 0, 1);
        grid.add(messageField, 1, 1);
        grid.add(new Label("Destinataire:"), 0, 2);
        grid.add(recipientField, 1, 2);
        grid.add(new Label("Expéditeur:"), 0, 3);
        grid.add(senderField, 1, 3);
        grid.add(new Label("Type sanguin:"), 0, 4);
        grid.add(typesangField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == modifierButtonType) {
                notifications n = new notifications();
                n.setTitle(titleField.getText());
                n.setMessage(messageField.getText());
                n.setRecipient(recipientField.getText());
                n.setSender(senderField.getText());
                n.setTypesang(typesangField.getValue());
                return n;
            }
            return null;
        });

        Optional<notifications> result = dialog.showAndWait();

        result.ifPresent(notification -> {
            Servicenotifications sn = new Servicenotifications();
            sn.modifier(selectedNotification.getId(), notification);
            //updating notification data after closing popup
            notificationsData = FXCollections.observableList(sn.afficher());
            tablenotifications.setItems(notificationsData);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Modification avec succès");
            alert.showAndWait();
        });
    }
}


}
