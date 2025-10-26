package system;

import java.io.Serializable;

public interface Area extends Serializable {

    String getAreaName();

    Coordinates getBottomLeft();

    Coordinates getTopRight();

}
