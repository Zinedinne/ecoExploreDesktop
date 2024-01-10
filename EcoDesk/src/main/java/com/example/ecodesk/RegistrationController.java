package com.example.ecodesk;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegistrationController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidoPaternoField;

    @FXML
    private TextField apellidoMaternoField;

    @FXML
    private TextField correoField;

    @FXML
    private TextField telefonoField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repetirPasswordField;

    @FXML
    private ImageView myImageView;  // Debes tener un ImageView con este ID en tu FXML.

    @FXML
    public void initialize() {
        // Cargar la imagen y asignarla al ImageView.
        Image image = new Image(getClass().getResourceAsStream("/com/example/ecodesk/imgs/eco.png"));
        myImageView.setImage(image);

        // Otras configuraciones si las necesitas.
        // Por ejemplo, si quieres mantener la relación de aspecto:
        myImageView.setPreserveRatio(true);
        myImageView.setFitWidth(200);  // Ancho deseado
        myImageView.setFitHeight(200); // Alto deseado
    }
    @FXML
    protected void registerUser() {
        String nombre = nombreField.getText();
        String apellidoPaterno = apellidoPaternoField.getText();
        String apellidoMaterno = apellidoMaternoField.getText();
        String correo = correoField.getText();
        String telefono = telefonoField.getText();
        String password = passwordField.getText();
        String repetirPassword = repetirPasswordField.getText();
    }
}
