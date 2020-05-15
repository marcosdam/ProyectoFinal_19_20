package proyecto.controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML private AnchorPane anchorPaneClientes;
    @FXML private AnchorPane anchorPaneCrearCliente;

    private BaseDatos controlDB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Crear BD
        controlDB = new BaseDatos();

        // Todas las "ventanas" se inicializan invisibles
        anchorPaneClientes.setVisible(false);
        anchorPaneCrearCliente.setVisible(false);
    }

    /**
     * Función que cierra el programa
     * @param event
     **/
    public void onBotonSalirClicked(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Función para activar AnchorPane sección Clientes
     * @param event
     **/
    public void onBotonClientesClicked(MouseEvent event){
        if (!anchorPaneClientes.isVisible())
            anchorPaneClientes.setVisible(true);
    }
    /**
     * Función para activar AnchorPane sección Clientes
     * @param event
     **/
    public void onBotonNuevoClicked(MouseEvent event){
        if (!anchorPaneClientes.isVisible())
            anchorPaneClientes.setVisible(true);
    }
}
