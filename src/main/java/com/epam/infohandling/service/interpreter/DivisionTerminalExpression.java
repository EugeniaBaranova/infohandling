package com.epam.infohandling.service.interpreter;

public class DivisionTerminalExpression implements AbstractExpression{

    @Override
    public void interpret(Context context) {
        Integer firstNumber = context.popValue();
        Integer secondNumber = context.popValue();
        Integer result = firstNumber / secondNumber;
        context.pushValue(result);
    }
}
