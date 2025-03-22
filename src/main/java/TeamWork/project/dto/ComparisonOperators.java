package TeamWork.project.dto;

public enum ComparisonOperators {
    MORE_THAN(">"),
    LESS_THAN("<"),
    EQUALS("=="),
    GREATER_OR_EQUAL(">="),
    LESS_OR_EQUAL("<=");

    private final String s;

    ComparisonOperators(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
