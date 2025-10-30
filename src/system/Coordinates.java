package system;

import java.io.Serializable;

public record Coordinates(long x, long y) implements Serializable {

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long manhattanDistanceFrom(Coordinates other) {
        return Math.abs(this.y - other.y) + Math.abs(this.x - other.x);
    }
}

