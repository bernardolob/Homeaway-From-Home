package system.service;

import dataStructures.List;
import dataStructures.SortedDoublyLinkedList;
import dataStructures.SortedList;
import system.Coordinates;

public abstract class AbstractServiceClass implements Service {

    private static final int DEFAULT_RATING = 4;

    private final SortedList<String> tags;

    private final Coordinates coordinates;
    protected int price;
    private final String name;
    private int evaluations;
    private int evalSum;

    public AbstractServiceClass(Coordinates coordinates, int price, String name){
        this.coordinates = coordinates;
        this.price = price;
        this.name = name;
        evaluations = 1;
        evalSum = DEFAULT_RATING;
        tags = new SortedDoublyLinkedList<>(new TagComparator());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getLatitude() {
        return coordinates.getY();
    }

    @Override
    public long getLongitude() {
        return coordinates.getX();
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public void addEvaluation(int stars) {
        evaluations++;
        evalSum += stars;
    }

    @Override
    public int getAverageStars() {
        return Math.round((float) evalSum /evaluations);
    }

    @Override
    public void evaluate(int stars, List<String> tags) {
        addEvaluation(stars);
        for (int i = 0; i < tags.size(); i++) {
            String s = tags.get(i);
            if (!this.tags.contains(s))
                this.tags.add(s);
        }
    }

    @Override
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    @Override
    public long distanceFrom(Coordinates other) {
        return coordinates.manhattanDistanceFrom(other);
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }
}
