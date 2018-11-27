package com.epam.infohandling.parser;

import com.epam.infohandling.entity.composite.Component;
import com.epam.infohandling.entity.composite.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParagraphParserTest {

    private static final String PARAGRAPH = "They will find nothing... That is why you may be calm. Just forget about this.";
    private static final Component FIRST_SENTENCE = new Word("They will find nothing");
    private static final Component SECOND_SENTENCE = new Word("That is why you may be calm");
    private static final Component THIRD_SENTENCE = new Word("Just forget about this");

    private SentenceParser sentenceParser = mock(SentenceParser.class);
    private ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);


    @Test
    public void shouldP(){
        List<String> sings = new ArrayList<>();
        List<Integer> numberPoint = new ArrayList<>();
        char[] charArray = PARAGRAPH.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char c : charArray) {
            characters.add(c);
        }
        for (Character character : characters) {
            int poryadkNumber = 0;
            if (character.equals('.') || character.equals('?') || character.equals('!')){
                String s = String.valueOf(character);
                sings.add(s);
                if (character.equals('.')){
                    numberPoint.add(poryadkNumber);
                }
            }
            poryadkNumber++;
        }


        String s = "([2][23][3]*-)";
        String EXPRESSION_REGEX = "\\((\\[\\d+])+((\\*)|(/)|(\\+)|(-))+\\)";
        System.out.println(s.matches(EXPRESSION_REGEX));
        int length = s.length();
        String substring1 = s.substring(1, length - 1);
        String[] splitS1 = substring1.split("\\[");
        for (String s1 : splitS1) {
            String[] split = s1.split("]");
            System.out.println(split[0]);

        }

    }

    @Test
    public void shouldParseAndReturnComponentsWhenGivenParagraph(){
        //given
        when(sentenceParser.parse(any(String.class))).thenAnswer(i -> {
            String entranceParameter = (String) i.getArguments()[0];
            return new Word(entranceParameter);
        });
        //when
        Component result = paragraphParser.parse(PARAGRAPH);
        //then
        List<Component> children = result.getChildren();
        Assert.assertThat(children.size(), is(3));

        Assert.assertThat(children.get(0), is(FIRST_SENTENCE));
        Assert.assertThat(children.get(1), is(SECOND_SENTENCE));
        Assert.assertThat(children.get(2), is(THIRD_SENTENCE));
    }

}
