package filemanager;

import java.util.*;
import dataModel.Reservation;

public class ReservationFileHandler {

    private static final String FILE_PATH = "database/reservations.txt";

    public void saveReservations(List<Reservation> list) {
        StringBuilder sb = new StringBuilder();

        for (Reservation r : list) {
            sb.append(r.getReservationId()).append(";")
              .append(r.getPatronId()).append(";")
              .append(r.getBookId()).append(";")
              .append(r.getReservationDate()).append(";")
              .append(r.isNotified()).append("\n");
        }

        FileManager.writeToFile(FILE_PATH, sb.toString());
    }

    public List<Reservation> loadReservations() {
        List<Reservation> list = new ArrayList<>();
        String data = FileManager.readFromFile(FILE_PATH);

        if (data.isEmpty()) return list;

        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] p = line.split(";");
            if (p.length != 5) continue;

            Reservation r = new Reservation(
                p[0],       // id
                p[1],       // patronId
                p[2],       // bookId
                java.time.LocalDate.parse(p[3]) // date
            );

            r.setNotificationStatus(Boolean.parseBoolean(p[4]));

            list.add(r);
        }

        return list;
    }
}
