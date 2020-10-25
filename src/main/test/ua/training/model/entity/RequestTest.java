package ua.training.model.entity;

import org.junit.Assert;
import org.junit.Test;

public class RequestTest {
    @Test
    public void should_correctly_build_request_instance_with_state_and_faculty() {
        Request request = new Request("state", new Faculty.FacultyBuilder().setName("name").build());
        Assert.assertEquals("state", request.getState());
        Assert.assertEquals("name", request.getFaculty().getName());
    }

    @Test
    public void should_correctly_build_request_instance_with_builder() {
        Request request = new Request.RequestBuilder().setState("state").setUniversity(University.EMPTY)
                .setFaculty(new Faculty.FacultyBuilder().setId(1).build())
                .setUser(User.EMPTY).setIntState(1).build();


        Assert.assertEquals("state", request.getState());
        Assert.assertEquals(1, request.getIntState(), 0);
        Assert.assertEquals(0, request.getUniversity().getId(), 0);
        Assert.assertEquals(1, request.getFaculty().getId(), 0);
        Assert.assertEquals(0, request.getUser().getId(), 0);
    }
}
