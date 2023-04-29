package tn.esprit.guid;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tn.esprit.entity.banquedesang;
import tn.esprit.service.ServiceBanqueDeSang;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class WebViewController implements Initializable {

    @FXML
    private AnchorPane mapPane;
    @FXML
    private WebView webView;

    private WebEngine engine;

    private final ServiceBanqueDeSang ServiceBanqueDeSang = new ServiceBanqueDeSang();

    private List<banquedesang> banquesDeSang;
    @FXML
    private Button bb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("leaflet.html").toString());
        
        

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("maininterfaces.fxml"));
            root = loader.load();
            bb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
