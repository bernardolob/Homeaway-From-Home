import java.util.Scanner;

import Exceptions.*;
import system.App;
import system.AppClass;
import system.service.ServiceType;
import system.student.StudentType;

public class Main {

// CONSTANTS

    // TODO NOTA: Perguntar à professora se é melhor ter uma (ou mais) classe(s) só para as constantes.

    /************ Commands ************/
    private static final String EXIT_CMD 		= "EXIT";
    private static final String HELP_CMD 		= "HELP";
    private static final String BOUNDS_CMD 		= "BOUNDS";
    private static final String SAVE_CMD 		= "SAVE";
    private static final String LOAD_CMD 		= "LOAD";
    private static final String SERVICE_CMD 	= "SERVICE";
    private static final String SERVICES_CMD    = "SERVICES";
    private static final String STUDENT_CMD     = "STUDENT";
    private static final String LEAVE_CMD       = "LEAVE";
    private static final String STUDENTS_CMD    = "STUDENTS";
    private static final String GO_CMD          = "GO";
    private static final String MOVE_CMD        = "MOVE";
    private static final String USERS_CMD       = "USERS";
    private static final String WHERE_CMD       = "WHERE";
    private static final String VISITED_CMD     = "VISITED";
    private static final String STAR_CMD        = "STAR";
    private static final String RANKING_CMD     = "RANKING";
    private static final String RANKED_CMD      = "RANKED";
    private static final String TAG_CMD         = "TAG";
    private static final String FIND_CMD        = "FIND";

    /************ Types ************/
    // Services
    private static final String EATING_TYPE     = "eating";
    private static final String LODGING_TYPE    = "lodging";
    private static final String LEISURE_TYPE    = "leisure";
    // Students
    private static final String BOOKISH_TYPE    = "bookish";
    private static final String OUTGOING_TYPE   = "outgoing";
    private static final String THRIFTY_TYPE    = "thrifty";

    /************ Errors ************/
    private static final String UNDEFINED_BOUNDS_ERR     = "System bounds not defined.\n";
    private static final String INVALID_BOUNDS_ERR       = "Invalid bounds.\n";
    private static final String EXISTING_BOUNDS_ERR      = "Bounds already exists. Please load it!\n";
    private static final String NON_EXISTING_BOUNDS_ERR  = "Bounds %s does not exists.\n";
    private static final String INVALID_SERVICE_TYPE_ERR = "Invalid service type!\n";
    private static final String INVALID_LOCATION_ERR     = "Invalid location!\n";
    private static final String INVALID_MENU_ERR         = "Invalid menu price!\n";
    private static final String INVALID_TICKET_ERR       = "Invalid ticket price!\n";
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
    private static final String NON_EXISTING_ORDER_ERR   = "This order does not exists!\n";
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
    private static final String BOUNDS_MSG      = "%s created\n";
    // Save
    private static final String SAVE_MSG        = "%s saved.\n";
    // Load
    private static final String LOAD_MSG        = "%s loaded.\n";
    // Service and Student
    private static final String ADDED_MSG       = "%s %s added.\n";
    // Services
    private static final String SERVICES_MSG    = "%s: %s (%d, %d).\n";
    private static final String NO_SERVICES_MSG = "No services yet!\n";
    // Leave
    private static final String LEAVE_MSG       = "%s has left.\n";
    // Students
    private static final String STUDENTS_MSG    = "%s: %s at %s.\n";
    private static final String NO_STUDENTS_MSG = "No students yet!\n";
    private static final String NO_COUNTRY_MSG  = "No students from %s!\n";
    // Go
    private static final String GO_MSG          = "‰s in now at %s.\n";
    private static final String DISTRACTED      = "%s is distracted!\n";
    // Move
    private static final String MOVE_MSG        = "lodging %s is now %s's home. %s is at home.\n";
    // Users
    private static final String USERS_MSG       = "%s: %s\n";
    private static final String NO_USERS_MSG    = "No students on %s!";
    // Where
    private static final String WHERE_MSG       = "%s is at %s %s (%d, %d).\n";
    // Visited
    private static final String VISITED_MSG     = "%s\n";
    private static final String NO_VISITED_MSG  = "%s has not visited any locations!\n";
    // Star
    private static final String STAR_MSG        = "Your evaluation has been registered!\n";
    // Ranking
    private static final String DESCENDING_MSG  = "Services sorted in descending order\n";
    private static final String RANKING_MSG     = "%s: %d\n"; // Enunciado inconsistente com o ponto final
    private static final String NO_RANKING_MSG  = "No services in the system.\n";
    // Ranked
    private static final String RANKED_HEAD_MSG = "%s services closer with %d average\n";
    private static final String RANKED_MSG      = "%s\n";
    private static final String NO_RANKED_MSG   = "No %s services with average!\n";
    // Tag
    private static final String TAG_MSG         = "%s %s\n";
    private static final String NO_TAG_MSG      = "There are no services with this tag!\n";
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
            case EXIT_CMD -> {return Commands.EXIT;}
            case HELP_CMD -> {return Commands.HELP;}
            case BOUNDS_CMD -> {return Commands.BOUNDS;}
            case SAVE_CMD -> {return Commands.SAVE;}
            case LOAD_CMD -> {return Commands.LOAD;}
            case SERVICE_CMD -> {return Commands.SERVICE;}
            case SERVICES_CMD -> {return Commands.SERVICES;}
            case STUDENT_CMD -> {return Commands.STUDENT;}
            case LEAVE_CMD -> {return Commands.LEAVE;}
            case STUDENTS_CMD -> {return Commands.STUDENTS;}
            case GO_CMD -> {return Commands.GO;}
            case MOVE_CMD -> {return Commands.MOVE;}
            case USERS_CMD -> {return Commands.USERS;}
            case STAR_CMD -> {return Commands.STAR;}
            case WHERE_CMD -> {return Commands.WHERE;}
            case VISITED_CMD -> {return Commands.VISITED;}
            case RANKING_CMD -> {return Commands.RANKING;}
            case RANKED_CMD -> {return Commands.RANKED;}
            case TAG_CMD -> {return Commands.TAG;}
            case FIND_CMD -> {return Commands.FIND;}
            default -> {return null;}
        }
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
                case SERVICES -> services(app);
                case STUDENT -> student(app, in);
                case LEAVE -> leave(app, in);
                case STUDENTS -> students(app);
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
            System.out.printf(LOAD_MSG, area);
        } catch (NonExistingBoundsException e) {
            System.out.printf(NON_EXISTING_BOUNDS_ERR, area);
        }
    }

    private static void service(App app, Scanner in) {
        try {
            String type = in.nextLine();
            long latitude = in.nextLong();
            long longitude = in.nextLong();
            int price = in.nextInt();
            int value = in.nextInt();
            String name = in.nextLine().trim();
            if (app.isUndefined())
                throw new UndefinedBoundsException();
            app.addService(getServiceType(type), latitude, longitude, price, value, name);
        } catch (UndefinedBoundsException e) {
            System.out.printf(UNDEFINED_BOUNDS_ERR);
        } catch (InvalidServiceTypeException e) {
            System.out.printf(INVALID_SERVICE_TYPE_ERR);
        } catch (InvalidLocationException e) {
            System.out.printf(INVALID_LOCATION_ERR);
        } catch (InvalidMenuPriceException e) {
            e.getMessage();
        }

    }

    private static void services(App app) {
    }

    private static void student(App app, Scanner in) {
    }

    private static void leave(App app, Scanner in) {
    }

    private static void students(App app) {
    }

    private static void go(App app, Scanner in) {
    }

    private static void move(App app, Scanner in) {
    }

    private static void users(App app, Scanner in) {

    }

    private static void star(App app, Scanner in) {
    }

    private static void where(App app, Scanner in) {
    }

    private static void visited(App app, Scanner in) {

    }

    private static void ranking(App app, Scanner in) {
    }

    private static void ranked(App app, Scanner in) {
    }

    private static void tag(App app, Scanner in) {
    }

    private static void find(App app, Scanner in) {
    }


    private static void unknown() {
        System.out.printf(UNKNOWN_MSG);
    }


    /**
     * Converts a string representation of a student type to its corresponding StudentType enum.
     *
     * @param type - the string representing a student type
     * @pre - type must not be null
     * @return - the corresponding StudentType enum; null if no match is found
     */
    private static StudentType getStudentType(String type) {
        StudentType studentType;
        switch (type) {
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
        switch (stringType) {
            case EATING_TYPE  -> type = ServiceType.EATING;
            case LEISURE_TYPE -> type = ServiceType.LEISURE;
            case LODGING_TYPE -> type = ServiceType.LODGING;
            default -> throw new InvalidServiceTypeException();
        }
        return type;
    }




}
