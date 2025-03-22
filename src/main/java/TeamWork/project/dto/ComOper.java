package TeamWork.project.dto;

import java.util.Map;
import java.util.function.BiPredicate;

public class ComOper {
    private static final Map<String, BiPredicate<Integer, Integer>> MAP = Map.of(
            ">",(w1,w2)->w1>w2,
            "<",(w1,w2)->w1<w2,
            "=",(w1,w2)->w1.equals(w2),
            "<=",(w1,w2)->w1<=w2,
            ">=",(w1,w2)->w1>=w2
    );

    private final String operator;

    public ComOper(String operator) {
        this.operator = operator;
    }

    public boolean compair(int a, int b) {
        return MAP.get(operator).test(a, b);
    }
}
