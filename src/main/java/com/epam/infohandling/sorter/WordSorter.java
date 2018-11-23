package com.epam.infohandling.sorter;

import com.epam.infohandling.composite.Lexeme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordSorter {

    public List<Lexeme> sort(List<Lexeme> lexemes, char sortingSymbol) {
        if (lexemes != null) {

            if(lexemes.stream()
                    .allMatch(Lexeme::isExpression)){
                return lexemes;
            }

            List<String> lexemeValues = new ArrayList<>();
            lexemes.forEach(lexeme -> lexemeValues.add(lexeme.getValue()));

            List<String> sortedString =
                    lexemeValues.stream()
                    .sorted(
                            (String s1, String s2) -> {
                                int repeatsInFirstWord = countRepeats(s1, sortingSymbol);
                                int repeatsInSecondWord = countRepeats(s2, sortingSymbol);
                                int deltaRepeats = repeatsInSecondWord - repeatsInFirstWord;
                                if (repeatsInSecondWord != 0 && repeatsInFirstWord != 0 && deltaRepeats == 0) {
                                    return s1.compareToIgnoreCase(s2);
                                }
                                return deltaRepeats;
                            }).collect(Collectors.toList());

            List<Lexeme> sortedLexemes = new ArrayList<>();
            sortedString.forEach(s -> sortedLexemes.add(Lexeme.word(s)));
            return sortedLexemes;
        }
        return Collections.emptyList();
    }

    private int countRepeats(String word, char sortingSymbol) {
        char[] wordChars = word.toLowerCase()
                .toCharArray();
        int repeatsInWord = 0;
        for (char symbol : wordChars) {
            if (symbol == sortingSymbol) {
                repeatsInWord++;
            }
        }
        return repeatsInWord;
    }
}
