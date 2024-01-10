package com.example.ecodesk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.example.ecodesk.SingletonClass.UsuarioSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LandingPageController {
    private static final String ECO_EXPLORE_API = "http://ec2-3-137-140-200.us-east-2.compute.amazonaws.com:8000";
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
    public void initialize() {
        realizarSolicitudHttpGetConToken(UsuarioSingleton.getInstance().getToken());
    }


    @FXML
    private void Buscar(ActionEvent event) {
        System.out.println("Buscar clickeado");
    }

    @FXML
    private void Siguiente(ActionEvent event) {
        System.out.println("Botón Siguiente clickeado");
    }

    @FXML
    private void Anterior(ActionEvent event) {
        System.out.println("Botón Anterior clickeado");
    }

    private void realizarSolicitudHttpGetConToken(String accessToken) {
        try {
            String apiUrl = ECO_EXPLORE_API + "/usuarios";

            String authorizationHeader = "Bearer " + accessToken;

            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setHeader("Authorization", authorizationHeader);

            HttpClient httpClient = HttpClients.createDefault();

            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String responseData = EntityUtils.toString(response.getEntity());
                System.out.println("Respuesta de la API: " + responseData);
                obtenerUsuarioDesdeJson(responseData);
                System.out.println(UsuarioSingleton.getInstance().getNombre());
                System.out.println(UsuarioSingleton.getInstance().getApellidoMaterno());
                System.out.println(UsuarioSingleton.getInstance().getApellidoPaterno());
                System.out.println(UsuarioSingleton.getInstance().getTelefono());
                System.out.println(UsuarioSingleton.getInstance().getEmail());
                System.out.println(UsuarioSingleton.getInstance().getId());


            } else {
                System.out.println("Error en la solicitud GET a " + apiUrl + ", código: " + response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void obtenerUsuarioDesdeJson(String  jsonResponse) throws IOException {
        if (jsonResponse != null && !jsonResponse.isEmpty()) {

                StringBuilder jsonString = new StringBuilder(jsonResponse);


                String nombreKey = "\"Nombre\":\"";
                int startIndexNombre = jsonString.indexOf(nombreKey);
                if (startIndexNombre != -1) {
                    startIndexNombre += nombreKey.length();
                    int endIndexNombre = jsonString.indexOf("\"", startIndexNombre);
                    String nombre = jsonString.substring(startIndexNombre, endIndexNombre);
                    UsuarioSingleton.getInstance().setNombre(nombre);
                    jsonString.delete(0, endIndexNombre + 1); // Limpiar la caché
                } else {
                    System.out.println("No se encontró la clave 'Nombre' en la respuesta JSON.");
                }

                String apellidoPaternoKey = "\"ApellidoPaterno\":\"";
                int startIndexApellidoPaterno = jsonString.indexOf(apellidoPaternoKey);
                if (startIndexApellidoPaterno != -1) {
                    startIndexApellidoPaterno += apellidoPaternoKey.length();
                    int endIndexApellidoPaterno = jsonString.indexOf("\"", startIndexApellidoPaterno);
                    String apellidoPaterno = jsonString.substring(startIndexApellidoPaterno, endIndexApellidoPaterno);
                    UsuarioSingleton.getInstance().setApellidoPaterno(apellidoPaterno);
                    jsonString.delete(0, endIndexApellidoPaterno + 1);

                } else {
                    System.out.println("No se encontró la clave 'ApellidoPaterno' en la respuesta JSON.");
                }

                String apellidoMaternoKey = "\"ApellidoMaterno\":\"";
                int startIndexApellidoMaterno = jsonString.indexOf(apellidoMaternoKey);
                if (startIndexApellidoMaterno != -1) {
                    startIndexApellidoMaterno += apellidoMaternoKey.length();
                    int endIndexApellidoMaterno = jsonString.indexOf("\"", startIndexApellidoMaterno);
                    String apellidoMaterno = jsonString.substring(startIndexApellidoMaterno, endIndexApellidoMaterno);
                    UsuarioSingleton.getInstance().setApellidoMaterno(apellidoMaterno);
                    jsonString.delete(0, endIndexApellidoMaterno + 1);
                } else {
                    System.out.println("No se encontró la clave 'ApellidoMaterno' en la respuesta JSON.");
                }

                String emailKey = "\"Email\":\"";
                int startIndexEmail = jsonString.indexOf(emailKey);
                if (startIndexEmail != -1) {
                    startIndexEmail += emailKey.length();
                    int endIndexEmail = jsonString.indexOf("\"", startIndexEmail);
                    String email = jsonString.substring(startIndexEmail, endIndexEmail);
                    UsuarioSingleton.getInstance().setEmail(email);
                    jsonString.delete(0, endIndexEmail + 1);
                } else {
                    System.out.println("No se encontró la clave 'Email' en la respuesta JSON.");
                }

                String urlImagenKey = "\"UrlImagen\":\"";
                int startIndexUrlImagen = jsonString.indexOf(urlImagenKey);
                if (startIndexUrlImagen != -1) {
                    startIndexUrlImagen += urlImagenKey.length();
                    int endIndexUrlImagen = jsonString.indexOf("\"", startIndexUrlImagen);
                    String urlImagen = jsonString.substring(startIndexUrlImagen, endIndexUrlImagen);
                    UsuarioSingleton.getInstance().setUrlImagen(urlImagen);
                    jsonString.delete(0, endIndexUrlImagen + 1);

                } else {
                    System.out.println("No se encontró la clave 'UrlImagen' en la respuesta JSON.");
                }

                String telefonoKey = "\"Telefono\":\"";
                int startIndexTelefono = jsonString.indexOf(telefonoKey);
                if (startIndexTelefono != -1) {
                    startIndexTelefono += telefonoKey.length();
                    int endIndexTelefono = jsonString.indexOf("\"", startIndexTelefono);
                    String telefono = jsonString.substring(startIndexTelefono, endIndexTelefono);
                    UsuarioSingleton.getInstance().setTelefono(telefono);
                    jsonString.delete(0, endIndexTelefono + 1);

                } else {
                    System.out.println("No se encontró la clave 'Telefono' en la respuesta JSON.");
                }

                String idKey = "\"id\":\"";
                int startIndexId = jsonString.indexOf(idKey);
                if (startIndexId != -1) {
                    startIndexId += idKey.length();
                    int endIndexId = jsonString.indexOf("\"", startIndexId);
                    String id = jsonString.substring(startIndexId, endIndexId);
                    UsuarioSingleton.getInstance().setId(id);
                    jsonString.delete(0, endIndexId + 1);
                } else {
                    System.out.println("No se encontró la clave 'id' en la respuesta JSON.");
                }

        }
    }

}
