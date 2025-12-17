package filemanager;

import java.io.*;
import java.util.Scanner;

public class FileManager {

    public static void writeToFile(String filePath, String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.print(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);

        if (!file.exists()) return "";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }
}