package piglatin;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Book {

    private String title;
    private ArrayList<String> lines = new ArrayList<>();

    Book() {}

    String getTitle() { return title; }
    void setTitle(String t) { title = t; }
    String getLine(int i) { return lines.get(i); }
    int getLineCount() { return lines.size(); }
    void appendLine(String line) { lines.add(line); }

    // Read all lines from a URL
    public void readFromUrl(String t, String link) {
        title = t;
        BufferedReader reader = null;

        try {
            // Check if the input is a real URL or just text
            if (link.startsWith("http")) {
                URL web = new URL(link);
                InputStream stream = web.openStream();
                InputStreamReader isr = new InputStreamReader(stream, "UTF-8");
                reader = new BufferedReader(isr);

                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                System.out.println("Book read from URL");
            } else {
                // Treat link as plain text (e.g. "Dog\nCat\nMouse")
                String[] parts = link.split("\n");
                for (String line : parts) {
                    lines.add(line);
                }
                System.out.println("Book read from text input");
            }

        } catch (Exception e) {
            System.out.println("Error reading book: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (Exception ignore) {}
        }
    }

    // ✅ Print specific lines
    public void printLines(int start, int end) {
        for (int i = start; i <= end && i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
    }

    // Write all lines to file
    void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Book written to " + fileName);
        } catch (Exception e) {
            System.out.println("Error writing book: " + e.getMessage());
        }
    }
}
