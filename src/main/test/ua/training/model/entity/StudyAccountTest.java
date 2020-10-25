package ua.training.model.entity;

import org.junit.Assert;
import org.junit.Test;

public class StudyAccountTest {
    @Test
    public void user_empty_instance_should_not_throw_NullPointerException() {
        StudyAccount.EMPTY.getUniversity().getName();
    }

    @Test
    public void should_correctly_build_account_instance_with_university_and_average_score() {
        StudyAccount account = new StudyAccount(new University(1), 12);
        Assert.assertEquals(1, account.getUniversity().getId(), 0);
        Assert.assertEquals(12, account.getAverageScore(), 0);
    }
}
