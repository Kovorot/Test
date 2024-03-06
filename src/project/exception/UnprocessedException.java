package project.exception;

/**
 * Ошибка проекта, расширяющая общий класс всех ошибок проекта. Используется в случае возникновения
 * ошибок, процесс обработки которых пока неизвестен или если разработчику не было понятно, при каких обстоятельствах
 * могут возникнуть ошибки в тех или иных местах.
 */
public class UnprocessedException extends ProjectException {

    public UnprocessedException() {
        super();
    }

    public UnprocessedException(Exception e) {
        super(e);
    }

    public UnprocessedException(String msg) {
        super(msg);
    }
}
