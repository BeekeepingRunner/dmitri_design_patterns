package patterns.behavioral.interpreter;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {

    static List<Token> lex(String input) {

        final ArrayList<Token> result = new ArrayList<>();

        for (int i = 0; i < input.length(); ++i)
        {
            switch (input.charAt(i))
            {
                case '+' -> result.add(new Token(Token.Type.PLUS, "+"));
                case '-' -> result.add(new Token(Token.Type.MINUS, "-"));
                case '(' -> result.add(new Token(Token.Type.LPARENTHESIS, "("));
                case ')' -> result.add(new Token(Token.Type.RPARENTHESIS, ")"));
                default -> {
                    final StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j = i + 1; j < input.length(); ++j){
                        char next = input.charAt(j);
                        if (Character.isDigit(next)) {
                            sb.append(next);
                            ++i;
                        } else {
                            result.add(new Token(Token.Type.INTEGER, sb.toString()));
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    static Element parse(List<Token> tokens) {

        final BinaryOperation result = new BinaryOperation();
        boolean haveLeftHandSide = false;

        for (int i = 0; i < tokens.size(); ++i) {
            Token token = tokens.get(i);
            switch (token.type) {
                case INTEGER -> {
                    final Integer integer =
                            new Integer(java.lang.Integer.parseInt(token.text));
                    if (!haveLeftHandSide) {
                        result.left = integer;
                        haveLeftHandSide = true;
                    } else {
                        result.right = integer;
                    }
                }
                case PLUS -> {
                    result.type = BinaryOperation.Type.ADDITION;
                }
                case MINUS -> {
                    result.type = BinaryOperation.Type.SUBTRACTION;
                }
                case LPARENTHESIS -> {
                    int j = i;
                    for (; j < tokens.size(); ++j) {
                        if (tokens.get(j).type == Token.Type.RPARENTHESIS)
                            break;
                    }

                    final List<Token> subexpression = tokens.stream()
                            .skip(i + 1)
                            .limit(j - i - 1)
                            .collect(Collectors.toList());

                    Element element = parse(subexpression);
                    if (!haveLeftHandSide) {
                        result.left = element;
                        haveLeftHandSide = true;
                    } else
                        result.right = element;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        final String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);
        System.out.println(tokens.stream()
                .map(Token::toString)
                .collect(Collectors.joining("\t")));

        Element parsed = parse(tokens);
        System.out.println(input + " = " + parsed.eval());
    }
}

class Token
{
    public enum Type
    {
        INTEGER,
        PLUS,
        MINUS,
        LPARENTHESIS,
        RPARENTHESIS
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}


interface Element
{
    int eval();
}

class Integer implements Element
{
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element
{
    public enum Type {
        ADDITION,
        SUBTRACTION
    }

    public Type type;
    public Element left;
    public Element right;

    @Override
    public int eval() {
        switch (type) {
            case ADDITION -> {
                return left.eval() + right.eval();
            }
            case SUBTRACTION -> {
                return left.eval() - right.eval();
            }
            default -> {
                return 0;
            }
        }
    }
}