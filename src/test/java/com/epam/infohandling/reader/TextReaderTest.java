package com.epam.infohandling.reader;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.reader.exception.MissingDataException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class TextReaderTest {

    private static final String FILE_PATH = "./src/test/resource/text.txt";
    private static final String NONEXISTENT_FILE_NAME = "xnjksdkv";
    private static final String TEXT = "    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged It was popularised Lorem.\n" +
            "    Lorem Ipsum is simply dummy text of ([21][3][5]+-) the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n" +
            "    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the ([2][3][56]/-) 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard.\n";

    private TextReader textReader = new TextReader();

    private Lexeme text = Lexeme.expression(TEXT);

    @Test
    public void shouldReadAndReturnExpressionLexemeWhenDataIsFound() throws MissingDataException {
        //when
        Component result = textReader.read(FILE_PATH);
        //then
        Lexeme lexemeResult = (Lexeme) result;
        Assert.assertThat(lexemeResult, is(text));
    }

    @Test
    public void shouldNotReadAndReturnNewCompositeWhenGivenNull() throws MissingDataException {
        //when
        Component result = textReader.read(null);
        //then
        Composite compositeResult = (Composite) result;
        Assert.assertThat(compositeResult, is(new Composite()));
    }

    @Test (expected = MissingDataException.class)
    public void shouldNotReadAndThrowMissingDataExceptionWhenDataIsNotFound() throws MissingDataException {
        //when
        Component result = textReader.read(NONEXISTENT_FILE_NAME);
    }
}
