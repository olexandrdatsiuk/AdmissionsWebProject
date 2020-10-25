package ua.training.model.entity;

import org.junit.Assert;
import org.junit.Test;

public class SubjectTest {
    @Test
    public void should_correctly_create_university_with_id_instance() {
        Subject subject = new Subject(12);
        Assert.assertEquals(12, subject.getId(), 0);
    }

    @Test
    public void should_correctly_create_university_with_id_and_name_instance() {
        Subject subject = new Subject(12, "name");
        Assert.assertEquals(12, subject.getId(), 0);
        Assert.assertEquals("name", subject.getName());
    }

    @Test
    public void should_correctly_create_university_with_name_and_score_instance() {
        Subject subject = new Subject("name", 199);
        Assert.assertEquals(199, subject.getScore(), 0);
        Assert.assertEquals("name", subject.getName());
    }

    @Test
    public void should_correctly_create_university_with_id_score_and_person_id_instance() {
        Subject subject = new Subject(1, 12, 199);
        Assert.assertEquals(199, subject.getScore(), 0);
        Assert.assertEquals(1, subject.getPerson_id(), 0);
        Assert.assertEquals(12, subject.getId(), 0);
    }

}
