package src;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link InterfacesController}
 */
public class InterfacesController {
    private final List<UserProfile> userProfile;

    public InterfacesController() {
        this.userProfile = new ArrayList<>();
    }

    public void newUser(UserInformation userInformation, String name, String password, boolean connected) {
        userProfile.add(new UserProfile(userInformation, name, password, connected));
    }

    public boolean login(String name, String password) {
        for (UserProfile userprofile : userProfile) {
            if (userprofile.login(name, password)) {
                userprofile.setConnected(true);
                return true;
            }
        }
        return false;
    }

    public UserProfile getUserProfile(String name) {
        for (UserProfile userprofile : userProfile) {
            if (userprofile.getName().equals(name)) {
                return userprofile;
            }
        }
        return null;
    }

}
