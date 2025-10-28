package system.service;

import system.Coordinates;

public class LeisureServiceClass extends AbstractServiceClass implements Leisure {

    private final int discount;

    public LeisureServiceClass(Coordinates coordinates, int price, String name, int discount) {
        super(coordinates, price, name);
        this.discount = discount;
    }

    /**
     * Returns the price of the leisure service after applying the discount.
     * The discount is expressed as a percentage (0 to 100), and is applied
     * to the original price.
     *
     * @return - The price of the leisure service after discount
     */
    public float getPrice() {
        return price * (1 - ((float) discount /100));
    }

    public ServiceType getType() {
        return ServiceType.LEISURE;
    }

}
