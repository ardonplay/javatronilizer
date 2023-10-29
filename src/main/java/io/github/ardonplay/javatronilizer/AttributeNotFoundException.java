package io.github.ardonplay.javatronilizer;

public class AttributeNotFoundException extends RuntimeException{
    public AttributeNotFoundException() {
    }

    public AttributeNotFoundException(String message) {
        super(message);
    }

    public AttributeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttributeNotFoundException(Throwable cause) {
        super(cause);
    }
}
