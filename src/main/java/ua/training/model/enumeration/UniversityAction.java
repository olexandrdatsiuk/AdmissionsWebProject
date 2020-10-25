package ua.training.model.enumeration;

/**
 * Represents an enumeration of universities action button.
 * Each enum contains a key for value in property file to get correct translation on view.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
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