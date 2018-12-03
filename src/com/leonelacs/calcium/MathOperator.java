package com.leonelacs.calcium;

public class MathOperator {
    public char Content;
    public int LPriority;
    public int RPriority;

    public MathOperator(char content, int lp, int rp) {
        this.Content = content;
        this.LPriority = lp;
        this.RPriority = rp;
    }
}