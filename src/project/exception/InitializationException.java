package project.exception;

public class InitializationException extends ProjectException {

    public InitializationException() {
        super();
    }

    public InitializationException(Exception e) {
        super(e);
    }

    public InitializationException(String msg) {
        super(msg);
    }
}
