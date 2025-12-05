package system;

import dataStructures.List;
import dataStructures.TwoWayIterator;
import exceptions.*;
import dataStructures.Iterator;
import system.service.Service;
import system.service.ServiceType;
import system.student.Student;
import system.student.StudentType;

import java.io.*;

public class AppClass implements App {

    private static Area currentArea;

    private static final char SPACE = ' ';
    private static final char HYPHEN = '-';
    private static final String FILE_TYPE = ".ser";
    private static final String ALL_STUDENTS = "ALL";
    private static final int MIN_STARS = 1;
    private static final int MAX_STARS = 5;


    public AppClass() {
        currentArea = null;
    }

    public boolean isUndefined() {
        return currentArea == null;
    }

    private Coordinates convertCoordinates(long x, long y) {
        return new Coordinates(x, y);
    }


    public String saveArea() {
        if (isUndefined())
            throw new UndefinedBoundsException();
        String fileName = getFileName(currentArea.getAreaName());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(currentArea);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return currentArea.getAreaName();
    }

    public void loadArea(String areaName) {
        if (!isUndefined())
            saveArea();
        String fileName = getFileName(areaName);
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName) );
            currentArea = (Area) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new NonExistingBoundsException();
        }
    }

    /**
     * Checks if the given area defined by coordinates is invalid.
     *
     * @param xMin - minimum X coordinate
     * @param xMax - maximum X coordinate
     * @param yMin - minimum Y coordinate
     * @param yMax - maximum Y coordinate
     * @pre - all parameters must be valid long values
     * @return - true if the area is invalid (i.e., xMin is not less than xMax or yMin is not less than yMax), false otherwise
     */
    private boolean isInvalidArea(long xMin, long xMax, long yMin, long yMax) {
        return !(xMin < xMax && yMin < yMax);
    }

    private boolean existsFile(String fileName) {
        File file = new File(fileName.toLowerCase());
        return file.exists();
    }

    private String getFileName(String s) {
        return s.replace(SPACE, HYPHEN).concat(FILE_TYPE).toLowerCase();
    }

    public void createArea(long xMin, long yMin, long xMax, long yMax, String areaName) {
        if (existsFile(getFileName(areaName)))
            throw new ExistingBoundException();
        if (isInvalidArea(xMin, xMax, yMin, yMax))
            throw new InvalidBoundsException();
        if (!isUndefined())
            saveArea();
        Coordinates bottomLeft = convertCoordinates(xMin, yMin);
        Coordinates topRight = convertCoordinates(xMax, yMax);
        currentArea = new AreaClass(areaName, bottomLeft, topRight);
        saveArea();
    }

    public void addService(ServiceType type, long latitude, long longitude, int price, int value, String name) {
        Coordinates serviceCoordinates = convertCoordinates(longitude, latitude);
        currentArea.addService(type, serviceCoordinates, price, value, name);
    }

    public Iterator<Service> getServicesIterator() {
        return currentArea.getServicesIterator();
    }

    @Override
    public String getAreaName() {
        return currentArea.getAreaName();
    }

    @Override
    public String getServiceName(String serviceName) {
        return currentArea.getServiceName(serviceName);
    }

    public String getStudentName(String studentName) {
        return currentArea.getStudentName(studentName);
    }

    @Override
    public void addStudent(StudentType studentType, String name, String country, String home) {
        currentArea.addStudent(studentType, name, country, home);
    }

    @Override
    public void removeStudent(String name) {
        currentArea.removeStudent(name);
    }

    @Override
    public boolean isListingAllStudents(String country) {
        return country.equalsIgnoreCase(ALL_STUDENTS);
    }

    @Override
    public Iterator<Student> getStudentIterator(String country) {
        if (isListingAllStudents(country))
            return currentArea.getAllStudentsIterator();
        else return currentArea.getCountryStudentsIterator(country);
    }

    @Override
    public Service getStudentLocation(String studentName) {
        return currentArea.getStudentLocation(studentName);
    }

    @Override
    public boolean goStudent(String name, String location) {
        return currentArea.goStudent(name, location);
    }

    @Override
    public void move(String student, String lodging) {
        currentArea.setHome(student, lodging);
    }

    @Override
    public TwoWayIterator<Student> getUsers(String order, String service) {
        return currentArea.getUsers(order, service);
    }

    @Override
    public Iterator<Service> getVisitsIterator(String student) {
        return currentArea.getVisitsIterator(student);
    }

    @Override
    public void evaluate(String service, int stars, List<String> tags) {
        if (stars < MIN_STARS || stars > MAX_STARS)
            throw new InvalidEvaluationException();
        currentArea.evaluate(service, stars, tags);
    }

    @Override
    public Iterator<Iterator<Service>> getRankingIterator() {
        return currentArea.getRankingServices();
    }

    @Override
    public Iterator<Service> getTaggedServices(String tag) {
        return currentArea.getTaggedServices(tag);
    }

    @Override
    public Iterator<Service> getRankedIterator(String studentName, int stars, String serviceType) {
        if (stars < MIN_STARS || stars > MAX_STARS)
            throw new InvalidEvaluationException();
        Coordinates coordinates = currentArea.getStudentCoordinates(studentName);
        ServiceType type = getServiceType(serviceType);
        if (!currentArea.hasServiceType(type))
            throw new NoServicesWithTypeException();
        if (!currentArea.hasServiceAvg(stars))
            throw new NoServicesWithAvgException();
        return currentArea.getRankedIterator(coordinates, stars, type);
    }

    @Override
    public ServiceType getServiceType(String stringType) {
        return ServiceType.fromString(stringType);
    }

    @Override
    public StudentType getStudentType(String stringType) {
        return StudentType.fromString(stringType);
    }

    @Override
    public String getServiceType(ServiceType type) {
        return type.toString();
    }

    @Override
    public String getStudentType(StudentType type) {
        return type.toString();
    }

    @Override
    public Service find(String studentName, String serviceType) {
        ServiceType type = getServiceType(serviceType);
        return currentArea.find(studentName, type);
    }
}
