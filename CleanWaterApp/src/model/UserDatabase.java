package model;

import model.User;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taiga on 9/21/2016.
 */
public class UserDatabase {

    private User user;
    private String username;
    private HashMap<String, User> database = new HashMap<>();
    private File databaseFile = new File("src/model/database.txt");
    /**
     * create userDatabase with user/pass default
     */
    public UserDatabase() {
        try {
            if(!databaseFile.exists()) {
                try {
                    databaseFile.createNewFile();
                    FileWriter databaseWriter = new FileWriter(databaseFile.getAbsolutePath());
                    BufferedWriter bufferedWriter = new BufferedWriter(databaseWriter);
                    databaseWriter.flush();
                    databaseWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileReader inputDatabase = new FileReader(databaseFile.getAbsolutePath());
            System.out.println(databaseFile.getAbsolutePath());
            BufferedReader bufferReader = new BufferedReader(inputDatabase);
            String databaseLine;
            while ((databaseLine = bufferReader.readLine()) != null) {
                String[] userData = databaseLine.split("/");
                User tempUser = new User(userData[0], userData[1], userData[2], AccountType.valueOf(userData[3]));
                database.put(tempUser.getUsername(), tempUser);
            }
            bufferReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * creates userdatabase with imported data
     * @param database imported database
     */
    public UserDatabase(HashMap<String, User> database) {
        this.database = database;
    }

    /**
     * checks to see if a user exists in the database
     * @param user String username
     * @return boolean value whether user exists in database
     */
    public boolean userExists(String user) {
        return database.containsKey(user);
    }

    /**
     * checks to see if a user exists in the database
     * @param user User object
     * @return boolean value whether user exists in database
     */
    public boolean userExists(User user) {
        try {
            return database.containsValue(user);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * checks to see if login (user/pass) is valid
     * @param username user's username
     * @param password user's password
     * @return boolean value whether login was valid
     */
    public boolean login(String username, String password) throws NullPointerException {
        User tempUser = database.get(username);
        if (tempUser == null) {
            throw new NullPointerException("This user does not exist");
        }
        if (tempUser.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * appends database to current user database
     * @param database database to be added
     */
    public void addDatabase(HashMap<String, User> database) {
        database.putAll(database);
    }

    /**
     * checks if user database is empty
     * @return boolean whether or not userbase is empty
     */
    public boolean isEmpty() {
        return database.isEmpty();
    }

    /**
     * return database
     * @return database
     */
    public HashMap<String, User> getDatabase() {
        return database;
    }

    /**
     * removes user
     * @param user user to be removed
     * @return boolean whether or not the user was removed
     */
    public boolean removeUser(User user) {
        if (userExists(user)) {
            database.remove(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * removes user
     * @param user user to be removed
     * @return boolean whether or not the user was removed
     */
    public boolean removeUser(String user) {
        if (userExists(user)) {
            database.remove(user);
            return true;
        } else {
            return false;
        }
    }


    /**
     * adds user to database
     * @param user user
     */
    public void addUser(User user) {
        database.put(user.getUsername(), user);
        try {
            if(!databaseFile.exists()) {
                try {
                    databaseFile.createNewFile();
                    FileWriter databaseWriter = new FileWriter(databaseFile.getAbsolutePath());
                    BufferedWriter bufferedWriter = new BufferedWriter(databaseWriter);
                    databaseWriter.flush();
                    databaseWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                FileWriter databaseWriter = new FileWriter(databaseFile.getAbsolutePath());
                BufferedWriter bufferedWriter = new BufferedWriter(databaseWriter);
                for (Map.Entry<String, User> entry : database.entrySet()) {
                    databaseWriter.write(entry.getValue().toString() + "\n");
                }
                databaseWriter.flush();
                databaseWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
