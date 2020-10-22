package ua.training.model.enumeration;

public enum UniversityAction {
    ADD("enum.university.action.add"),
    DELETE("enum.university.action.delete"),
    UPDATE("enum.university.action.update");

    private String key;

    UniversityAction(String key) {
        this.key = key;
    }

    public static UniversityAction getAction(String action) {
        for (UniversityAction a : UniversityAction.values()) {
            if (a.name().equals(action)) {
                return a;
            }
        }
        return ADD;
    }

    public String getKey() {
        return key;
    }
}