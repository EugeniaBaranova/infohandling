package com.epam.infohandling.entity.composite;

import java.util.List;

public interface Component {

    List<Component> getChildren();

    String getValue();

    ComponentEnum getType();


}
