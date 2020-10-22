package ua.training.model.entity;

public class University {
    public static final University EMPTY = new University(0, "");
    private int id;
    private String name;

    public University(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public University(int id) {
        this.id = id;
    }

    public University(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

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
