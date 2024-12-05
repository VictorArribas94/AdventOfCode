package logic;

import objects.Multiplication;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    public static int[] multiplyAll(Multiplication[] multiplications) {
        return java.util.Arrays.stream(multiplications)
                .mapToInt(m -> m.getNumberA() * m.getNumberB())
                .toArray();
    }

    public static int sumArray(int[] numbers) {
        return java.util.Arrays.stream(numbers).sum();
    }

    public static String[] filterArray(String[] input) {
        List<String> validValues = new ArrayList<>();
        boolean doMode = true;

        for (String value : input) {
            if (value.equals("don't()")) {
                doMode = false;
            } else if (value.equals("do()")) {
                doMode = true;
            }

            if (doMode && !value.equals("do()")) {
                validValues.add(value);
            }
        }

        return validValues.toArray(new String[0]);
    }
}
