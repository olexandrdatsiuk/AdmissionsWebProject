package ua.training.model.entity;

/**
 * Represents an University.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class University {
    /**
     * Represents the empty University.
     */
    public static final University EMPTY = new University(0, "");
    private int id;
    private String name;

    /**
     * Creates a university with the specified id, name.
     *
     * @param id   The university’s id.
     * @param name The university’s name.
     */
    public University(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Creates a university with the specified id.
     *
     * @param id The university’s id.
     */
    public University(int id) {
        this.id = id;
    }

    /**
     * Creates a university with the specified name.
     *
     * @param name The university’s name.
     */
    public University(String name) {
        this.name = name;
    }

    /**
     * Gets the university’s id.
     *
     * @return A int representing the university’s id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the university’s name.
     *
     * @return A String representing the university’s name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
