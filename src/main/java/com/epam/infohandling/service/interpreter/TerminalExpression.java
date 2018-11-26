package com.epam.infohandling.service.interpreter;

public class TerminalExpression implements AbstractExpression {

    private static final String PLUS_SIGN = "+";
    private static final String MINUS_SIGN = "-";
    private static final String MULTIPLICATION_SIGN = "*    ";

    private String mathSign;

    public TerminalExpression(String mathSign) {
        this.mathSign = mathSign;
    }

    @Override
    public void interpret(Context context) {
        Integer firstNumber = context.popValue();
        Integer secondNumber = context.popValue();
        Integer result = null;
        switch (mathSign) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }
        context.pushValue(result);
    }
}
