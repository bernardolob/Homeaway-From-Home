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
    private static final String EATING_TYPE     = "EATING";
    private static final String LODGING_TYPE    = "LODGING";
    private static final String LEISURE_TYPE    = "LEISURE";
    // Students
    private static final String BOOKISH_TYPE    = "BOOKISH";
    private static final String OUTGOING_TYPE   = "OUTGOING";
    private static final String THRIFTY_TYPE    = "THRIFTY";

    /************ Errors ************/
    private static final String UNDEFINED_BOUNDS_ERR    = "System bounds not defined.\n";
    private static final String INVALID_BOUNDS_ERR      = "Invalid bounds.\n";
    private static final String EXISTING_BOUNDS_ERR     = "Bounds already exists. Please load it!\n";
    private static final String NON_EXISTING_BOUNDS_ERR = "Bounds %s does not exists.\n";



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


    public static void main(String[] args) {

    }
}
