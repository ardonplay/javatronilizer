package io.github.ardonplay.javatronilizer.templater.patterns;

import io.github.ardonplay.javatronilizer.models.Model;

import java.util.regex.Pattern;

public class IfElsePattern extends AbstractPattern{
    public IfElsePattern(Model model) {
        super(model, Pattern.compile("\\{if(.*?)}(.*?)(\\{else(.*?)})(.*?)\\{/if}", Pattern.DOTALL));
    }

    @Override
    public String transform(String source) {
        String transformed = source;
        matcher = matcher(source);
        while (matcher.find()) {
            String path = source.substring(matcher.start(), matcher.end());

            System.out.println(path);
        }
        return transformed;
    }
}
