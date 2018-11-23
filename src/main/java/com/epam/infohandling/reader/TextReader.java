package com.epam.infohandling.reader;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.reader.exception.MissingDataException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {

    private static final Logger logger = Logger.getLogger(TextReader.class);

    public Component read(String filePath) throws MissingDataException {
        if(filePath != null){
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                    line = bufferedReader.readLine();
                }
                String text = stringBuilder.toString();
                return Lexeme.expression(text);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                throw new MissingDataException(e.getMessage(), e);
            }
        }
        return new Composite();
    }
}
