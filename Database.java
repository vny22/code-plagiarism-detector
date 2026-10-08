import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL =
            "jdbc:mysql://localhost:3306/plagiarism_db";

    private static final String USER = "root";

    // CHANGE THIS to your MySQL password
    private static final String PASSWORD =
            "computer";


    public static Connection connect()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }


    public static List<Submission> getSubmissions()
            throws SQLException {

        List<Submission> submissions =
                new ArrayList<>();

        String query =
                "SELECT id, student_name, code FROM submissions";

        try (
                Connection connection = connect();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String studentName =
                        resultSet.getString("student_name");

                String code =
                        resultSet.getString("code");

                submissions.add(
                        new Submission(
                                id,
                                studentName,
                                code
                        )
                );
            }
        }

        return submissions;
    }


    public static class Submission {

        int id;

        String studentName;

        String code;


        public Submission(
                int id,
                String studentName,
                String code) {

            this.id = id;

            this.studentName =
                    studentName;

            this.code =
                    code;
        }
    }
}