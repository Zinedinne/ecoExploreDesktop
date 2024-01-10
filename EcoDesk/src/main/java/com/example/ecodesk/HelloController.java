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
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import com.example.ecodesk.SingletonClass.UsuarioSingleton;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    private static final String ECO_EXPLORE_API = "http://ec2-3-137-140-200.us-east-2.compute.amazonaws.com:8000";

    @FXML
    private Label welcomeText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView myImageView;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/ecodesk/imgs/eco.png"));
        myImageView.setImage(image);

        myImageView.setPreserveRatio(true);
        myImageView.setFitWidth(200);  // Ancho deseado
        myImageView.setFitHeight(200); // Alto deseado
    }
    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();

        String password = passwordField.getText();

        verifyLogin(username, password);
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

    public void verifyLogin(String mail, String password) {
        if (mail.isEmpty() || password.isEmpty()) {
            System.out.println("Debes ingresar tu email y contraseña");
        } else {
            try {
                String url = ECO_EXPLORE_API + "/token";

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("username", mail));
                params.add(new BasicNameValuePair("password", password));

                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpClient httpClient = HttpClients.createDefault();

                HttpResponse response = httpClient.execute(httpPost);

                if (response.getStatusLine().getStatusCode() == 200) {
                    try {
                        obtenerTokenDesdeJson(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLandingPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ecodesk/LandingPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) usernameField.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Landing Page");


            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void obtenerTokenDesdeJson(HttpResponse response) throws IOException {
        try (InputStream content = response.getEntity().getContent();
             InputStreamReader reader = new InputStreamReader(content);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonString.append(line);
            }

            String tokenKey = "\"access_token\":\"";
            int startIndex = jsonString.indexOf(tokenKey);

            if (startIndex != -1) {
                startIndex += tokenKey.length();
                int endIndex = jsonString.indexOf("\"", startIndex);

                String accessToken = jsonString.substring(startIndex, endIndex);

                System.out.println("Token: " + accessToken);

                UsuarioSingleton.getInstance().setToken(accessToken);
                content.close();
                reader.close();
                bufferedReader.close();
                loadLandingPage();
            } else {
                System.out.println("No se encontró la clave 'access_token' en la respuesta JSON.");
            }
        }
    }


}
