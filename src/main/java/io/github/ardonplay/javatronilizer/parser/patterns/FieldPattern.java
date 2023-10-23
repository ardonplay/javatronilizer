package io.github.ardonplay.javatronilizer.parser.patterns;

import io.github.ardonplay.javatronilizer.models.Model;
import io.github.ardonplay.javatronilizer.parser.patterns.AbstractPattern;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldPattern extends AbstractPattern {

    public FieldPattern(Model model) {
        super(model, Pattern.compile("\\{(.*?)\\}"));
    }

    @Override
    public String transform(String source) {
        String transformed = source;
        matcher = matcher(source);
        while (matcher.find()) {
            String path = source.substring(matcher.start(), matcher.end());
            String attribute = path.substring(1, path.length() - 1);
            String[] splittedField = attribute.split("\\.");
            Object data;
            try {
                data = recursiveGet(splittedField, model.getAttribute(splittedField[0]));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            transformed = transformed.replace(path, data.toString());
        }
        return transformed;
    }

    private Object recursiveGet(String[] splittedField, Object data) throws NoSuchFieldException, IllegalAccessException {
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
