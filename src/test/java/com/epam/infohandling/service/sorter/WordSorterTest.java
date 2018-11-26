package com.epam.infohandling.service.sorter;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class WordSorterTest {

    private static final char SORTING_SYMBOL = 'e';
    private static final char ABSENT_SYMBOL = 'q';

    private WordSorter wordSorter = new WordSorter();

    private String firstWord = "sentence";
    private String secondWord = "then";
    private String thirdWord =  "there";
    private String forthWord = "here";
    private String firstExpression = "good day";
    private String secondExpression = "every day";
    private List<String> differentCharNumberLexemes = Arrays.asList(firstWord, secondWord, thirdWord);
    private List<String> notDifferentCharNumberLexemes = Arrays.asList(firstWord, secondWord, thirdWord, forthWord);
    private List<String> withoutAbsentSymbolLexemes = Arrays.asList(secondWord, thirdWord);


    /*@Test
    public void shouldSortDescendingNumberOfCharWhenNumberIsDifferent(){
        //when
        List<Lexeme> result = wordSorter.sort(differentCharNumberLexemes, SORTING_SYMBOL);
        //then
        Assert.assertThat(result.size(), is(3));
        Lexeme firstSortedLexeme = result.get(0);
        Assert.assertThat(firstSortedLexeme, is(firstWord));
        Lexeme secondSortedLexeme = result.get(1);
        Assert.assertThat(secondSortedLexeme, is(thirdWord));
        Lexeme thirdSortedLexeme = result.get(2);
        Assert.assertThat(thirdSortedLexeme, is(secondWord));
    }

    @Test
    public void shouldSortDescendingNumberOfCharWhenNumberIsNotDifferent(){
        //when
        List<Lexeme> result = wordSorter.sort(notDifferentCharNumberLexemes, SORTING_SYMBOL);
        //then
        Assert.assertThat(result.size(), is(4));
        Lexeme firstSortedLexeme = result.get(0);
        Assert.assertThat(firstSortedLexeme, is(firstWord));
        Lexeme secondSortedLexeme = result.get(1);
        Assert.assertThat(secondSortedLexeme, is(forthWord));
        Lexeme thirdSortedLexeme = result.get(2);
        Assert.assertThat(thirdSortedLexeme, is(thirdWord));
        Lexeme forthSortedLexeme = result.get(3);
        Assert.assertThat(forthSortedLexeme, is(secondWord));
    }

    @Test
    public void shouldNotSortAndReturnEmptyListWhenGivenNullList(){
        //when
        List<Lexeme> result = wordSorter.sort(null, SORTING_SYMBOL);
        //then
        Assert.assertThat(result.size(), is(0));
    }

    @Test
    public void shouldNotSortAndReturnTheSameListWhenSymbolIsAbsent(){
        //when
        List<Lexeme> result = wordSorter.sort(withoutAbsentSymbolLexemes, ABSENT_SYMBOL);
        //then
        Assert.assertThat(result.size(), is(2));
        Lexeme firstLexeme = result.get(0);
        Assert.assertThat(firstLexeme, is(secondWord));
        Lexeme secondLexeme = result.get(1);
        Assert.assertThat(secondLexeme, is(thirdWord));
    }

    @Test
    public void shouldNotSortAndReturnTheSameListWhenGivenNotWords(){
        //given
        List<Lexeme> expressionLexemes = Arrays.asList(firstExpression, secondExpression);
        //when
        List<Lexeme> result = wordSorter.sort(expressionLexemes, SORTING_SYMBOL);
        //then
        Assert.assertThat(result.size(), is(2));
        Lexeme firstLexeme = result.get(0);
        Assert.assertThat(firstLexeme, is(firstExpression));
        Lexeme secondLexeme = result.get(1);
        Assert.assertThat(secondLexeme, is(secondExpression));
    }*/
}
