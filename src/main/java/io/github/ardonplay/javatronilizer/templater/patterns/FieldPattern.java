package io.github.ardonplay.javatronilizer.templater.patterns;

import io.github.ardonplay.javatronilizer.models.Model;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
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
            log.info("Expression: {}", path.replaceAll(System.lineSeparator(), " "));
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
