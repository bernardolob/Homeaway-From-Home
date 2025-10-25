import java.io.*;
import java.util.Scanner;
import system.App;
import system.AppClass;

public class Main {

// CONSTANTS

    // NOTA: Perguntar à professora se é melhor ter uma (ou mais) classe(s) só para as constantes.

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
    // Save
    private static final String SAVE_MSG        = "%s saved.\n";
    // Load
    private static final String LOAD_MSG        = "%s loaded.\n";
    // Service and Student
    private static final String ADDED_MSG       = "%s %s added.\n";
    // Services
    private static final String SERVICES_MSG    = "%s: %s %d %d.\n";
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
        String command;
        App s = new AppClass();
        do {
            command = in.next().toUpperCase();
            executeCommand(in, command);
        } while (!command.equals(EXIT_CMD));
        in.close();
    }

    /**
     * Executes a specific command based on user input.
     *
     * @param in - Scanner object for reading user input
     * @param command - the command string to execute
     * @pre - 'in' must not be null, and 'command' must be a valid string
     */
    private static void executeCommand(Scanner in, String command) {
        switch (command) {
            case EXIT_CMD -> exit();
            case HELP_CMD -> help();
            case BOUNDS_CMD -> bounds(in);
            case SAVE_CMD -> save();
            case LOAD_CMD -> load(in);
            case SERVICE_CMD -> service(in);
            case SERVICES_CMD -> services();
            case STUDENT_CMD -> student(in);
            case LEAVE_CMD -> leave(in);
            case STUDENTS_CMD -> students();
            case GO_CMD -> go(in);
            case MOVE_CMD -> move(in);
            case USERS_CMD -> users(in);
            case STAR_CMD -> star(in);
            case WHERE_CMD -> where(in);
            case VISITED_CMD -> visited(in);
            case RANKING_CMD -> ranking(in);
            case RANKED_CMD -> ranked(in);
            case TAG_CMD -> tag(in);
            case FIND_CMD -> find(in);
            default -> unknown();
        }
    }

    private static void exit() {
    }

    private static void help() {
        for (Commands c : Commands.values())
            System.out.printf(HELP_MSG, c.name().toLowerCase(), c.getDescription());
    }

    private static void bounds(Scanner in) {
    }

    private static void save() {
        // TODO
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
//            out.writeObject();
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            throw new NoSystemException();
//        }
    }

    private static App load(Scanner in) {
        App app = null;
//        try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
//            app = (App) inFile.readObject();
//            inFile.close();
//
//        } catch (IOException | ClassNotFoundException e) {
//            app = new AppClass();
//        }
        return app;
    }

    private static void service(Scanner in) {

    }

    private static void services() {
    }

    private static void student(Scanner in) {
    }

    private static void leave(Scanner in) {
    }

    private static void students() {
    }

    private static void go(Scanner in) {
    }

    private static void move(Scanner in) {
    }

    private static void users(Scanner in) {

    }

    private static void star(Scanner in) {
    }

    private static void where(Scanner in) {
    }

    private static void visited(Scanner in) {

    }

    private static void ranking(Scanner in) {
    }

    private static void ranked(Scanner in) {
    }

    private static void tag(Scanner in) {
    }

    private static void find(Scanner in) {
    }


    private static void unknown() {
        System.out.printf(UNKNOWN_MSG);
    }

}
