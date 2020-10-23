package ua.training.model.entity;

/**
 * Represents an Account.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class Account {
    /**
     * Represents the empty Account.
     */
    public static final Account EMPTY;
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String city;
    private String region;

    static {
        EMPTY = new AccountBuilder().setId(0).setFirstName("").setLastName("").setMiddleName("").setCity("").setRegion("").build();
    }

    private Account(AccountBuilder ab) {
        this.id = ab.id;
        this.firstName = ab.firstName;
        this.lastName = ab.lastName;
        this.middleName = ab.middleName;
        this.city = ab.city;
        this.region = ab.region;
    }

    public static class AccountBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String middleName;
        private String city;
        private String region;

        public AccountBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AccountBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AccountBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AccountBuilder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public AccountBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AccountBuilder setRegion(String region) {
            this.region = region;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    /**
     * Gets the account’s id.
     *
     * @return A int representing the account’s id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the account’s first name.
     *
     * @return A String representing the account’s first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the account’s last name.
     *
     * @return A String representing the account’s last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the account’s middle name.
     *
     * @return A String representing the account’s middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Gets the account’s city name.
     *
     * @return A String representing the account’s city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the account’s region name.
     *
     * @return A String representing the account’s region name.
     */
    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
