package com.epam.infohandling.service.sorter;

import java.util.*;
import java.util.stream.Collectors;

public class WordSorter {


    /*static public List<Lexeme> sort(List<Lexeme> lexemes, Comparator<Lexeme> sortedBy){
        List<Lexeme> sortedLexemes = new ArrayList<>(lexemes);
        Collections.sort(sortedLexemes, sortedBy);
        return sortedLexemes;
    }


    public static void main(String[] args) {
        char ch = 'a';
        sort(new ArrayList<>(), new Comparator<Lexeme>() {
            @Override
            public int compare(Lexeme lexemeFirst, Lexeme lexemeSecond) {
                return count(ch,lexemeFirst.getValue()) - count(ch, lexemeSecond.getValue());
            }
        });
    }

    private static int count(char ch, String word){
        if(word != null){
            int count = 0;
            char[] chars = word.toCharArray();
            for(char buf : chars){

                if(buf == ch){
                    count++;
                }
            }
            return count;
        }
        return 0;
    }



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
    }*/
}
