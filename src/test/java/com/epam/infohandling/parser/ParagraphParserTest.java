package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;

public class ParagraphParserTest {

    private static final String PARAGRAPH = "They will find nothing. That is why you may be calm. Just forget about this.";

    private SentenceParser sentenceParser = mock(SentenceParser.class);
    private ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

    @Test
    public void shouldParseAndReturnComponentsWhenGivenParagraph(){
        //when
        Component result = paragraphParser.parse(PARAGRAPH);
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
