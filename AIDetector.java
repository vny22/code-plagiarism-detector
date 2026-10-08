import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AIDetector {

    public static double calculateAILikelihood(String code) {

        if (code == null || code.trim().isEmpty()) {
            return 0;
        }

        double score = 0;

        // =====================================
        // 1. COMMENTS
        // =====================================

        int commentLines = countCommentLines(code);

        if (commentLines >= 5) {
            score += 15;
        } else if (commentLines >= 2) {
            score += 8;
        }


        // =====================================
        // 2. EXCESSIVE STRUCTURE
        // =====================================

        int methodCount =
                countMatches(
                        code,
                        "\\b(public|private|protected)?\\s*(static\\s+)?\\w+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{"
                );

        if (methodCount >= 5) {
            score += 15;
        } else if (methodCount >= 3) {
            score += 8;
        }


        // =====================================
        // 3. GENERIC VARIABLE NAMES
        // =====================================

        int genericNames =
                countMatches(
                        code,
                        "\\b(temp|result|output|input|data|value|number|count|index)\\b"
                );

        if (genericNames >= 4) {
            score += 15;
        } else if (genericNames >= 2) {
            score += 8;
        }


        // =====================================
        // 4. EXPLANATORY COMMENTS
        // =====================================

        String lowerCode =
                code.toLowerCase();

        String[] AIStylePhrases = {
                "this program",
                "this method",
                "this code",
                "initialize",
                "calculate the",
                "return the",
                "check whether",
                "the following"
        };

        int phraseMatches = 0;

        for (String phrase : AIStylePhrases) {

            if (lowerCode.contains(phrase)) {
                phraseMatches++;
            }
        }

        score += phraseMatches * 5;


        // =====================================
        // 5. VERY REGULAR INDENTATION
        // =====================================

        int fourSpaceLines =
                countMatches(
                        code,
                        "(?m)^    [^\\s]"
                );

        if (fourSpaceLines >= 10) {
            score += 10;
        }


        // =====================================
        // LIMIT SCORE
        // =====================================

        if (score > 100) {
            score = 100;
        }

        return score;
    }


    // =====================================
    // COUNT COMMENT LINES
    // =====================================

    private static int countCommentLines(
            String code) {

        int count = 0;

        String[] lines =
                code.split("\\n");

        for (String line : lines) {

            String trimmed =
                    line.trim();

            if (trimmed.startsWith("//")
                    || trimmed.startsWith("*")
                    || trimmed.startsWith("/*")
                    || trimmed.startsWith("*/")) {

                count++;
            }
        }

        return count;
    }


    // =====================================
    // COUNT REGEX MATCHES
    // =====================================

    private static int countMatches(
            String text,
            String regex) {

        Pattern pattern =
                Pattern.compile(regex);

        Matcher matcher =
                pattern.matcher(text);

        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }
}