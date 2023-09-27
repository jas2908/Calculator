package com.cal.calculator;

import java.util.Stack;

public class Calculator {
    public static Double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
                StringBuffer sbuf = new StringBuffer();

                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')) {
                    if (tokens[i] == '.' && sbuf.indexOf(".") != -1) {
                        break;
                    }
                    sbuf.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(evaluation(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == 'x' || tokens[i] == '÷') {
                while (!ops.empty() && precedence(tokens[i], ops.peek()))
                    values.push(evaluation(ops.pop(), values.pop(), values.pop()));

                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
            values.push(evaluation(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }
    public static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == 'x' || op1 == '÷') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public static double evaluation(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'x':
                return a * b;
            case '÷':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
