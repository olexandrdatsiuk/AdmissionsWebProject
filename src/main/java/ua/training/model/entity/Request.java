package ua.training.model.entity;

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

    public int getIntState() {
        return intState;
    }

    public User getUser() {
        return user;
    }

    public University getUniversity() {
        return university;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getState() {
        return state;
    }

    public enum State {
        CONSIDERED(1),
        REJECTED(2, "enum.request.state.reject"),
        ACCEPTED(3, "enum.request.state.accept"),
        NOT_CREDITED(4),
        CREDITED_TO_BUDGET(5),
        ENROLLED_IN_CONTRACT(6);

        private int state;
        private String key;

        State(int s) {
            state = s;
            key = "";
        }

        State(int state, String key) {
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
}
