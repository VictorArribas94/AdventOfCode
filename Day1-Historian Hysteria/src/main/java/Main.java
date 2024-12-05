import java.io.IOException;

import static data.LoadData.loadColumnsFromFile;
import static logic.Logic.*;

public class Main {

    private static final String FILE_NAME = "Data";

    public static void main(String[] args) throws IOException {
        final var values = loadColumnsFromFile(FILE_NAME);
        System.out.println("values: " + values);

        final var orderedLists = orderLists(values);
        final var listA = orderedLists.getListA();
        final var listB = orderedLists.getListB();

        System.out.println("List A: " + listA.toString());
        System.out.println("List B: " + listB.toString());

        final var difference = getSumOfDifferences(listA, listB);

        System.out.println("Difference: " + difference);

        final var similarity = getSumOfAppearances(listA, listB);

        System.out.println("Similarity Score: " + similarity);
    }
}
