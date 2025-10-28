package system;

import system.service.ServiceType;

public interface App {

    String saveArea();

    void loadArea(String areaName);

    boolean isUndefined();

    void createArea(long xMin, long yMin, long xMax, long yMax, String areaName);

    void addService(ServiceType type, long latitude, long longitude, int price, int value, String name);
}
