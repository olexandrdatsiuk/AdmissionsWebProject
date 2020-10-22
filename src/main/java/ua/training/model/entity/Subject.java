package ua.training.model.entity;

public class Subject {
    private int id;
    private int person_id;
    private String name;
    private int score;

    public Subject(int id) {
        this.id = id;
    }

    public Subject(int person_id, int id, int score) {
        this.id = id;
        this.person_id = person_id;
        this.score = score;
    }

    public int getPerson_id() {
        return person_id;
    }

    public Subject(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
