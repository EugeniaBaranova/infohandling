package com.epam.infohandling.service.sorter.comparator;

import com.epam.infohandling.entity.composite.Component;

import java.util.Comparator;

public interface ComponentComparator {
    Comparator<Component> compare();
}
