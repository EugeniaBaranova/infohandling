package com.epam.infohandling.parser;

import com.epam.infohandling.entity.composite.Component;
import com.epam.infohandling.entity.composite.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class SentenceParserTest {

    private static final String SENTENCE = "They ([2][23]+) found nothing.";

    private SentenceParser sentenceParser = new SentenceParser();

    private Component firstWord = new Word("They");
    private Component secondWord = new Word("([2][23]+)");
    private Component thirdWord = new Word("found");
    private Component forthWord = new Word("nothing.");



    @Test
    public void shouldParseAndReturnComponentsWhenGivenSentence(){
        //when
        Component result = sentenceParser.parse(SENTENCE);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(4));

        Assert.assertThat(children.get(0), is(firstWord));
        Assert.assertThat(children.get(1), is(secondWord));
        Assert.assertThat(children.get(2), is(thirdWord));
        Assert.assertThat(children.get(3), is(forthWord));
    }

    @Test
    public void shouldNotParseAndReturnComponentWithEmptyListWhenGivenNull(){
        //when
        Component result = sentenceParser.parse(null);
        //then
        List<Component> children = result.getChildren();
        Assert.assertTrue(children.isEmpty());
    }
}
