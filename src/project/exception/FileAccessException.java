package project.exception;

/**
 * Ошибка проекта, расширяющая общий класс всех ошибок проекта. По большей части используется, как альтернатива
 * {@link java.io.IOException}.
 */
public class FileAccessException extends ProjectException {

    public FileAccessException() {
        super();
    }

    public FileAccessException(Exception e) {
        super(e);
    }

    public FileAccessException(String msg) {
        super(msg);
    }
}
