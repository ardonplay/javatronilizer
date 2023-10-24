package io.github.ardonplay.javatronilizer.templater.patterns;

import io.github.ardonplay.javatronilizer.models.Model;

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



}
