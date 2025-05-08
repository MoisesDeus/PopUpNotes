package br.com.mddeveloper.Setup;

import br.com.mddeveloper.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void initialize() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String createNoteTable = """
                    CREATE TABLE IF NOT EXISTS Notes (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            text TEXT NOT NULL
                            date TEXT NOT NULL
                            time TEXT NOT NULL
                        );
                    """;

            statement.execute(createNoteTable);

            String setNoteInitialValue = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Notes', 1000);";

            statement.execute(setNoteInitialValue);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
