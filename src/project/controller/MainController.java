package project.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

/**
 * Контроллер основной формы программы.
 */
public class MainController {
    @FXML
    private TableView<ObservableList<Object>> tableView; //Таблица

    /**
     * Инициализирует форму основными компонентами.
     */
    @FXML
    private void initialize() {
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.setPlaceholder(new Label("Нет данных"));
    }

    /**
     * Открывает окно для создания нового запроса.
     * @throws IOException Бросает исключение при ошибке загрузки формы окна.
     */
    @FXML
    private void createQuery() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/forms/queryCreation.fxml"));
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Создание запроса");
        stage.setResizable(false);

        loader.<QueryCreationController>getController().setStage(stage);
        stage.showAndWait();
    }

    @FXML
    private void savesDirectory() {

    }
}
