package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.Paragraph;

import java.util.*;

public class ParagraphParser extends Parser {

    private static final String SEPARATING_REGEX = "(((\\.\\.\\.)|(\\.)|(\\?)|(!))(\\s?))";
    private static final String THREE_POINT_MARK = "...";
    private static final char QUESTION_MARK = '?';
    private static final char POINT_MARK = '.';
    private static final char EXCLAMATION_MARK = '!';
    private static final int INITIALIZATION_ZERO = 0;

    public ParagraphParser(Parser successor) {
        super(successor);
    }


    @Override
    public Component parse(String text) {
        if (text != null && successor != null) {
            List<String> punctuationMarks = new ArrayList<>();
            extractPunctuationMarks(text, punctuationMarks);

            String[] splittedParagraph = text.split(SEPARATING_REGEX);
            List<String> sentencesAfterSplit = Arrays.asList(splittedParagraph);

            List<String> sentences = returnPunctuationMarks(punctuationMarks, sentencesAfterSplit);
            List<Component> sentencesComponents = new ArrayList<>();
            sentences.forEach(sentence -> {
                Component parsedSentence = successor.parse(sentence);
                sentencesComponents.add(parsedSentence);
            });
            return new Paragraph(sentencesComponents);
        }
        return new Paragraph(Collections.emptyList());
    }

    private List<String> returnPunctuationMarks(List<String> punctuationMarks,
                                        List<String> sentencesAfterSplit) {
        List<String> sentences = new ArrayList<>();
        int sentenceNumber = INITIALIZATION_ZERO;
        for (String sentenceAfterSplit : sentencesAfterSplit) {
            String sentenceWithMark = sentenceAfterSplit + punctuationMarks.get(sentenceNumber);
            sentences.add(sentenceWithMark);
            sentenceNumber++;
        }
        return sentences;
    }

    private void extractPunctuationMarks(String text, List<String> punctuationMarks) {
        Map<Integer, String> punctuationMarksOrder = new LinkedHashMap<>();
        List<Integer> ordinalNumberOfPoints = new ArrayList<>();

        findPunctuationMarks(text, punctuationMarksOrder, ordinalNumberOfPoints);
        editThreePointMark(punctuationMarksOrder, ordinalNumberOfPoints);

        Collection<String> markValues = punctuationMarksOrder.values();
        punctuationMarks.addAll(markValues);
    }

    private void findPunctuationMarks(String text,
                                      Map<Integer, String> punctuationMarksOrder,
                                      List<Integer> ordinalNumberOfPoints) {
        char[] charArray = text.toCharArray();
        int ordinalNumber = INITIALIZATION_ZERO;
        for (Character character : charArray) {
            if (character.equals(POINT_MARK) || character.equals(QUESTION_MARK) || character.equals(EXCLAMATION_MARK)) {
                String s = String.valueOf(character);
                punctuationMarksOrder.put(ordinalNumber, s);
                if (character.equals(POINT_MARK)) {
                    ordinalNumberOfPoints.add(ordinalNumber);
                }
            }
            ordinalNumber++;
        }
    }

    private void editThreePointMark(Map<Integer, String> punctuationMarksOrder,
                                    List<Integer> ordinalNumberOfPoints) {
        List<Integer> ordinalNumbersOfThreePoint = new ArrayList<>();
        findThreePointMark(ordinalNumberOfPoints, ordinalNumbersOfThreePoint);
        if (!ordinalNumbersOfThreePoint.isEmpty()) {
            updateThreePointMark(punctuationMarksOrder, ordinalNumbersOfThreePoint);
        }
    }

    private void findThreePointMark(List<Integer> ordinalNumberOfPoints,
                                    List<Integer> ordinalNumbersOfThreePoint) {
        int previousNumber = INITIALIZATION_ZERO;
        for (Integer number : ordinalNumberOfPoints) {
            if (number - previousNumber == 1) {
                ordinalNumbersOfThreePoint.add(number);
            }
            previousNumber = number;
        }
    }

    private void updateThreePointMark(Map<Integer, String> punctuationMarksOrder,
                                      List<Integer> ordinalNumbersOfThreePoint) {
        int threePointIndicator = 1;
        for (Integer number : ordinalNumbersOfThreePoint) {
            if (threePointIndicator == 1) {
                punctuationMarksOrder.put(number - 1, THREE_POINT_MARK);
            }
            punctuationMarksOrder.remove(number);
            threePointIndicator++;
            if (threePointIndicator == 3) {
                threePointIndicator = 1;
            }
        }
    }
}
