package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class SentenceParserTest {

    private static final String SENTENCE = "They will find nothing";

    private SentenceParser sentenceParser = new SentenceParser();

    private Lexeme sentence = Lexeme.expression(SENTENCE);

    @Test
    public void shouldParseAndReturnComponentsWhenGivenSentence(){
        //when
        Component result = sentenceParser.parse(sentence);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(4));

        Assert.assertThat(children.get(0),
                is(Lexeme.word("They")));
        Assert.assertThat(children.get(1),
                is(Lexeme.word("will")));
        Assert.assertThat(children.get(2),
                is(Lexeme.word("find")));
        Assert.assertThat(children.get(3),
                is(Lexeme.word("nothing")));
    }
}
