package com.epam.infohandling.parser;

import com.epam.infohandling.entity.composite.Component;

public abstract class Parser {

    protected Parser successor;

    public Parser(Parser successor) {
        this.successor = successor;
    }

    public Parser() {
    }

    public Parser getSuccessor() {
        return successor;
    }

    abstract public Component parse(String text);

}
