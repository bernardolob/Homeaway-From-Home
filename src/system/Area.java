package system;

import system.service.ServiceType;

import java.io.Serializable;

public interface Area extends Serializable {

    String getAreaName();

    Coordinates getBottomLeft();

    Coordinates getTopRight();

    boolean isInside(Coordinates coordinates);

    void addService(ServiceType type, Coordinates coordinates, int price, int value, String name);

}
