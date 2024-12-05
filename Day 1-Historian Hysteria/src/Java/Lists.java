package Java;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Lists {
    private List<Integer> listA;
    private List<Integer> listB;

    private Lists(List<Integer> listA, List<Integer> listB) {
        this.listA = listA;
        this.listB = listB;
    }
}
