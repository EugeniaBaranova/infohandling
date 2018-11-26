package com.epam.infohandling.parser;

import com.epam.infohandling.entity.composite.Component;
import com.epam.infohandling.entity.composite.Paragraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParagraphParser extends Parser {

    private static final String SEPARATING_REGEX = "(((\\.\\.\\.)|(\\.)|(\\?)|(!))(\\s?))";

    public ParagraphParser(Parser successor) {
        super(successor);
    }


    @Override
    public Component parse(String text) {
        if (text != null && successor != null) {
            String[] splittedParagraph = text.split(SEPARATING_REGEX);
            List<String> sentences = Arrays.asList(splittedParagraph);

            List<Component> sentencesComponents = new ArrayList<>();

            sentences.forEach(sentence -> {
                        Component parsedSentence = successor.parse(sentence);
                        sentencesComponents.add(parsedSentence);
                    });
            return new Paragraph(sentencesComponents);
        }
        return new Paragraph(new ArrayList<>(0));
    }
}
