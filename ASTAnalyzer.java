import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import java.util.*;

public class ASTAnalyzer {

    public static List<String> getASTStructure(String code) {

        List<String> nodes = new ArrayList<>();

        try {

            CompilationUnit compilationUnit =
                    StaticJavaParser.parse(code);

            collectNodes(compilationUnit, nodes);

        } catch (Exception e) {

            System.out.println(
                    "Could not parse code into AST."
            );
        }

        return nodes;
    }

    private static void collectNodes(
            Node node,
            List<String> nodes) {

        // Store the type of each AST node
        nodes.add(
                node.getClass().getSimpleName()
        );

        // Visit all child nodes
        for (Node child : node.getChildNodes()) {

            collectNodes(child, nodes);
        }
    }

    public static double calculateSimilarity(
            List<String> first,
            List<String> second) {

        if (first.isEmpty() || second.isEmpty()) {
            return 0;
        }

        Set<String> firstSet =
                new HashSet<>(first);

        Set<String> secondSet =
                new HashSet<>(second);

        Set<String> intersection =
                new HashSet<>(firstSet);

        intersection.retainAll(secondSet);

        Set<String> union =
                new HashSet<>(firstSet);

        union.addAll(secondSet);

        if (union.isEmpty()) {
            return 0;
        }

        return ((double) intersection.size()
                / union.size()) * 100;
    }
}