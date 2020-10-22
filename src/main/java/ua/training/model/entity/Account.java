package ua.training.model.entity;

public class Account {
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

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCity() {
        return city;
    }

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
