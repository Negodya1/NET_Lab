package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;

import javafx.scene.layout.HBox;

import client.network.Network;

public class Controller {
    Network ntwrk;

    @FXML
    HBox matchBox;

    @FXML
    Label counter;
    int counterInt;

    @FXML
    HBox btns;

    @FXML
    Button rdy;

    public Controller() {

    }

    @FXML
    private void takeMatches(ActionEvent e) {
        Node node = (Node)e.getSource();
        int data = Integer.parseInt((String)node.getUserData());

        counterInt -= data;

        try {
            while (counterInt < matchBox.getChildren().size() && !matchBox.getChildren().isEmpty()) {
                matchBox.getChildren().remove(0);
            }
            if (counterInt > 0) counter.setText("" + counterInt);
            else counter.setText("Victory!");

            btns.setDisable(true);

            ntwrk.send(data);
            counterInt = ntwrk.waitForInt();

            while (counterInt < matchBox.getChildren().size() && !matchBox.getChildren().isEmpty()) {
                matchBox.getChildren().remove(0);
            }
            counter.setText("" + counterInt);

            btns.setDisable(false);
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    @FXML
    private void ready() {
        rdy.setVisible(false);
        btns.setVisible(true);

        try {
            ntwrk = new Network();
            counterInt = ntwrk.waitForInt();

            while (counterInt < matchBox.getChildren().size()) {
                matchBox.getChildren().remove(0);
            }
            counter.setText("" + counterInt);

            btns.setDisable(false);
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}