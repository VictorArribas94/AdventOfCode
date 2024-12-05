import objects.Data;
import objects.PageOrdering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoadData {

    private static final String FILE_NAME = "DataTest";

    public static void main(String[] args) {
        try {
            Data result = loadColumnsFromFile(FILE_NAME);

            // Mostrar los resultados
            System.out.println("objects.PageOrdering List: " + result.getRules());
            for (List<Integer> array : result.getLines()) {
                System.out.print("Line array: ");
                for (int num : array) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static Data loadColumnsFromFile(String archivoNombre) throws IOException {
        List<PageOrdering> pageOrderings = new ArrayList<>();
        List<List<Integer>> lineArrays = new ArrayList<>();

        // ClassLoader to get File inside classpath
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(LoadData.class.getClassLoader().getResourceAsStream(archivoNombre))))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                // Limpia la línea de espacios en blanco
                linea = linea.trim();

                // Si la línea tiene formato X|Y
                if (linea.contains("|")) {
                    String[] partes = linea.split("\\|");
                    if (partes.length == 2) {
                        int first = Integer.parseInt(partes[0]);
                        int last = Integer.parseInt(partes[1]);
                        pageOrderings.add(PageOrdering.builder().first(first).last(last).build());
                    }
                }
                // Si la línea tiene formato X,Y,Z,...
                else if (linea.contains(",")) {
                    String[] partes = linea.split(",");
                    List<Integer> nums = new ArrayList<Integer>();
                    for (int i = 0; i < partes.length; i++) {
                        nums.add(Integer.parseInt(partes[i].trim()));
                    }
                    lineArrays.add(nums);
                }
            }
        }

        // Usar el patrón Builder para construir la instancia de DataTest
        return Data.builder()
                .rules(pageOrderings)
                .lines(lineArrays)
                .build();
    }
}
