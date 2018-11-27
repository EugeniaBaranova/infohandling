package com.epam.infohandling.service;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.ComponentEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComponentServiceImpl implements ComponentService{

    public List<Component> getParagraph(Component text) {
        return getComponents(ComponentEnum.PARAGRAPH, text);
    }

    public List<Component> getSentences(Component text) {
        return getComponents(ComponentEnum.SENTENCE, text);
    }

    public List<Component> getWords(Component text) {
        return getComponents(ComponentEnum.WORD, text);
    }


    private List<Component> getComponents(ComponentEnum type, Component text) {

        if (text != null) {
            List<Component> saveTo = new ArrayList<>();
            fillComponentsAccordingBy(type, text, saveTo);
            return saveTo;
        }
        return Collections.emptyList();
    }

    private void fillComponentsAccordingBy(ComponentEnum type, Component parent, List<Component> saveTo) {

        if (parent != null) {
            if (parent.getType().equals(type)) {
                saveTo.add(parent);
            }

            List<Component> children = parent.getChildren();
            for (Component component : children) {
                fillComponentsAccordingBy(type, component, saveTo);
            }
        }
    }

}
