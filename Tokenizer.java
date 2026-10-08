import java.util.*;

public class Tokenizer {

    private static final Set<String> JAVA_KEYWORDS =
            new HashSet<>(Arrays.asList(

                    "abstract",
                    "assert",
                    "boolean",
                    "break",
                    "byte",
                    "case",
                    "catch",
                    "char",
                    "class",
                    "const",
                    "continue",
                    "default",
                    "do",
                    "double",
                    "else",
                    "enum",
                    "extends",
                    "final",
                    "finally",
                    "float",
                    "for",
                    "if",
                    "implements",
                    "import",
                    "instanceof",
                    "int",
                    "interface",
                    "long",
                    "new",
                    "package",
                    "private",
                    "protected",
                    "public",
                    "return",
                    "short",
                    "static",
                    "super",
                    "switch",
                    "synchronized",
                    "this",
                    "throw",
                    "throws",
                    "transient",
                    "try",
                    "void",
                    "volatile",
                    "while",
                    "true",
                    "false",
                    "null"
            ));

    public static List<String> tokenize(String code) {

        // Remove single-line comments
        code = code.replaceAll("//.*", "");

        // Remove multi-line comments
        code = code.replaceAll(
                "/\\*[\\s\\S]*?\\*/",
                ""
        );

        List<String> tokens = new ArrayList<>();

        String regex =
                "\"(?:\\\\.|[^\"\\\\])*\""
                + "|\\d+(?:\\.\\d+)?"
                + "|[A-Za-z_$][A-Za-z0-9_$]*"
                + "|==|!=|<=|>=|&&|\\|\\|"
                + "|\\+\\+|--|\\+=|-=|\\*=|/="
                + "|[{}()\\[\\];,.+\\-*/%=<>!?:]";

        java.util.regex.Pattern pattern =
                java.util.regex.Pattern.compile(regex);

        java.util.regex.Matcher matcher =
                pattern.matcher(code);

        while (matcher.find()) {

            String token = matcher.group();

            tokens.add(normalizeToken(token));
        }

        return tokens;
    }

    private static String normalizeToken(String token) {

        // Keep Java keywords
        if (JAVA_KEYWORDS.contains(token)) {
            return token;
        }

        // Replace numbers
        if (token.matches("\\d+(\\.\\d+)?")) {
            return "NUMBER";
        }

        // Replace strings
        if (token.startsWith("\"")) {
            return "STRING";
        }

        // Replace identifiers
        if (token.matches("[A-Za-z_$][A-Za-z0-9_$]*")) {
            return "IDENTIFIER";
        }

        return token;
    }
}