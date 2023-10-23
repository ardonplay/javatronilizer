package io.github.ardonplay.javatronilizer.models;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public interface Model {
    Model addAttribute(String var1, @Nullable Object var2);

    Model addAttribute(Object var1);

    Object getAttribute(String var1);

    Model addAllAttributes(Collection<?> var1);

    Model addAllAttributes(Map<String, ?> var1);

    Model mergeAttributes(Map<String, ?> var1);

    boolean containsAttribute(String var1);

    Map<String, Object> asMap();
}
