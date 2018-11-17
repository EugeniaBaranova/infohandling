package com.epam.infohandling.composite;

import java.util.List;

public class Lexeme implements Component, Value {

    private final String value;
    private boolean expression;

    private Lexeme(String value, boolean expression) {
        this.value = value;
        this.expression = expression;
    }

    public static Lexeme word(String value){
        return new Lexeme(value, false);
    }

    public static Lexeme expression(String value){
        return new Lexeme(value, true);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lexeme)) return false;

        Lexeme lexeme = (Lexeme) o;

        if (expression != lexeme.expression) return false;
        return getValue() != null ? getValue().equals(lexeme.getValue()) : lexeme.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = getValue() != null ? getValue().hashCode() : 0;
        result = 31 * result + (expression ? 1 : 0);
        return result;
    }

    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    public List<Component> getChildren() {
        throw new UnsupportedOperationException();
    }
}
