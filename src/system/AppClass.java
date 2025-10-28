package system;

import Exceptions.*;
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

    public String saveArea() {
        if (isUndefined())
            throw new UndefinedBoundsException();
        String fileName = getFileName(currentArea.getAreaName());
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(currentArea);
            out.flush();
        } catch (IOException e) {
            throw new UndefinedBoundsException();
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

}
