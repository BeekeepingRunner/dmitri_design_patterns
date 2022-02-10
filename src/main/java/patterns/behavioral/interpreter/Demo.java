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

    public static void main(String[] args) {

        final String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);
        System.out.println(tokens.stream()
                .map(Token::toString)
                .collect(Collectors.joining("\t")));
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
