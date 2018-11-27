package com.epam.infohandling.service.sorter.comparator;

import com.epam.infohandling.entity.composite.Component;

import java.util.Comparator;

public class WordLengthComparator implements ComponentComparator{
    @Override
    public Comparator<Component> compare() {
        return Comparator.comparingInt(word -> word.getValue()
                .length());
    }
}
