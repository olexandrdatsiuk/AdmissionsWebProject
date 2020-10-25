package ua.training.model.entity;

import ua.training.model.enumeration.UserRole;
import ua.training.model.enumeration.UserStatus;

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
        EMPTY = new UserBuilder().setId(0).setRole(UserRole.GUEST).setEmail("").setPassword("").setStatus(UserStatus.UNBLOCKED)
                .setStudyAccount(StudyAccount.EMPTY)
                .setAccount(Account.EMPTY)
                .build();
    }

    private int id;
    private String email;
    private String password;
    private UserStatus status;
    private UserRole role;
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
        private UserStatus status;
        private UserRole role;
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

        public UserBuilder setStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserBuilder setRole(UserRole role) {
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
     * @return A UserStatus representing the user’s status.
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Gets the user’s role.
     *
     * @return A Role representing the user’s role.
     */
    public UserRole getRole() {
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
}
