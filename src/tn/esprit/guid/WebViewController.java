package tn.esprit.guid;

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

public class WebViewController implements Initializable {

    @FXML
    private AnchorPane mapPane;
    @FXML
    private WebView webView;

    private WebEngine engine;

    private final ServiceBanqueDeSang ServiceBanqueDeSang = new ServiceBanqueDeSang();

    private List<banquedesang> banquesDeSang;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("leaflet.html").toString());
        
    }
}
