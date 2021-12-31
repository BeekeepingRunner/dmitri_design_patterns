package patterns.creational.builder;

import java.util.ArrayList;
import java.util.Collections;

public class BuilderPattern {

    public static void main(String[] args) {

        HtmlBuilder builder = new HtmlBuilder("ul");
        builder.addChild("li", "hello")
                .addChild("li", "world");
        System.out.println(builder);
    }
}


class HtmlBuilder {

    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    public HtmlBuilder addChild(String childName, String childText) {

        HtmlElement el = new HtmlElement(childName, childText);
        root.elements.add(el);
        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

class HtmlElement {

    public String name;
    public String text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();

    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indentLevel) {

        StringBuilder sb = new StringBuilder();
        String indent = String.join("", Collections.nCopies(indentLevel * indentSize, " "));
        sb.append(String.format("%s<%s>%s", indent, name, newLine));

        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indentLevel + 1), " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement e : elements)
            sb.append(e.toStringImpl(indentLevel + 1));

        sb.append(String.format("%s</%s>%s", indent, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}
