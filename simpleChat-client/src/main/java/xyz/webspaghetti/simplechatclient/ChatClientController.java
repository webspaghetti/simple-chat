package xyz.webspaghetti.simplechatclient;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ChatClientController {
    private PrintWriter writer;
    private BufferedReader reader;

    @FXML
    private AnchorPane chatPane;
    @FXML
    private AnchorPane infoPane;
    @FXML
    private TextArea messageArea;
    @FXML
    private TextField TextField;
    @FXML
    private TextField hostnameTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private TextField usernameTextField;


    @FXML
    void connectToChat() {
        if (!usernameTextField.getText().isBlank() && !hostnameTextField.getText().isBlank() && !portTextField.getText().isBlank()) {
            setUpNetworking();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new ConnectingReader());

            infoPane.setVisible(false);
            chatPane.setVisible(true);
        }
    }

    @FXML
    void sendAction() {
        String textToSend = TextField.getText();
        String username = usernameTextField.getText();

        if (!textToSend.isBlank() && !username.isBlank()) {
            writer.println(username + ": " + textToSend);
            writer.flush();
            TextField.setText("");
            TextField.requestFocus();
        }
    }

    @FXML
    void checkKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendAction();
        }
    }

    public void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress(hostnameTextField.getText(), Integer.parseInt(portTextField.getText()));
            SocketChannel socketChannel = SocketChannel.open(serverAddress);

            writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));
            reader = new BufferedReader(Channels.newReader(socketChannel, UTF_8));

            System.out.println("connection established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ConnectingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + "\"" + message + "\"");
                    messageArea.appendText(message + "\n");
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}