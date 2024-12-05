import java.io.IOException;

public class Main {

    private static final String FILE_NAME = "Data";

    public static void main(String[] args) throws IOException {

        final var data = LoadData.loadColumnsFromFile(FILE_NAME);

        Logic.logic(data.getRules(), data.getLines());
    }
}
