package system.service;

import exceptions.*;
import system.Coordinates;

/**
 * Enum representing all available service types and their specific validation
 * and creation rules. Each type implements argument checking and service
 * instantiation according to its constraints.
 */
public enum ServiceType {
    LODGING {
        /**
         * Validates price and capacity for lodging services.
         *
         * @param roomPrice price per room
         * @param capacity number of available rooms
         */
        @Override
        public void checkArguments(int roomPrice, int capacity) {
            if (roomPrice <= 0)
                throw new InvalidRoomPriceException();
            if (capacity <= 0)
                throw new InvalidCapacityException();
        }

        /**
         * Creates a lodging service instance.
         *
         * @param coordinates location of the service
         * @param roomPrice price per room
         * @param capacity number of rooms
         * @param name service name
         * @pre arguments must pass checkArguments
         * @return a newly constructed LodgingServiceClass instance
         */
        @Override
        public Service createService(Coordinates coordinates, int roomPrice, int capacity, String name) {
            return new LodgingServiceClass(coordinates, roomPrice, name, capacity);
        }
    },

    EATING {
        /**
         * Validates price and capacity for eating services.
         *
         * @param menuPrice price of the menu
         * @param capacity max number of clients
         */
        @Override
        public void checkArguments(int menuPrice, int capacity) {
            if (menuPrice <= 0)
                throw new InvalidMenuPriceException();
            if (capacity <= 0)
                throw new InvalidCapacityException();
        }

        /**
         * Creates an eating service instance.
         *
         * @param coordinates location of the service
         * @param menuPrice price of the menu
         * @param capacity number of clients supported
         * @param name service name
         * @pre arguments must pass checkArguments
         * @return a newly constructed EatingServiceClass instance
         */
        @Override
        public Service createService(Coordinates coordinates, int menuPrice, int capacity, String name) {
            return new EatingServiceClass(coordinates, menuPrice, name, capacity);
        }
    },

    LEISURE {
        /**
         * Validates price and discount for leisure services.
         *
         * @param ticketPrice price of a ticket
         * @param discount discount percentage [0–100]
         */
        @Override
        public void checkArguments(int ticketPrice, int discount) {
            if (ticketPrice <= 0)
                throw new InvalidTicketPriceException();
            if (discount < 0 || discount > 100)
                throw new InvalidDiscountException();
        }

        /**
         * Creates a leisure service instance.
         *
         * @param coordinates location of the service
         * @param ticketPrice price of a ticket
         * @param discount discount percentage
         * @param name service name
         * @pre arguments must pass checkArguments
         * @return a newly constructed LeisureServiceClass instance
         */
        @Override
        public Service createService(Coordinates coordinates, int ticketPrice, int discount, String name) {
            return new LeisureServiceClass(coordinates, ticketPrice, name, discount);
        }
    };

    /**
     * Validates arguments for a specific service type.
     *
     * @param price base price of the service
     * @param value capacity or discount depending on the service type
     * @pre price and value must satisfy constraints defined by each enum constant
     */
    public abstract void checkArguments(int price, int value);

    /**
     * Creates a service of the respective type.
     *
     * @param coordinates service location
     * @param price base price
     * @param value capacity or discount
     * @param name service name
     * @pre parameters must pass checkArguments
     * @return a service instance corresponding to the type
     */
    public abstract Service createService(Coordinates coordinates, int price, int value, String name);

    /**
     * Converts the enum name to lowercase.
     *
     * @return lowercase string representation
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * Converts a string into a ServiceType.
     *
     * @param text textual representation of the service type
     * @pre text must match an existing service type (case-insensitive)
     * @return the matching ServiceType
     */
    public static ServiceType fromString(String text) {
        for (ServiceType type : values())
            if (type.toString().equalsIgnoreCase(text))
                return type;
        throw new InvalidServiceTypeException();
    }
}
