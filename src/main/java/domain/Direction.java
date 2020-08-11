package domain;

import java.util.EnumSet;

public enum Direction {
    LEFT("L"), RIGHT("R"), FORWARD("F");

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    public static Direction getDirectionByValue(String value) {
        return EnumSet.allOf(Direction.class).stream().filter(direction -> direction.value.equals(value)).findFirst().orElseThrow();
    }
}
