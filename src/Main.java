import java.util.Scanner;

import dataStructures.TwoWayIterator;
import exceptions.*;
import dataStructures.Iterator;
import system.*;
import system.service.Service;
import system.student.Student;

public class Main {

// CONSTANTS


    // TODO NOTA: Perguntar à professora se é melhor ter uma (ou mais) classe(s) só para as constantes.


    /************ Errors ************/

    private static final String UNDEFINED_BOUNDS_ERR     = "System bounds not defined.\n";
    private static final String INVALID_BOUNDS_ERR       = "Invalid bounds.\n";
    private static final String EXISTING_BOUNDS_ERR      = "Bounds already exists. Please load it!\n";
    private static final String NON_EXISTING_BOUNDS_ERR  = "Bounds %s does not exists.\n";
    private static final String INVALID_SERVICE_TYPE_ERR = "Invalid service type!\n";
    private static final String INVALID_LOCATION_ERR     = "Invalid location!\n";
    private static final String INVALID_MENU_ERR         = "Invalid menu price!\n";
    private static final String INVALID_TICKET_ERR       = "Invalid ticket price!\n";
    private static final String INVALID_ROOM_ERR         = "Invalid room price!\n";
    private static final String INVALID_DISCOUNT_ERR     = "Invalid discount price!\n";
    private static final String INVALID_CAPACITY_ERR     = "Invalid capacity!\n";
    private static final String ALREADY_EXISTS_ERR       = "%s already exists!\n";
    private static final String INVALID_STUDENT_ERR      = "Invalid student type!\n";
    private static final String NON_EXISTING_LODGING_ERR = "lodging %s does not exist!\n";
    private static final String LODGING_FULL_ERR         = "lodging %s is full!\n";
    private static final String UNKNOWN_LOCATION_ERR     = "Unknown %s!\n";
    private static final String NON_EXISTING_ERR         = "%s does not exist!\n";
    private static final String INVALID_SERVICE_ERR      = "%s is not a valid service!\n";
    private static final String ALREADY_THERE_ERR        = "Already there!\n";
    private static final String EATING_FULL_ERR          = "eating %s is full!\n";
    private static final String ALREADY_HOME_ERR         = "That is %s's home!\n";
    private static final String UNACCEPTABLE_MOVE_ERR    = "Move is not acceptable for %s!\n";
    private static final String INVALID_ORDER_ERR        = "This order does not exists!\n";
    private static final String NO_USERS_SERVICE_ERR     = "%s does not control student entry and exit!\n";
    private static final String IS_THRIFTY_ERR           = "%s is thrifty!\n";
    private static final String INVALID_EVALUATION_ERR   = "Invalid evaluation!\n";
    private static final String INVALID_STARS_ERR        = "Invalid stars!\n";
    private static final String NO_SERVICE_TYPE_ERR      = "No %s services!\n";

    /************ Messages ************/
    // General
    private static final String UNKNOWN_MSG     = "Unknown command. Type help to see available commands.\n";
    // Help
    private static final String HELP_MSG        = "%s - %s\n";
    // Exit
    private static final String EXIT_MSG        = "Bye!\n";
    // Bounds
    private static final String BOUNDS_MSG      = "%s created.\n";
    // Save
    private static final String SAVE_MSG        = "%s saved.\n";
    // Load
    private static final String LOAD_MSG        = "%s loaded.\n";
    // Service
    private static final String SERVICE_MSG     = "%s %s added.\n";
    // Services
    private static final String SERVICES_MSG    = "%s: %s (%d, %d).\n";
    private static final String NO_SERVICES_MSG = "No services yet!\n";
    // Student
    private static final String STUDENT_MSG     = "%s added.\n";
    // Leave
    private static final String LEAVE_MSG       = "%s has left.\n";
    // Students
    private static final String STUDENTS_MSG    = "%s: %s at %s.\n";
    private static final String NO_STUDENTS_MSG = "No students yet!\n";
    private static final String NO_COUNTRY_MSG  = "No students from %s!\n";
    // Go
    private static final String GO_MSG          = "%s is now at %s.";
    private static final String DISTRACTED_MSG  = " %s is distracted!\n";
    // Move
    private static final String MOVE_MSG        = "lodging %s is now %s's home. %s is at home.\n";
    // Users
    private static final String USERS_MSG       = "%s: %s\n";
    private static final String NO_USERS_MSG    = "No students on %s!\n";
    // Where
    private static final String WHERE_MSG       = "%s is at %s %s (%d, %d).\n";
    // Visited
    private static final String VISITED_MSG     = "%s\n";
    private static final String NO_VISITED_MSG  = "%s has not visited any locations!\n";
    // Star
    private static final String STAR_MSG        = "Your evaluation has been registered!\n";
    // Ranking
    private static final String DESCENDING_MSG  = "Services sorted in descending order\n";
    private static final String RANKING_MSG     = "%s: %d\n";
    private static final String NO_RANKING_MSG  = "No services in the system.\n";
    // Ranked
    private static final String RANKED_HEAD_MSG = "%s services closer with %d average\n";
    private static final String RANKED_MSG      = "%s\n";
    private static final String AVG_RANKED_MSG  = "No %s services with average!\n";
    private static final String TYPE_RANKED_MSG = "No %s services!\n";
    // Tag
    private static final String TAG_MSG         = "%s %s\n";
    private static final String NO_TAG_MSG      = "There are no services with this tag!\n";
    // Find
    private static final String FIND_MSG = "%s\n";


    private static final String CRESCENT    = ">";
    private static final String DECRESCENT  = "<";

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
        return Commands.fromCommandString(command);
    }

    /**
     * Executes a specific command based on user input.
     *
     * @param in - Scanner object for reading user input
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
            System.out.printf(UNDEFINED_BOUNDS_ERR);;
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
            app.addService(app.getServiceType(type), latitude, longitude, price, value, name);
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
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            Iterator<Service> it = app.getServicesIterator();
            if (!it.hasNext())
                System.out.printf(NO_SERVICES_MSG);
            else {
                while (it.hasNext()) {
                    Service s = it.next();
                    System.out.printf(SERVICES_MSG, s.getName(), s.getStringType(), s.getLatitude(), s.getLongitude());
                }
            }
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        }
    }

    private static void student(App app, Scanner in) {
        String type = in.nextLine().trim();
        String name = in.nextLine().trim();
        String country = in.nextLine().trim();
        String home = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            app.addStudent(app.getStudentType(type), name, country, home);
            System.out.printf(STUDENT_MSG, name);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidStudentTypeException e) {
            System.out.printf(INVALID_STUDENT_ERR);
        } catch (NonExistingLodgingException e) {
            System.out.printf(NON_EXISTING_LODGING_ERR, home);
        } catch (ServiceFullException e) {
            System.out.printf(LODGING_FULL_ERR, app.getServiceName(home));
        } catch (AlreadyExistsException e) {
            System.out.printf(ALREADY_EXISTS_ERR, app.getStudentName(name));
        }
    }

    private static void leave(App app, Scanner in) {
        String name = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            String student = app.getStudentName(name);
            app.removeStudent(name);
            System.out.printf(LEAVE_MSG, student);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (NonExistingStudentException e) {
            System.out.printf(NON_EXISTING_ERR, name);
        }
    }

    private static void students(App app, Scanner in) {
        String country = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();

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
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        }
    }

    private static void go(App app, Scanner in) {
        String name = in.nextLine().trim();
        String location = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            boolean isDistracted = app.goStudent(name, location);
            System.out.printf(GO_MSG, app.getStudentName(name), app.getServiceName(location));
            if (isDistracted)
                System.out.printf(DISTRACTED_MSG, app.getStudentName(name));
            else
                System.out.println();
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (UnknownLocationException e) {
            System.out.printf(UNKNOWN_LOCATION_ERR, location);
        } catch (NonExistingStudentException e) {
            System.out.printf(NON_EXISTING_ERR, name);
        } catch (InvalidServiceException e) {
            System.out.printf(INVALID_SERVICE_ERR, location);
        } catch (AlreadyThereException e) {
            System.out.printf(ALREADY_THERE_ERR);
        } catch (ServiceFullException e) {
            System.out.printf(EATING_FULL_ERR, app.getServiceName(location));
        }
    }

    private static void move(App app, Scanner in) {
        String student = in.nextLine().trim();
        String lodging = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();

            app.move(student, lodging);
            String studentName = app.getStudentName(student);
            System.out.printf(MOVE_MSG, app.getServiceName(lodging), studentName, studentName);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (NonExistingLodgingException e) {
            System.out.printf(NON_EXISTING_LODGING_ERR, lodging);
        } catch (NonExistingStudentException e) {
            System.out.printf(NON_EXISTING_ERR, student);
        } catch (AlreadyHomeException e){
            System.out.printf(ALREADY_HOME_ERR, app.getStudentName(student));
        } catch (ServiceFullException e){
            System.out.printf(LODGING_FULL_ERR, lodging);
        } catch (UnacceptableMoveException e) {
            System.out.printf(UNACCEPTABLE_MOVE_ERR, app.getStudentName(student));
        }
    }

    private static void users(App app, Scanner in) {
        String order = in.next().trim();
        String service = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            if (!CRESCENT.equals(order) && !DECRESCENT.equals(order))
                throw new InvalidOrderException();
            TwoWayIterator<Student> it = app.getUsers(order, service);
            String serviceName = app.getServiceName(service);
            if (CRESCENT.equals(order)) {
                listUsersCrescent(it, serviceName);
            } else {
                listUsersDecrescent(it, serviceName);
            }
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidOrderException e) {
            System.out.printf(INVALID_ORDER_ERR);
        } catch (UnknownLocationException e) {
            System.out.printf(NON_EXISTING_ERR, service);
        } catch (InvalidServiceException e) {
            System.out.printf(NO_USERS_SERVICE_ERR, app.getServiceName(service));
        }
    }

    private static void star(App app, Scanner in) {
        int stars = in.nextInt();
        String service = in.nextLine().trim();
        String tag = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            app.evaluate(service, stars, tag);
            System.out.printf(STAR_MSG);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidEvaluationException e) {
            System.out.printf(INVALID_EVALUATION_ERR);
        } catch (UnknownLocationException e) {
            System.out.printf(NON_EXISTING_ERR, service);
        }
    }

    private static void where(App app, Scanner in) {
        String studentName = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            Service location = app.getStudentLocation(studentName);
            System.out.printf(WHERE_MSG, app.getStudentName(studentName), location.getName(),
                    location.getStringType(), location.getLatitude(), location.getLongitude());
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (NonExistingStudentException e) {
            System.out.printf(NON_EXISTING_ERR, studentName);
        }
    }

    private static void visited(App app, Scanner in) {
        String student = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();

            Iterator<Service> it = app.getVisitsIterator(student);
            if (!it.hasNext()) {
                System.out.printf(NO_VISITED_MSG, app.getStudentName(student));
            } else {
                while (it.hasNext()) {
                    Service s = it.next();
                    System.out.printf(VISITED_MSG, s.getName());
                }
            }
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (NonExistingStudentException e) {
            System.out.printf(NON_EXISTING_ERR, student);
        } catch (ThriftyStudentException e) {
            System.out.printf(IS_THRIFTY_ERR, app.getStudentName(student));
        }
    }

    private static void ranking(App app, Scanner in) {
        in.nextLine();
        try {
            if (!app.getServicesIterator().hasNext())
                System.out.printf(NO_RANKING_MSG);
            else {
                Iterator<Iterator<Service>> it1 = app.getRankingIterator();
                System.out.printf(DESCENDING_MSG);
                while (it1.hasNext()) {
                    Iterator<Service> it2 = it1.next();
                    while (it2.hasNext()) {
                        Service s = it2.next();
                        System.out.printf(RANKING_MSG, s.getName(), s.getAverageStars());
                    }
                }
            }
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        }
    }

    private static void ranked(App app, Scanner in) {
        String typeString = in.next().trim();
        int stars = in.nextInt();
        String studentName = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            Iterator<Service> it = app.getRankedIterator(studentName, stars, typeString);
            System.out.printf(RANKED_HEAD_MSG, app.getServiceType(typeString), stars);
            while (it.hasNext()) {
                Service s = it.next();
                System.out.printf(RANKED_MSG, s.getName());
            }
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidEvaluationException e) {
            System.out.printf(INVALID_STARS_ERR);
        } catch (NonExistingStudentException e) {
            System.out.printf(NON_EXISTING_ERR, studentName);
        } catch (InvalidServiceTypeException e) {
            System.out.printf(INVALID_SERVICE_TYPE_ERR);
        } catch (NoServicesWithTypeException e) {
            System.out.printf(TYPE_RANKED_MSG, typeString.toLowerCase());
        } catch (NoServicesWithAvgException e) {
            System.out.printf(AVG_RANKED_MSG, typeString.toLowerCase());
        }
    }

    private static void tag(App app, Scanner in) {
        String tag = in.nextLine().trim();
        try {
            if (app.isUndefined()) throw new UndefinedBoundsException();
            Iterator<Service> it = app.getTaggedServices(tag);
            if (!it.hasNext())
                System.out.printf(NO_TAG_MSG);
            else
                while (it.hasNext()) {
                    Service s = it.next();
                    System.out.printf(TAG_MSG, s.getStringType(), s.getName());
                }
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        }
    }

    private static void find(App app, Scanner in) {
        String studentName = in.nextLine().trim();
        String serviceType = in.nextLine().trim();
        try {
            Service s = app.find(studentName, serviceType);
            System.out.printf(FIND_MSG, s.getName());
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidServiceTypeException e) {
            System.out.printf(INVALID_SERVICE_TYPE_ERR);
        } catch (NonExistingStudentException e) {
            System.out.printf(NON_EXISTING_ERR, studentName);
        } catch (NoServicesWithTypeException e) {
            System.out.printf(NO_SERVICE_TYPE_ERR, serviceType.toLowerCase());
        }
    }

    private static void unknown() {
        System.out.printf(UNKNOWN_MSG);
    }

    private static void listUsersCrescent(TwoWayIterator<Student> it, String serviceName) {
        it.rewind();
        if (!it.hasNext())
            System.out.printf(NO_USERS_MSG, serviceName);
        else {
            while (it.hasNext()) {
                Student s = it.next();
                System.out.printf(USERS_MSG, s.getName(), s.getStringType());
            }
        }
    }

    private static void listUsersDecrescent(TwoWayIterator<Student> it, String serviceName) {
        it.fullForward();
        if (!it.hasPrevious())
            System.out.printf(NO_USERS_MSG, serviceName);
        else {
            while (it.hasPrevious()) {
                Student s = it.previous();
                System.out.printf(USERS_MSG, s.getName(), s.getStringType());
            }
        }

    }
}
