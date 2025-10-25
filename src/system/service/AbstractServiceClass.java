package system.service;

import dataStructures.List;
import system.Coordinates;

public abstract class AbstractServiceClass implements Service {

    private static final int DEFAULT_RATING = 4;

    private Coordinates coordinates;
    private int price;
    private String name;
    private ServiceType type;
    private int evaluations;
    private int evalSum;

    public AbstractServiceClass(Coordinates coordinates, int price, String name, ServiceType type){
        this.coordinates = coordinates;
        this.price = price;
        this.name = name;
        this.type = type;
        evaluations = 1;
        evalSum = DEFAULT_RATING;
    }

    public String getName() {
        return name;
    }

    public long getLatitude() {
        return coordinates.getY();
    }

    public long getLongitude() {
        return coordinates.getX();
    }

    public ServiceType getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public void addEvaluation(int stars) {
        evaluations++;
        evalSum += stars;
    }

    public int getAverageStars() {
        return Math.round((float) evalSum /evaluations);
    }


}
