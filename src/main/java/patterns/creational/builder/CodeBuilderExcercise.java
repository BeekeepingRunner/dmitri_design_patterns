package patterns.creational.builder;

import java.util.HashMap;
import java.util.Map;

public class CodeBuilderExcercise {

    public static void main(String[] args) {

        CodeBuilder cb = new CodeBuilder("Person")
                .addField("name", "String")
                .addField("age", "int");

        System.out.println(cb);
    }
}

class CodeBuilder {

    String className;
    Map<String, String> fields = new HashMap<>();

    public CodeBuilder(String className) {
        this.className = className;
    }

    public CodeBuilder addField(String fieldName, String fieldType) {

        fields.put(fieldName, fieldType);
        return this;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(className).append(System.lineSeparator())
                .append("{").append(System.lineSeparator());

        fields.forEach((fieldName, fieldType) -> {
            sb.append("\tpublic ")
                    .append(fieldType)
                    .append(" ")
                    .append(fieldName)
                    .append(";")
                    .append(System.lineSeparator());
        });

        sb.append("}");

        return sb.toString();
    }
}