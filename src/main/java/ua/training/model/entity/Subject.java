package ua.training.model.entity;

/**
 * Represents a Subject.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class Subject {
    private int id;
    private int person_id;
    private String name;
    private int score;

    /**
     * Creates a subject with the specified id.
     *
     * @param id The subject’s id.
     */
    public Subject(int id) {
        this.id = id;
    }

    /**
     * Creates a subject with the specified person_id, id, score.
     *
     * @param person_id The study subject’s person_id.
     * @param id        The study subject’s id.
     * @param score     The study subject’s score.
     */
    public Subject(int person_id, int id, int score) {
        this.id = id;
        this.person_id = person_id;
        this.score = score;
    }

    /**
     * Creates a subject with the specified name and score.
     *
     * @param name  The subject’s name.
     * @param score The subject’s score.
     */
    public Subject(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Creates a subject with the specified id, name.
     *
     * @param id   The study subject’s university.
     * @param name The study subject’s average score.
     */
    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the subject’s person_id.
     *
     * @return A int representing the subject’s person_id.
     */
    public int getPerson_id() {
        return person_id;
    }

    /**
     * Gets the subject’s id.
     *
     * @return A int representing the subject’s id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the subject’s name.
     *
     * @return A String representing the subject’s name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the subject’s score.
     *
     * @return A int representing the subject’s score.
     */
    public int getScore() {
        return score;
    }
}
