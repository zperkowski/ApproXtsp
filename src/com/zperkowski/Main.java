package com.zperkowski;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static TSPList mainTspList = new TSPList(0, 0);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("ApproXtsp");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(625);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
