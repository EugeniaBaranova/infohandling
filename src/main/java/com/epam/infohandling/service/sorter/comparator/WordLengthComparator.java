package com.epam.infohandling.service.sorter.comparator;

import com.epam.infohandling.entity.Component;

import java.util.Comparator;

public class WordLengthComparator implements Comparator<Component>{

    @Override
    public int compare(Component o1, Component o2) {
        return o1.getValue().length() - o2.getValue().length();
    }
}
