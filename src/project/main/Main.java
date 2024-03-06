package project.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.data.ProcessData;
import project.data.Query;
import project.dialog.Message;

public class Main extends Application {

    /**
     * Основная точка входа в программу.
     * @param args Аргументы.
     */
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/forms/mainForm.fxml"));
        primaryStage.setTitle("Редактор таблиц (TableEditor)");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(event -> {
            ProcessData processData = ProcessData.getInstance();
            Query query = processData.getCurrentQuery();

            if (query != null && query.isModified()) {
                event.consume();

                switch (Message.getInstance().closeRequest()) {
                    case SAVE:
                        processData.getFileManager().saveQuery(query);
                    case REFUSE:
                        Platform.exit();
                    default:
                }
            }
        });
        ProcessData.init();
        primaryStage.show();
    }
}
