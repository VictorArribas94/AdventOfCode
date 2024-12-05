package Java;

import java.io.IOException;

import static Java.LoadData.loadRowsFromFile;
import static Java.Logic.countSafeLevels;
import static Java.Logic.countSafeLevelsWithProblemDampener;

public class Main {

    private static final String FILE_NAME = "Data";

    public static void main(String[] args) throws IOException {
        final var values = loadRowsFromFile(FILE_NAME);

        final var totalLevels= values.length;
        final var safeLevels = countSafeLevels(values);

        System.out.printf("Number of Safe Levels: '%d', from a total of '%d' levels.\n", safeLevels, totalLevels);

        final var safeLevelsWithPowerDampener = countSafeLevelsWithProblemDampener(values);

        System.out.printf("PROBLEM DAMPENER: ON.\nNumber of Safe Levels: '%d', from a total of '%d' levels.\n", safeLevelsWithPowerDampener, totalLevels);
    }
}
