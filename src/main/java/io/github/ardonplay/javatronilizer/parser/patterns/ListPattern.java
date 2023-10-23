package io.github.ardonplay.javatronilizer.parser.patterns;

import io.github.ardonplay.javatronilizer.models.DefaultModel;
import io.github.ardonplay.javatronilizer.models.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static javax.swing.UIManager.put;

public class ListPattern extends AbstractPattern {
    public ListPattern(Model model) {
        super(model, Pattern.compile("\\{list(.*?)as(.*?):(.*?)\\{(.*?)\\}\\}",  Pattern.DOTALL));
    }

    @Override
    public String transform(String source) {
        matcher = matcher(source);
        String transformed = source;
        while (matcher.find()) {
            String path = source.substring(matcher.start(), matcher.end());
            String substring = path.substring(1, path.length()-1);
            String listName = getListName(substring);
            String entityName = getListEntityName(substring);
            String expression = getAttributeField(substring);
            List<?> list =(List<?>) model.getAttribute(listName);
            Model fieldPatternModel = new DefaultModel().addAllAttributes(model.asMap());
            FieldPattern pattern = new FieldPattern(fieldPatternModel);
            List<String> transformedExpressions = new ArrayList<>();
            for (Object o: list) {
                fieldPatternModel.addAttribute(entityName, o);
                transformedExpressions.add(pattern.transform(expression));
                fieldPatternModel.asMap().remove(entityName);
            }
            transformed = transformed.replace(path, transformedExpressions.stream().map(String::valueOf)
                    .collect(Collectors.joining("", "", "")));
        }
        return transformed;
    }

    private String getListName(String expression) {
        return findSubstring(expression, Pattern.compile("(?<=list\\s).*?(?=\\sas)"));
    }

    private String getListEntityName(String expression) {
        return findSubstring(expression, Pattern.compile("(?<=as\\s).*?(?=\\s:)"));
    }

    private String getAttributeField(String expression){
        return expression.substring(expression.indexOf("{") + 1, expression.lastIndexOf("}"));
    }
    private String findSubstring(String expression, Pattern pattern) {
        Matcher substringMatcher = pattern.matcher(expression);
        return substringMatcher.find() ? expression.substring(substringMatcher.start(), substringMatcher.end()) : null;
    }
}
