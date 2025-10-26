package system;

import java.io.Serializable;

public record Coordinates(long x, long y) implements Serializable {

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}

