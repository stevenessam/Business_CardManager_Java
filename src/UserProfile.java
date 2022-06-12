package src;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link UserProfile}
 * {@link UserInformation}
 */
public class UserProfile {
    private final String name;
    private final String password;
    private boolean connected;
    private final UserInformation UserInformation;
    private final List<UserInformation> library;

    public UserProfile(UserInformation UserInformation, String name, String password, boolean connected) {
        this.name = name;
        this.UserInformation = UserInformation;
        this.password = password;
        this.connected = connected;
        this.library = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public boolean login(String name, String password) {
        return password.equals(this.password) && name.equals(this.name);
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void addUserInformation(UserInformation UserInformation) {
        this.library.add(UserInformation);
    }

    public UserInformation getUserInformation() {
        return this.UserInformation;
    }

    public String userInformationDisplay() {
        return UserInformation.toString();
    }

    public List<UserInformation> getLibrary() {
        return this.library;
    }
}
