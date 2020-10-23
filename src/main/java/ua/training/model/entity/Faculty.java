package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Faculty.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class Faculty {
    private int id;
    private String name;
    private String nameEn;
    private int allAmount;
    private int freeAmount;
    private int paidAmount;
    private List<Subject> subjects = new ArrayList<>();

    private Faculty(FacultyBuilder fb) {
        this.id = fb.id;
        this.name = fb.name;
        this.nameEn = fb.nameEn;
        this.allAmount = fb.allAmount;
        this.freeAmount = fb.freeAmount;
        this.paidAmount = fb.paidAmount;
    }

    public static class FacultyBuilder {
        private int id;
        private String name;
        private String nameEn;
        private int allAmount;
        private int freeAmount;
        private int paidAmount;

        public FacultyBuilder setPaidAmount(int paidAmount) {
            this.paidAmount = paidAmount;
            return this;
        }

        public FacultyBuilder setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public FacultyBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public FacultyBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FacultyBuilder setAllAmount(int allAmount) {
            this.allAmount = allAmount;
            return this;
        }

        public FacultyBuilder setFreeAmount(int freeAmount) {
            this.freeAmount = freeAmount;
            return this;
        }

        public Faculty build() {
            return new Faculty(this);
        }
    }

    /**
     * Gets the faculty’s id.
     *
     * @return A int representing the faculty’s id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the faculty’s name.
     *
     * @return A String representing the faculty’s first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the faculty’s name in english translation.
     *
     * @return A String representing the faculty’s name.
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * Gets the faculty’s all amount.
     *
     * @return A int representing the faculty’s all amount.
     */
    public int getAllAmount() {
        return allAmount;
    }

    /**
     * Gets the faculty’s free amount.
     *
     * @return A int representing the faculty’s free amount.
     */
    public int getFreeAmount() {
        return freeAmount;
    }

    /**
     * Gets the list of the faculty’s subjects.
     *
     * @return A List representing of the faculty’s subjects.
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", allAmount=" + allAmount +
                ", freeAmount=" + freeAmount +
                '}';
    }
}
