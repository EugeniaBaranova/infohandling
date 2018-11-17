package com.epam.infohandling.composite;

import java.util.List;

public interface Component {

    void add(Component component);

    List<Component> getChildren();

}
