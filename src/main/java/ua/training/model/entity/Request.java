package ua.training.model.entity;

/**
 * Represents an Request.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class Request {
    private String state;
    private Faculty faculty;
    private User user;
    private University university;
    private int intState;

    public Request(String state, Faculty faculty) {
        this.state = state;
        this.faculty = faculty;
    }

    private Request(RequestBuilder rb) {
        this.state = rb.state;
        this.faculty = rb.faculty;
        this.user = rb.user;
        this.university = rb.university;
        this.intState = rb.intState;
    }

    public static class RequestBuilder {
        private String state;
        private Faculty faculty;
        private User user;
        private University university;
        private int intState;

        public RequestBuilder setIntState(int intState) {
            this.intState = intState;
            return this;
        }

        public RequestBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public RequestBuilder setFaculty(Faculty faculty) {
            this.faculty = faculty;
            return this;
        }

        public RequestBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public RequestBuilder setUniversity(University university) {
            this.university = university;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    /**
     * Gets the request’s state.
     *
     * @return A int representing the request’s state.
     */
    public int getIntState() {
        return intState;
    }

    /**
     * Gets the request’s user.
     *
     * @return A User representing the request’s user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the request’s university.
     *
     * @return A University representing the request’s university.
     */
    public University getUniversity() {
        return university;
    }

    /**
     * Gets the request’s faculty.
     *
     * @return A Faculty representing the request’s faculty.
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Gets the request’s state.
     *
     * @return A String representing the request’s state.
     */
    public String getState() {
        return state;
    }

}
