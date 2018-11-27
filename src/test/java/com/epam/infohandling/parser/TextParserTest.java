package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextParserTest {

    private static final String TEXT = "    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged It was popularised Lorem.\n" +
            "    Lorem Ipsum is simply dummy text of ([21][3][5]+-) the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the ([2][3][56]/-) 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n";

    private static final Component FIRST_PARAGRAPH = new Word("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged It was popularised Lorem.");
    private static final Component SECOND_PARAGRAPH = new Word("Lorem Ipsum is simply dummy text of ([21][3][5]+-) the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.");
    private static final Component THIRD_PARAGRAPH = new Word("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.");
    private static final Component FORTH_PARAGRAPH = new Word("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the ([2][3][56]/-) 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.");

    private ParagraphParser paragraphParser = mock(ParagraphParser.class);
    private TextParser textParser = new TextParser(paragraphParser);

    @Test
    public void shouldParseAndReturnComponentsWhenGivenText(){
        //given
        when(paragraphParser.parse(any(String.class))).thenAnswer(i -> {
            String entranceParameter = (String) i.getArguments()[0];
            return new Word(entranceParameter);
        });
        //when
        Component result = textParser.parse(TEXT);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(4));

        Assert.assertThat(children.get(0), is(FIRST_PARAGRAPH));
        Assert.assertThat(children.get(1), is(SECOND_PARAGRAPH));
        Assert.assertThat(children.get(2), is(THIRD_PARAGRAPH));
        Assert.assertThat(children.get(3), is(FORTH_PARAGRAPH));
    }

    @Test
    public void shouldNotParseAndReturnComponentWithEmptyListWhenGivenNull(){
        //when
        Component result = textParser.parse(null);
        //then
        List<Component> children = result.getChildren();
        Assert.assertTrue(children.isEmpty());
    }

}
