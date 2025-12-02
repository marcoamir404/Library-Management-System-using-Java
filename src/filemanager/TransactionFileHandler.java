package filemanager;

import java.util.*;
import dataModel.Transaction;

public class TransactionFileHandler {

    private static final String FILE_PATH = "dataFiles/transactions.txt";

    public void saveTransactions(List<Transaction> list) {
        StringBuilder sb = new StringBuilder();

        for (Transaction t : list) {
            sb.append(t.getTransactionId()).append(";")
              .append(t.getPatronId()).append(";")
              .append(t.getBookId()).append(";")
              .append(t.getCheckoutDate()).append(";")
              .append(t.getDueDate()).append(";");

            if (t.getReturnDate() != null)
                sb.append(t.getReturnDate());
            else
                sb.append("null");

            sb.append("\n");
        }

        FileManager.writeToFile(FILE_PATH, sb.toString());
    }

    public List<Transaction> loadTransactions() {
        List<Transaction> list = new ArrayList<>();
        String data = FileManager.readFromFile(FILE_PATH);

        if (data.isEmpty()) return list;

        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] p = line.split(";");
            if (p.length != 6) continue;

            Transaction t = new Transaction(
                p[0], // id
                p[1], // patronId
                p[2], // bookId
                java.time.LocalDate.parse(p[3]),
                java.time.LocalDate.parse(p[4])
            );

            if (!p[5].equals("null")) {
                t.setReturnDate(java.time.LocalDate.parse(p[5]));
            }

            list.add(t);
        }

        return list;
    }
}