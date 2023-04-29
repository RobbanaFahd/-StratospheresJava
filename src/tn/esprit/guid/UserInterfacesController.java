/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.guid;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
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
    @FXML
    private Button pdfButton;

    /**
     * Initializes the controller class.
     *
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

    @FXML
    private void DownloadPDF(ActionEvent event) throws DocumentException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("banques_de_sang.pdf"));
            document.open();
            document.add(new Paragraph("Liste des banques de sang :"));
            document.add(new Paragraph("\n"));
            for (banquedesang banque : banqueData) {
                document.add(new Paragraph("Nom : " + banque.getNom()));
                document.add(new Paragraph("Adresse : " + banque.getAdresse()));
                document.add(new Paragraph("Téléphone : " + banque.getTel()));
                document.add(new Paragraph("Longitude : " + banque.getLongitude()));
                document.add(new Paragraph("Latitude : " + banque.getLatitude()));
                document.add(new Paragraph("\n"));
            }
            document.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF généré");
            alert.setHeaderText(null);
            alert.setContentText("Le PDF a été généré avec succès !");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de la génération du PDF");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la génération du PDF : " + e.getMessage());
            alert.showAndWait();
        }
    }

}
