package com.example.ecodesk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProfileUserController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField TFNombre;

    @FXML
    private TextField TFApellidoPaterno;

    @FXML
    private TextField TFApellidoMaterno;

    @FXML
    private TextField TFCorreo;

    @FXML
    private TextField TFNumeroTelefono;

    @FXML
    private Button guardarButton;

    @FXML
    private Button cerrarButton;

    @FXML
    public void handleImagenClick() {
        // Lógica cuando se hace clic en la imagen
        System.out.println("Clic en la imagen");
    }

    @FXML
    public void handleGuardarClick() {
        // Lógica cuando se hace clic en el botón de guardar
        System.out.println("Guardar datos");
    }

    @FXML
    public void handleCerrarClick() {
        // Lógica cuando se hace clic en el botón de cerrar sesión
        System.out.println("Cerrar sesión");
    }
}
