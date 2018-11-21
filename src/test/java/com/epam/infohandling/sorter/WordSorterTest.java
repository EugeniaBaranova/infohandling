package com.epam.infohandling.sorter;

import com.epam.infohandling.composite.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class WordSorterTest {

    private WordSorter wordSorter = new WordSorter();

    private Lexeme firstWord = Lexeme.word("sentence");
    private Lexeme secondWord = Lexeme.word("then");
    private Lexeme thirdWord =  Lexeme.word("there");
    private Lexeme forthWord = Lexeme.word("here");
    private List<Lexeme> differentCharNumberLexemes = Arrays.asList(firstWord, secondWord, thirdWord);

    @Test
    public void shouldSortDescendingNumberOfCharWhenNumberIsDifferent(){
        //given

        //when
        List<Lexeme> result = wordSorter.sort(differentCharNumberLexemes, 'e');
        //then
        Assert.assertThat(result.size(), is(3));
        Lexeme firstSortedLexeme = result.get(0);
        Assert.assertThat(firstSortedLexeme, is(firstWord));
        Lexeme secondSortedLexeme = result.get(1);
        Assert.assertThat(secondSortedLexeme, is(thirdWord));
        Lexeme thirdSortedLexeme = result.get(2);
        Assert.assertThat(thirdSortedLexeme, is(secondWord));
    }

    //TODO
    @Test
    public void shouldSortDescendingNumberOfCharWhenNumberIsNotDifferent(){
        //given

        //when
        List<Lexeme> result = wordSorter.sort(differentCharNumberLexemes, 'e');
        //then
        Assert.assertThat(result.size(), is(3));
        Lexeme firstSortedLexeme = result.get(0);
        Assert.assertThat(firstSortedLexeme, is(firstWord));
        Lexeme secondSortedLexeme = result.get(1);
        Assert.assertThat(secondSortedLexeme, is(thirdWord));
        Lexeme thirdSortedLexeme = result.get(2);
        Assert.assertThat(thirdSortedLexeme, is(secondWord));
    }
}
