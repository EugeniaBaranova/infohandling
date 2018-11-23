package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;

import java.util.Arrays;
import java.util.List;

public class SentenceParser extends Parser {

    private static final String SPACE = " ";

    @Override
    public Component parse(Lexeme textLexeme) {
        Component component = new Composite();
        if(textLexeme != null){
            String textString = textLexeme.getValue();
            String[] splittedSentence = textString.split(SPACE);
            List<String> words = Arrays.asList(splittedSentence);
            words.stream()
                    .map(Lexeme::word)
                    .forEach(component::add);
            return component;
        }
        return component;
    }
}
