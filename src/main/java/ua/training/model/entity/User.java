package ua.training.model.entity;

import static ua.training.controller.Path.*;

/**
 * Represents an User.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class User {
    /**
     * Represents the empty User.
     */
    public static final User EMPTY;

    static {
        EMPTY = new UserBuilder().setId(0).setRole(Role.GUEST).setEmail("").setPassword("").setStatus(Status.UNBLOCKED)
                .setStudyAccount(StudyAccount.EMPTY)
                .setAccount(Account.EMPTY)
                .build();
    }

    private int id;
    private String email;
    private String password;
    private Status status;
    private Role role;
    private Account account;
    private StudyAccount studyAccount;

    private User(UserBuilder ub) {
        this.id = ub.id;
        this.email = ub.email;
        this.password = ub.password;
        this.status = ub.status;
        this.role = ub.role;
        this.account = ub.account;
        this.studyAccount = ub.studyAccount;
    }

    public static class UserBuilder {
        private int id;
        private String email;
        private String password;
        private Status status;
        private Role role;
        private Account account;
        private StudyAccount studyAccount;

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public UserBuilder setStudyAccount(StudyAccount studyAccount) {
            this.studyAccount = studyAccount;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public enum Status {
        BLOCKED(1),
        UNBLOCKED(2);

        private int status;

        Status(int s) {
            status = s;
        }

        public int getStatus() {
            return status;
        }
    }

    public enum Role {
        GUEST(0, "", INDEX),
        USER(1, DIR_USER, USER_BASIC_PATH),
        ADMIN(2, DIR_ADMIN, ADMIN_BASIC_PATH);

        private int role;
        private String directory;
        private String redirect;


        Role(int r, String dir, String red) {
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

        public static Role getRoleByNumber(int number) {
            for (Role r : Role.values()) {
                if (r.role == number) {
                    return r;
                }
            }
            return GUEST;
        }
    }

    /**
     * Updates the user’s password.
     *
     * @param pass A String containing the new user’s password.
     */
    public void updatePassword(String pass) {
        password = pass;
    }

    /**
     * Gets the user’s id.
     *
     * @return A int representing the user’s id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the user’s email.
     *
     * @return A String representing the user’s email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user’s password.
     *
     * @return A String representing the user’s password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user’s status.
     *
     * @return A Status representing the user’s status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the user’s role.
     *
     * @return A Role representing the user’s role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets the user’s account.
     *
     * @return A Account representing the user’s account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Gets the user’s study account.
     *
     * @return A StudyAccount representing the user’s study account.
     */
    public StudyAccount getStudyAccount() {
        return studyAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", account=" + account +
                ", studyAccount=" + studyAccount +
                '}';
    }
}
