package project.dialog;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import project.exception.ProjectException;
import project.exception.UnprocessedException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * Класс для диалогового взаимодействия с пользователем.
 */
public class Message {
    private static Message instance; //Единственный экземпляр класса

    /**
     * Возвращает единственный экземпляр класса, если его нет, создает новый.
     * @return Единственный экземпляр класса.
     */
    public static Message getInstance() {
        if (instance == null)
            instance = new Message();
        return instance;
    }

    /**
     * Выводит сообщение о необрабатываемом в программе исключении с трассировкой стека. (+1 перегрузка).
     * @param e Исключение для вывода стека на экран.
     */
    public void showUnprocessedException(Exception e) {
        Alert alert = createAlert(Alert.AlertType.ERROR, "Необрабатываемое исключение");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Стек исключения");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
        throw new UnprocessedException(e);
    }

    /**
     * Выводит сообщение о необрабатываемом в программе исключении с трассировкой стека. (+1 перегрузка).
     */
    public void showUnprocessedException() {
        showUnprocessedException(new UnprocessedException());
    }

    /**
     * Выводит сообщение об ошибке в программе и завершает ее работу, путем выбрасывания общего исключения для всех
     * исключений данного проекта.
     * @param e Исключение для дальнейшего пробрасывания.
     * @param text Текст ошибки для вывода юзеру.
     */
    public void err(ProjectException e, String text) {
        Alert alert = createAlert(Alert.AlertType.ERROR, text);
        alert.showAndWait();
        throw new ProjectException(e);
    }

    /**
     * Выводит сообщение с вопросом о сохранении внесенных изменений.
     * @return Ответ юзера.
     */
    public Response closeRequest() {
        Alert alert = createAlert(Alert.AlertType.CONFIRMATION, "Сохранить изменения?");
        alert.getButtonTypes().clear();

        ButtonType save = new ButtonType("Сохранить", ButtonBar.ButtonData.YES);
        ButtonType refuse = new ButtonType("Отказаться", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().addAll(save, refuse, new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE));
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent()) {
            if (response.get() == save) return Response.SAVE;
            else if (response.get() == refuse) return Response.REFUSE;
            else return Response.CANCEL;
        }
        return Response.CLOSE;
    }

    public void info(String text) {
        Alert alert = createAlert(Alert.AlertType.INFORMATION, text);
        alert.showAndWait();
    }

    //region private
    private Message() {}

    private Alert createAlert(Alert.AlertType alertType, String text) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(text);

        switch (alertType) {
            case NONE:
                alert.setTitle("");
                break;
            case CONFIRMATION:
                alert.setTitle("Подтверждение действия");
                break;
            case INFORMATION:
                alert.setTitle("Сообщение");
                break;
            case WARNING:
                alert.setTitle("Предупреждение");
                break;
            default:
                alert.setTitle("Ошибка");
        }
        return alert;
    }
    //endregion
}
