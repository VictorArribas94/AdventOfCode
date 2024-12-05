package objects;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Data {
    private final List<PageOrdering> rules;
    private final List<List<Integer>> lines;
}
