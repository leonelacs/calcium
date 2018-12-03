package com.leonelacs.calcium;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

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
        exp = exp.trim();
        exp = "{" + exp + "}";
        exp = BracketComplete(exp);
        exp = exp.replaceAll("×", "*");
        exp = exp.replaceAll("÷", "/");
        exp = exp.replaceAll("\\{-", "{~");
        exp = exp.replaceAll("\\(-", "(~");
        exp = exp.replaceAll("\\{", "");
        exp = exp.replaceAll("}", "");
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
        return exp;
    }

    public String BracketComplete(String exp) {
        char[] expArray = exp.toCharArray();
        int left = 0, right = 0;
        for (char c: expArray) {
            if (c == '(') {
                left++;
            }
            if (c == ')') {
                right++;
            }
        }
        if (right < left) {
            int diff = left - right;
            for (int i = 0; i < diff; i++) {
                exp = exp + ")";
            }
        }
        return exp;
    }

    public boolean BracketVaildate(String exp) {
        if (exp.contains("()")) {
            return false;
        }
        char[] expArray = exp.toCharArray();
        Stack<Integer> veriStack = new Stack<Integer>();
        for (char c: expArray) {
            if (c == '(') {
                veriStack.push(1);
            }
            else if (c == ')') {
                if (veriStack.isEmpty()) {
                    return false;
                }
                else {
                    veriStack.pop();
                }
            }
        }
        return true;
    }

    public boolean FormatedExpressionVaildate(String expf) {
        return true;
    }

    public BigDecimal FormatedExpressionCalculate(String expf) {
        return null;
    }

    public String Compute(String expression) {
        return null;
    }
}
