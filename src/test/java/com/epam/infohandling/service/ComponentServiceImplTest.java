package com.epam.infohandling.service;

import com.epam.infohandling.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class ComponentServiceImplTest {

    private static final String FIRST_WORD = "Hello";
    private static final String SECOND_WORD = "world.";

    private ComponentService componentServiceImpl = new ComponentServiceImpl();

    private Component firstWord;
    private Component secondWord;
    private Component firstSentence;
    private Component secondSentence;
    private Component firstParagraph;
    private Component secondParagraph;
    private Component text;

    @Before
    public void setUp(){
        firstWord = new Word(FIRST_WORD);
        secondWord = new Word(SECOND_WORD);
        firstSentence = new Sentences(Arrays.asList(firstWord, secondWord));
        secondSentence = new Sentences(Arrays.asList(firstWord, secondWord));
        firstParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        secondParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        text = new Text(Arrays.asList(firstParagraph, secondParagraph));
    }

    @Test
    public void shouldGetParagraphsWhenGivenText(){
        //when
        List<Component> result = componentServiceImpl.getParagraph(text);
        //then
        Assert.assertThat(result.size(), is(2));

        Component firstParagraph = result.get(0);
        Assert.assertThat(firstParagraph, is(this.firstParagraph));
        Component secondParagraph = result.get(1);
        Assert.assertThat(secondParagraph, is(this.secondParagraph));
    }

    @Test
    public void shouldNotGetParagraphsAndGetEmptyListWhenGivenNull(){
        //when
        List<Component> result = componentServiceImpl.getParagraph(null);
        //then
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldGetSentencesWhenGivenText(){
        //when
        List<Component> result = componentServiceImpl.getSentences(text);
        //then
        Assert.assertThat(result.size(), is(4));

        Component firstSentence = result.get(0);
        Assert.assertThat(firstSentence, is(this.firstSentence));
        Component secondSentence = result.get(1);
        Assert.assertThat(secondSentence, is(this.secondSentence));
        Component thirdSentence = result.get(2);
        Assert.assertThat(thirdSentence, is(this.firstSentence));
        Component forthSentence = result.get(3);
        Assert.assertThat(forthSentence, is(this.secondSentence));
    }

    @Test
    public void shouldNotGetSentencesAndGetEmptyListWhenGivenNull(){
        //when
        List<Component> result = componentServiceImpl.getSentences(null);
        //then
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldGetWordsWhenGivenText(){
        //when
        List<Component> result = componentServiceImpl.getWords(text);
        //then
        Assert.assertThat(result.size(), is(8));

        Component firstWord = result.get(0);
        Assert.assertThat(firstWord, is(this.firstWord));
        Component secondWord = result.get(1);
        Assert.assertThat(secondWord, is(this.secondWord));
        Component thirdWord = result.get(2);
        Assert.assertThat(thirdWord, is(this.firstWord));
        Component forthWord = result.get(3);
        Assert.assertThat(forthWord, is(this.secondWord));
        Component fifthWord = result.get(4);
        Assert.assertThat(fifthWord, is(this.firstWord));
        Component sixthWord = result.get(5);
        Assert.assertThat(sixthWord, is(this.secondWord));
        Component seventhWord = result.get(6);
        Assert.assertThat(seventhWord, is(this.firstWord));
        Component eighthWord = result.get(7);
        Assert.assertThat(eighthWord, is(this.secondWord));
    }

    @Test
    public void shouldNotGetWordsAndGetEmptyListWhenGivenNull(){
        //when
        List<Component> result = componentServiceImpl.getWords(null);
        //then
        Assert.assertTrue(result.isEmpty());
    }
}
