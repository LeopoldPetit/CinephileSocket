package com.helha.java.q2.cinephile.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur pour la vue de la page de paiement.
 */
public class checkoutViewController {

    @FXML
    private ComboBox<Integer> adultComboBox;

    @FXML
    private ComboBox<Integer> childComboBox;

    @FXML
    private ComboBox<Integer> seniorComboBox;

    @FXML
    private ComboBox<Integer> pmrComboBox;

    @FXML
    private Button checkoutbtn;

    @FXML
    private Button resetbtn;

    @FXML
    private Label ticketPriceLabel;

    private NavListener listener;

    private double adultPrice = 8.50;
    private double childPrice = 5.00;
    private double seniorPrice = 7.50;
    private double pmrPrice = 7.50; // Prix pour les personnes à mobilité réduite

    /**
     * Initialise le contrôleur.
     */
    @FXML
    private void initialize() {
        checkoutbtn.setOnAction(event -> openBancontactPage());

        // Ajout d'écouteurs aux ComboBox pour mettre à jour le prix total
        adultComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        childComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        seniorComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        pmrComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
    }

    /**
     * Ouvre la page de paiement Bancontact.
     */


    /**
     * Réinitialise les ComboBox de sélection.
     *
     * @param event L'événement de bouton.
     */
    @FXML
    private void handleResetButton(ActionEvent event) {
        if (adultComboBox != null) {
            adultComboBox.getSelectionModel().clearSelection();
        }
        if (childComboBox != null) {
            childComboBox.getSelectionModel().clearSelection();
        }
        if (seniorComboBox != null) {
            seniorComboBox.getSelectionModel().clearSelection();
        }
        if (pmrComboBox != null) {
            pmrComboBox.getSelectionModel().clearSelection();
        }
    }

    /**
     * Met à jour le prix total en fonction des sélections dans les ComboBox.
     */
    private void updateTotalPrice() {
        int adultCount = adultComboBox.getValue() != null ? adultComboBox.getValue() : 0;
        int childCount = childComboBox.getValue() != null ? childComboBox.getValue() : 0;
        int seniorCount = seniorComboBox.getValue() != null ? seniorComboBox.getValue() : 0;
        int pmrCount = pmrComboBox.getValue() != null ? pmrComboBox.getValue() : 0;

        double totalPrice = (adultCount * adultPrice) + (childCount * childPrice) + (seniorCount * seniorPrice) + (pmrCount * pmrPrice);
        ticketPriceLabel.setText(String.format("%.2f €", totalPrice));
    }
    private void openBancontactPage() {
        if (listener != null){
            listener.sendToTerminal();
        }

    }


    public void setListener(NavListener listener) {
        this.listener = listener;
    }
    public interface NavListener {
        void sendToTerminal();
    }
}
