package domain;

public class CartesianVector {
    private final Integer x;
    private final Integer y;

    public CartesianVector(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
