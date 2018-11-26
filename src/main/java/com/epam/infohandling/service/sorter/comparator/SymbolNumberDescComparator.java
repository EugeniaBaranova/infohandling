package com.epam.infohandling.service.sorter.comparator;

import com.epam.infohandling.entity.composite.Component;

import java.util.Comparator;

public class SymbolNumberDescComparator implements ComponentComparator {

    private char symbol;

    public SymbolNumberDescComparator(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public Comparator<Component> compare() {
        return null;
    }
}
