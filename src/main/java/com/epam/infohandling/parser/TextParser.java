package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextParser extends Parser {

    private static final String PARAGRAPH_SEPARATOR = "\n([\\s]{4})";
    private static final int FIRST_ELEMENT = 0;
    private static final int SUBSTRING_STARTING_POINT = 0;
    private static final int RECEIVING_LAST_ELEMENT_INDEX = 1;
    private static final int SUBSTRING_STARTING_SPACES = 4;

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        if (text != null && successor != null) {
            String[] splittedText = text.split(PARAGRAPH_SEPARATOR);
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
            String firstParagraphSubstring = firstParagraph.substring(SUBSTRING_STARTING_SPACES);
            paragraphs.set(FIRST_ELEMENT, firstParagraphSubstring);
        }
    }

    private void removeLastParagraphMark(List<String> paragraphs) {
        if (paragraphs != null) {
            int lastElement = paragraphs.size() - RECEIVING_LAST_ELEMENT_INDEX;
            String lastParagraph = paragraphs.get(lastElement);
            int lastParagraphLength = lastParagraph.length();
            String lastParagraphSubstring =
                    lastParagraph.substring(SUBSTRING_STARTING_POINT,
                            lastParagraphLength - RECEIVING_LAST_ELEMENT_INDEX);
            paragraphs.set(lastElement, lastParagraphSubstring);
        }
    }
}
