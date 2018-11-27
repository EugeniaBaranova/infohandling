package com.epam.infohandling.service.sorter.comparator;

import com.epam.infohandling.entity.composite.Component;

import java.util.Comparator;

public class SentencesNumberParagraphComparator implements ComponentComparator{
    @Override
    public Comparator<Component> compare() {
        return Comparator.comparingInt(paragraph -> paragraph.getChildren()
                .size());
    }
}
