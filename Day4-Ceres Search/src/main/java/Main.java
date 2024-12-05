import java.io.IOException;

import static data.LoadData.loadFilteredGrid;
import static logic.Logic.*;

public class Main {

    private static final String FILE_NAME = "Data";
    final static char[] sequence1 = {'X', 'M', 'A', 'S'};

    public static void main(String[] args) throws IOException {

        final char[][] matrixXmas = loadFilteredGrid(FILE_NAME, "XMAS");

        final int horizontalAndVerticalSequences = countHorizontalAndVerticalSequencesInMatrix(matrixXmas, sequence1);

        final int diagonalSequences = countDiagonalSequences(matrixXmas, sequence1);

        final int counter = horizontalAndVerticalSequences + diagonalSequences;

        System.out.println("Total XMAS Sequences: " + counter);

        final char[][] matrixMas = loadFilteredGrid(FILE_NAME, "MAS");

        final int crossCounter = countMasCrossPattern(matrixMas);

        System.out.println("Total X-MAS Sequences: " + crossCounter);
    }
}
