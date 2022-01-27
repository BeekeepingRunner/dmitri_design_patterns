package patterns.structural.flyweight.textformatting;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        FormattedText ft = new FormattedText("This is a brave new world");
        ft.capitalize(10, 15);
        System.out.println(ft);

        
    }
}

class FormattedText {

    private String plainText;

    private boolean [] capitalize;

    public FormattedText(String plainText) {
        this.plainText = plainText;
        this.capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end) {
        for (int i = start; i <= end; ++i) {
            this.capitalize[i] = true;
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < plainText.length(); ++i) {
            char c = plainText.charAt(i);
            sb.append(capitalize[i] ? Character.toUpperCase(c) : c);
        }

        return sb.toString();
    }
}
