package system;

import dataStructures.*;
import exceptions.*;
import system.service.*;
import system.student.*;
import system.student.StudentType;

public class AreaClass implements Area {

    private static final int NUMBER_OF_RANKS = 5;

    private final List<Service> servicesByInsertion;
    private final SortedMap<String, Service> servicesByName;
    private final List<List<Service>> servicesByRank;
    private final SortedMap<String, Student> studentsByName;
    private final List<Country> countries;

    private Lodging cheapestLodging;
    private Eating  cheapestEating;
    private Leisure cheapestLeisure;

    private final String name;
    private final Coordinates bottomLeft;
    private final Coordinates topRight;

    public AreaClass(String name, Coordinates bottomLeft, Coordinates topRight) {
        this.name = name;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;

        cheapestLodging = null;
        cheapestEating = null;
        cheapestLeisure = null;

        servicesByInsertion = new DoublyLinkedList<>();
        servicesByName = new AVLSortedMap<>();
        servicesByRank = new ListInArray<>(NUMBER_OF_RANKS);
        studentsByName = new AVLSortedMap<>();
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
        type.checkArguments(price, value);
        if (hasService(name))
            throw new AlreadyExistsException();
        Service newService = type.createService(coordinates, price, value, name);
        servicesByName.put(name.toLowerCase(), newService);
        servicesByInsertion.addLast(newService);
        int averageStars = newService.getAverageStars();
        if (servicesByRank.get(averageStars-1) == null)
            servicesByRank.add(averageStars-1, new DoublyLinkedList<>());
        servicesByRank.get(averageStars-1).addLast(newService);
        checkForCheapest(newService);
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

    @Override
    public void addStudent(StudentType studentType, String name, String countryName, String home) {
        if (!(getService(home) instanceof Lodging lodging))
            throw new NonExistingLodgingException();
        if (lodging.isFull())
            throw new ServiceFullException();
        if (hasStudent(name))
            throw new AlreadyExistsException();

        Country country = getCountry(countryName);
        if (country == null) {
            country = new CountryClass(countryName);
            countries.addLast(country);
        }
        Student newStudent = studentType.createStudent(name, lodging, country);
        studentsByName.put(name.toLowerCase(), newStudent);
    }

    @Override
    public void removeStudent(String name) {
        Student student = studentsByName.remove(name.toLowerCase());
        if (student == null)
            throw new NonExistingStudentException();
        student.removeStudent();
    }

    @Override
    public Iterator<Student> getAllStudentsIterator() {
        return studentsByName.values();
    }

    @Override
    public Iterator<Student> getCountryStudentsIterator(String country) {
        Country c = getCountry(country);
        if (c != null)
            return c.getStudentsIterator();
        else return null;
    }

    @Override
    public Service getStudentLocation(String studentName) {
        Student s = getStudent(studentName);
        if (s == null)
            throw new NonExistingStudentException();
        return s.getLocation();
    }

    @Override
    public boolean goStudent(String name, String location) {
        Service service = getService(location);
        if (service == null)
            throw new UnknownLocationException();
        Student student = getStudent(name);
        if (student == null)
            throw new NonExistingStudentException();
        if (service.getType() == ServiceType.LODGING)
            throw new InvalidServiceException();
        return student.changeLocation(service);
    }

    @Override
    public void setHome(String studentName, String lodging) {
        Service newHome = getService(lodging);
        if (!(newHome instanceof Lodging))
            throw new NonExistingLodgingException();
        Student student = getStudent(studentName);
        if (student == null)
            throw new NonExistingStudentException();
        student.changeHome((Lodging) newHome);
    }

    @Override
    public TwoWayIterator<Student> getUsers(String order, String serviceName) {
        Service service = getService(serviceName);
        if (service == null)
            throw new UnknownLocationException();
        if (!(service instanceof LimitedService))
            throw new InvalidServiceException();
        return ((LimitedService) service).getPresentStudents();
    }

    @Override
    public Iterator<Service> getVisitsIterator(String studentName) {
        Student student = getStudent(studentName);
        if (student == null) {
            throw new NonExistingStudentException();
        }
        if (!(student instanceof VisitingStudent))
            throw new ThriftyStudentException();
        return ((VisitingStudent) student).getVisitsIterator();
    }

    @Override
    public void evaluate(String serviceName, int stars, List<String> tags) {
        Service service = getService(serviceName);
        if (service == null)
            throw new UnknownLocationException();
        int oldAverage = service.getAverageStars();
        service.evaluate(stars, tags);
        updateService(oldAverage, service);
    }

    private void updateService(int oldAverage, Service service) {
        int newAverage = service.getAverageStars();
        if (oldAverage != newAverage) {
            int idx = servicesByRank.get(oldAverage-1).indexOf(service);
            servicesByRank.get(oldAverage-1).remove(idx);
            if (servicesByRank.get(newAverage-1) == null)
                servicesByRank.add(newAverage-1, new DoublyLinkedList<>());
            servicesByRank.get(newAverage-1).addLast(service);
        }
    }

    @Override
    public Iterator<Iterator<Service>> getRankingServices() {
        List<Iterator<Service>> l = new DoublyLinkedList<>();
        for (int i = 0; i < NUMBER_OF_RANKS; i++) {
            l.addFirst(servicesByRank.get(i).iterator());
        }
        return l.iterator();
    }

    @Override
    public Iterator<Service> getTaggedServices(String tag) {
        return new FilterIterator<>(servicesByInsertion.iterator(), new ServiceTagFilter(tag));
    }

    @Override
    public Iterator<Service> getRankedIterator(Coordinates studentCoordinates, int stars, ServiceType serviceType) {
        Iterator<Service> rankedIterator = new FilterIterator<>(servicesByRank.get(stars-1).iterator(), new ServiceTypeFilter(serviceType));
        long minDistance = getMinDistanceFrom(studentCoordinates, rankedIterator);
        rankedIterator.rewind();
        return new FilterIterator<>(rankedIterator, new ServiceDistanceFilter(studentCoordinates, minDistance));
    }

    @Override
    public Coordinates getStudentCoordinates(String studentName) {
        Student student = getStudent(studentName);
        if (student == null) {
            throw new NonExistingStudentException();
        }
        return student.getCoordinates();
    }

    @Override
    public boolean hasServiceType(ServiceType type) {
        Iterator<Service> it = servicesByInsertion.iterator();
        while (it.hasNext())
            if (it.next().getType() == type)
                return true;
        return false;
    }

    @Override
    public boolean hasServiceAvg(int stars) {
        return !servicesByRank.get(stars-1).isEmpty();
    }

    @Override
    public Service find(String studentName, ServiceType type) {
        Student student = getStudent(studentName);
        if (!hasServiceType(type))
            throw new NoServicesWithTypeException();
        if (!(student instanceof Thrifty)) {
            List<Iterator<Service>> l = new DoublyLinkedList<>();
            for (int i = 0; i < NUMBER_OF_RANKS; i++) {
                l.addFirst(new FilterIterator<>(servicesByRank.get(i).iterator(), new ServiceTypeFilter(type)));
            }
            Iterator<Iterator<Service>> it1 = l.iterator();
            while (it1.hasNext()) {
                Iterator<Service> it2 = it1.next();
                if (it2.hasNext()) {
                    return it2.next();
                }
            }
        }
        else {
            return switch (type) {
                case LODGING    -> cheapestLodging;
                case EATING     -> cheapestEating;
                case LEISURE    -> cheapestLeisure;
            };
        }
        return null;
    }

    private long getMinDistanceFrom(Coordinates coordinates, Iterator<Service> it) {
        long min = -1;
        while (it.hasNext()) {
            Service s = it.next();
            long dist = s.distanceFrom(coordinates);
            if (min == -1 || dist < min)
                min = dist;
        }
        return min;
    }


    private Service getService(String serviceName) {
        return servicesByName.get(serviceName.toLowerCase());
    }

    private boolean hasService(String serviceName) {
        return getService(serviceName) != null;
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
        return studentsByName.get(studentName.toLowerCase());
    }

    private boolean hasStudent(String studentName) {
        return getStudent(studentName) != null;
    }

    private void checkForCheapest(Service s) {
        switch (s.getType()) {
            case LODGING :
                if (cheapestLodging == null || s.getPrice() < cheapestLodging.getPrice())
                    cheapestLodging = (Lodging) s;
                break;
            case LEISURE :
                if (cheapestLeisure == null || s.getPrice() < cheapestLeisure.getPrice())
                    cheapestLeisure = (Leisure) s;
                break;
            case EATING :
                if (cheapestEating == null || s.getPrice() < cheapestEating.getPrice())
                    cheapestEating = (Eating) s;
                break;
        }
    }
}
