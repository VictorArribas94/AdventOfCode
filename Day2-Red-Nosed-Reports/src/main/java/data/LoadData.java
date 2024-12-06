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
            int[][] data = loadRowsFromFile(FILE_NAME);

            for (int[] row : data) {
                System.out.println(java.util.Arrays.toString(row));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static int[][] loadRowsFromFile(String fileName) throws IOException {
        List<int[]> rows = new ArrayList<>();

        // ClassLoader to get File inside classpath
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(LoadData.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Divide lane by empty spaces
                String[] numbers = line.trim().split("\\s+");

                int[] row = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    row[i] = Integer.parseInt(numbers[i]);
                }
                rows.add(row);
            }
        }

        return rows.toArray(new int[0][]);
    }
}
