package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class QueryCreationController {
    private Stage stage;
    @FXML
    private CheckBox firstRowAsHeaders;
    @FXML
    private Label label;
    @FXML
    private TextField queryName;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void addData() {
    }

    @FXML
    private void create() {
    }
}
