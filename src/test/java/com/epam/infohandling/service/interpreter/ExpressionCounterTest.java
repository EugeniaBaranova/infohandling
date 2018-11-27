package com.epam.infohandling.service.interpreter;

import com.epam.infohandling.entity.composite.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class ExpressionCounterTest {

    private static final String FIRST_WORD = "Hello";
    private static final String EXPRESSION = "([2][23][3]*-)";
    private static final String COUNTED_EXPRESSION = "43";
    private static final String SECOND_WORD = "world.";

    private ExpressionCounter expressionCounter = new ExpressionCounter();

    private Component firstWord = new Word(FIRST_WORD);
    private Component countedExpression = new Word(COUNTED_EXPRESSION);
    private Component secondWord = new Word(SECOND_WORD);
    private Component firstSentence = new Sentences(Arrays.asList(firstWord, countedExpression, secondWord));
    private Component secondSentence = new Sentences(Arrays.asList(firstWord, secondWord));
    private Component firstParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
    private Component secondParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
    private Component countedText = new Text(Arrays.asList(firstParagraph, secondParagraph));

    @Test
    public void shouldCountExpressionWhenGivenText(){
        //given
        Component firstWord = new Word(FIRST_WORD);
        Component expression = new Word(EXPRESSION);
        Component secondWord = new Word(SECOND_WORD);
        Component firstSentence = new Sentences(Arrays.asList(firstWord, expression, secondWord));
        Component secondSentence = new Sentences(Arrays.asList(firstWord, secondWord));
        Component firstParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        Component secondParagraph = new Paragraph(Arrays.asList(firstSentence, secondSentence));
        Component text = new Text(Arrays.asList(firstParagraph, secondParagraph));
        //when
        Component result = expressionCounter.countExpression(text);
        //then
        Assert.assertThat(result, is(countedText));
    }

    @Test
    public void shouldNotCountExpressionAndReturnEmptyComponentWhenGivenNull(){
        //when
        Component result = expressionCounter.countExpression(null);
        //then
        List<Component> resultChildren = result.getChildren();
        Assert.assertTrue(resultChildren.isEmpty());
    }
}
