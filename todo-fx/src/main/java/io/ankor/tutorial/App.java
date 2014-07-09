package io.ankor.tutorial;

import at.irian.ankor.system.AnkorClient;
import at.irian.ankor.system.WebSocketFxClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private AnkorClient client;

    public static void main(String[] args) {
        launch(args);
    }

    public App() throws Exception {
        client = WebSocketFxClient.builder()
                .withApplicationName("Todo FX Client")
                .withModelName("root")
                .withServer("ws://localhost:8080/websocket/ankor")
                .build();
    }

    @Override
    public void start(Stage stage) throws Exception {
        client.start();

        stage.setTitle("Ankor JavaFX Todo Sample");
        Pane myPane = FXMLLoader.load(getClass().getClassLoader().getResource("tasks.fxml"));

        Scene myScene = new Scene(myPane);
        myScene.getStylesheets().add("style.css");

        stage.setScene(myScene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        client.stop();
        super.stop();
    }
}

