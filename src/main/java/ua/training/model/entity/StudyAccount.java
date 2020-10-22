package ua.training.model.entity;

public class StudyAccount {
    public static final StudyAccount EMPTY;

    private float averageScore;
    private University university;

    static {
        EMPTY = new StudyAccount(University.EMPTY, 0);
    }

    public StudyAccount(University university, float averageScore) {
        this.university = university;
        this.averageScore = averageScore;
    }

    public University getUniversity() {
        return university;
    }

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
