package proyecto.controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML private ButtonBar btnBarClientes;
    @FXML private AnchorPane anchorPaneDerecha;
    @FXML private AnchorPane anchorPaneClientes;
    @FXML private AnchorPane anchorPaneCrearCliente;
    @FXML private Button btnLogin;
    @FXML private Button btnClientes;
    @FXML private Button btnCitas;
    @FXML private Button btnTratamientos;
    @FXML private Button btnSalir;
    @FXML private Button btnNuevo;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private TextField txtDNI;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido1;
    @FXML private TextField txtApellido2;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    // anchorPaneDerecha contiene -> btnBarClientes -> anchorPaneClientes -> anchorPaneCrearCliente
    // !!!!!!! PENDIENTE CREAR anchorPaneModificarCliente y anchorPaneEliminarCliente

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
        if (!anchorPaneCrearCliente.isVisible())
            anchorPaneCrearCliente.setVisible(true);
    }
}
