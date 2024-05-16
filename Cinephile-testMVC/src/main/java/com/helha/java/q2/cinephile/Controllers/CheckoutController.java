package com.helha.java.q2.cinephile.Controllers;

import com.helha.java.q2.cinephile.Views.checkoutViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CheckoutController {
    public static void main(String[] args) {

    }
    public static void openCheckout(){
        try {
            FXMLLoader loader = new FXMLLoader(CheckoutController.class.getResource("/com/helha/java/q2/cinephile/checkout.fxml"));
            Parent root = loader.load();
            checkoutViewController controller = loader.getController();
            controller.setListener(new checkoutViewController.NavListener() {
                @Override
                public void sendToTerminal() {
                    System.out.println("sendToTerminal2");
                    startClient();


                }
            });

            // Obtient la scène actuelle
            Scene newScene = new Scene(root);

            // Créez un nouveau stage pour la nouvelle scène
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.setWidth(875);
            newStage.setHeight(800);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startClient() {
        String serverAddress = "127.0.0.1"; // Adresse IP du serveur (localhost)
        int serverPort = 12345; // Port utilisé par le serveur

        try {
            // Créez un socket client pour se connecter au serveur
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connecté au serveur.");

            // Envoyez le montant prédéfini au serveur
            double montant = 50.0; // Montant prédéfini
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(montant);

            // Fermez le socket client
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
