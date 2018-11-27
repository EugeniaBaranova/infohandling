package com.epam.infohandling.entity;

import java.util.List;

public interface Component {

    List<Component> getChildren();

    void addChildren(Component component);

    String getValue();

    ComponentEnum getType();


}
