package com.epam.infohandling.parser.reader;

import com.epam.infohandling.parser.reader.exception.MissingDataException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class TextReader {

    private static final Logger logger = Logger.getLogger(TextReader.class);

    public Optional<String> read(String filePath) throws MissingDataException {
        if(filePath != null){
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                    line = bufferedReader.readLine();
                }
                return Optional.of(stringBuilder.toString());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                throw new MissingDataException(e.getMessage(), e);
            }
        }
        return Optional.empty();
    }
}
