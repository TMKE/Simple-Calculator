package com.tmke.android.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Calculation {
    public static Double doCalculate(String equation) {
        //our stacks for storage/temp variables
        Stack<Operator> operatorStack;
        Stack<Double> operandStack;
        double valOne, valTwo, newVal;
        Operator temp;

        //initalize
        StringTokenizer tokenizer = new StringTokenizer(equation, " +-*/()^", true);
        String token = "";
        operandStack = new Stack();
        operatorStack = new Stack();
        try {
            while(tokenizer.hasMoreTokens()){ //run through the string
                token = tokenizer.nextToken();
                if (token.equals(" ")) { //handles spaces, goes back up top
                    continue;
                }
                else if (!Operator.isOperator(token)){ //number check
                    operandStack.push(Double.parseDouble(token));
                }
                else if (token.equals("(")) {
                    operatorStack.push(Operator.getOperator(token));
                }
                else if (token.equals(")")) { //process until matching paraentheses is found
                    while (!((temp = operatorStack.pop()).isStartBrace())) {
                        valTwo = operandStack.pop();
                        valOne = operandStack.pop();
                        newVal = temp.doCalc(valOne, valTwo);
                        operandStack.push(newVal);
                    }
                }
                else { //other operators
                    while (true) { //infinite loop, check for stack empty/top of stack '('/op precedence
                        if ((operatorStack.empty()) || (operatorStack.peek().isStartBrace()) ||
                                (operatorStack.peek().getPrecedenceLevel() < Operator.getOperator(token).getPrecedenceLevel())) {
                            operatorStack.push(Operator.getOperator(token));
                            break; //exit inner loop
                        }
                        temp = operatorStack.pop();
                        valTwo = operandStack.pop();
                        valOne = operandStack.pop();
                        //calculate and push
                        newVal = temp.doCalc(valOne, valTwo);
                        operandStack.push(newVal);
                    }
                }
            }
        }
        catch (InvalidOperatorException e) {
            System.out.println("Invalid operator found!");
        }

        //calculate any remaining items (ex. equations with no outer paraentheses)
        while(!operatorStack.isEmpty()) {
            temp = operatorStack.pop();
            valTwo = operandStack.pop();
            valOne = operandStack.pop();
            newVal = temp.doCalc(valOne, valTwo);
            operandStack.push(newVal);
        }
        //print final answer
        // System.out.println("Answer is: " + operandStack.pop());
        return operandStack.pop();
    }
}

