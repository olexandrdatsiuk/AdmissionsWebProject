package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public int getAllAmount() {
        return allAmount;
    }

    public int getFreeAmount() {
        return freeAmount;
    }

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
