package io.github.ardonplay.javatronilizer.Model;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultModel implements Model {
    private final HashMap<String, Object> model = new HashMap<>();
    @Override
    public Model addAttribute(String var1, @Nullable Object var2) {
        model.put(var1, var2);
        return this;
    }

    @Override
    public Model addAttribute(Object var1) {
        model.put(var1.getClass().getName().toLowerCase(), var1);
        return this;
    }

    @Override
    public Object getAttribute(String var1) {
        return model.get(var1);
    }

    @Override
    public Model addAllAttributes(Collection<?> var1) {
        for(Object attribute: var1){
            addAttribute(attribute);
        }
        return this;
    }

    @Override
    public Model addAllAttributes(Map<String, ?> var1) {
        model.putAll(var1);
        return this;
    }

    @Override
    public Model mergeAttributes(Map<String, ?> var1) {
        model.putAll(var1);
        return this;
    }

    @Override
    public boolean containsAttribute(String var1) {
        return model.containsKey(var1);
    }

    @Override
    public Map<String, Object> asMap() {
        return model;
    }
}
