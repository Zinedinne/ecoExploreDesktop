package com.example.ecodesk;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InformationBitacoraController {

    private HostServices hostServices;

    @FXML
    private ImageView imageView;

    @FXML
    private Button BtnContactarGuía;

    @FXML
    private Label LBNombreBitacora;

    @FXML
    private Label LBDescripcion;

    @FXML
    private Label LBPuntuacion;

    @FXML
    private Label LBDificultad;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }


    @FXML
    void ContactarGuia(ActionEvent event) {
        String whatsappLink = "https://api.whatsapp.com/send?phone=+525565111269&text=%22Hola%20quiero%20explorar%22";
        abrirEnlace(whatsappLink);
    }

    private void abrirEnlace(String enlace) {
        if (hostServices != null) {
            hostServices.showDocument(enlace);
        } else {
            System.out.println("No se puede abrir el enlace, HostServices no está disponible.");
        }
    }
}
