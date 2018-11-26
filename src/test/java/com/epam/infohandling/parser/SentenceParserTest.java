package com.epam.infohandling.parser;

import com.epam.infohandling.entity.composite.Component;
import com.epam.infohandling.entity.composite.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class SentenceParserTest {

    private static final String SENTENCE = "They will find nothing";

    private SentenceParser sentenceParser = new SentenceParser();

    private Component firstWord = new Word("They");
    private Component secondWord = new Word("will");
    private Component thirdWord = new Word("find");
    private Component forthWord = new Word("nothing");



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
}
