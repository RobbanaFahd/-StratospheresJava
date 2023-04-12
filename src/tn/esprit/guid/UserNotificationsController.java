/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.guid;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.esprit.entity.notifications;
import tn.esprit.service.Servicenotifications;

/**
 * FXML Controller class
 *
 * @author Fahd
 */
public class UserNotificationsController implements Initializable {

    notifications n = new notifications();
    Servicenotifications sn = new Servicenotifications();
    private ObservableList<notifications> notificationsData = FXCollections.observableArrayList();
    private final String[] typesang = {"Type A", "Type O", "Type B"};

    @FXML
    private ChoiceBox<String> cbtypesang;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfmessage;
    @FXML
    private TextField tfsender;
    @FXML
    private TextField tfrecipient;
    @FXML
    private TableView<notifications> tablenotifications;
    @FXML
    private TableColumn<notifications, String> titleColumn;
    @FXML
    private TableColumn<notifications, String> messageColumn;
    @FXML
    private TableColumn<notifications, String> senderColumn;
    @FXML
    private TableColumn<notifications, String> recipientColumn;
    @FXML
    private TableColumn<notifications, String> typesangColumn;
    @FXML
    private Button bb;

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
        cbtypesang.setItems(FXCollections.observableArrayList(typesang));
        List<notifications> notifications = sn.afficher();
        notificationsData.addAll(notifications);
        tablenotifications.setItems(notificationsData);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String title = tftitle.getText();
        String message = tfmessage.getText();
        String recipient = tfrecipient.getText();
        String sender = tfsender.getText();
        String typesang = cbtypesang.getValue();

        // Input validation
        if (title.isEmpty() || message.isEmpty() || recipient.isEmpty() || sender.isEmpty() || typesang == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if (!isValidEmail(recipient)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid recipient email address", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if (!isValidEmail(sender)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid sender email address", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Create a new notification object and set its properties
        notifications n = new notifications();
        n.setTitle(title);
        n.setMessage(message);
        n.setRecipient(recipient);
        n.setSender(sender);
        n.setTypesang(typesang);

        // Call the service layer to add the notification to the database
        sn.ajouter(n);

        // Add the new notification to the table view
        notificationsData.add(n);

        // Clear the input fields
        tftitle.clear();
        tfmessage.clear();
        tfrecipient.clear();
        tfsender.clear();
        cbtypesang.getSelectionModel().clearSelection();
    }

    private boolean isValidEmail(String email) {
        // A simple regex pattern to validate email addresses
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    @FXML
    private void modifier(ActionEvent event) {
        notifications selectedNotification = tablenotifications.getSelectionModel().getSelectedItem();

        if (selectedNotification == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une notification dans le tableau !");
            alert.showAndWait();
        } else {
            Dialog<notifications> dialog = new Dialog<>();
            dialog.setTitle("Modifier la notification");
            dialog.setHeaderText("Modifier les informations de la notification");

            // Set the button types.
            ButtonType modifierButtonType = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

            // Create the labels and text fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField titleField = new TextField();
            titleField.setText(selectedNotification.getTitle());
            TextField messageField = new TextField();
            messageField.setText(selectedNotification.getMessage());
            TextField senderField = new TextField();
            senderField.setText(selectedNotification.getSender());
            TextField recipientField = new TextField();
            recipientField.setText(selectedNotification.getRecipient());
            ChoiceBox<String> typesangField = new ChoiceBox<>(FXCollections.observableArrayList(typesang));
            typesangField.getSelectionModel().select(selectedNotification.getTypesang());

            grid.add(new Label("Titre :"), 0, 0);
            grid.add(titleField, 1, 0);
            grid.add(new Label("Message :"), 0, 1);
            grid.add(messageField, 1, 1);
            grid.add(new Label("Expéditeur :"), 0, 2);
            grid.add(senderField, 1, 2);
            grid.add(new Label("Destinataire :"), 0, 3);
            grid.add(recipientField, 1, 3);
            grid.add(new Label("Type sanguin :"), 0, 4);
            grid.add(typesangField, 1, 4);

            dialog.getDialogPane().setContent(grid);

            // Convert the result to a notifications object when the modifier button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == modifierButtonType) {
                    notifications n = new notifications();
                    n.setId(selectedNotification.getId());
                    n.setTitle(titleField.getText());
                    n.setMessage(messageField.getText());
                    n.setSender(senderField.getText());
                    n.setRecipient(recipientField.getText());
                    n.setTypesang(typesangField.getValue());
                    return n;
                }
                return null;
            });

            Optional<notifications> result = dialog.showAndWait();

            if (result.isPresent()) {
                sn.modifier(result.get());
                // updating notification data after closing popup
                notificationsData.setAll(sn.afficher());
                tablenotifications.setItems(notificationsData);
            }
        }
    }


    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInterfaces.fxml"));
            root = loader.load();
            bb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
