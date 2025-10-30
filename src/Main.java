import java.util.Scanner;

import exceptions.*;
import dataStructures.Iterator;
import system.*;
import system.service.Service;
import system.service.ServiceType;
import system.student.Student;
import system.student.StudentType;

public class Main {

// CONSTANTS


    // TODO NOTA: Perguntar à professora se é melhor ter uma (ou mais) classe(s) só para as constantes.

    /************ Commands ************/
    private static final String EXIT_CMD = "EXIT";
    private static final String HELP_CMD = "HELP";
    private static final String BOUNDS_CMD = "BOUNDS";
    private static final String SAVE_CMD = "SAVE";
    private static final String LOAD_CMD = "LOAD";
    private static final String SERVICE_CMD = "SERVICE";
    private static final String SERVICES_CMD = "SERVICES";
    private static final String STUDENT_CMD = "STUDENT";
    private static final String LEAVE_CMD = "LEAVE";
    private static final String STUDENTS_CMD = "STUDENTS";
    private static final String GO_CMD = "GO";
    private static final String MOVE_CMD = "MOVE";
    private static final String USERS_CMD = "USERS";
    private static final String WHERE_CMD = "WHERE";
    private static final String VISITED_CMD = "VISITED";
    private static final String STAR_CMD = "STAR";
    private static final String RANKING_CMD = "RANKING";
    private static final String RANKED_CMD = "RANKED";
    private static final String TAG_CMD = "TAG";
    private static final String FIND_CMD = "FIND";

    /************ Types ************/
    // Services
    private static final String EATING_TYPE = "eating";
    private static final String LODGING_TYPE = "lodging";
    private static final String LEISURE_TYPE = "leisure";
    // Students
    private static final String BOOKISH_TYPE = "bookish";
    private static final String OUTGOING_TYPE = "outgoing";
    private static final String THRIFTY_TYPE = "thrifty";

    /************ Errors ************/
    private static final String UNDEFINED_BOUNDS_ERR = "System bounds not defined.\n";
    private static final String INVALID_BOUNDS_ERR = "Invalid bounds.\n";
    private static final String EXISTING_BOUNDS_ERR = "Bounds already exists. Please load it!\n";
    private static final String NON_EXISTING_BOUNDS_ERR = "Bounds %s does not exists.\n";
    private static final String INVALID_SERVICE_TYPE_ERR = "Invalid service type!\n";
    private static final String INVALID_LOCATION_ERR = "Invalid location!\n";
    private static final String INVALID_MENU_ERR = "Invalid menu price!\n";
    private static final String INVALID_TICKET_ERR = "Invalid ticket price!\n";
    private static final String INVALID_ROOM_ERR = "Invalid room price!\n";
    private static final String INVALID_DISCOUNT_ERR = "Invalid discount price!\n";
    private static final String INVALID_CAPACITY_ERR = "Invalid capacity!\n";
    private static final String ALREADY_EXISTS_ERR = "%s already exists!\n";
    private static final String INVALID_STUDENT_ERR = "Invalid student type!\n";
    private static final String NON_EXISTING_LODGING_ERR = "lodging %s does not exist!\n";
    private static final String LODGING_FULL_ERR = "lodging %s is full!\n";
    private static final String UNKNOWN_LOCATION_ERR = "Unknown %s!\n";
    private static final String NON_EXISTING_ERR = "%s does not exist!\n";
    private static final String INVALID_SERVICE_ERR = "%s is not a valid service!\n";
    private static final String ALREADY_THERE_ERR = "Already there!\n";
    private static final String EATING_FULL_ERR = "eating %s is full!\n";
    private static final String ALREADY_HOME_ERR = "That is %s's home!\n";
    private static final String UNACCEPTABLE_MOVE_ERR = "Move is not acceptable for %s!\n";
    private static final String NON_EXISTING_ORDER_ERR = "This order does not exists!\n";
    private static final String NO_USERS_SERVICE_ERR = "%s does not control student entry and exit!\n";
    private static final String IS_THRIFTY_ERR = "%s is thrifty!\n";
    private static final String INVALID_EVALUATION_ERR = "Invalid evaluation!\n";
    private static final String INVALID_STARS_ERR = "Invalid stars!\n";
    private static final String NO_SERVICE_TYPE_ERR = "No %s services!\n";

    /************ Messages ************/
    // General
    private static final String UNKNOWN_MSG = "Unknown command. Type help to see available commands.\n";
    private static final String DOES_NOT_EXIST = "%s does not exist!\n";
    // Help
    private static final String HELP_MSG = "%s - %s\n";
    // Exit
    private static final String EXIT_MSG = "Bye!\n";
    // Bounds
    private static final String BOUNDS_MSG = "%s created.\n";
    // Save
    private static final String SAVE_MSG = "%s saved.\n";
    // Load
    private static final String LOAD_MSG = "%s loaded.\n";
    // Service
    private static final String SERVICE_MSG = "%s %s added.\n";
    // Services
    private static final String SERVICES_MSG = "%s: %s (%d, %d).\n";
    private static final String NO_SERVICES_MSG = "No services yet!\n";
    // Student
    private static final String STUDENT_MSG = "%s added.\n";
    // Leave
    private static final String LEAVE_MSG = "%s has left.\n";
    // Students
    private static final String STUDENTS_MSG = "%s: %s at %s.\n";
    private static final String NO_STUDENTS_MSG = "No students yet!\n";
    private static final String NO_COUNTRY_MSG = "No students from %s!\n";
    // Go
    private static final String GO_MSG = "‰s in now at %s.\n";
    private static final String DISTRACTED = "%s is distracted!\n";
    private static final String ALREADYTHERE_MSG = "Already there!\n";
    // Move
    private static final String MOVE_MSG = "lodging %s is now %s's home. %s is at home.\n";
    private static final String ALREADY_HOME_MSG = "That is %s's home!\n";
    // Users
    private static final String USERS_MSG = "%s: %s\n";
    private static final String NO_USERS_MSG = "No students on %s!";
    // Where
    private static final String WHERE_MSG = "%s is at %s %s (%d, %d).\n";
    // Visited
    private static final String VISITED_MSG = "%s\n";
    private static final String THRIFTY_MSG = "%s is thrifty!\n";
    private static final String NO_VISITED_MSG = "%s has not visited any locations!\n";
    // Star
    private static final String STAR_MSG = "Your evaluation has been registered!\n";
    // Ranking
    private static final String DESCENDING_MSG = "Services sorted in descending order\n";
    private static final String RANKING_MSG = "%s: %d\n"; // Enunciado inconsistente com o ponto final
    private static final String NO_RANKING_MSG = "No services in the system.\n";
    // Ranked
    private static final String RANKED_HEAD_MSG = "%s services closer with %d average\n";
    private static final String RANKED_MSG = "%s\n";
    private static final String NO_RANKED_MSG = "No %s services with average!\n";
    // Tag
    private static final String TAG_MSG = "%s %s\n";
    private static final String NO_TAG_MSG = "There are no services with this tag!\n";
    // Find
    private static final String FIND_MSG = "%s\n";


    /******************************** MAIN ********************************/

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Commands command;
        App app = new AppClass();
        do {
            command = getCommand(in.next().toUpperCase());
            executeCommand(app, in, command);
        } while (!Commands.EXIT.equals(command));
        in.close();
    }

    private static Commands getCommand(String command) {
        switch (command) {
            case EXIT_CMD -> {
                return Commands.EXIT;
            }
            case HELP_CMD -> {
                return Commands.HELP;
            }
            case BOUNDS_CMD -> {
                return Commands.BOUNDS;
            }
            case SAVE_CMD -> {
                return Commands.SAVE;
            }
            case LOAD_CMD -> {
                return Commands.LOAD;
            }
            case SERVICE_CMD -> {
                return Commands.SERVICE;
            }
            case SERVICES_CMD -> {
                return Commands.SERVICES;
            }
            case STUDENT_CMD -> {
                return Commands.STUDENT;
            }
            case LEAVE_CMD -> {
                return Commands.LEAVE;
            }
            case STUDENTS_CMD -> {
                return Commands.STUDENTS;
            }
            case GO_CMD -> {
                return Commands.GO;
            }
            case MOVE_CMD -> {
                return Commands.MOVE;
            }
            case USERS_CMD -> {
                return Commands.USERS;
            }
            case STAR_CMD -> {
                return Commands.STAR;
            }
            case WHERE_CMD -> {
                return Commands.WHERE;
            }
            case VISITED_CMD -> {
                return Commands.VISITED;
            }
            case RANKING_CMD -> {
                return Commands.RANKING;
            }
            case RANKED_CMD -> {
                return Commands.RANKED;
            }
            case TAG_CMD -> {
                return Commands.TAG;
            }
            case FIND_CMD -> {
                return Commands.FIND;
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Executes a specific command based on user input.
     *
     * @param in      - Scanner object for reading user input
     * @param command - the command to execute
     * @pre - 'in' must not be null
     */
    private static void executeCommand(App app, Scanner in, Commands command) {
        try {
            switch (command) {
                case EXIT -> exit(app);
                case HELP -> help();
                case BOUNDS -> bounds(app, in);
                case LOAD -> load(app, in);
                case SAVE -> save(app);
                case SERVICE -> service(app, in);
                case SERVICES -> services(app, in);
                case STUDENT -> student(app, in);
                case LEAVE -> leave(app, in);
                case STUDENTS -> students(app, in);
                case GO -> go(app, in);
                case MOVE -> move(app, in);
                case USERS -> users(app, in);
                case STAR -> star(app, in);
                case WHERE -> where(app, in);
                case VISITED -> visited(app, in);
                case RANKING -> ranking(app, in);
                case RANKED -> ranked(app, in);
                case TAG -> tag(app, in);
                case FIND -> find(app, in);
                case null -> unknown();
            }
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        }
    }

    private static void exit(App app) {
        System.out.printf(EXIT_MSG);
        if (!app.isUndefined())
            app.saveArea();
    }

    private static void help() {
        for (Commands c : Commands.values())
            System.out.printf(HELP_MSG, c.name().toLowerCase(), c.getDescription());
    }

    private static void bounds(App app, Scanner in) {
        long yMax = in.nextLong();
        long xMin = in.nextLong();
        long yMin = in.nextLong();
        long xMax = in.nextLong();
        String areaName = in.nextLine().trim();
        try {
            app.createArea(xMin, yMin, xMax, yMax, areaName);
            System.out.printf(BOUNDS_MSG, areaName);
        } catch (ExistingBoundException e) {
            System.out.printf(EXISTING_BOUNDS_ERR);
        } catch (InvalidBoundsException e) {
            System.out.printf(INVALID_BOUNDS_ERR);
        }

    }

    private static void save(App app) {
        try {
            String areaName = app.saveArea();
            System.out.printf(SAVE_MSG, areaName);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
            ;
        }
    }

    private static void load(App app, Scanner in) {
        String area = in.nextLine().trim();
        try {
            app.loadArea(area);
            System.out.printf(LOAD_MSG, app.getAreaName());
        } catch (NonExistingBoundsException e) {
            System.out.printf(NON_EXISTING_BOUNDS_ERR, area);
        }
    }

    private static void service(App app, Scanner in) {
        String type = in.next().toLowerCase();
        long latitude = in.nextLong();
        long longitude = in.nextLong();
        int price = in.nextInt();
        int value = in.nextInt();
        String name = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            app.addService(getServiceType(type), latitude, longitude, price, value, name);
            System.out.printf(SERVICE_MSG, type, name);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidServiceTypeException e) {
            System.out.printf(INVALID_SERVICE_TYPE_ERR);
        } catch (InvalidLocationException e) {
            System.out.printf(INVALID_LOCATION_ERR);
        } catch (InvalidMenuPriceException e) {
            System.out.printf(INVALID_MENU_ERR);
        } catch (InvalidRoomPriceException e) {
            System.out.printf(INVALID_ROOM_ERR);
        } catch (InvalidTicketPriceException e) {
            System.out.printf(INVALID_TICKET_ERR);
        } catch (InvalidDiscountException e) {
            System.out.printf(INVALID_DISCOUNT_ERR);
        } catch (InvalidCapacityException e) {
            System.out.printf(INVALID_CAPACITY_ERR);
        } catch (AlreadyExistsException e) {
            System.out.printf(ALREADY_EXISTS_ERR, app.getServiceName(name));
        }
    }

    private static void services(App app, Scanner in) {
        in.nextLine();
        Iterator<Service> it = app.getServicesIterator();
        if (!it.hasNext())
            System.out.printf(NO_SERVICES_MSG);
        else {
            while (it.hasNext()) {
                Service s = it.next();
                System.out.printf(SERVICES_MSG, s.getName(), s.getStringType(), s.getLatitude(), s.getLongitude());
            }
        }

    }

    private static void student(App app, Scanner in) {
        String type = in.nextLine().trim();
        String name = in.nextLine().trim();
        String country = in.nextLine().trim();
        String home = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            // Succeeded
            app.addStudent(getStudentType(type), name, country, home);
            System.out.printf(STUDENT_MSG, name);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidStudentTypeException e) {
            System.out.printf(INVALID_STUDENT_ERR);
        } catch (NonExistingLodgingException e) {
            System.out.printf(NON_EXISTING_LODGING_ERR, home);
        } catch (LodgingFullException e) {
            System.out.printf(LODGING_FULL_ERR, app.getServiceName(home));
        } catch (AlreadyExistsException e) {
            System.out.printf(ALREADY_EXISTS_ERR, app.getStudentName(name));
        }
    }

    private static void leave(App app, Scanner in) {
        String name = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            app.removeStudent(name);
            System.out.printf(LEAVE_MSG, name);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (AlreadyExistsException e) {
            System.out.printf(NON_EXISTING_ERR, name);
        }
    }

    private static void students(App app, Scanner in) {
        String country = in.nextLine().trim();
        Iterator<Student> it = app.getStudentIterator(country);
        if (it == null)
            System.out.printf(NO_COUNTRY_MSG, country);
        else if (!it.hasNext())
            if (app.isListingAllStudents(country))
                System.out.printf(NO_STUDENTS_MSG);
            else
                System.out.printf(NO_COUNTRY_MSG, country);
        else {
            while (it.hasNext()) {
                Student s = it.next();
                System.out.printf(STUDENTS_MSG, s.getName(), s.getStringType(), s.getLocationName());
            }
        }
    }

    //GO_MSG precisa 2 arg, so tem 1. eu n sei como mudar
    private static void go(App app, Scanner in) {
        String name = in.nextLine().trim();
        String location = in.nextLine().trim();
        try {
            if (!app.getStudentName(name).equals(name)) {
                System.out.printf(DOES_NOT_EXIST, name);
            } else if (app.getStudentName(name).equals(name) && app.getStudent(name).getStudentType().equals(THRIFTY_TYPE)
                    && location.equals(EATING_TYPE) && app.thriftyCheapest(app.getStudentName(name)) < app.eatingLocationPrice(location)) {
                app.goStudent(name, location);
                System.out.printf(DISTRACTED, name);
                System.out.printf(GO_MSG, name, location);
            } else if (!app.getServiceType(location).equals(EATING_TYPE) || !app.getServiceType(location).equals(LEISURE_TYPE)) {
                System.out.printf(INVALID_SERVICE_ERR, location);
            } else if (app.getStudentLocation(name).equals(location)) {
                System.out.printf(ALREADYTHERE_MSG);
            } else {
                app.goStudent(name, location);
                System.out.printf(GO_MSG, name, location);
            }
        } catch (InvalidLocationException e) {
            System.out.printf(UNKNOWN_LOCATION_ERR, location);
        } catch (EatingFullException e) {
            System.out.printf(EATING_FULL_ERR, location);
        }

    }

    private static void move(App app, Scanner in) {
        String student = in.nextLine().trim();
        String service = in.nextLine().trim();

        try {
            if (!app.getStudentName(student).equals(student)) {
                System.out.printf(DOES_NOT_EXIST, student);
            } else if (app.getStudent(student).getStudentType().equals(THRIFTY_TYPE)) {
                if (!app.isLodgingCheaper(student, service)) {
                    System.out.printf(UNACCEPTABLE_HOME, student);
                } else {
                    app.setHome(student, service);
                    System.out.printf(MOVE_MSG, service, student, student);
                }
            } else {
                app.setHome(student, service);
                System.out.printf(MOVE_MSG, service, student, student);
            }
        } catch (NonExistingBoundsException e) {
            System.out.printf(NON_EXISTING_BOUNDS_ERR, student);
        } catch (NonExistingLodgingException e) {
            System.out.printf(NON_EXISTING_LODGING_ERR, service);
        } catch (AlreadyHomeException e) {
            System.out.printf(ALREADY_HOME_MSG, student);
        } catch (LodgingFullException e) {
            System.out.printf(LODGING_FULL_ERR, student);
        }
    }

    private static void users(App app, Scanner in) {

    }

    private static void star(App app, Scanner in) {
        int stars = in.nextInt();
        String service = in.nextLine().trim();
        if (1 >= stars || stars >= 5) {
            System.out.printf(INVALID_EVALUATION_ERR);
        } else if (!app.getServiceName(service).equals(service)) {
            System.out.printf(DOES_NOT_EXIST, service);
        } else {
            app.evaluate(service);
        }
    }

    private static void where(App app, Scanner in) {
        String studentName = in.nextLine().trim();
        try {
            Service location = app.getStudentLocation(studentName);
            System.out.printf(WHERE_MSG, app.getStudentName(studentName), location.getName(),
                    location.getStringType(), location.getLatitude(), location.getLongitude());
        } catch (NonExistingStudentException e) {
            System.out.printf(STUDENT_DOES_NOT_EXIST, studentName);
        }
    }

    private static void visited(App app, Scanner in) {
        String name = in.nextLine().trim();
        if (!app.getServiceName(name).equals(name)) {
            System.out.printf(DOES_NOT_EXIST, name);
        } else if (app.getstudentType(name)) {
            System.out.printf(THRIFTY_MSG, name);
        } else if (app.hasNotVisited(name)) {
            System.out.printf(NO_VISITED_MSG, name);
        } else {
            Iterator<Service> it = app.getServicesIterator();
            while (it.hasNext()) {
                System.out.printf(VISITED_MSG, it.next().getName());
            }
        }
    }

    private static void ranking(App app, Scanner in) {
        in.hasNextLine();
        try {
            Iterator<Service> it = app.getSortedServicesIterator();
            if (!it.hasNext()) {
                System.out.printf(NO_RANKING_MSG);
            }
            System.out.printf(RANKING_MSG);
            while (it.hasNext()) {
                Service s = it.next();
                System.out.printf(RANKING_MSG, s.getName(), s.getAverageStars());
            }
        } catch (NonExistingBoundsExceptione) {
            System.out.printf(BOUND_NOT_DEFINED_MSG);
        } catch (NoServicesException e)
        System.out.printf(RANKING_NO_SERVICES_MSG);

        }

    private static void ranked(App app, Scanner in) {
        String type = in.next().toLowerCase();
        String studentName = in.nextLine().trim();
        ServiceType sType = getServiceType(type);
        int stars = in.nextInt();
        try {
            if (stars > 5 || stars < 1) {
                System.out.printf(INVALID_EVALUATION_ERR);
            }
            else if(!app.getStudentName(studentName).equals(studentName)){
                System.out.printf(DOES_NOT_EXIST, studentName);
            }
            else if(app.noServHasNStars(sType, stars)){
                System.out.printf(NO_RANKED_MSG, type);
            }
            else {
                Iterator<Service> it = app.getSortedServicesIterator();
                while (it.hasNext()) {
                    Service s = it.next();
                    if (s.getType().equals(sType) && s.getAverageStars() == stars) {
                        String name = s.getName();
                        System.out.printf(RANKED_MSG);
                    }
                }
            }
        }
        catch (NonExistingBoundsException e){
            System.out.printf(BOUND_NOT_DEFINED_MSG);
        }
        catch (InvalidServiceTypeException e){
            System.out.printf(INVALID_SERVICE_TYPE_ERR);
        }
    }

    private static void tag(App app, Scanner in) {
    }

    private static void find(App app, Scanner in) {
        String name = in.nextLine().trim();
        String sType = in.nextLine();
        ServiceType type = getServiceType(sType);
        try {
            if (!app.getStudentName(name).equals(name)) {
                System.out.printf(DOES_NOT_EXIST, name);
            }
            else if (app.noTypeServices(type)) {
                System.out.printf(NO_TYPE_MSG, sType);
            }
            else {
                if (app.isThrifty(name) && app.updates(name, type)) {
                    String service = app.find(name, type);
                    System.out.printf(PLACE_MSG, service);
                    System.out.printf(UPDATED_MSG, name);
                } else {
                    String service = app.find(name, type);
                    System.out.printf(FIND_MSG, service);
                }
            }
        }
        catch (InvalidServiceTypeException e) {
            System.out.printf(NO_TYPE_MSG, sType);
        }
    }


    private static void unknown() {
        System.out.printf(UNKNOWN_MSG);
    }


    // TODO: PASSAR ISTO PARA O ENUM!!!

    /**
     * Converts a string representation of a student type to its corresponding StudentType enum.
     *
     * @param type - the string representing a student type
     * @pre - type must not be null
     * @return - the corresponding StudentType enum; null if no match is found
     */
    private static StudentType getStudentType(String type) {
        StudentType studentType;
        switch (type.toLowerCase()) {
            case BOOKISH_TYPE  -> studentType = StudentType.BOOKISH;
            case OUTGOING_TYPE -> studentType = StudentType.OUTGOING;
            case THRIFTY_TYPE  -> studentType = StudentType.THRIFTY;
            default -> throw new InvalidStudentTypeException();
        }
        return studentType;
    }

    /**
     * Converts a StudentType enum to its corresponding string representation.
     *
     * @param type - the StudentType enum to convert
     * @pre - type must not be null
     * @return - the string representation of the student type; null if no match is found
     */
    private static String getStudentType(StudentType type) {
        String studentType;
        switch (type) {
            case BOOKISH  -> studentType = BOOKISH_TYPE;
            case OUTGOING -> studentType = OUTGOING_TYPE;
            case THRIFTY  -> studentType = THRIFTY_TYPE;
            default -> throw new InvalidStudentTypeException();
        }
        return studentType;
    }


    /**
     * Converts a ServiceType enum to its corresponding string representation.
     *
     * @param type - the ServiceType enum to convert
     * @pre - type must not be null
     * @return - the string representation of the service type; null if no match is found
     */
    private static String getServiceType(ServiceType type) {
        String stringType;
        switch (type) {
            case EATING  -> stringType = EATING_TYPE;
            case LEISURE -> stringType = LEISURE_TYPE;
            case LODGING -> stringType = LODGING_TYPE;
            default -> throw new InvalidServiceTypeException();
        }
        return stringType;
    }

    /**
     * Converts a string representation of a service type to its corresponding ServiceType enum.
     *
     * @param stringType - the string representing a service type
     * @pre - stringType must not be null
     * @return - the corresponding ServiceType enum; null if no match is found
     */
    private static ServiceType getServiceType(String stringType) {
        ServiceType type;
        switch (stringType.toLowerCase()) {
            case EATING_TYPE  -> type = ServiceType.EATING;
            case LEISURE_TYPE -> type = ServiceType.LEISURE;
            case LODGING_TYPE -> type = ServiceType.LODGING;
            default -> throw new InvalidServiceTypeException();
        }
        return type;
    }




}
