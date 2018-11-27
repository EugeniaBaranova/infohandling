package com.epam.infohandling.service.sorter;

import com.epam.infohandling.entity.composite.*;
import com.epam.infohandling.service.sorter.comparator.ComponentComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentSorter {

    private ComponentComparator componentComparator;

    public ComponentSorter(ComponentComparator componentComparator) {
        this.componentComparator = componentComparator;
    }

    public Component sortBy (Component component, ComponentEnum componentName) {

        if (component!=null && componentName!= null) {

            if(componentName.equals(component.getType())){
                List<Component> children = component.getChildren();

                List<Component> sortedChildren = children.stream()
                        .sorted(componentComparator
                                .compare())
                        .collect(Collectors.toList());

                switch(componentName){
                    case SENTENCE:
                        return new Sentences(sortedChildren);
                    case PARAGRAPH:
                        return new Paragraph(sortedChildren);
                    case TEXT:
                        return new Text(sortedChildren);
                }
            }
        }
        return new Text(Collections.emptyList());
    }
}
