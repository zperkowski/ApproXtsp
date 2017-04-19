package com.zperkowski;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainWindowController {
    @FXML
    private TextArea textAreaLeft;
    @FXML
    private TextArea textAreaRight;

    /**
     * Clears programs environment.
     */
    @FXML
    private void menuFileNewClicked() {
        textAreaLeft.setText("");
        textAreaRight.setText("");
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
}
