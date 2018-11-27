package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.Sentences;
import com.epam.infohandling.entity.Word;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SentenceParser extends Parser {

    private static final String SPACE = " ";

    @Override
    public Component parse(String text) {

        if(text != null){
            String[] splittedSentence = text.split(SPACE);
            List<String> words = Arrays.asList(splittedSentence);
            List<Component> wordComponents = words.stream()
                    .map(value -> (Component) new Word(value))
                    .collect(Collectors.toList());
            return new Sentences(wordComponents);
        }
        return new Sentences(Collections.emptyList());
    }
}
