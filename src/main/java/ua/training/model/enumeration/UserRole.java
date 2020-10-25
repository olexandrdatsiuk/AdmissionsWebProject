package ua.training.model.enumeration;

import static ua.training.controller.Path.*;

/**
 * Represents an enumeration of roles of users.
 * Each enum contains user`s role, directory and
 * redirect path.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public enum UserRole {
    GUEST(0, "", INDEX),
    USER(1, DIR_USER, USER_BASIC_PATH),
    ADMIN(2, DIR_ADMIN, ADMIN_BASIC_PATH);

    private int role;
    private String directory;
    private String redirect;


    UserRole(int r, String dir, String red) {
        role = r;
        directory = dir;
        redirect = "/" + red;
    }

    public int getRole() {
        return role;
    }

    public String getDirectory() {
        return directory;
    }

    public String getRedirect() {
        return redirect;
    }

    public static UserRole getRoleByNumber(int number) {
        for (UserRole r : UserRole.values()) {
            if (r.role == number) {
                return r;
            }
        }
        return GUEST;
    }
}
