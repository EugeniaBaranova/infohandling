package com.epam.infohandling.service.sorter;

import com.epam.infohandling.entity.composite.Component;
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

    public List<Component> sortBy(List<Component> component) {

        if (component!=null) {

            return component
                    .stream()
                    .sorted(componentComparator
                            .compare())
                    .collect(Collectors.toList());
        }
        //TODO better!
        return Collections.emptyList();
    }
}
