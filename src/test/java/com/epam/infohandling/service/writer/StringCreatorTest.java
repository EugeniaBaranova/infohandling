package com.epam.infohandling.service.writer;

import com.epam.infohandling.entity.composite.Paragraph;
import com.epam.infohandling.entity.composite.Sentences;
import com.epam.infohandling.entity.composite.Text;
import com.epam.infohandling.entity.composite.Word;
import com.epam.infohandling.service.ComponentService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class StringCreatorTest {

    public static final String FIRST_WORD = "Hello";
    public static final String SECOND_WORD = "world.";
    public static final String TEST_STRING = "    Hello world. Hello world.\n    Hello world. Hello world.\n";

    private ComponentService componentService = new ComponentService();
    private StringCreator stringCreator = new StringCreator(componentService);

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
        Word firstWord = new Word(FIRST_WORD);
        Word secondWord = new Word(SECOND_WORD);
        Sentences firstSentence = new Sentences(Arrays.asList(firstWord, secondWord));
        Sentences secondSentence = new Sentences(Arrays.asList(firstWord, secondWord));
        Paragraph firstParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        Paragraph secondParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        Text text = new Text(Arrays.asList(firstParagraph, secondParagraph));
        //when
        Optional<String> result = stringCreator.createText(text);
        //then
        Assert.assertThat(result.get(), is(TEST_STRING));
    }
}
