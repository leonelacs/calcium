package com.leonelacs.calcium;

public class MathOperatorSet {
    public static MathOperator ADD = new MathOperator('+', 3, 2);
    public static MathOperator SUB = new MathOperator('-', 3, 2);
    public static MathOperator MUL = new MathOperator('*', 5, 4);
    public static MathOperator DIV = new MathOperator('/', 5, 4);
    public static MathOperator POW = new MathOperator('^', 7, 6);
    public static MathOperator LB = new MathOperator('(', 1, 8);
    public static MathOperator ASIN = new MathOperator('S', 1, 8);
    public static MathOperator ACOS = new MathOperator('C', 1, 8);
    public static MathOperator ATAN = new MathOperator('T', 1, 8);
    public static MathOperator SIN = new MathOperator('s', 1, 8);
    public static MathOperator COS = new MathOperator('c', 1, 8);
    public static MathOperator TAN = new MathOperator('t', 1, 8);
    public static MathOperator LN = new MathOperator('n', 1, 8);
    public static MathOperator LOG = new MathOperator('g', 1, 8);
    public static MathOperator SQRT = new MathOperator('q', 1, 8);
    public static MathOperator RB = new MathOperator(')', 8, 1);
    public static MathOperator FACT = new MathOperator('!', -1, -1);

    public static int PriorityCompare(MathOperator op1, MathOperator op2) {
        if (op1.LPriority < op2.RPriority) {
            return 1;
        }
        else if (op1.LPriority > op2.RPriority) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
