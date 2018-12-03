package com.leonelacs.calcium;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String str;
        Scanner in = new Scanner(System.in);
        str = "{" + in.nextLine() + "}";
        boolean flag;
        str = str.trim();
        Calc calc = new Calc();
        str = calc.BracketComplete(str);
        str = calc.ExpressionFormat(str);
        System.out.println(str + "end");

    }
}
