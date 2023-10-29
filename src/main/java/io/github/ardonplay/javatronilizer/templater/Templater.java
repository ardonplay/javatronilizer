package io.github.ardonplay.javatronilizer.templater;

import io.github.ardonplay.javatronilizer.AttributeNotFoundException;
import io.github.ardonplay.javatronilizer.models.Model;
import io.github.ardonplay.javatronilizer.templater.patterns.AbstractPattern;
import io.github.ardonplay.javatronilizer.templater.patterns.FieldPattern;
import io.github.ardonplay.javatronilizer.templater.patterns.IfElsePattern;
import io.github.ardonplay.javatronilizer.templater.patterns.ListPattern;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
@Slf4j
public class Templater {
    private String source;

    private final List<AbstractPattern> patterns;

    public Templater(String source, Model model) {
        this.source = source;
        this.patterns = List.of(new ListPattern(model), new IfElsePattern(model), new FieldPattern(model));
    }

    public String transform() throws AttributeNotFoundException {
        log.info("Starting transforming {}", source.replace(System.getProperty("line.separator"), ""));

        for (AbstractPattern pattern : patterns) {
            source = pattern.transform(source);
        }
        return source;
    }
}
