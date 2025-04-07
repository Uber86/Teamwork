package TeamWork.project.dto;

import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Класс ComparisonOperators относиться к dto
 * создан для динамических правил
 * реализация операторов сравнения
 */
public class ComparisonOperators {
    private static final Map<String, BiPredicate<Integer, Integer>> MAP = Map.of(
            ">",(w1,w2)->w1>w2,
            "<",(w1,w2)->w1<w2,
            "=",(w1,w2)->w1.equals(w2),
            "<=",(w1,w2)->w1<=w2,
            ">=",(w1,w2)->w1>=w2
    );

    private final String operator;

    public ComparisonOperators(String operator) {
        this.operator = operator;
    }

    public boolean comparison(int a, int b) {
        return MAP.get(operator).test(a, b);
    }
}
