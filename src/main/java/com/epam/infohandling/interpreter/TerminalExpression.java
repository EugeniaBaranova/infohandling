package com.epam.infohandling.interpreter;

public class TerminalExpression implements AbstractExpression {

    private String mathSign;

    public TerminalExpression(String mathSign) {
        this.mathSign = mathSign;
    }

    @Override
    public void interpret(Context context) {
        Integer firstNumber = context.popValue();
        Integer secondNumber = context.popValue();

    }
}
