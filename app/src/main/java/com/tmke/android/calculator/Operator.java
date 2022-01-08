package com.tmke.android.calculator;

//enum for Operator "objects"
import java.util.*;

public enum Operator {

    ADD("+", 1)
            {
                double doCalc(double d1, double d2) {
                    return d1+d2;
                }
            },
    SUBTRACT("-",1)
            {
                double doCalc(double d1, double d2) {
                    return d1-d2;
                }
            },
    MULTIPLY("*", 2)
            {
                double doCalc(double d1, double d2) {
                    return d1*d2;
                }
            },
    DIVIDE("/",2)
            {
                double doCalc(double d1, double d2) {
                    return d1/d2;
                }
            },
    STARTBRACE("(", 0)
            {
                double doCalc(double d1, double d2) {
                    return 0;
                }
            },
    ENDBRACE(")",0)
            {
                double doCalc(double d1, double d2) {
                    return 0;
                }
            },
    EXP("^", 3)
            {
                double doCalc(double d1, double d2) {
                    return Math.pow(d1,d2);
                }
            };

    private String operator;
    private int precedence;

    Operator(String operator, int precedence) {
        this.operator = operator;
        this.precedence = precedence;
    }

    public int getPrecedenceLevel() {
        return precedence;
    }

    public String getSymbol() {
        return operator;
    }

    public static boolean isOperator(String s) {
        for(Operator op : Operator.values()) { //iterate through enum values
            if (op.getSymbol().equals(s))
                return true;
        }
        return false;
    }

    public static Operator getOperator(String s)
            throws InvalidOperatorException {
        for(Operator op : Operator.values()) { //iterate through enum values
            if (op.getSymbol().equals(s))
                return op;
        }
        throw new InvalidOperatorException(s + " Is not a valid operator!");
    }

    public boolean isStartBrace() {
        return (operator.equals("("));
    }
    //overriding calculation provided by each enum part
    abstract double doCalc(double d1, double d2);
}
//error to be thrown/caught in ProjectOne.java
class InvalidOperatorException extends Exception {
    public InvalidOperatorException() {
    }

    public InvalidOperatorException(String s) {
        super(s);
    }
}



    //reading in a string at doing the parsing/arithmetic
    /*
    public static void main (String[] args) {
        String input = "";
        //get input
        System.out.print("Enter an infix exp<b></b>ression: ");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            input = in.readLine();
        }
        catch (IOException e)
        {
            System.out.println("Error getting input!");
        }

        doCalculate(input);
    }
    */
    // Input: user entered string
    // Output: Display of answer
