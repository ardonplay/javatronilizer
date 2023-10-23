package io.github.ardonplay.javatronilizer.parser.patterns;
import io.github.ardonplay.javatronilizer.models.Model;

import java.lang.reflect.Field;
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
            Class<?> clazz = data.getClass();
            Field field = clazz.getField(splittedField[1]);
            data = field.get(data);
            String[] remainingField = new String[splittedField.length - 1];
            System.arraycopy(splittedField, 1, remainingField, 0, remainingField.length);
            return recursiveGet(remainingField, data);
        }
        return data;
    }
}
