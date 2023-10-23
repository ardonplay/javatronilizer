package io.github.ardonplay.javatronilizer.parser.patterns;
import io.github.ardonplay.javatronilizer.models.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractPattern {

    private final Pattern pattern;
    protected Matcher matcher;
    protected final Model model;

    protected AbstractPattern(Model model, Pattern pattern) {
        this.model = model;
        this.pattern = pattern;
    }

    public abstract String transform(String source);

    public Matcher matcher(String expression) {
        this.matcher = pattern.matcher(expression);
        return this.matcher;
    }
}
