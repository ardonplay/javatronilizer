package io.github.ardonplay.javatronilizer.templater;

import io.github.ardonplay.javatronilizer.templater.patterns.AbstractPattern;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
public class Templater {
    private String source;

    private final List<AbstractPattern> patterns = new ArrayList<>();

    public Templater(String source) {
        this.source = source;
    }

    public void addPattern(AbstractPattern... patterns) {
        this.patterns.addAll(Arrays.asList(patterns));
    }

    public String transform() {
        for (AbstractPattern pattern : patterns) {
            source = pattern.transform(source);
        }
        return source;
    }
}
