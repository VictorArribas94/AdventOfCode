package Java;

import lombok.Builder;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Getter
public class Multiplication {
    private int NumberA;
    private int NumberB;

    public static Multiplication[] getOperations(String[] operations) {
        Multiplication[] result = new Multiplication[operations.length];

        for (int i = 0; i < operations.length; i++) {
            result[i] = getOperation(operations[i]);
        }

        return result;
    }

    public static Multiplication getOperation(String operation) {
        String regex = "mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(operation.trim());

        if (matcher.matches()) {
            int numberA = Integer.parseInt(matcher.group(1));
            int numberB = Integer.parseInt(matcher.group(2));

            return Multiplication.builder()
                    .NumberA(numberA)
                    .NumberB(numberB)
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid operation format: " + operation);
        }
    }

    @Override
    public String toString() {
        return "Multiplication(" + NumberA + "," + NumberB + ")";
    }
}
