package com.epam.infohandling.sorter;

import com.epam.infohandling.composite.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class WordSorterTest {

    private static final char SORTING_SYMBOL = 'e';
    private static final char ABSENT_SYMBOL = 'q';

    private WordSorter wordSorter = new WordSorter();

    private Lexeme firstWord = Lexeme.word("sentence");
    private Lexeme secondWord = Lexeme.word("then");
    private Lexeme thirdWord =  Lexeme.word("there");
    private Lexeme forthWord = Lexeme.word("here");
    private List<Lexeme> differentCharNumberLexemes = Arrays.asList(firstWord, secondWord, thirdWord);
    private List<Lexeme> notDifferentCharNumberLexemes = Arrays.asList(firstWord, secondWord, thirdWord, forthWord);
    private List<Lexeme> withoutAbsentSymbolLexemes = Arrays.asList(secondWord, thirdWord);


    @Test
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
        Lexeme firstSortedLexeme = result.get(0);
        Assert.assertThat(firstSortedLexeme, is(secondWord));
        Lexeme secondSortedLexeme = result.get(1);
        Assert.assertThat(secondSortedLexeme, is(thirdWord));
    }
}
