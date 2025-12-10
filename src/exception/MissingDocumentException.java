package exception;

public class MissingDocumentException extends Exception {
    public MissingDocumentException(String message) {
        super(message);
    }
}