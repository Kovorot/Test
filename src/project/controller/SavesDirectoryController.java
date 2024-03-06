package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;

public class SavesDirectoryController {
    @FXML
    private TextField textField;
    @FXML
    private Button directorySetter;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    private File directory;

    @FXML
    private void initialize() {
        //TODO Инициализация файла directory
    }

    @FXML
    private void browse() {

    }

    @FXML
    private void close() {

    }
}
