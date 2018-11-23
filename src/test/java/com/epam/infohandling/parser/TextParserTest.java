package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextParserTest {

    private static final String FIRST_PARAGRAPH = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged It was popularised Lorem.";
    private static final String SECOND_PARAGRAPH = "Lorem Ipsum is simply dummy text of ([21][3][5]+-) the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.";
    private static final String THIRD_PARAGRAPH = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.";
    private static final String FORTH_PARAGRAPH = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the ([2][3][56]/-) 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.";

    private static final String TEXT = "    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged It was popularised Lorem.\n" +
            "    Lorem Ipsum is simply dummy text of ([21][3][5]+-) the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the ([2][3][56]/-) 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n";

    private ParagraphParser paragraphParser = mock(ParagraphParser.class);
    private TextParser textParser = new TextParser(paragraphParser);

    private Lexeme text = Lexeme.expression(TEXT);

    @Test
    public void shouldParseAndReturnComponentsWhenGivenText(){
        //given
        when(paragraphParser.parse(any(Lexeme.class))).thenAnswer(i -> i.getArguments()[0]);
        //when
        Component result = textParser.parse(text);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(4));

        Assert.assertThat(children.get(0),
                is(Lexeme.expression(FIRST_PARAGRAPH)));
        Assert.assertThat(children.get(1),
                is(Lexeme.expression(SECOND_PARAGRAPH)));
        Assert.assertThat(children.get(2),
                is(Lexeme.expression(THIRD_PARAGRAPH)));
        Assert.assertThat(children.get(3),
                is(Lexeme.expression(FORTH_PARAGRAPH)));
    }



}
