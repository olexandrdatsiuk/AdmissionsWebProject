package ua.training.model.enumeration;

/**
 * Represents an enumeration of request states.
 * Each enum contains a state as int and
 * some of them a key for value in property file
 * to get correct translation on view.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public enum RequestState {
    CONSIDERED(1),
    REJECTED(2, "enum.request.state.reject"),
    ACCEPTED(3, "enum.request.state.accept"),
    NOT_CREDITED(4),
    CREDITED_TO_BUDGET(5),
    ENROLLED_IN_CONTRACT(6);

    private int state;
    private String key;

    RequestState(int s) {
        state = s;
        key = "";
    }

    RequestState(int state, String key) {
        this.state = state;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getState() {
        return state;
    }

}
