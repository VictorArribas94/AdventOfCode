package Java;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    final static char[] sequence1 = {'X', 'M', 'A', 'S'};

    public static int countHorizontalAndVerticalSequencesInMatrix(char[][] matrix, char[] sequence) {
        int totalCount = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Rows
        for (char[] row : matrix) {
            totalCount += countSequences(row, sequence);
        }

        // Columns
        for (int col = 0; col < cols; col++) {
            char[] column = new char[rows];
            for (int row = 0; row < rows; row++) {
                column[row] = matrix[row][col];
            }
            totalCount += countSequences(column, sequence);
        }

        return totalCount;
    }

    public static int countDiagonalSequences(char[][] matrix, char[] sequence) {
        int totalCount = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rowIncrement = 1;

        // Main Diagonals (↘)
        for (int startRow = 0; startRow < rows; startRow++) {
            totalCount += countSequencesInDiagonal(matrix, sequence, startRow, 0, rowIncrement, 1);
        }
        for (int startCol = 1; startCol < cols; startCol++) {
            totalCount += countSequencesInDiagonal(matrix, sequence, 0, startCol, rowIncrement, 1);
        }

        // Secondary Diagonals (↙)
        for (int startRow = 0; startRow < rows; startRow++) {
            totalCount += countSequencesInDiagonal(matrix, sequence, startRow, cols - 1, rowIncrement, -1);
        }
        for (int startCol = cols - 2; startCol >= 0; startCol--) {
            totalCount += countSequencesInDiagonal(matrix, sequence, 0, startCol, rowIncrement, -1);
        }

        return totalCount;
    }

    private static int countSequencesInDiagonal(char[][] matrix, char[] sequence, int startRow, int startCol, int rowIncrement, int colIncrement) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Character> diagonal = new ArrayList<>();

        // Get diagonal
        int row = startRow;
        int col = startCol;
        while (row >= 0 && row < rows && col >= 0 && col < cols) {
            diagonal.add(matrix[row][col]);
            row += rowIncrement;
            col += colIncrement;
        }

        // Convert into array and countSequence
        char[] diagonalArray = new char[diagonal.size()];
        for (int i = 0; i < diagonal.size(); i++) {
            diagonalArray[i] = diagonal.get(i);
        }
        return countSequences(diagonalArray, sequence);
    }



    private static int countSequences(char[] array, char[] sequence) {
        final var reverseSequence = reverseCharArray(sequence);
        int count = 0;
        int targetLength = sequence1.length;

        // Check Sequences
        for (int i = 0; i <= array.length - targetLength; i++) {
            boolean match1 = true;
            boolean matchReverse = true;

            // Check elements of subsequences starting on actual i
            for (int j = 0; j < targetLength; j++) {
                if (array[i + j] != sequence[j]) {
                    match1 = false;
                }
                if (array[i + j] != reverseSequence[j]) {
                    matchReverse = false;
                }

                if (!match1 && !matchReverse) {
                    break;
                }
            }

            if (match1 || matchReverse) {
                count++;
            }
        }

        return count;
    }

    public static int countMasCrossPattern(char[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                // Only check A as center cell
                if (matrix[i][j] == 'A') {
                    // Verify 4 possible patterns
                    boolean pattern1 = matrix[i - 1][j - 1] == 'M' && matrix[i + 1][j + 1] == 'S'; // Top Left -> Bottom Right
                    boolean pattern2 = matrix[i - 1][j + 1] == 'M' && matrix[i + 1][j - 1] == 'S'; // Top Right -> Bottom Left
                    boolean pattern3 = matrix[i + 1][j - 1] == 'M' && matrix[i - 1][j + 1] == 'S'; // Bottom Left -> Top Right
                    boolean pattern4 = matrix[i + 1][j + 1] == 'M' && matrix[i - 1][j - 1] == 'S'; // Bottom Right -> Top Left

                    if (atLeastTwoTrue(pattern1, pattern2, pattern3, pattern4)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static boolean atLeastTwoTrue(boolean a, boolean b, boolean c, boolean d) {
        int trueCount = 0;

        if (a) trueCount++;
        if (b) trueCount++;
        if (c) trueCount++;
        if (d) trueCount++;

        return trueCount >= 2;
    }


    private static char[] reverseCharArray(char[] input) {
        int n = input.length;
        char[] reversed = new char[n];

        for (int i = 0; i < n; i++) {
            reversed[i] = input[n - 1 - i];
        }

        return reversed;
    }

}
