package com.epam.infohandling.service.interpreter;

import com.epam.infohandling.entity.*;
import com.epam.infohandling.service.ComponentServiceImpl;

import java.util.*;

import static com.epam.infohandling.entity.ComponentEnum.PARAGRAPH;
import static com.epam.infohandling.entity.ComponentEnum.SENTENCE;
import static com.epam.infohandling.entity.ComponentEnum.WORD;

public class ExpressionCounter {

    private static final String EXPRESSION_REGEX = "\\((\\[\\d+])+((\\*)|(/)|(\\+)|(-))+\\)";

    private Context context;

    private AbstractExpression plusExpression;
    private AbstractExpression minusExpression;
    private AbstractExpression multiplicationExpression;
    private AbstractExpression divisionExpression;

    private ComponentServiceImpl componentServiceImpl;

    public ExpressionCounter() {
        this.context = new Context();
        this.plusExpression = new PlusTerminalExpression();
        this.minusExpression = new MinusTerminalExpression();
        this.multiplicationExpression = new MultiplicationTerminalExpression();
        this.divisionExpression = new DivisionTerminalExpression();
        this.componentServiceImpl = new ComponentServiceImpl();
    }

    public Component countExpression(Component text) {
        if (text != null) {

            List<Component> paragraphs = componentServiceImpl.getParagraph(text);
            for (Component paragraph : paragraphs) {

                List<Component> sentences = componentServiceImpl.getSentences(paragraph);
                for (Component sentence : sentences) {

                    List<Component> words = componentServiceImpl.getWords(sentence);

                    for (Component word : words) {
                        String wordValue = word.getValue();
                        if (wordValue.matches(EXPRESSION_REGEX)) {
                            parseExpression(wordValue);
                            updateList(words, word, WORD, Collections.emptyList());
                        }
                    }
                    updateList(sentences, sentence, SENTENCE, words);
                }
                updateList(paragraphs, paragraph, PARAGRAPH, sentences);
            }
            return new Text(paragraphs);
        }
        return new Text(Collections.emptyList());
    }

    private void parseExpression(String wordValue) {
        if (wordValue != null) {
            int wordLength = wordValue.length();
            String wordWithoutParentheses = wordValue.substring(1, wordLength - 1);
            String[] splittedExpression = wordWithoutParentheses.split("\\[");
            ArrayDeque<Integer> numbers = new ArrayDeque<>();
            for (String expression : splittedExpression) {
                String[] cleanExpressions = expression.split("]");
                String cleanExpression = cleanExpressions[0];
                if (cleanExpression.matches("\\d+")) {
                    Integer number = Integer.valueOf(cleanExpressions[0]);
                    numbers.push(number);
                }
                if (cleanExpressions.length == 2) {
                    for (Integer number : numbers) {
                        AbstractExpression nonTerminalExpression = new NonTerminalExpression(number);
                        nonTerminalExpression.interpret(context);
                    }
                    interpretTerminalExpressions(cleanExpressions[1]);
                }
            }
        }
    }

    private void updateList(List<Component> components, Component currentComponent,
                            ComponentEnum componentName, List<Component> innerComponents) {
        if (components != null && currentComponent != null && componentName != null && innerComponents != null) {
            int componentIndex = components.indexOf(currentComponent);
            Component countedComponent = null;
            switch (componentName) {
                case WORD:
                    Integer result = context.popValue();
                    countedComponent = new Word(String.valueOf(result));
                    break;
                case SENTENCE:
                    countedComponent = new Sentences(innerComponents);
                    break;
                case PARAGRAPH:
                    countedComponent = new Paragraph(innerComponents);
                    break;
            }
            components.set(componentIndex, countedComponent);
        }
    }

    private void interpretTerminalExpressions(String sings) {
        if (sings != null) {
            char[] mathSings = sings.toCharArray();
            for (char mathSing : mathSings) {
                switch (mathSing) {
                    case '+':
                        plusExpression.interpret(context);
                        break;
                    case '-':
                        minusExpression.interpret(context);
                        break;
                    case '/':
                        divisionExpression.interpret(context);
                        break;
                    case '*':
                        multiplicationExpression.interpret(context);
                        break;
                }
            }
        }
    }
}
