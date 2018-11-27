package com.epam.infohandling.service.sorter;

import com.epam.infohandling.entity.*;
import com.epam.infohandling.service.sorter.comparator.ComponentComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentSorter implements Sorter<Component> {

    @Override
    public Component sortBy(Component component, Comparator<Component> comparator) {

        if (component != null) {
            List<Component> children = component.getChildren();
            List<Component> sortedChildren = children.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());

            ComponentEnum type = component.getType();

            switch (type) {
                case SENTENCE:
                    return new Sentences(sortedChildren);
                case PARAGRAPH:
                    return new Paragraph(sortedChildren);
                case TEXT:
                    return new Text(sortedChildren);
            }
        }
        return new Text(Collections.emptyList());
    }
}
