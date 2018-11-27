package com.epam.infohandling.service.writer;

import com.epam.infohandling.entity.*;
import com.epam.infohandling.service.ComponentServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class StringCreatorTest {

    private static final String FIRST_WORD = "Hello";
    private static final String SECOND_WORD = "world.";
    private static final String TEST_STRING = "    Hello world. Hello world.\n    Hello world. Hello world.\n";

    private ComponentServiceImpl componentServiceImpl = new ComponentServiceImpl();
    private StringCreator stringCreator = new StringCreator(componentServiceImpl);

    @Test
    public void shouldCreateTextWhenTextComponent(){
        //when
        Optional<String> result = stringCreator.createText(null);
        //then
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotCreateTextAndReturnEmptyOptionalWhenGivenNull(){
        //given
        Component firstWord = new Word(FIRST_WORD);
        Component secondWord = new Word(SECOND_WORD);
        Component firstSentence = new Sentences(Arrays.asList(firstWord, secondWord));
        Component secondSentence = new Sentences(Arrays.asList(firstWord, secondWord));
        Component firstParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        Component secondParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        Component text = new Text(Arrays.asList(firstParagraph, secondParagraph));
        //when
        Optional<String> result = stringCreator.createText(text);
        //then
        Assert.assertThat(result.get(), is(TEST_STRING));
    }
}
