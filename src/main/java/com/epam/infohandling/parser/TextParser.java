package com.epam.infohandling.parser;

import com.epam.infohandling.entity.composite.Component;
import com.epam.infohandling.entity.composite.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextParser extends Parser {

    public static final int FIRST_ELEMENT = 0;

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        if (text != null && successor != null) {
            String[] splittedText = text.split("\n([\\s]{4})");
            List<String> paragraphs = new ArrayList<>(Arrays.asList(splittedText));

            removeFirstParagraphSpaces(paragraphs);
            removeLastParagraphMark(paragraphs);

            List<Component> paragraphComponents = new ArrayList<>();
            paragraphs.forEach(paragraph -> {
                Component parsedParagraph = successor.parse(paragraph);
                paragraphComponents.add(parsedParagraph);
            });
            return new Text(paragraphComponents);
        }
        return new Text(Collections.emptyList());
    }

    private void removeFirstParagraphSpaces(List<String> paragraphs) {
        if (paragraphs != null) {
            String firstParagraph = paragraphs.get(FIRST_ELEMENT);
            String firstParagraphSubstring = firstParagraph.substring(4);
            paragraphs.set(FIRST_ELEMENT, firstParagraphSubstring);
        }
    }

    private void removeLastParagraphMark(List<String> paragraphs) {
        if (paragraphs != null) {
            int lastElement = paragraphs.size() - 1;
            String lastParagraph = paragraphs.get(lastElement);
            int lastParagraphLength = lastParagraph.length();
            String lastParagraphSubstring = lastParagraph.substring(0, lastParagraphLength - 1);
            paragraphs.set(lastElement, lastParagraphSubstring);
        }
    }
}
