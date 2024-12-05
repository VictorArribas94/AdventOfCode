import java.io.IOException;
import java.util.Arrays;

import static data.LoadData.loadStringsMatchingPattern;
import static logic.Logic.*;
import static objects.Multiplication.getOperations;

public class Main {

    private static final String FILE_NAME = "Data";

    public static void main(String[] args) throws IOException {

        final var data = loadStringsMatchingPattern(FILE_NAME, "mul\\(\\d+,\\d+\\)");

        final var operations = getOperations(data);

        final var multiplicationValues = multiplyAll(operations);

        final var totalValue = sumArray(multiplicationValues);

        System.out.println("Final value is: " + totalValue);


        final var dataDoDont = loadStringsMatchingPattern(FILE_NAME, "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");

       System.out.println("Founded Values: " + Arrays.toString(dataDoDont));

       final var filteredData = filterArray(dataDoDont);

       System.out.println("Filtered Values: " + Arrays.toString(filteredData));

       final var filteredOperations = getOperations(filteredData);

        final var filteredMultiplicationValues = multiplyAll(filteredOperations);

       final var filteredTotalValue = sumArray(filteredMultiplicationValues);

       System.out.println("Final Filtered Value is : " + filteredTotalValue);
    }
}
