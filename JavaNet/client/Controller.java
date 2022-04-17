package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

import client.network.Network;

public class Controller {
    Network ntwrk;

    private boolean bool;
    private boolean bool2;
    private int counter;

    @FXML
    private TextField field4;

    @FXML
    private Label negative;
    @FXML
    private Label result;
    @FXML
    private Label sqrT;

    int total;

    public Controller() {
        bool = false;
        counter = 0;
        total = 0;

        ntwrk = new Network();
    }

    @FXML
    private void addSymbol(ActionEvent e) {
        Node node = (Node)e.getSource();
        String data = (String)node.getUserData();

        field4.appendText(data);
    }

    @FXML
    private void clear() {
        field4.clear();
    }

    @FXML
    private void  erase() {
        if (field4.getLength() > 0) field4.setText(field4.getText(0, field4.getLength() - 1));
    }

    @FXML
    private void calculate() {
        String text = field4.getText();
        ntwrk.send(text);

        double res = ntwrk.waitForDouble();

        if (bool2) res = Math.sqrt(res);
        if (!bool) result.setText("=" + res);
        else result.setText("=" + '-' + res);
    }

    @FXML
    private void negative() {
        bool = !bool;
        negative.setVisible(bool);
    }

    @FXML
    private void sqrt() {
        bool2 = !bool2;
        sqrT.setVisible(bool2);
    }
}