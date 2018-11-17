package com.epam.infohandling.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private List<Component> components = new ArrayList<>();


    public void add(Component component) {
        components.add(component);
    }

    public List<Component> getChildren() {
        return components;
    }
}
