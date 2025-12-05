package system.service;

import dataStructures.List;
import system.Coordinates;
import java.io.Serializable;

public interface Service extends Serializable {
    /**
     * Returns the name of the service.
     *
     * @return - The name of the service
     */
    String getName();

    /**
     * Returns the latitude of the service location.
     *
     * @return - The latitude of the service location
     */
    long getLatitude();

    /**
     * Returns the longitude of the service location.
     *
     * @return - The longitude of the service location
     */
    long getLongitude();

    /**
     * Returns the type of the service (e.g., eating, lodging, leisure).
     *
     * @return - The service type
     */
    ServiceType getType();

    /**
     * Returns the type of the service (e.g., eating, lodging, leisure).
     *
     * @return - The service type
     */
    String getStringType();

    /**
     * Returns the price of the service.
     *
     * @return - The price of the service
     */
    float getPrice();

    /**
     * Adds an evaluation (rating) to the service.
     *
     * @param stars - The star rating for the service (between 1 and 5)
     * @pre - stars must be between 1 and 5
     */
    void addEvaluation(int stars);

    /**
     * Returns the average rating of the service based on all evaluations.
     * The average rating is rounded to the nearest integer.
     *
     * @return - The average star rating of the service
     */
    int getAverageStars();

    void evaluate(int stars, List<String> tag);

    boolean hasTag(String tag);

    long distanceFrom(Coordinates other);

    Coordinates getCoordinates();

}
