package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Lexeme;

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

    abstract public Component parse(Lexeme textLexeme);

}
