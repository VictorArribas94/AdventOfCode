package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoadData {

    private static final String FILE_NAME = "Data";

    public static void main(String[] args) {
        try {
            char[][] grid = loadFilteredGrid(FILE_NAME, "XMAS");

            // Print Array
            for (char[] row : grid) {
                System.out.println(new String(row));
            }
        } catch (IOException e) {
            System.err.printf("Error reading file: {%s}%n", e.getMessage());
        }
    }

    public static char[][] loadFilteredGrid(String fileName, String allowedChars) throws IOException {
        List<char[]> grid = new ArrayList<>();

        // ClassLoader to get File inside classpath
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(LoadData.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] row = new char[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    // Change not needed characters for '.'
                    row[i] = allowedChars.indexOf(c) != -1 ? c : '.';
                }
                grid.add(row);
            }
        }

        return grid.toArray(new char[0][]);
    }
}
