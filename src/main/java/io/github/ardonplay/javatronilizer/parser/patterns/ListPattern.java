package io.github.ardonplay.javatronilizer.parser.patterns;

import io.github.ardonplay.javatronilizer.models.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListPattern extends AbstractPattern {
    public ListPattern(Model model) {
        super(model, Pattern.compile("\\{list(.*?)as(.*?):(.*?)\\{(.*?)\\}\\}")); //TODO: fix for multiple lines
    }

    @Override
    public String transform(String source) {
        matcher = matcher(source);
        String substring = "";
        while (matcher.find()) {
            substring = source.substring(matcher.start()+1, matcher.end()-1);
            String listName = getListName(substring);
            String entityName = getListEntityName(substring);
            String expression = getAttributeField(substring);
            System.out.println(entityName);
            System.out.println(listName);
            System.out.println(expression);

            System.out.println(substring);
        }
        return substring;
    }

    private String getListName(String expression) {
        return findSubstring(expression, Pattern.compile("(?<=list\\s).*?(?=\\sas)"));
    }

    private String getListEntityName(String expression) {
        return findSubstring(expression, Pattern.compile("(?<=as\\s).*?(?=\\s:)"));
    }

    private String getAttributeField(String expression){
        return findSubstring(expression, Pattern.compile("\\{(.*?)\\}")); //TODO: fix to catch {<div>{user.username}</div>} instead of {<div>{user.username}
    }
    private String findSubstring(String expression, Pattern pattern) {
        Matcher substringMatcher = pattern.matcher(expression);
        return substringMatcher.find() ? expression.substring(substringMatcher.start(), substringMatcher.end()) : null;
    }
}
