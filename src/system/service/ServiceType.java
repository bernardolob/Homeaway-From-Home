package system.service;

import exceptions.*;
import system.Coordinates;

public enum ServiceType {
    LODGING {
        @Override
        public void checkArguments(int roomPrice, int capacity) {
            if (roomPrice <= 0)
                throw new InvalidRoomPriceException();
            if (capacity <= 0)
                throw new InvalidCapacityException();
        }

        @Override
        public Service createService(Coordinates coordinates, int roomPrice, int capacity, String name) {
            return new LodgingServiceClass(coordinates, roomPrice, name, capacity);
        }
    },
    EATING {
        @Override
        public void checkArguments(int menuPrice, int capacity) {
            if (menuPrice <= 0)
                throw new InvalidMenuPriceException();
            if (capacity <= 0)
                throw new InvalidCapacityException();
        }

        @Override
        public Service createService(Coordinates coordinates, int menuPrice, int capacity, String name) {
            return new EatingServiceClass(coordinates, menuPrice, name, capacity);
        }
    },
    LEISURE {
        @Override
        public Service createService(Coordinates coordinates, int ticketPrice, int discount, String name) {
            return new LeisureServiceClass(coordinates, ticketPrice, name, discount);
        }

        public void checkArguments(int ticketPrice, int discount) {
            if (ticketPrice <= 0)
                throw new InvalidTicketPriceException();
            if (discount < 0 || discount > 100)
                throw new InvalidDiscountException();
        }
    };




    public abstract void checkArguments(int price, int value);

    public abstract Service createService(Coordinates coordinates, int price, int value, String name);

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static ServiceType fromString(String text) {
        for (ServiceType type : values()) {
            if (type.toString().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new InvalidServiceTypeException();
    }
}
