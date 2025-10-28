package system.service;

import dataStructures.DoublyLinkedList;
import dataStructures.List;
import system.Coordinates;

public abstract class AbstractServiceClass implements Service {

    private static final int DEFAULT_RATING = 4;

    private List<String> tags;

    private Coordinates coordinates;
    protected int price;
    private String name;
    private int evaluations;
    private int evalSum;

    public AbstractServiceClass(Coordinates coordinates, int price, String name){
        this.coordinates = coordinates;
        this.price = price;
        this.name = name;
        evaluations = 1;
        evalSum = DEFAULT_RATING;
        tags = new DoublyLinkedList<>();
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

    public abstract ServiceType getType();

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
