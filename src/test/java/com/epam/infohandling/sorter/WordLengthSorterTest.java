package com.epam.infohandling.sorter;

import com.epam.infohandling.composite.Lexeme;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class WordLengthSorterTest {

    private WordLengthSorter wordLengthSorter = new WordLengthSorter();

    private List<Lexeme> lexemes = Arrays.asList();

    @Test
    public void shouldSortDescendingNumberOfCharWhenNumberIsDifferent(){
        //when
        List<Lexeme> result = wordLengthSorter.sort(lexemes);
        //then

    }
}
