package io.github.ardonplay.javatronilizer.templater.patterns;

import io.github.ardonplay.javatronilizer.AttributeNotFoundException;
import io.github.ardonplay.javatronilizer.models.Model;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractPattern {

    private final Pattern pattern;
    protected Matcher matcher;
    protected final Model model;

    protected AbstractPattern(Model model, Pattern pattern) {
        this.model = model;
        this.pattern = pattern;
    }

    public abstract String transform(String source) throws AttributeNotFoundException;

    public Matcher matcher(String expression) {
        this.matcher = pattern.matcher(expression);
        return this.matcher;
    }

    protected Object recursiveGet(String[] splitField, Object data) throws AttributeNotFoundException {
        if (splitField.length > 1) {
            try {
                data = data.getClass().getField(splitField[1]).get(data);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new AttributeNotFoundException(e);
            }
            String[] remainingField = new String[splitField.length - 1];
            System.arraycopy(splitField, 1, remainingField, 0, remainingField.length);
            return recursiveGet(remainingField, data);
        }
        return data;
    }
}
