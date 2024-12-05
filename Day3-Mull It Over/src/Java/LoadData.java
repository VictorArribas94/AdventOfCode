package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadData {

    private static final String FILE_NAME = "Data";

    public static void main(String[] args) {
        try {
            String[] filteredData = loadStringsMatchingPattern(FILE_NAME, "mul\\(\\d+,\\d+\\)");

            for (String item : filteredData) {
                System.out.println(item);
            }
        } catch (IOException e) {
            System.err.printf("Error reading file: {%s}", e.getMessage());
        }
    }

    public static String[] loadStringsMatchingPattern(String fileName, String regexPattern) throws IOException {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regexPattern);

        // ClassLoader to get File inside classpath
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(LoadData.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    matches.add(matcher.group());
                }
            }
        }

        return matches.toArray(new String[0]);
    }
}
