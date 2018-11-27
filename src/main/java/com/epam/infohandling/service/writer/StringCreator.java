package com.epam.infohandling.service.writer;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.ComponentEnum;
import com.epam.infohandling.service.ComponentServiceImpl;

import java.util.List;
import java.util.Optional;

public class StringCreator {

    private static final String SPACE_BETWEEN_WORDS = " ";
    private static final String SPACES_BEFORE_PARAGRAPH = "   ";
    private static final String NEW_LINE_SIGN = "\n";

    private ComponentServiceImpl componentServiceImpl;

    public StringCreator(ComponentServiceImpl componentServiceImpl) {
        this.componentServiceImpl = componentServiceImpl;
    }

    public Optional<String> createText(Component text) {
        if (text != null) {
            StringBuilder stringBuilder = new StringBuilder();
            List<Component> paragraphs = componentServiceImpl.getParagraph(text);
            if (ComponentEnum.TEXT.equals(
                    text.getType())) {
                for (Component paragraph : paragraphs) {
                    stringBuilder.append(SPACES_BEFORE_PARAGRAPH);

                    appendWords(stringBuilder, paragraph);

                    stringBuilder.append(NEW_LINE_SIGN);
                }
            }
            String textString = stringBuilder.toString();
            return Optional.of(textString);
        }
        return Optional.empty();
    }

    private void appendWords(StringBuilder stringBuilder, Component component) {
        if(stringBuilder != null && component != null){
            List<Component> words = componentServiceImpl.getWords(component);
            words.forEach(word -> {
                String wordValue = word.getValue();
                stringBuilder.append(SPACE_BETWEEN_WORDS);
                stringBuilder.append(wordValue);
            });
        }
    }
}
