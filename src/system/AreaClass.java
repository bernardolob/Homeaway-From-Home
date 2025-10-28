package system;

import Exceptions.*;
import dataStructures.*;
import system.service.*;
import system.student.Student;
import system.student.StudentNameComparator;

public class AreaClass implements Area {

    private static final int NUMBER_OF_RANKS = 5;

    private final List<Service> servicesByInsertion;
    private final SortedList<Service> servicesByName;
    private final List<TwoWayList<Service>> servicesByRank;
    private SortedList<Student> studentsByName;
    private List<Country> countries;

    private final String name;
    private final Coordinates bottomLeft;
    private final Coordinates topRight;

    public AreaClass(String name, Coordinates bottomLeft, Coordinates topRight) {
        this.name = name;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;

        servicesByInsertion = new DoublyLinkedList<>();
        servicesByName = new SortedDoublyLinkedList<>(new ServiceNameComparator());
        servicesByRank = new ListInArray<>(NUMBER_OF_RANKS);
        studentsByName = new SortedDoublyLinkedList<>(new StudentNameComparator());
        countries = new DoublyLinkedList<>();
    }

    public String getAreaName() {
        return name;
    }

    public Coordinates getBottomLeft() {
        return bottomLeft;
    }

    public Coordinates getTopRight() {
        return topRight;
    }

    public boolean isInside(Coordinates coordinates) {
        boolean xInside = bottomLeft.getX() <= coordinates.getX() && coordinates.getX() <= topRight.getX();
        boolean yInside = bottomLeft.getY() <= coordinates.getY() && coordinates.getY() <= topRight.getY();
        return xInside && yInside;
    }

    public void addService(ServiceType type, Coordinates coordinates, int price, int value, String name) {
        if (!isInside(coordinates))
            throw new InvalidLocationException();
        Service newService = switch (type) {
            case LEISURE    -> addLeisure(coordinates, price, value, name);
            case EATING     -> addEating(coordinates, price, value, name);
            case LODGING    -> addLodging(coordinates, price, value, name);
        };
        servicesByName.add(newService);
        servicesByInsertion.addLast(newService);
        int averageStars = newService.getAverageStars();
        if (servicesByRank.get(averageStars) == null)
            servicesByRank.add(averageStars, new DoublyLinkedList<>());
        servicesByRank.get(averageStars).addFirst(newService);
    }

    private boolean hasService(String serviceName) {
        Iterator<Service> it = servicesByName.iterator();
        while (it.hasNext()) {
            Service s = it.next();
            if (s.getName().equals(serviceName))
                return true;
        }
        return false;
    }

    private Service addLeisure(Coordinates coordinates, int ticketPrice, int discount, String name) {
        if (ticketPrice < 0)
            throw new InvalidTicketPriceException();
        if (discount < 0 || discount > 100)
            throw new InvalidDiscountException();
        if (hasService(name))
            throw new AlreadyExistsException();
        return new LeisureServiceClass(coordinates, ticketPrice, name, discount);
    }

    private Service addEating(Coordinates coordinates, int menuPrice, int capacity, String name) {
        if (menuPrice < 0)
            throw new InvalidMenuPriceException();
        if (capacity <= 0)
            throw new InvalidCapacityException();
        if (hasService(name))
            throw new AlreadyExistsException();
        return new EatingServiceClass(coordinates, menuPrice, name, capacity);
    }

    private Service addLodging(Coordinates coordinates, int roomPrice, int capacity, String name) {
        if (roomPrice < 0)
            throw new InvalidRoomPriceException();
        if (capacity <= 0)
            throw new InvalidCapacityException();
        if (hasService(name))
            throw new AlreadyExistsException();
        return new LodgingServiceClass(coordinates, roomPrice, name, capacity);
    }

}
