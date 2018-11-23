package com.epam.infohandling.interpreter;

public class NonTerminalExpression implements AbstractExpression {

    private int number;

    public NonTerminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
