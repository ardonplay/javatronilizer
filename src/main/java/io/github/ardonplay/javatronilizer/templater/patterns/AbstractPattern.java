package io.github.ardonplay.javatronilizer.templater.patterns;
import io.github.ardonplay.javatronilizer.models.Model;

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

    public abstract String transform(String source);

    public Matcher matcher(String expression) {
        this.matcher = pattern.matcher(expression);
        return this.matcher;
    }

    protected Object recursiveGet(String[] splittedField, Object data) throws NoSuchFieldException, IllegalAccessException {
        if (splittedField.length > 1) {
            data = data.getClass().getField(splittedField[1]).get(data);
            String[] remainingField = new String[splittedField.length - 1];
            System.arraycopy(splittedField, 1, remainingField, 0, remainingField.length);
            return recursiveGet(remainingField, data);
        }
        return data;
    }
}
