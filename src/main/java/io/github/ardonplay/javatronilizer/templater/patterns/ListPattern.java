package io.github.ardonplay.javatronilizer.templater.patterns;

import io.github.ardonplay.javatronilizer.AttributeNotFoundException;
import io.github.ardonplay.javatronilizer.models.DefaultModel;
import io.github.ardonplay.javatronilizer.models.Model;
import io.github.ardonplay.javatronilizer.templater.Templater;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class ListPattern extends AbstractPattern {
    public ListPattern(Model model) {
        super(model, Pattern.compile("\\{list(.*?)as(.*?):(.*?)\\{(.*?)\\}\\}", Pattern.DOTALL));
    }

    @Override
    public String transform(String source) throws AttributeNotFoundException {
        matcher = matcher(source);
        String transformed = source;
        Model subModel = new DefaultModel().addAllAttributes(model.asMap());
        List<String> transformedExpressions = new ArrayList<>();
        while (matcher.find()) {
            String path = source.substring(matcher.start(), matcher.end());
            log.info("Expression: {}", path);
            String substring = path.substring(1, path.length() - 1);
            String entityName = getListEntityName(substring);
            List<?> list = (List<?>) model.getAttribute(getListName(substring));
            if(list == null){
                throw new AttributeNotFoundException();
            }
            Templater subTemplater = new Templater(getAttributeField(substring), subModel);
            for (Object o : list) {
                subModel.addAttribute(entityName, o);
                transformedExpressions.add(subTemplater.transform());
                subModel.asMap().remove(entityName);
            }
            transformed = transformed.replace(path, transformedExpressions.stream().map(String::valueOf)
                    .collect(Collectors.joining("", "", "")));
            transformedExpressions.clear();
        }
        return transformed;
    }

    private String getListName(String expression) {
        return findSubstring(expression, Pattern.compile("(?<=list\\s).*?(?=\\sas)"));
    }

    private String getListEntityName(String expression) {
        return findSubstring(expression, Pattern.compile("(?<=as\\s).*?(?=\\s:)"));
    }

    private String getAttributeField(String expression) {
        return expression.substring(expression.indexOf("{") + 1, expression.lastIndexOf("}"));
    }

    private String findSubstring(String expression, Pattern pattern) {
        Matcher substringMatcher = pattern.matcher(expression);
        return substringMatcher.find() ? expression.substring(substringMatcher.start(), substringMatcher.end()) : null;
    }
}
