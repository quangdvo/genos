package model;

/**
 * Created by Allen on 10/30/2016.
 */
public class Control {
    private Database database;
    private static final Control instance = new Control();

    private Control() {
        database = new Database();
        //test users
        if (database != null && database.addUser(new User("u", "a", "p", AccountType.User))) {
            database.getUsers().add(new User("u", "a", "p", AccountType.User));
        }
        if (database != null && database.addUser(new User("w", "a", "p", AccountType.Worker))) {
            database.getUsers().add(new User("w", "a", "p", AccountType.Worker));
        }
        if (database != null && database.addUser(new User("m", "a", "p", AccountType.Manager))) {
            database.getUsers().add(new User("m", "a", "p", AccountType.Manager));
        }
        if (database != null && database.addUser(new User("a", "a", "p", AccountType.Admin))) {
            database.getUsers().add(new User("a","a", "p", AccountType.Admin));
        }
    }

    /**
     * add a user to the database
     *
     * @param user the user to add
     * @return true if successful add of user
     */
    public boolean addUser(User user) {
        if (database != null && database.addUser(user)) {
            return true;
        }
        return false;
    }

    public Database getDatabase() {
        return database;
    }

    public static Control getInstance() {
        return instance;
    }
}
