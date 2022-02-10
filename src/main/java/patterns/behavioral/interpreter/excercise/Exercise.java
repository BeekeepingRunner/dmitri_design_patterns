package patterns.behavioral.interpreter.excercise;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class ExpressionProcessor
{
    public Map<Character, Integer> variables = new HashMap<>();

    enum Operator {
        PLUS,
        MINUS
    }

    private Operator lastOperator;

    public int calculate(String expression)
    {
        lastOperator = null;
        int result = 0;

        for (int parsedCharIdx = 0; parsedCharIdx < expression.length(); ++parsedCharIdx) {

            char parsedChar = expression.charAt(parsedCharIdx);
            if (Character.isDigit(parsedChar)) {
                String numberStr = String.valueOf(parsedChar);

                for (int j = parsedCharIdx + 1; j < expression.length(); ++j) {
                    if (Character.isDigit(expression.charAt(j))) {
                        numberStr += expression.charAt(j);
                        ++parsedCharIdx;
                    } else {
                        break;
                    }
                }

                int numValue = Integer.parseInt(numberStr);
                result = modifyResult(result, numValue);

            } else if (parsedChar == '+') {
                lastOperator = Operator.PLUS;
            } else if (parsedChar == '-') {
                lastOperator = Operator.MINUS;
            } else if (parsedChar == ' ') {
                continue;
            } else { // we've encountered a variable!

                char variable = expression.charAt(parsedCharIdx);
                if (parsedCharIdx != expression.length() - 1) {

                    String nextChar = String.valueOf(expression.charAt(parsedCharIdx + 1));
                    // if the next character is part of variable name or variable is not found
                    String regex = "[\\s+-[0-9]]";
                    if (!Pattern.compile(regex).matcher(nextChar).matches()
                            || !variables.containsKey(variable)) {
                        return 0;
                    }
                }

                final Integer variableValue = variables.get(variable);
                result = modifyResult(result, variableValue);
            }
        }

        return result;
    }

    private int modifyResult(int result, int value) {
        if (lastOperator == null) {
            result = value;
        } else if (lastOperator == Operator.PLUS) {
            result += value;
        } else {
            result -= value;
        }

        return result;
    }
}

public class Exercise {

    public static void main(String[] args) {

        final ExpressionProcessor ep = new ExpressionProcessor();
        System.out.println(ep.calculate("1 + 2 + 3"));
        System.out.println(ep.calculate("1 + 2 + 3"));
        System.out.println(ep.calculate("1 + 2 - 3"));
        System.out.println(ep.calculate("1 - 2 + 3"));
        System.out.println(ep.calculate("1 - 2 - 3"));

        ep.variables.put('x', 3);
        System.out.println(ep.calculate("10 - 2 - x"));
        System.out.println(ep.calculate("10 - 2 + x"));
        System.out.println(ep.calculate("x - 2 + 10"));
        System.out.println(ep.calculate("2 - x + 10"));
        System.out.println(ep.calculate("x + x + x"));
    }
}