package br.com.mddeveloper.Service;

import br.com.mddeveloper.Model.NotesModel;
import br.com.mddeveloper.Repository.NotesRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static br.com.mddeveloper.Util.DateTimeConverter.*;

public class NotesService {

    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public int createNote(String text) {
        String currentDate = toDateString(LocalDate.now());
        String currentTime = toTimeString(LocalTime.now());

        NotesModel note = new NotesModel(0, text, currentDate, currentTime);

        try {
            return notesRepository.saveNote(note);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao Salvar a aotação", e);
        }
    }

    public List<NotesModel> listAllNotes() {
        try {
            return notesRepository.getAllNotes();
        } catch (RuntimeException e) {
            System.out.println("Não foi possível buscar as anotações: " + e.getMessage());
            return List.of();
        }
    }

    public List<NotesModel> listNoteByDate(LocalDate date) {
        try {
            return notesRepository.getNoteByDate(toDateString(date));
        } catch (RuntimeException e) {
            System.out.println("Não foi possível achar as anotações pela data: " + e.getMessage());
            return List.of();
        }
    }
}
