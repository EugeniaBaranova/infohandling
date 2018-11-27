package com.epam.infohandling.service.sorter.comparator;

import com.epam.infohandling.entity.composite.Component;

import java.util.Comparator;

public class SymbolNumberDescWordComparator implements ComponentComparator {

    private char sortingSymbol;

    public SymbolNumberDescWordComparator(char sortingSymbol) {
        this.sortingSymbol = sortingSymbol;
    }

    @Override
    public Comparator<Component> compare() {
        return (w1, w2) -> {
            String w1Value = w1.getValue();
            String w2Value = w2.getValue();

            int repeatsInFirstWord = countRepeats(w1Value, sortingSymbol);
            int repeatsInSecondWord = countRepeats(w2Value, sortingSymbol);
            int deltaRepeats = repeatsInSecondWord - repeatsInFirstWord;
            if (repeatsInSecondWord != 0 && repeatsInFirstWord != 0 && deltaRepeats == 0) {
                return w1Value.compareToIgnoreCase(w2Value);
            }
            return deltaRepeats;
        };
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
