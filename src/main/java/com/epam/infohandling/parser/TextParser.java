package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;

import java.util.Arrays;
import java.util.List;

public class TextParser extends Parser{
    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String textString) {
        Component component = new Composite();
        if(textString != null){
            String[] split = textString.split("\n");
            List<String> parts = Arrays.asList(split);
            parts.stream().map(Lexeme::expression).forEach(component::add);
            return component;
        }
        return component;
    }
}
