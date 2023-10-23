package io.github.ardonplay.javatronilizer.parser;

import io.github.ardonplay.javatronilizer.models.DefaultModel;
import io.github.ardonplay.javatronilizer.models.Model;
import io.github.ardonplay.javatronilizer.parser.patterns.AbstractPattern;
import io.github.ardonplay.javatronilizer.parser.patterns.FieldPattern;

import java.util.List;
import java.util.regex.Matcher;

public class Parser {
    private String source;

    private final Model model = new DefaultModel();
    private final List<AbstractPattern> patterns = List.of(new FieldPattern(model));

    public Parser(String source){
        this.source = source;
    }

    public void parse(){
        for (AbstractPattern pattern: patterns) {
            Matcher matcher = pattern.matcher(source);
            while (matcher.find())  {
                System.out.println(source.substring(matcher.start()+1, matcher.end()-1));
            }
        }

    }
}
