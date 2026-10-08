import java.util.*;

public class PlagiarismDetector {

    public static Result compare(
            String userCode,
            String databaseCode) {

        // =====================================
        // 1. TOKENIZATION
        // =====================================

        List<String> userTokens =
                Tokenizer.tokenize(userCode);

        List<String> databaseTokens =
                Tokenizer.tokenize(databaseCode);

        double tokenSimilarity =
                tokenSimilarity(
                        userTokens,
                        databaseTokens
                );


        // =====================================
        // 2. FINGERPRINTING
        // =====================================

        Set<String> userFingerprints =
                Fingerprint.generateFingerprints(
                        userTokens
                );

        Set<String> databaseFingerprints =
                Fingerprint.generateFingerprints(
                        databaseTokens
                );

        double fingerprintSimilarity =
                Fingerprint.calculateSimilarity(
                        userFingerprints,
                        databaseFingerprints
                );


        // =====================================
        // 3. AST ANALYSIS
        // =====================================

        List<String> userAST =
                ASTAnalyzer.getASTStructure(
                        userCode
                );

        List<String> databaseAST =
                ASTAnalyzer.getASTStructure(
                        databaseCode
                );

        double astSimilarity =
                ASTAnalyzer.calculateSimilarity(
                        userAST,
                        databaseAST
                );


        // =====================================
        // 4. FINAL SCORE
        // =====================================

        /*
         * Tokenization     = 30%
         * Fingerprinting   = 30%
         * AST              = 40%
         */

        double finalScore =
                (tokenSimilarity * 0.30)
                +
                (fingerprintSimilarity * 0.30)
                +
                (astSimilarity * 0.40);


        return new Result(
                tokenSimilarity,
                fingerprintSimilarity,
                astSimilarity,
                finalScore
        );
    }


    private static double tokenSimilarity(
            List<String> first,
            List<String> second) {

        Set<String> firstSet =
                new HashSet<>(first);

        Set<String> secondSet =
                new HashSet<>(second);

        if (firstSet.isEmpty() &&
                secondSet.isEmpty()) {

            return 100;
        }

        if (firstSet.isEmpty() ||
                secondSet.isEmpty()) {

            return 0;
        }

        Set<String> intersection =
                new HashSet<>(firstSet);

        intersection.retainAll(secondSet);

        Set<String> union =
                new HashSet<>(firstSet);

        union.addAll(secondSet);

        return ((double) intersection.size()
                / union.size()) * 100;
    }


    // =====================================
    // RESULT CLASS
    // =====================================

    public static class Result {

        public double tokenSimilarity;

        public double fingerprintSimilarity;

        public double astSimilarity;

        public double finalScore;


        public Result(
                double tokenSimilarity,
                double fingerprintSimilarity,
                double astSimilarity,
                double finalScore) {

            this.tokenSimilarity =
                    tokenSimilarity;

            this.fingerprintSimilarity =
                    fingerprintSimilarity;

            this.astSimilarity =
                    astSimilarity;

            this.finalScore =
                    finalScore;
        }
    }
}