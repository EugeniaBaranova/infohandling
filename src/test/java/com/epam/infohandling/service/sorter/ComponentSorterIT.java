package com.epam.infohandling.service.sorter;

import com.epam.infohandling.entity.composite.*;
import com.epam.infohandling.service.sorter.comparator.SentencesNumberParagraphComparator;
import com.epam.infohandling.service.sorter.comparator.SymbolNumberDescWordComparator;
import com.epam.infohandling.service.sorter.comparator.WordLengthComparator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class ComponentSorterIT {

    private static final char SORTING_SYMBOL = 'e';

    private static final Component FIRST_WORD = new Word("sentence");
    private static final Component SECOND_WORD = new Word("then");
    private static final Component THIRD_WORD = new Word("there");
    private static final Component FORTH_WORD = new Word("here");
    private static final Component FIFTH_WORD = new Word("black");
    private static final Component SIXTH_WORD = new Word("bad");
    private static final Component DIFFERENT_CHAR_NUMBER_SENTENCE =
            new Sentences(Arrays.asList(FIRST_WORD, SECOND_WORD, THIRD_WORD));
    private static final Component NOT_DIFFERENT_CHAR_NUMBER_SENTENCE =
            new Sentences(Arrays.asList(FIRST_WORD, SECOND_WORD, THIRD_WORD, FORTH_WORD));
    private static final Component WITHOUT_SORTING_SYMBOL_SENTENCE =
            new Sentences(Arrays.asList(FIFTH_WORD, SIXTH_WORD));
    private static final Component FIRST_PARAGRAPH =
            new Paragraph(Arrays.asList(DIFFERENT_CHAR_NUMBER_SENTENCE,
                    DIFFERENT_CHAR_NUMBER_SENTENCE, DIFFERENT_CHAR_NUMBER_SENTENCE));
    private static final Component SECOND_PARAGRAPH =
            new Paragraph(Arrays.asList(WITHOUT_SORTING_SYMBOL_SENTENCE, WITHOUT_SORTING_SYMBOL_SENTENCE,
                    WITHOUT_SORTING_SYMBOL_SENTENCE, WITHOUT_SORTING_SYMBOL_SENTENCE));
    private static final Component THIRD_PARAGRAPH =
            new Paragraph(Arrays.asList(NOT_DIFFERENT_CHAR_NUMBER_SENTENCE, NOT_DIFFERENT_CHAR_NUMBER_SENTENCE));
    private static final Component TEXT =
            new Text(Arrays.asList(FIRST_PARAGRAPH, SECOND_PARAGRAPH, THIRD_PARAGRAPH));

    @Test
    public void shouldSortWordsBySymbolNumberDescWhenNumberIsDifferent() {
        //given
        ComponentSorter componentSorter = new ComponentSorter(new SymbolNumberDescWordComparator(SORTING_SYMBOL));
        //when
        Component result = componentSorter.sortBy(DIFFERENT_CHAR_NUMBER_SENTENCE, ComponentEnum.SENTENCE);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(3));
        Assert.assertThat(children.get(0), is(FIRST_WORD));
        Assert.assertThat(children.get(1), is(THIRD_WORD));
        Assert.assertThat(children.get(2), is(SECOND_WORD));
    }

    @Test
    public void shouldSortWordsBySymbolNumberDescWhenNumberIsNotDifferent() {
        //given
        ComponentSorter componentSorter = new ComponentSorter(new SymbolNumberDescWordComparator(SORTING_SYMBOL));
        //when
        Component result = componentSorter.sortBy(NOT_DIFFERENT_CHAR_NUMBER_SENTENCE, ComponentEnum.SENTENCE);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(4));
        Assert.assertThat(children.get(0), is(FIRST_WORD));
        Assert.assertThat(children.get(1), is(FORTH_WORD));
        Assert.assertThat(children.get(2), is(THIRD_WORD));
        Assert.assertThat(children.get(3), is(SECOND_WORD));
    }

    @Test
    public void shouldNotSortWordsBySymbolNumberDescWhenGivenNull() {
        //given
        ComponentSorter componentSorter = new ComponentSorter(new SymbolNumberDescWordComparator(SORTING_SYMBOL));
        //when
        Component result = componentSorter.sortBy(null, ComponentEnum.SENTENCE);
        //then
        List<Component> children = result.getChildren();
        Assert.assertTrue(children.isEmpty());
    }

    @Test
    public void shouldNotSortWordsBySymbolNumberDescWhenSymbolIsAbsent() {
        //given
        ComponentSorter componentSorter = new ComponentSorter(new SymbolNumberDescWordComparator(SORTING_SYMBOL));
        //when
        Component result = componentSorter.sortBy(WITHOUT_SORTING_SYMBOL_SENTENCE, ComponentEnum.SENTENCE);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(2));
        Assert.assertThat(children.get(0), is(FIFTH_WORD));
        Assert.assertThat(children.get(1), is(SIXTH_WORD));
    }

    @Test
    public void shouldSortWordsByLengthWhenGivenSentence() {
        //given
        ComponentSorter componentSorter = new ComponentSorter(new WordLengthComparator());
        //when
        Component result = componentSorter.sortBy(DIFFERENT_CHAR_NUMBER_SENTENCE, ComponentEnum.SENTENCE);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(3));
        Assert.assertThat(children.get(0), is(SECOND_WORD));
        Assert.assertThat(children.get(1), is(THIRD_WORD));
        Assert.assertThat(children.get(2), is(FIRST_WORD));
    }

    @Test
    public void shouldSortParagraphsBySentencesNumberWhenGivenText() {
        //given
        ComponentSorter componentSorter = new ComponentSorter(new SentencesNumberParagraphComparator());
        //when
        Component result = componentSorter.sortBy(TEXT, ComponentEnum.TEXT);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(3));
        Assert.assertThat(children.get(0), is(THIRD_PARAGRAPH));
        Assert.assertThat(children.get(1), is(FIRST_PARAGRAPH));
        Assert.assertThat(children.get(2), is(SECOND_PARAGRAPH));
    }


}
