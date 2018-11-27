package com.epam.infohandling.entity.composite;

import java.util.List;

public class Text implements Component {

    private List<Component> components;

    public Text(List<Component> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text = (Text) o;

        return components != null ? components.equals(text.components) : text.components == null;
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
        return ComponentEnum.TEXT;
    }
}
