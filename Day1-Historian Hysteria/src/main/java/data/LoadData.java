package data;

import objects.Lists;

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
            Lists lists = loadColumnsFromFile(FILE_NAME);

            List<Integer> ListA = lists.getListA();
            List<Integer> ListB = lists.getListB();

            System.out.println("ListA: " + ListA);
            System.out.println("ListB: " + ListB);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static Lists loadColumnsFromFile(String archivoNombre) throws IOException {
        List<Integer> column1Temp = new ArrayList<>();
        List<Integer> column2Temp = new ArrayList<>();

        // ClassLoader to get File inside classpath
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(LoadData.class.getClassLoader().getResourceAsStream(archivoNombre))))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        // Divide lane by empty spaces
                        String[] numeros = linea.trim().split("\\s+");
                        if (numeros.length == 2) {
                            column1Temp.add(Integer.parseInt(numeros[0]));
                            column2Temp.add(Integer.parseInt(numeros[1]));
                        }
                    }
        }

        return Lists.builder()
                .listA(column1Temp)
                .listB(column2Temp)
                .build();
    }
}
