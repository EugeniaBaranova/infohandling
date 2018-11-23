package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParagraphParserTest {

    private static final String PARAGRAPH = "They will find nothing... That is why you may be calm. Just forget about this.";

    private SentenceParser sentenceParser = mock(SentenceParser.class);
    private ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

    private Lexeme paragraph = Lexeme.expression(PARAGRAPH);

    @Test
    public void shouldParseAndReturnComponentsWhenGivenParagraph(){
        //given
        when(sentenceParser.parse(any(Lexeme.class))).thenAnswer(i -> i.getArguments()[0]);
        //when
        Component result = paragraphParser.parse(paragraph);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(3));

        Assert.assertThat(children.get(0),
                is(Lexeme.expression("They will find nothing")));
        Assert.assertThat(children.get(1),
                is(Lexeme.expression("That is why you may be calm")));
        Assert.assertThat(children.get(2),
                is(Lexeme.expression("Just forget about this")));
    }

}
