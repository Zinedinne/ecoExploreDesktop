package com.example.ecodesk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LandingPageController {

    @FXML
    private TextField TFBuscar;

    @FXML
    private Button BTNBucar;

    @FXML
    private Label LBNombreBitacora;

    @FXML
    private Label LBDescripcionBitacora;

    @FXML
    private Label LBPuntuacionBitacora;

    @FXML
    private ImageView ImgBitacora;

    @FXML
    private Button BTNAnterior;

    @FXML
    private Button BTNSiguiente;

    @FXML
    private void Buscar(ActionEvent event) {
        // Lógica para manejar el evento de búsqueda
        System.out.println("Buscar clickeado");
    }

    @FXML
    private void Siguiente(ActionEvent event) {
        // Lógica para manejar el evento de siguiente
        System.out.println("Botón Siguiente clickeado");
    }

    @FXML
    private void Anterior(ActionEvent event) {
        // Lógica para manejar el evento de anterior
        System.out.println("Botón Anterior clickeado");
    }


}
