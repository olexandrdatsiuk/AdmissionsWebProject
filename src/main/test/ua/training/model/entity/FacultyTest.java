package ua.training.model.entity;

import org.junit.Assert;
import org.junit.Test;

public class FacultyTest {
    @Test
    public void should_correctly_build_account_instance_with_builder() {
        Faculty faculty = new Faculty.FacultyBuilder().setId(1).setName("name")
                .setNameEn("nameEn")
                .setFreeAmount(12)
                .setAllAmount(25)
                .build();
        faculty.getSubjects().add(new Subject(1));
        Assert.assertEquals(1, faculty.getId(), 0);
        Assert.assertEquals(12, faculty.getFreeAmount(), 0);
        Assert.assertEquals(25, faculty.getAllAmount(), 0);
        Assert.assertEquals("name", faculty.getName());
        Assert.assertEquals("nameEn", faculty.getNameEn());
        Assert.assertEquals(1, faculty.getSubjects().size(), 0);
    }
}
