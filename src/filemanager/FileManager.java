package filemanager;

import java.io.*;

public class FileManager {

    // Write a full string into a file (overwrites the old file)
    public static void writeToFile(String filePath, String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.print(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read entire file into one string
    public static String readFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);

        if (!file.exists()) return ""; // return empty if missing

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
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