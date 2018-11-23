package com.epam.infohandling.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private List<Component> components = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Composite composite = (Composite) object;

        return components != null ? components.equals(composite.components) : composite.components == null;
    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }

    public void add(Component component) {
        components.add(component);
    }

    public List<Component> getChildren() {
        return components;
    }
}
