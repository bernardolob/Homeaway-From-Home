package system;

import exceptions.*;
import dataStructures.Iterator;
import system.service.Service;
import system.service.ServiceType;
import system.student.StudentType;

import java.io.*;

public class AppClass implements App {

    private static Area currentArea;

    private static final char SPACE = ' ';
    private static final char HYPHEN = '-';
    private static final String FILE_TYPE = ".ser";

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
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(currentArea);
            out.flush();
        } catch (IOException e) {
            System.out.println(e);;
        }
        return currentArea.getAreaName();
    }

    public void loadArea(String areaName) {
        String fileName = getFileName(areaName);
        try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(fileName))) {
            currentArea = (Area) inFile.readObject();
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
        File file = new File(fileName);
        return file.exists();
    }

    private String getFileName(String s) {
        return s.replace(SPACE, HYPHEN).concat(FILE_TYPE);
    }

    public void createArea(long xMin, long yMin, long xMax, long yMax, String areaName) {
        if (existsFile(getFileName(areaName)))
            throw new ExistingBoundException();
        if (isInvalidArea(xMin, xMax, yMin, yMax))
            throw new InvalidBoundsException();
        Coordinates bottomLeft = new Coordinates(xMin, yMin);
        Coordinates topRight = new Coordinates(xMax, yMax);
        currentArea = new AreaClass(areaName, bottomLeft, topRight);
    }

    public void addService(ServiceType type, long latitude, long longitude, int price, int value, String name) {
        Coordinates serviceCoordinates = new Coordinates(longitude, latitude);
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


}
