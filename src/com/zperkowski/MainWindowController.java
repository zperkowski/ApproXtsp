package com.zperkowski;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainWindowController {
    @FXML
    private TextArea textAreaLeft;
    @FXML
    private TextArea textAreaRight;
    @FXML
    private Label labelProgress;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextField textFieldQuantity;
    @FXML
    private TextField textFieldMaxValue;

    /**
     * Clears programs environment.
     */
    @FXML
    private void menuFileNewClicked() {
        textAreaLeft.setText("");
        textAreaRight.setText("");
        labelProgress.setText("Idle");
        progressBar.setProgress(0.0);
    }

    /**
     * Opens an input file and puts content of the file to the {@see textAreaLeft}.
     */
    @FXML
    private void menuFileOpenClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open input file");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        File inputFile = fileChooser.showOpenDialog(new Stage());
        if (inputFile != null) {
            try {
                textAreaLeft.setText(new String(Files.readAllBytes(Paths.get(inputFile.getAbsolutePath()))));
            } catch (IOException e) {
                e.printStackTrace();
                inputFile = null;
                textAreaLeft.setText("");
            }
        }
    }

    /**
     * Saves results to a file
     */
    @FXML
    private void menuFileSaveClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text (*.txt)", "*.txt")
        );
        File outputFile = fileChooser.showSaveDialog(new Stage());
        if (outputFile != null) {
            try {
                PrintWriter printWriter = new PrintWriter(outputFile.getAbsolutePath());
                for (String line : textAreaRight.getText().split("\\n"))
                    printWriter.println(line);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                outputFile = null;
                textAreaLeft.setText("");
            }
        }
    }

    /**
     * Closes the program.
     */
    @FXML
    private void menuFileCloseClicked() {
        Stage stage = (Stage) textAreaLeft.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void menuHelpAboutClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Travelling salesman problem\nApproximation algorithms");
        alert.setContentText("Author: Zdzisław Perkowski\n" +
                "Version: 1.0");

        alert.showAndWait();
    }

    @FXML
    private void buttonStartClicked() {
        labelProgress.setText("Finished");
        progressBar.setProgress(100.0);
    }

    @FXML
    private void buttonGenerateClicked() {
        if (textFieldQuantity.getText().matches("[0-9]+") &&
                textFieldMaxValue.getText().matches("[0-9]+")) {
            Main.mainTspList.generate(Integer.parseInt(textFieldQuantity.getText()),
                                        Integer.parseInt(textFieldMaxValue.getText()));
            textAreaLeft.setText(Main.mainTspList.toString().replace(' ', '\t'));
        }
    }
}
