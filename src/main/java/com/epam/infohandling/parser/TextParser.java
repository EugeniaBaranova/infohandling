package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser extends Parser {
    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(Lexeme textLexeme) {
        Component component = new Composite();
        if (textLexeme != null && successor != null) {
            String textString = textLexeme.getValue();
            String[] splittedText = textString.split("\n([\\s]{4})");
            List<String> paragraphs = new ArrayList<>(Arrays.asList(splittedText));

            //TODO Separate method
            String firstParagraph = paragraphs.get(0);
            String firstParagraphSubstring = firstParagraph.substring(4);
            paragraphs.set(0, firstParagraphSubstring);

            int lastElement = paragraphs.size() - 1;
            String lastParagraph = paragraphs.get(lastElement);
            int lastParagraphLength = lastParagraph.length();
            String lastParagraphSubstring = lastParagraph.substring(0, lastParagraphLength-1);
            paragraphs.set(lastElement, lastParagraphSubstring);

            paragraphs.stream()
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
