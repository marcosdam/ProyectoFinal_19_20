package proyecto.controladores;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    private BaseDatos controlDB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Crear BD
        controlDB = new BaseDatos();
    }
}
