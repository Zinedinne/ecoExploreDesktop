package com.example.ecodesk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private TextField nombreField;

    @FXML
    private Label welcomeText;

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
    private ImageView myImageView;

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
    protected void returnToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        HelloController helloController = loader.getController();

        Stage currentStage = (Stage) myImageView.getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("EcoDesk - Login");
        currentStage.show();
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

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() ||
                correo.isEmpty() || telefono.isEmpty() || password.isEmpty() || repetirPassword.isEmpty()) {
            welcomeText.setText("Todos los campos son requeridos");
            return;
        } else if ((!password.equals(repetirPassword))) {
            welcomeText.setText("Las contraseñas no coinciden");
            return;
        } else {
            String formattedTelefono = "tel:+52-" + telefono.substring(0, 3) + "-" + telefono.substring(3, 6) + "-" + telefono.substring(6);
            String jsonInputString = "{"
                    + "\"Nombre\": \"" + nombre + "\","
                    + "\"ApellidoPaterno\": \"" + apellidoPaterno + "\","
                    + "\"ApellidoMaterno\": \"" + apellidoMaterno + "\","
                    + "\"Email\": \"" + correo + "\","
                    + "\"UrlImagen\": \"\","
                    + "\"PerfilPublico\": true,"
                    + "\"Guia\": false,"
                    + "\"Telefono\": \"" + formattedTelefono + "\","
                    + "\"Bitacoras\": [],"
                    + "\"Clave\": \"" + password + "\""
                    + "}";
            try {
                String url = "http://ec2-3-137-140-200.us-east-2.compute.amazonaws.com:8000/usuarios/crear";

                HttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(url);

                StringEntity entity = new StringEntity(jsonInputString);
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");

                HttpResponse response = httpClient.execute(httpPost);

                String responseBody = EntityUtils.toString(response.getEntity());

                if (response.getStatusLine().getStatusCode() == 201) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario creado exitosamente");
                    alert.showAndWait();
                    returnToLogin();
                } else {
                    // Manejar otros códigos de respuesta si es necesario
                    System.out.println("Error al crear usuario: " + response.getStatusLine().getStatusCode());
                }

                System.out.println(responseBody);
                welcomeText.setText("creamos el json "+ jsonInputString);
                System.out.println(jsonInputString);
                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
