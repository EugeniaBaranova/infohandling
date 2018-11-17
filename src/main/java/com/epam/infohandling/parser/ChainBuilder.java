package com.epam.infohandling.parser;

public class ChainBuilder {

    public Parser buildChain(){
        return new TextParser(new ParagraphParser(new SentenceParser()));
    }
}
