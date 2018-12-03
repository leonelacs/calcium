package com.leonelacs.calcium;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calc {
    public static List<MathOperator> mathOperators = new ArrayList<MathOperator>();

    public Calc() {
        mathOperators.add(new MathOperator('+', 3, 2));
        mathOperators.add(new MathOperator('-', 3, 2));
        mathOperators.add(new MathOperator('*', 5, 4));
        mathOperators.add(new MathOperator('/', 5, 4));
        mathOperators.add(new MathOperator('^', 7, 6));
        mathOperators.add(new MathOperator('(', 1, 8));
        mathOperators.add(new MathOperator('S', 1, 8));
        mathOperators.add(new MathOperator('C', 1, 8));
        mathOperators.add(new MathOperator('T', 1, 8));
        mathOperators.add(new MathOperator('s', 1, 8));
        mathOperators.add(new MathOperator('c', 1, 8));
        mathOperators.add(new MathOperator('t', 1, 8));
        mathOperators.add(new MathOperator('n', 1, 8));
        mathOperators.add(new MathOperator('g', 1, 8));
        mathOperators.add(new MathOperator('q', 1, 8));
        mathOperators.add(new MathOperator(')', 8, 1));
    }

    public static int OperatorPrioritiesCompare(char op1, char op2) {
        int op1Prior = -1;
        int op2Prior = -1;
        for (MathOperator mathOp: mathOperators) {
            if (mathOp.Content == op1) {
                op1Prior = mathOp.LPriority;
            }
            if (mathOp.Content == op2) {
                op2Prior = mathOp.RPriority;
            }
        }

        if (op1Prior < op2Prior) {
            return 1;
        }
        else if (op1Prior > op2Prior) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public String ExpressionFormat(String exp) {
        exp = exp.replaceAll("×", "*");
        exp = exp.replaceAll("÷", "/");
        exp = exp.replaceAll("arcsin\\(", "S");
        exp = exp.replaceAll("arccos\\(", "C");
        exp = exp.replaceAll("arctan\\(", "T");
        exp = exp.replaceAll("sin\\(", "s");
        exp = exp.replaceAll("cos\\(", "c");
        exp = exp.replaceAll("tan\\(", "t");
        exp = exp.replaceAll("ln\\(", "n");
        exp = exp.replaceAll("log\\(", "g");
        exp = exp.replaceAll("ln\\(", "n");
        exp = exp.replaceAll("√\\(", "q");
        exp = exp.replaceAll("π", "3.14159");
        exp = exp.replaceAll("e", "2.71828");
        exp = exp.replaceAll("\\)\\(", ")*(");
        exp = exp.replaceAll("\\{-", "~");
        exp = exp.replaceAll("\\(-", "~");
        return exp;
    }

    public
}
