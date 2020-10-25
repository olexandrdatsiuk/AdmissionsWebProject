package ua.training.model.entity;

import org.junit.Assert;
import org.junit.Test;

public class UniversityTest {
    @Test
    public void user_empty_instance_should_not_throw_NullPointerException() {
        University.EMPTY.getName();
    }

    @Test
    public void should_correctly_create_university_with_id_instance() {
        University university = new University(1);
        Assert.assertEquals(1, university.getId(), 0);
    }

    @Test
    public void should_correctly_create_university_with_name_instance() {
        University university = new University("name");
        Assert.assertEquals("name", university.getName());
    }

    @Test
    public void should_correctly_create_university_with_id_and_name_instance() {
        University university = new University(1, "name");
        Assert.assertEquals(1, university.getId(), 0);
        Assert.assertEquals("name", university.getName());
    }
}
