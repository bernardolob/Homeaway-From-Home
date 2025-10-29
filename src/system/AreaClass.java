package system;

import exceptions.*;
import dataStructures.*;
import system.service.*;
import system.student.*;
import system.student.StudentType;

public class AreaClass implements Area {

    private static final int NUMBER_OF_RANKS = 5;

    private final Comparator<Service> serviceComparator;
    private final Comparator<Student> studentComparator;

    private final List<Service> servicesByInsertion;
    private final SortedList<Service> servicesByName;
    private final List<TwoWayList<Service>> servicesByRank;
    private final SortedList<Student> studentsByName;
    private final List<Country> countries;

    private final String name;
    private final Coordinates bottomLeft;
    private final Coordinates topRight;

    public AreaClass(String name, Coordinates bottomLeft, Coordinates topRight) {
        this.name = name;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;

        serviceComparator = new ServiceNameComparator();
        studentComparator = new StudentNameComparator();

        servicesByInsertion = new DoublyLinkedList<>();
        servicesByName = new SortedDoublyLinkedList<>(serviceComparator);
        servicesByRank = new ListInArray<>(NUMBER_OF_RANKS);
        studentsByName = new SortedDoublyLinkedList<>(new StudentNameComparator());
        countries = new DoublyLinkedList<>();
        for (int i = 0; i < NUMBER_OF_RANKS; i++) {
            servicesByRank.add(i, new DoublyLinkedList<>());
        }
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

    public Iterator<Service> getServicesIterator() {
        return servicesByInsertion.iterator();
    }

    public String getServiceName(String serviceName) {
        Service s = getService(serviceName);
        if (s == null)
            return null;
        return s.getName();
    }

    public String getStudentName(String studentName) {
        Student s = getStudent(studentName);
        if (s == null)
            return null;
        return s.getName();
    }

    private Country getCountry(String countryName) {
        Iterator<Country> it = countries.iterator();
        while (it.hasNext()) {
            Country country = it.next();
            if (country.getCountryName().equalsIgnoreCase(countryName))
                return country;
        }
        return null;
    }

    private Student getStudent(String studentName) {
        Iterator<Student> it = studentsByName.iterator();
        while (it.hasNext()) {
            Student student = it.next();
            if (student.getName().equalsIgnoreCase(studentName))
                return student;
        }
        return null;
    }

    private boolean hasStudent(String studentName) {
        return getStudent(studentName) == null;
    }

    @Override
    public void addStudent(StudentType studentType, String name, String countryName, String home) {
        Lodging lodging = (Lodging) getService(home);
        if (lodging == null)
            throw new NonExistingLodgingException();
        if (lodging.isFull())
            throw new LodgingFullException();
        if (hasStudent(name))
            throw new AlreadyExistsException();

        Country country = getCountry(countryName);
        if (country == null) {
            country = new CountryClass(countryName);
            countries.addLast(country);
        }
        Student newStudent = studentType.createStudent(name, lodging);
        lodging.addStudent(newStudent);
        country.addCitizen(newStudent);
    }


    private Service getService(String serviceName) {
        Iterator<Service> it = servicesByName.iterator();
        while (it.hasNext()) {
            Service s = it.next();
            if (s.getName().equalsIgnoreCase(serviceName))
                return s;
        }
        return null;
    }

    private boolean hasService(String serviceName) {
        return getService(serviceName) != null;
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
