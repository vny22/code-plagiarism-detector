import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // =====================================
        // MAIN MENU
        // =====================================

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "          CODE ANALYSIS SYSTEM"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "\nChoose an option:"
        );

        System.out.println(
                "1. AI Code Detector"
        );

        System.out.println(
                "2. Detect Plagiarism from Database"
        );

        System.out.print(
                "\nEnter your choice: "
        );

        String choice = scanner.nextLine();


        // =====================================
        // OPTION 1: AI DETECTOR
        // =====================================

        if (choice.equals("1")) {

            System.out.println(
                    "\n=============================================="
            );

            System.out.println(
                    "             AI CODE DETECTOR"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "\nEnter your Java code."
            );

            System.out.println(
                    "Type END on a new line when finished.\n"
            );


            String userCode =
                    readCode(scanner);


            if (userCode.trim().isEmpty()) {

                System.out.println(
                        "\nNo code entered."
                );

                scanner.close();

                return;
            }


            // Calculate AI likelihood
            double aiLikelihood =
                    AIDetector.calculateAILikelihood(
                            userCode
                    );


            // =====================================
            // AI RESULT
            // =====================================

            System.out.println(
                    "\n=============================================="
            );

            System.out.println(
                    "                AI ANALYSIS"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.printf(
                    "AI-Generation Likelihood: %.2f%%%n",
                    aiLikelihood
            );


            if (aiLikelihood >= 70) {

                System.out.println(
                        "Result: HIGH AI-LIKE PATTERNS"
                );

            } else if (aiLikelihood >= 40) {

                System.out.println(
                        "Result: MODERATE AI-LIKE PATTERNS"
                );

            } else {

                System.out.println(
                        "Result: LOW AI-LIKE PATTERNS"
                );
            }


            System.out.println(
                    "\nNote: This is an analytical estimate"
                    + " and does not prove AI usage."
            );


        }


        // =====================================
        // OPTION 2: DATABASE PLAGIARISM
        // =====================================

        else if (choice.equals("2")) {

            System.out.println(
                    "\n=============================================="
            );

            System.out.println(
                    "          DATABASE PLAGIARISM DETECTOR"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "\nEnter your Java code."
            );

            System.out.println(
                    "Type END on a new line when finished.\n"
            );


            String userCode =
                    readCode(scanner);


            if (userCode.trim().isEmpty()) {

                System.out.println(
                        "\nNo code entered."
                );

                scanner.close();

                return;
            }


            try {

                // =====================================
                // GET DATABASE SUBMISSIONS
                // =====================================

                List<Database.Submission> submissions =
                        Database.getSubmissions();


                if (submissions.isEmpty()) {

                    System.out.println(
                            "\nNo submissions found in database."
                    );

                    scanner.close();

                    return;
                }


                double highestScore = 0;

                Database.Submission closestSubmission =
                        null;

                PlagiarismDetector.Result bestResult =
                        null;


                // =====================================
                // COMPARE WITH DATABASE
                // =====================================

                for (
                        Database.Submission submission :
                        submissions
                ) {

                    PlagiarismDetector.Result result =
                            PlagiarismDetector.compare(
                                    userCode,
                                    submission.code
                            );


                    // Keep only the highest score
                    if (result.finalScore > highestScore) {

                        highestScore =
                                result.finalScore;

                        closestSubmission =
                                submission;

                        bestResult =
                                result;
                    }
                }


                // =====================================
                // FINAL PLAGIARISM RESULT
                // =====================================

                System.out.println(
                        "\n=============================================="
                );

                System.out.println(
                        "             PLAGIARISM RESULT"
                );

                System.out.println(
                        "=============================================="
                );


                System.out.printf(
                        "Similarity Score: %.2f%%%n",
                        highestScore
                );


                System.out.println(
                        "Most Similar Submission: "
                        + closestSubmission.studentName
                );


                // =====================================
                // CLASSIFICATION
                // =====================================

                if (highestScore >= 80) {

                    System.out.println(
                            "Result: HIGH SIMILARITY"
                    );

                } else if (highestScore >= 50) {

                    System.out.println(
                            "Result: POSSIBLE PLAGIARISM"
                    );

                } else {

                    System.out.println(
                            "Result: LOW SIMILARITY"
                    );
                }


                // =====================================
                // SCORE BREAKDOWN
                // =====================================

                System.out.println(
                        "\nAnalysis Breakdown:"
                );

                System.out.printf(
                        "Token Similarity       : %.2f%%%n",
                        bestResult.tokenSimilarity
                );

                System.out.printf(
                        "Fingerprint Similarity : %.2f%%%n",
                        bestResult.fingerprintSimilarity
                );

                System.out.printf(
                        "AST Similarity         : %.2f%%%n",
                        bestResult.astSimilarity
                );


            } catch (Exception e) {

                System.out.println(
                        "\nDatabase connection error."
                );

                System.out.println(
                        "Please check your MySQL setup."
                );

                e.printStackTrace();
            }

        }


        // =====================================
        // INVALID OPTION
        // =====================================

        else {

            System.out.println(
                    "\nInvalid choice."
            );

            System.out.println(
                    "Please choose 1 or 2."
            );
        }


        scanner.close();
    }


    // =====================================
    // READ JAVA CODE
    // =====================================

    private static String readCode(
            Scanner scanner) {

        StringBuilder code =
                new StringBuilder();


        while (true) {

            String line =
                    scanner.nextLine();


            if (line.equals("END")) {
                break;
            }


            code.append(line)
                 .append("\n");
        }


        return code.toString();
    }
}


