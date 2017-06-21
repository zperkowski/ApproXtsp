package com.zperkowski;

import com.zperkowski.tspAlgorithms.*;
import javafx.collections.FXCollections;
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
    @FXML
    private ComboBox<String> comboBoxAlgo;

    @FXML
    public void initialize() {
        // Sets the list of algorithms
        comboBoxAlgo.setItems(FXCollections.observableArrayList(
                "All",
                "Nearest Neighbour",
                "Approx Tsp Tour",
                "Multi Approx Tsp Tour"
        ));
        comboBoxAlgo.getSelectionModel().selectFirst();
    }

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
        Main.mainTspList.clear();
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
                Main.mainTspList.setFromFile(textAreaLeft.getText());
            } catch (IOException e) {
                e.printStackTrace();
                inputFile = null;
                textAreaLeft.setText("");
                Main.mainTspList = null;
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
        if (Main.mainTspList.size() > 0)
            progressBar.setProgress(0);
            switch (comboBoxAlgo.getSelectionModel().getSelectedItem()) {
                case "All":
                    labelProgress.setText("Nearest Neighbour is running");
                    startNearestNeighbour();
                    progressBar.setProgress(0.3);
                    labelProgress.setText("Approx Tsp Tour is running");
                    startApproxTspTour();
                    progressBar.setProgress(0.6);
                    labelProgress.setText("Multi Approx Tsp Tour is running");
                    startMultiApproxTspTour();
                    break;
                case "Nearest Neighbour":
                    labelProgress.setText("Nearest Neighbour is running");
                    startNearestNeighbour();
                    break;
                case "Approx Tsp Tour":
                    labelProgress.setText("Approx Tsp Tour is running");
                    startApproxTspTour();
                    break;
                case "Multi Approx Tsp Tour":
                    labelProgress.setText("Multi Approx Tsp Tour is running");
                    startMultiApproxTspTour();
                    break;
                default:
                    break;
            }

        labelProgress.setText("Finished");
        progressBar.setProgress(100.0);
    }

    @FXML
    private void buttonGenerateClicked() {
        if (textFieldQuantity.getText().matches("[0-9]+") &&
                textFieldMaxValue.getText().matches("[0-9]+")) {
            progressBar.setProgress(0);
            labelProgress.setText("Generating coordinates...");
            Main.mainTspList.generate(Integer.parseInt(textFieldQuantity.getText()),
                                        Integer.parseInt(textFieldMaxValue.getText()));
            progressBar.setProgress(1);
            labelProgress.setText("Coordinates generated");
            textAreaLeft.setText(Main.mainTspList.toString().replace(' ', '\t'));
            textAreaRight.clear();
        }
    }

    private void startNearestNeighbour() {
        NearestNeighbourAlgorithm nnAlgorithm = new NearestNeighbourAlgorithm();
        nnAlgorithm.addCities(Main.mainTspList);
        nnAlgorithm.calculateTour();
        textAreaRight.setText(textAreaRight.getText()
                + "Nearest Neighbour\n"
                + nnAlgorithm.toString());
    }

    private void startApproxTspTour() {
        ApproxTspTourAlgorithm tourAlgorithm = new ApproxTspTourAlgorithm();
        tourAlgorithm.addCities(Main.mainTspList);
        tourAlgorithm.calculateTour();
        textAreaRight.setText(textAreaRight.getText()
                + "Approx Tsp Tour\n"
                + tourAlgorithm.toString());
    }

    private void startMultiApproxTspTour() {
        ApproxTspTourAlgorithm tourAlgorithm = new ApproxTspTourAlgorithm();
        tourAlgorithm.addCities(Main.mainTspList);
        tourAlgorithm.calculateAllTours();
        textAreaRight.setText(textAreaRight.getText()
                + "Multi Approx Tsp Tour - Calculates MST starting from every vertex\n"
                + tourAlgorithm.toString());
    }
}
