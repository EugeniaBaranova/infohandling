package com.epam.infohandling.parser.reader;

import com.epam.infohandling.parser.reader.exception.MissingDataException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class TextReaderTest {

    private static final String FILE_PATH = "./src/test/resource/text.txt";
    private static final String NONEXISTENT_FILE_PATH = "xnjksdkv";
    private static final String TEXT = "    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged It was popularised Lorem.\n" +
            "    Lorem Ipsum is simply dummy text of ([21][3][5]+-) the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the ([2][3][56]/-) 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n";

    private TextReader textReader = new TextReader();

    @Test
    public void shouldReadAndReturnExpressionLexemeWhenDataIsFound() throws MissingDataException {
        //when
        Optional<String> result = textReader.read(FILE_PATH);
        //then
        Assert.assertTrue(result.isPresent());
        String textString = result.get();
        Assert.assertThat(textString, is(TEXT));
    }

    @Test
    public void shouldNotReadAndReturnNewCompositeWhenGivenNull() throws MissingDataException {
        //when
        Optional<String> result = textReader.read(null);
        //then
        Assert.assertFalse(result.isPresent());
    }

    @Test (expected = MissingDataException.class)
    public void shouldNotReadAndThrowMissingDataExceptionWhenDataIsNotFound() throws MissingDataException {
        //when
        textReader.read(NONEXISTENT_FILE_PATH);
    }
}
