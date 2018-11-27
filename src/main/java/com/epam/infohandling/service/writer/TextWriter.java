package com.epam.infohandling.service.writer;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.service.writer.exeption.WriterException;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class TextWriter {

    private static final Logger logger = Logger.getLogger(TextWriter.class);

    private StringCreator stringCreator;

    public TextWriter(StringCreator stringCreator) {
        this.stringCreator = stringCreator;
    }

    public boolean write(Component text, String filePath) throws WriterException {
        try {
            if (text != null && filePath != null) {

                File file = new File(filePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                Optional<String> textStringOptional = stringCreator.createText(text);
                if (textStringOptional.isPresent()) {
                    String textString = textStringOptional.get();
                    bufferedWriter.write(textString);
                    bufferedWriter.close();
                    return true;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new WriterException(e.getMessage(), e);
        }
        return false;
    }

}
