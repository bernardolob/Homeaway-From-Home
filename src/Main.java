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
    private static final String INVALID_SERVICE_ERR      = "Invalid service type!\n";
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
    private static final String GO_MSG = "‰s in now at %s.\n";
    private static final String DISTRACTED = "%s is distracted!\n";





    public static void main(String[] args) {

    }
}
