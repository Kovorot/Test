package project.exception;

/**
 * Основной класс ошибок проекта. Служит основой для других видов ошибок, которые могут возникнуть в проекте.
 */
public class ProjectException extends RuntimeException {

    public ProjectException() {
        super();
    }

    public ProjectException(Exception e) {
        super(e);
    }

    public ProjectException(String msg) {
        super(msg);
    }
}
