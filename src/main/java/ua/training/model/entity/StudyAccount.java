package ua.training.model.entity;

/**
 * Represents a StudyAccount.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class StudyAccount {
    /**
     * Represents the empty StudyAccount.
     */
    public static final StudyAccount EMPTY;

    private float averageScore;
    private University university;

    static {
        EMPTY = new StudyAccount(University.EMPTY, 0);
    }

    /**
     * Creates a study account with the specified university and average score.
     *
     * @param university   The study account’s university.
     * @param averageScore The study account’s average score.
     */
    public StudyAccount(University university, float averageScore) {
        this.university = university;
        this.averageScore = averageScore;
    }

    /**
     * Gets the study account’s university.
     *
     * @return A University representing the study account’s university.
     */
    public University getUniversity() {
        return university;
    }

    /**
     * Gets the study account’s average score.
     *
     * @return A float representing the account’s average score.
     */
    public float getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return "StudyAccount{" +
                "averageScore=" + averageScore +
                ", university=" + university +
                '}';
    }
}
