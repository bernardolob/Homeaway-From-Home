package system.service;

import dataStructures.SortedDoublyLinkedList;
import dataStructures.SortedList;
import system.Coordinates;

public abstract class AbstractServiceClass implements Service {

    private static final int DEFAULT_RATING = 4;

    private static final String TAG_REGEX = " ";

    private SortedList<String> tags;

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

    public String getName() {
        return name;
    }

    public long getLatitude() {
        return coordinates.getY();
    }

    public long getLongitude() {
        return coordinates.getX();
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

    @Override
    public void evaluate(int stars, String tag) {
        addEvaluation(stars);
        for (String s : tag.split(TAG_REGEX))
            if (!tags.contains(s))
                tags.add(s);
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
