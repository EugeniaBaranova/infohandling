package com.epam.infohandling.parser;

import com.epam.infohandling.entity.composite.Component;
import com.epam.infohandling.entity.composite.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class ParserIT {

    private static final String TEXT = "    Hello ([2][3]*) world! Hello ([2][3]*) world!\n    Hello ([2][3]*) world! Hello ([2][3]*) world!\n";
    private static final Component FIRST_WORD = new Word("Hello");
    private static final Component SECOND_WORD = new Word("([2][3]*)");
    private static final Component THIRD_WORD = new Word("world!");

    private ChainBuilder chainBuilder = new ChainBuilder();

    @Test
    public void shouldParseAndReturnComponentsWhenGivenText(){
        //given
        Parser parser = chainBuilder.buildChain();
        //when
        Component result = parser.parse(TEXT);
        //then
        List<Component> paragraphs = result.getChildren();
        Assert.assertThat(paragraphs.size(), is(2));

        Component firstParagraph = paragraphs.get(0);
        List<Component> firstParagraphSentences = firstParagraph.getChildren();
        Assert.assertThat(firstParagraphSentences.size(), is(2));

        Component firstSentence = firstParagraphSentences.get(0);
        List<Component> firstSentenceWords = firstSentence.getChildren();
        Assert.assertThat(firstSentenceWords.size(), is(3));
        Assert.assertThat(firstSentenceWords.get(0), is(FIRST_WORD));
        Assert.assertThat(firstSentenceWords.get(1), is(SECOND_WORD));
        Assert.assertThat(firstSentenceWords.get(2), is(THIRD_WORD));

        Component secondSentence = firstParagraphSentences.get(1);
        List<Component> secondSentenceWords = secondSentence.getChildren();
        Assert.assertThat(secondSentenceWords.size(), is(3));
        Assert.assertThat(secondSentenceWords.get(0), is(FIRST_WORD));
        Assert.assertThat(secondSentenceWords.get(1), is(SECOND_WORD));
        Assert.assertThat(secondSentenceWords.get(2), is(THIRD_WORD));

        Component secondParagraph = paragraphs.get(1);
        List<Component> secondParagraphSentences = secondParagraph.getChildren();
        Assert.assertThat(secondParagraphSentences.size(), is(2));

        Component thirdSentence = secondParagraphSentences.get(0);
        List<Component> thirdSentenceWords = thirdSentence.getChildren();
        Assert.assertThat(thirdSentenceWords.size(), is(3));
        Assert.assertThat(thirdSentenceWords.get(0), is(FIRST_WORD));
        Assert.assertThat(thirdSentenceWords.get(1), is(SECOND_WORD));
        Assert.assertThat(thirdSentenceWords.get(2), is(THIRD_WORD));

        Component forthSentence = secondParagraphSentences.get(1);
        List<Component> forthSentenceWords = forthSentence.getChildren();
        Assert.assertThat(forthSentenceWords.size(), is(3));
        Assert.assertThat(forthSentenceWords.get(0), is(FIRST_WORD));
        Assert.assertThat(forthSentenceWords.get(1), is(SECOND_WORD));
        Assert.assertThat(forthSentenceWords.get(2), is(THIRD_WORD));
    }

    @Test
    public void shouldNotParseAndReturnComponentWithEmptyListWhenGivenNull(){
        //given
        Parser parser = chainBuilder.buildChain();
        //when
        Component result = parser.parse(null);
        //then
        List<Component> children = result.getChildren();
        Assert.assertTrue(children.isEmpty());
    }

}
