package io.github.ardonplay.javatronilizer.templater.patterns;

import io.github.ardonplay.javatronilizer.AttributeNotFoundException;
import io.github.ardonplay.javatronilizer.models.Model;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
@Slf4j
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
            log.info("Expression: {}", path.replaceAll(System.lineSeparator(), " ").replaceAll("\t", ""));
            transformed = transformed.replace(path, "<!-- If/else construictions not working now! -->");
            log.warn("If/else construictions not working now!");
        }
        return transformed;
    }
}
