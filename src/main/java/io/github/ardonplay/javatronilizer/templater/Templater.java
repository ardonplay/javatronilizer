package io.github.ardonplay.javatronilizer.templater;

import io.github.ardonplay.javatronilizer.templater.patterns.AbstractPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Slf4j
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
        log.info("Starting transforming {}", source);
        for (AbstractPattern pattern : patterns) {
            source = pattern.transform(source);
        }
        return source;
    }
}
