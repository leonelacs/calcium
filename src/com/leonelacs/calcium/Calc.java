package com.leonelacs.calcium;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
        mathOperators.add(new MathOperator('#', 0, 0));
    }

    public class DecimalAnswer {
        BigDecimal result;
        String errInfo;

        public DecimalAnswer(BigDecimal result, String errInfo) {
            this.result = result;
            this.errInfo = errInfo;
        }
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
        boolean bracketgood = BracketVaildate(exp);
        if (!bracketgood) {
            return "b";
        }
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
        exp = exp.replaceAll("√\\(", "q");
        exp = exp.replaceAll("π", "3.14159");
        exp = exp.replaceAll("e", "2.71828");
        //exp = exp.replaceAll("\\)\\(", ")*(");
        exp = MultiplyComplete(exp);
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

    public String MultiplyComplete(String exp) {
        String patReplace = "([\\d\\)])([SCTstcngq\\(])";
        exp = exp.replaceAll(patReplace, "$1" + "*" + "$2");
        return exp;
    }

    public List<BigDecimal> NumbersSeparate(String expf) {
        String patSplit = "[\\+\\-\\*/\\^\\(SCTsctngq\\)!]";
        String[] numStrs = expf.split(patSplit);
        List<BigDecimal> numbers = new ArrayList<BigDecimal>();
        String patMatch = "(~)?\\d+(\\.\\d+)?";
        for (String s: numStrs) {
            if (s.matches(patMatch)) {
                s = s.replace('~', '-');
                BigDecimal decTemp = new BigDecimal(s);
                numbers.add(decTemp);
            }
        }
        return numbers;
    }

    public boolean FormatedExpressionVaildate(String expf) {
        List<String> patList = new ArrayList<String>();

        return true;
    }

    public DecimalAnswer FormatedExpressionCalculate(String expf) {
        if (expf.equals("b")) {
            DecimalAnswer ans = new DecimalAnswer(null, "SYNEb");
            return ans;
        }
        try {
            Stack<Character> operators = new Stack<Character>();
            Stack<BigDecimal> numbers = new Stack<BigDecimal>();
            List<BigDecimal> numSet = NumbersSeparate(expf);
            String patReplace = "(~)?\\d+(\\.\\d+)?";
            String expm = expf.replaceAll(patReplace, "@");
            expm = expm + "#";
            operators.push('#');
            if (!numSet.isEmpty()) {
                for (char c : expm.toCharArray()) {
                    if (c == '@') {
                        numbers.push(numSet.get(0));
                        numSet.remove(0);
                    } else {
                        boolean flag = true;
                        while (flag) {
                            if (c == '#' && operators.peek() == '#') {
                                DecimalAnswer ans = new DecimalAnswer(numbers.peek(), "");
                                return ans;
                            }
                            if (c == '!') {
                                BigDecimal opnd = numbers.pop();
                                BigInteger ndint;
//                                long posinega;
//                                long ndlong;
                                try {
                                    ndint = opnd.toBigIntegerExact();
//                                    ndlong = ndint.longValue();
//                                    BigInteger big_0 = new BigInteger("0");
//                                    BigInteger big_49 = new BigInteger("49");
//                                    if (ndlong < 0) {
//                                        posinega = -1;
//                                    }
//                                    else {
//                                        posinega = 1;
//                                    }
//                                    if (ndlong > 49 || ndlong < -49) {
//                                        DecimalAnswer ans = new DecimalAnswer(null, "SOFE");
//                                        return ans;
//                                    }
                                } catch (Exception e) {
                                    DecimalAnswer ans = new DecimalAnswer(null, "DOME");
                                    return ans;
                                }
//                                long res = 1;
//                                for (long i = ndint.longValue() * posinega; i >= 2; i--) {
//                                    res *= i;
//                                }
//                                res *= posinega;
                                //////////
                                BigInteger bigres = BigFactorial(ndint);
                                //////////
                                numbers.push(new BigDecimal(bigres));
                                break;
                            }
                            int sign = OperatorPrioritiesCompare(operators.peek(), c);
                            if (sign == 1) {
                                operators.push(c);
                                flag = false;
                            } else if (sign == 0) {
                                char curOp = operators.pop();
                                flag = false;
                                if (curOp == '(') {
                                    continue;
                                } else {
                                    BigDecimal opnd = numbers.pop();
                                    double nddou = opnd.doubleValue();
                                    Double res = 0.;
                                    if (curOp == 'S') res = Math.asin(nddou);
                                    else if (curOp == 'C') res = Math.acos(nddou);
                                    else if (curOp == 'T') res = Math.atan(nddou);
                                    else if (curOp == 's') res = Math.sin(nddou);
                                    else if (curOp == 'c') res = Math.cos(nddou);
                                    else if (curOp == 't') res = Math.tan(nddou);
                                    else if (curOp == 'n') res = Math.log(nddou);
                                    else if (curOp == 'g') res = Math.log10(nddou);
                                    else if (curOp == 'q') res = Math.sqrt(nddou);
                                    String restr = res.toString();
                                    BigDecimal resbd = new BigDecimal(restr);
                                    numbers.push(resbd);
                                }
                            } else if (sign == -1) {
                                char op = operators.pop();
                                BigDecimal b = numbers.pop();
                                BigDecimal a = numbers.pop();
                                BigDecimal res;
                                if (op == '+') res = a.add(b);
                                else if (op == '-') res = a.subtract(b);
                                else if (op == '*') res = a.multiply(b);
                                else if (op == '/') {
                                    try {
                                        BigInteger bint = b.toBigIntegerExact();
                                        if (bint.equals(new BigInteger("0"))) {
                                            DecimalAnswer ans = new DecimalAnswer(null, "DIV0");
                                            return ans;
                                        }
                                    } catch (Exception e) {}
                                    res = a.divide(b);
                                } else if (op == '^') {
                                    Double adou = a.doubleValue();
                                    Double bdou = b.doubleValue();
                                    Double resdou = Math.pow(adou, bdou);
                                    String resstr = resdou.toString();
                                    res = new BigDecimal(resstr);
                                } else {
                                    res = new BigDecimal("0");
                                    DecimalAnswer ans = new DecimalAnswer(null, "SYNEp");
                                }
                                numbers.push(res);
                            }
                        }
                    }
                }
            }
            DecimalAnswer ans = new DecimalAnswer(null, "UNKE");
            return ans;
        }
        catch (Exception e) {
            DecimalAnswer ans = new DecimalAnswer(null, "SYNEs");
            return ans;
        }
    }

    public DecimalAnswer Compute(String expression) {
        String expf = ExpressionFormat(expression);
        DecimalAnswer answer = FormatedExpressionCalculate(expf);
        return answer;
    }

    public BigInteger BigFactorial(BigInteger bas) {
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        //BigInteger two = new BigInteger("2");
        BigInteger absRes = new BigInteger("1");
        BigInteger symb = new BigInteger("1");
        BigInteger i = bas;
        if (bas.compareTo(zero) == -1) {
            symb = new BigInteger("-1");
        }
        i = i.multiply(symb);
        while (i.compareTo(one) == 1) {
            absRes = absRes.multiply(i);
            i = i.subtract(one);
        }
        absRes = absRes.multiply(symb);
        return absRes;
    }

    public String StrCompute(String exp) {
        DecimalAnswer deca = Compute(exp);
        String stra;
        if (deca.result != null) {
            BigDecimal biggestView = new BigDecimal("999999999999");
            BigDecimal smallestView = new BigDecimal("-99999999999");
            BigDecimal toPosi0View = new BigDecimal("0.0000001");
            BigDecimal toNega0View = new BigDecimal("-0.0000001");

            if (deca.result.compareTo(biggestView) == 1 || deca.result.compareTo(smallestView) == -1 || (deca.result.compareTo(toPosi0View) == -1 && deca.result.compareTo(toNega0View) == 1)) {
                stra = deca.result.stripTrailingZeros().toString();
                String patScale = "(\\d+)(.?)(\\d{0,8})(\\d*)(E)([+-])(\\d+)";
                stra = stra.replaceAll(patScale, "$1" + "." + "$3" + "E" + "$6" + "$7");
                //stra = "1.123456789E"
            }
            else {
                stra = deca.result.stripTrailingZeros().setScale(8, RoundingMode.DOWN).toPlainString();
            }

            //stra = deca.result.stripTrailingZeros().toString();
        }
        else {
            stra = deca.errInfo;
        }
        return stra;
    }
}
