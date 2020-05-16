package proyecto.controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import proyecto.modelos.Cita;
import proyecto.modelos.Cliente;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML private Button btnLogin;
    @FXML private Button btnClientes;
    @FXML private AnchorPane anchorPaneDerecha;

    // MENÚ CLIENTES
    @FXML private AnchorPane anchorPaneZonaClientes;
    // CLIENTES -> CREAR CLIENTE
    @FXML private AnchorPane anchorPaneCrearCliente;
    @FXML private TextField txtDNI;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido1;
    @FXML private TextField txtApellido2;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;

    // CLIENTES -> MODIFICAR CLIENTE
    @FXML private AnchorPane anchorPaneModificarCliente;
    @FXML private ComboBox<String> cbModificarCliente;
    @FXML private Button btnOK;
    @FXML private TextField txtNuevoNombre;
    @FXML private TextField txtNuevoApellido1;
    @FXML private TextField txtNuevoApellido2;
    @FXML private TextField txtNuevoTelefono;
    @FXML private TextField txtNuevoEmail;
    @FXML private Button btnGuardarCambios;

    // CLIENTES -> ELIMINAR CLIENTE
    // (Va a usar el mismo que modificar cliente, cambiando texto de btnGuardar "Guardar" por "Eliminar"

    // MENÚ CITAS
    @FXML private AnchorPane anchorPaneZonaCitas;
    // CITAS -> CREAR CITA
    @FXML private AnchorPane anchorPaneCrearCita;
    @FXML private TextField txtNombreCita;
    @FXML private TextField txtApellidoCita;
    @FXML private TextField txtFechaCita;
    @FXML private TextField txtHoraCita;
    @FXML private RadioButton rbLavadoSecado;
    @FXML private RadioButton rbCorte;
    @FXML private RadioButton rbTinte;
    @FXML private Button btnGuardarCita;
    @FXML private Button btnCancelarCita;

    // CITAS -> MODIFICAR CITA
    @FXML private AnchorPane anchorPaneModificarCita;
    // CITAS -> ELIMINAR CITA
    @FXML private AnchorPane anchorPaneEliminarCita;

    @FXML private Button btnCitas;
    @FXML private Button btnTratamientos;
    @FXML private Button btnSalir;
    @FXML private Button btnEliminar;

    private BaseDatos controlDB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Crear BD
        controlDB = new BaseDatos();

        // Todas las "ventanas" se inicializan invisibles
        anchorPaneZonaClientes.setVisible(false);
        anchorPaneCrearCliente.setVisible(false);
        anchorPaneModificarCliente.setVisible(false);
        anchorPaneZonaCitas.setVisible(false);
        anchorPaneCrearCita.setVisible(false);
        //anchorPaneModificarCita.setVisible(false);
        //anchorPaneEliminarCita.setVisible(false);

        // Rellenar comboBox
        //cbModificarCliente.setItems();

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
     * Función para limpiar al pulsar Cancelar en crearCliente
     * @param event
     **/
    public void onBotonCancelarClicked(MouseEvent event){
        limpiarCrearCliente();
    }


    /**
     * Función que recoge los datos de la interfaz y crea el Objeto Cliente
     * @return  Objeto Cliente creado
     */
    private Cliente rellenarCliente(){
        Cliente cliente = new Cliente();
        cliente.setCodCliente(1);           // MODIFICAR ESTO controlarlo de otra forma
        cliente.setDni(txtDNI.getText());
        cliente.setNombre(txtNombre.getText());
        cliente.setApellido1(txtApellido1.getText());
        cliente.setApellido2(txtApellido2.getText());
        cliente.setTelefono(txtTelefono.getText());
        cliente.setEmail(txtEmail.getText());

        return cliente;
    }

    /**
     * Función para guardar el Nuevo Cliente en la BD
     * @param event
     */
    public void guardarCliente(MouseEvent event){
        Cliente cliente = rellenarCliente();
        try {
            Connection connection = controlDB.getConnection();
            boolean guardado = controlDB.crearCliente(connection, cliente);
            connection.close();
            if (guardado){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("GUARDAR CLIENTE");
                alert.setContentText("Cliente creado y guardado");
                alert.showAndWait();
                limpiarCrearCliente();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL GUARDAR CLIENTE");
                alert.setContentText("Error al guardar en la Base de Datos");
                alert.showAndWait();
            }

        } catch (SQLException throwables){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR BASE DE DATOS");
            alert.setContentText("Error al conectar a la Base de Datos");
            alert.showAndWait();
        }
    }

    public void actualizarCliente(MouseEvent event){}
    public void eliminarCliente(MouseEvent event){}

    /**
     * Función para limpiar los campos de crearCliente
     */
    private void limpiarCrearCliente() {
        txtDNI.clear();
        txtNombre.clear();
        txtApellido1.clear();
        txtApellido2.clear();
        txtTelefono.clear();
        txtEmail.clear();
    }

    /**
     * Función para limpiar los campos de crearCita
     */
    private void limpiarCrearCita() {
        txtNombreCita.clear();
        txtApellidoCita.clear();
        txtFechaCita.clear();
        txtHoraCita.clear();
        rbLavadoSecado.setSelected(false);
        rbCorte.setSelected(false);
        rbTinte.setSelected(false);
    }

    /**
     * Función para ocultar la zona de clientes (al pulsar Citas o Tratamientos)
     */
    public void ocultarZonaClientes(MouseEvent event){
        // PENDIENTE CREAR UN BOTON VOLVER
        if(anchorPaneZonaClientes.isVisible())
            anchorPaneZonaClientes.setVisible(false);
    }

    /**
     * Función que recoge los datos de la interfaz y crea el Objeto Cita
     * @return  Objeto Cita creado
     */
    private Cita rellenarCita(){
        String fechaCita = txtFechaCita.getText();
        String trozos[] = fechaCita.split("/");
        int dia = Integer.parseInt(trozos[0]);
        int mes = Integer.parseInt(trozos[1]);
        int anyo = Integer.parseInt(trozos[2]);
        LocalDate dateFecha = LocalDate.of(dia, mes, anyo);

        Cita cita = new Cita();
        cita.setCodCita(1); // MODIFICAR ESTO controlarlo de otra forma
        cita.setFecha(dateFecha);
        cita.setHora(txtHoraCita.getText());

        return cita;
    }

    public void guardarCita(MouseEvent event){
        Cita cita = rellenarCita();
        try {
            Connection connection = controlDB.getConnection();
            boolean guardada = controlDB.crearCita(connection, cita);
            connection.close();
            if (guardada){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("GUARDAR CITA");
                alert.setContentText("Cita creada y guardada");
                alert.showAndWait();
                limpiarCrearCita();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR AL GUARDAR CITA");
                alert.setContentText("Error al guardar en la Base de Datos");
                alert.showAndWait();
            }

        } catch (SQLException throwables){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR BASE DE DATOS");
            alert.setContentText("Error al conectar a la Base de Datos");
            alert.showAndWait();
        }
    }

    public void actualizarCita(MouseEvent event){}
    public void eliminarCita(MouseEvent event){}


    // CONTROL VENTANAS ZONA CLIENTES
    /**
     * Función para activar AnchorPane Zona Clientes
     * @param event
     **/
    public void mostrarZonaClientes(MouseEvent event){
        if (!anchorPaneZonaClientes.isVisible())
            anchorPaneZonaClientes.setVisible(true);
        anchorPaneZonaCitas.setVisible(false);
    }

    /**
     * Función para activar AnchorPane sección Clientes
     * @param event
     **/
    public void mostrarZonaCrearCliente(MouseEvent event){
        if (!anchorPaneCrearCliente.isVisible())
            anchorPaneCrearCliente.setVisible(true);
        anchorPaneModificarCliente.setVisible(false);
    }

    public void mostrarZonaModificarCliente(MouseEvent event){
        if (!anchorPaneModificarCliente.isVisible())
            anchorPaneModificarCliente.setVisible(true);
        anchorPaneCrearCliente.setVisible(false);
        btnGuardarCambios.setText("Guardar");
    }

    /**
     * Función que reutiliza la "ventana" de moficiar cliente con variaciones
     * @param event
     */
    public void mostrarZonaEliminarCliente(MouseEvent event){
        if (!anchorPaneModificarCliente.isVisible())
            anchorPaneModificarCliente.setVisible(true);
        anchorPaneCrearCliente.setVisible(false);
        btnGuardarCambios.setText("Eliminar Cliente");
    }


    // CONTROL VENTANAS ZONA CITAS
    public void mostrarZonaCitas(MouseEvent event){
        if (!anchorPaneZonaCitas.isVisible())
            anchorPaneZonaCitas.setVisible(true);
        anchorPaneZonaClientes.setVisible(false);
    }

    public void mostrarZonaCrearCita(MouseEvent event){
        if (!anchorPaneCrearCita.isVisible())
            anchorPaneCrearCita.setVisible(true);
        anchorPaneModificarCita.setVisible(false);
        anchorPaneEliminarCita.setVisible(false);
    }

    public void mostrarZonaModificarCita(MouseEvent event){
        if (!anchorPaneModificarCita.isVisible())
            anchorPaneModificarCita.setVisible(true);
        anchorPaneCrearCita.setVisible(false);
        anchorPaneEliminarCita.setVisible(false);
    }

    public void mostrarZonaEliminarCita(MouseEvent event){
        if (!anchorPaneEliminarCita.isVisible())
            anchorPaneEliminarCita.setVisible(true);
        anchorPaneCrearCita.setVisible(false);
        anchorPaneModificarCita.setVisible(false);
    }
}
