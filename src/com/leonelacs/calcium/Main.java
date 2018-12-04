package com.leonelacs.calcium;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String str;
        Scanner in = new Scanner(System.in);
        str = "{" + in.nextLine() + "}";
        str = str.trim();
        Calc calc = new Calc();
        String cres = calc.Compute(str);
        System.out.println(cres);
    }
}
