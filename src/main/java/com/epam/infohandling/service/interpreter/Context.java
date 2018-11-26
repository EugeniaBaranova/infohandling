package com.epam.infohandling.service.interpreter;

import java.util.ArrayDeque;

public class Context {

    private ArrayDeque<Integer> contextValues;

    public Integer popValue() {
        return contextValues.pop();
    }

    public void pushValue(Integer value) {
        contextValues.push(value);
    }
}
