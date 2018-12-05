package com.leonelacs.calcium;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        for (; ; ) {
            String str;
            Scanner in = new Scanner(System.in);
            str = "{" + in.nextLine() + "}";
            str = str.trim();
            Calc calc = new Calc();
            String cres;
            cres = calc.StrCompute(str);
//            Calc.DecimalAnswer resda = calc.Compute(str);
//            if (resda.result != null) {
//                cres = resda.result.toString();
//            } else {
//                cres = resda.errInfo;
//            }
            System.out.println(cres);
        }
    }
}
