package domain;

import java.util.EnumSet;

public enum Orientation {
    NORTH("N",0, new CartesianVector(0,1)),
    SOUTH("S",2, new CartesianVector(0,-1)),
    WEST("W",3, new CartesianVector(-1,0)),
    EAST("E",1, new CartesianVector(1,0));

    public final String name;
    public final Integer value;
    public final CartesianVector associatedVector;

    Orientation(String name, Integer value, CartesianVector associatedVector){
        this.name = name;
        this.value = value;
        this.associatedVector = associatedVector;
    }

    static Orientation getOrientationByValue(Integer value){
       return EnumSet.allOf(Orientation.class).stream().filter(orientation -> orientation.value.equals(value)).findFirst().orElse(null);
    }
}
