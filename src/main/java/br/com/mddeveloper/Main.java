package br.com.mddeveloper;

import br.com.mddeveloper.Model.NotesModel;
import br.com.mddeveloper.Repository.NotesRepository;
import br.com.mddeveloper.Service.NotesService;
import br.com.mddeveloper.Setup.DatabaseSetup;
import br.com.mddeveloper.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        DatabaseSetup.initialize();
        Connection connection = DatabaseConnection.getConnection();
        NotesRepository repository = new NotesRepository(connection);
        NotesService service = new NotesService(repository);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Adicionar anotação");
            System.out.println("2. Listar todas anotações");
            System.out.println("3. Listar por data");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> addNote(scanner, service);
                case "2" -> listAllNotes(service);
                case "3" -> listByDate(scanner, service);
                case "0" -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void addNote(Scanner scanner, NotesService service) {
        System.out.print("Digite a anotação: ");
        String text = scanner.nextLine();
        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString();

        NotesModel note = new NotesModel(text, date, time);
        boolean success = service.createNote(note);

        if (success) {
            System.out.println("Anotação salva com sucesso!");
        } else {
            System.out.println("Erro ao salvar anotação.");
        }
    }

    private static void listAllNotes(NotesService service) {
        List<NotesModel> notes = service.listAllNotes();
        if (notes.isEmpty()) {
            System.out.println("Nenhuma anotação encontrada.");
        } else {
            notes.forEach(System.out::println);
        }
    }

    private static void listByDate(Scanner scanner, NotesService service) {
        System.out.print("Digite a data (yyyy-MM-dd): ");
        String input = scanner.nextLine();
        try {
            LocalDate date = LocalDate.parse(input);
            List<NotesModel> notes = service.listNoteByDate(date);

            if (notes.isEmpty()) {
                System.out.println("Nenhuma anotação para essa data.");
            } else {
                notes.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Formato de data inválido.");
        }
    }
}