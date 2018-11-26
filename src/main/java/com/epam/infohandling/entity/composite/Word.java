package com.epam.infohandling.entity.composite;

import java.util.Collections;
import java.util.List;

public class Word implements Component {

    private String value;

    public Word(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return value != null ? value.equals(word.value) : word.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public List<Component> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public ComponentEnum getType() {
        return ComponentEnum.WORD;
    }


}
