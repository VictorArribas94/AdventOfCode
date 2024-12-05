package objects;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageOrdering {
    private int first;
    private int last;
}
