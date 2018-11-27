package com.epam.infohandling.entity.composite;

import java.util.List;

public class Sentences implements Component {

    private List<Component> components;

    public Sentences(List<Component> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentences sentences = (Sentences) o;

        return components != null ? components.equals(sentences.components) : sentences.components == null;
    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }

    @Override
    public List<Component> getChildren() {
        return components;
    }

    @Override
    public void addChildren(Component component) {
        components.add(component);
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public ComponentEnum getType() {
        return ComponentEnum.SENTENCE;
    }
}
