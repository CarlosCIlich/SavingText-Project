package co.edu.univalle.poe.mvc.controller;

import co.edu.univalle.poe.mvc.model.Estado;
import co.edu.univalle.poe.mvc.model.Record;
import co.edu.univalle.poe.mvc.model.RecordJugador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecordController {
    @FXML
    private TextField txtFieldNombre;
    @FXML
    private TextField txtFieldPuntaje;
    @FXML
    private Button btnGuardar;

    private Record record;
    private Estado estado;

    public RecordController(){
        record = new Record();
    }

    @FXML
    public void guardarDatos(){
        estado = record.saveRecord(txtFieldNombre.getText(),
                                   Integer.parseInt(txtFieldPuntaje.getText()));
        mostrarMensaje(estado);
    }
    @FXML
    public void cargarDatos(){
        RecordJugador recordJugador = record.loadRecord();
        txtFieldNombre.setText(recordJugador.getNombre());
        txtFieldPuntaje.setText(Integer.toString(recordJugador.getPuntaje()));
    }

    private void mostrarMensaje(Estado estado){
        Alert alerta;
        switch (estado){
            case GUARDADO_OK:
                alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Guardado Exitoso");
                alerta.setHeaderText("Datos Guardados en el Archivo.");
                alerta.setContentText("Se ha guardado el nombre y puntaje exitosamente.");
                alerta.showAndWait();
                limpiarTextField();
                break;
            case CARGADO_ERROR:
                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERror al guardar datos");
                alerta.setHeaderText("No se han podido guardar datos en el archivo.");
                alerta.setContentText("Se ha presentado una falla al intentar acceder al archivo.");
                alerta.showAndWait();
                limpiarTextField();
                break;
            case GUARDADO_ERROR:
                break;
        }
    }

    private void limpiarTextField(){
        txtFieldNombre.clear();
        txtFieldPuntaje.clear();
    }

}
