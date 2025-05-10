package br.com.mddeveloper.Repository;

import br.com.mddeveloper.Model.NotesModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotesRepository {

    private final Connection connection;

    public NotesRepository(Connection connection) {
        this.connection = connection;
    }

    public int saveNote(NotesModel notes) throws SQLException {
        String sql = "INSERT INTO Notes (text, date, time) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, notes.getText());
            stmt.setString(2, notes.getDate());
            stmt.setString(3, notes.getTime());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Falha ao obter o ID gerado");
            }
        }
    }

    public List<NotesModel> getAllNotes() {
        List<NotesModel> notesList = new ArrayList<>();
        String sql = "SELECT id, text, date, time FROM Notes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                NotesModel notes = new NotesModel(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("date"),
                        rs.getString("time")
                );
                notesList.add(notes);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notesList;
    }

    public List<NotesModel> getNoteByDate(String date) {
        List<NotesModel> notesList = new ArrayList<>();
        String sql = "SELECT id, text, date, time FROM Notes WHERE date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, date);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    NotesModel notes = new NotesModel(
                            rs.getInt("id"),
                            rs.getString("text"),
                            rs.getString("date"),
                            rs.getString("time")
                    );
                    notesList.add(notes);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar as anotações pela data", e);
        }
        return notesList;
    }

}