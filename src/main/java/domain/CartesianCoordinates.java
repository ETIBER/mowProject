package domain;

import java.util.Objects;

public class CartesianCoordinates {
    private final Integer x;
    private final Integer y;

    private CartesianCoordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
    public static CartesianCoordinates create(Integer x, Integer y) throws NegativeCoordinateException {
        if (x < 0 || y < 0) throw new NegativeCoordinateException();
        return new CartesianCoordinates(x, y);
    }

    public CartesianCoordinates addVector(CartesianVector vector) throws NegativeCoordinateException {
        return CartesianCoordinates.create(this.getX()+ vector.getX(),this.getY()+ vector.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartesianCoordinates that = (CartesianCoordinates) o;
        return x.equals(that.x) &&
                y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isIn(CartesianCoordinates upperRightCornerPosition) {
        return this.getX() <= upperRightCornerPosition.getX() && this.getY() <= upperRightCornerPosition.getY();
    }
}
