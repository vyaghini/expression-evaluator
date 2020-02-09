package com.agritask;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpressionEvaluatorTest {

    @Test
    public void evaluate_validInputOnlyMultiply_returnsExpectedResult() {

        String expression = "1*2*3*4*5";
        ExpressionEvaluator ev = new ExpressionEvaluator(expression);

        assertEquals(120, ev.evaluate(), 0);
    }

    @Test
    public void evaluate_validInputOnlySum_returnsExpectedResult() {

        String expression = "1+2+3+4+5";
        ExpressionEvaluator ev = new ExpressionEvaluator(expression);

        assertEquals(15, ev.evaluate(), 0);
    }

    @Test
    public void evaluate_validInputMix_returnsExpectedResult() {

        String expression = " 10 +2 *3+4*5*7 +8  ";
        ExpressionEvaluator ev = new ExpressionEvaluator(expression);

        assertEquals(164, ev.evaluate(), 0);
    }

    @Test(expected = UnexpectedCharacterException.class)
    public void evaluate_invalidEndingWithOperator_throwsUnexpectedCharacterException() {

        String expression = " 1 +2 *3+4*5*7 +8*  ";
        ExpressionEvaluator ev = new ExpressionEvaluator(expression);

        ev.evaluate();
    }

    @Test(expected = UnexpectedCharacterException.class)
    public void evaluate_invalidConsecutiveOperators_throwsUnexpectedCharacterException() {

        String expression = " 1 +2 *3+4*5*7 + +  8  ";
        ExpressionEvaluator ev = new ExpressionEvaluator(expression);

        ev.evaluate();
    }

    @Test(expected = UnexpectedCharacterException.class)
    public void evaluate_invalidStartingWithOperator_throwsUnexpectedCharacterException() {

        String expression = " +1 +2 *3+4*5*7 +  8  ";
        ExpressionEvaluator ev = new ExpressionEvaluator(expression);

        ev.evaluate();
    }

}