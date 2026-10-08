import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public class Fingerprint {

    // Number of tokens in each K-gram
    private static final int K = 5;

    public static Set<String> generateFingerprints(
            List<String> tokens) {

        Set<String> fingerprints = new HashSet<>();

        if (tokens.size() < K) {
            return fingerprints;
        }

        for (int i = 0;
             i <= tokens.size() - K;
             i++) {

            StringBuilder kgram = new StringBuilder();

            for (int j = i;
                 j < i + K;
                 j++) {

                kgram.append(tokens.get(j));
                kgram.append(" ");
            }

            String hash = createHash(kgram.toString());

            fingerprints.add(hash);
        }

        return fingerprints;
    }

    private static String createHash(String text) {

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash =
                    digest.digest(
                            text.getBytes(StandardCharsets.UTF_8)
                    );

            StringBuilder result = new StringBuilder();

            for (byte b : hash) {

                String hex =
                        Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    result.append("0");
                }

                result.append(hex);
            }

            return result.toString();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public static double calculateSimilarity(
            Set<String> first,
            Set<String> second) {

        if (first.isEmpty() && second.isEmpty()) {
            return 100;
        }

        if (first.isEmpty() || second.isEmpty()) {
            return 0;
        }

        Set<String> intersection =
                new HashSet<>(first);

        intersection.retainAll(second);

        Set<String> union =
                new HashSet<>(first);

        union.addAll(second);

        return ((double) intersection.size()
                / union.size()) * 100;
    }
}