package filemanager;

import java.util.*;
import dataModel.*;
import enums.BookStatus;

public class BookFileHandler {

    private static final String FILE_PATH = "database/books.txt";

    public void saveBooks(List<Book> books) {

        StringBuilder sb = new StringBuilder();

        for (Book b : books) {
            sb.append(b.getBookId()).append(";")
              .append(b.getTitle()).append(";")
              .append(b.getAuthor()).append(";")
              .append(b.getGenre()).append(";")
              .append(b.getPublicationYear()).append(";")
              .append(b.getSummary()).append(";")
              .append(b.getStatus()).append("\n");
        }

        FileManager.writeToFile(FILE_PATH, sb.toString());
    }

    public List<Book> loadBooks() {
        List<Book> list = new ArrayList<>();
        String data = FileManager.readFromFile(FILE_PATH);

        if (data.isEmpty()) return list;

        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] p = line.split(";");
            if (p.length != 7) continue;

            Book b = new Book(
                p[0], // id
                p[1], // title
                p[2], // author
                p[3], // genre
                Integer.parseInt(p[4]),
                p[5] // summary
            );

            b.setStatus(BookStatus.valueOf(p[6]));
            list.add(b);
        }

        return list;
    }
}