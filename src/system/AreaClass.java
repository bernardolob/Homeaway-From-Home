package system;

public class AreaClass implements Area {

    private final String name;
    private final Coordinates bottomLeft;
    private final Coordinates topRight;


    public AreaClass(String name, Coordinates bottomLeft, Coordinates topRight) {
        this.name = name;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public String getAreaName() {
        return name;
    }

    public Coordinates getBottomLeft() {
        return bottomLeft;
    }

    public Coordinates getTopRight() {
        return topRight;
    }
}
