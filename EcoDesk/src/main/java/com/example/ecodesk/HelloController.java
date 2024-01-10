package com.example.ecodesk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
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
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Verificación simple: si el usuario y la contraseña coinciden con valores predeterminados
        if ("admin".equals(username) && "password".equals(password)) {
            welcomeText.setText("Login Successful!");
        } else {
            welcomeText.setText("Invalid credentials!");
        }
    }
    @FXML
    protected void goToRegistrationView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ecodesk/registration-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Registro");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
