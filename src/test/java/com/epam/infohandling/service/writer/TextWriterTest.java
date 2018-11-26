package com.epam.infohandling.service.writer;

import com.epam.infohandling.entity.composite.Component;
import com.epam.infohandling.entity.composite.Word;
import com.epam.infohandling.service.writer.exeption.WriterException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextWriterTest {

    private static final String FILE_PATH = "./src/test/resource/createdText.txt";
    private static final String NONEXISTENT_FILE_PATH = "xnjksdkv";
    private static final Component TEXT = new Word("Just forget about this.");

    private StringCreator stringCreator = mock(StringCreator.class);
    private TextWriter textWriter = new TextWriter(stringCreator);


    @Test
    public void shouldWriteToFileWhenFilePathIsCorrect() throws WriterException {
        //given
        when(stringCreator.createText(any(Component.class))).thenAnswer(i -> {
            Word entranceParameter = (Word) i.getArguments()[0];
            return Optional.of(entranceParameter.getValue());
        });
        //when
        boolean result = textWriter.write(TEXT, FILE_PATH);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldNotWriteAndReturnFalseWhenGivenNull() throws WriterException {
        //when
        boolean result = textWriter.write(null, null);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldNotWriteAndReturnFalseWhenFilePathIsNotFound() throws WriterException {
        //when
        boolean result = textWriter.write(null, NONEXISTENT_FILE_PATH);
        //then
        Assert.assertFalse(result);
    }
}
