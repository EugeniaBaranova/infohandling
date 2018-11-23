package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParagraphParser extends Parser {

    private static final String SEPARATING_REGEX = "(((\\.\\.\\.)|(\\.)|(\\?)|(!))(\\s?))";

    public ParagraphParser(Parser successor) {
        super(successor);
    }


    @Override
    public Component parse(Lexeme textLexeme) {
        Component component = new Composite();
        if (textLexeme != null && successor != null) {
            String textString = textLexeme.getValue();
            String[] splittedParagraph = textString.split(SEPARATING_REGEX);
            List<String> sentences = Arrays.asList(splittedParagraph);

            sentences.stream()
                    .map(Lexeme::expression)
                    .forEach(lexeme -> {
                        Component parsedSentence = successor.parse(lexeme);
                        component.add(parsedSentence);
                    });
            return component;
        }
        return component;
    }
}
