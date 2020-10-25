package ua.training.model.entity;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
    @Test
    public void account_empty_instance_should_not_throw_NullPointerException() {
        Account.EMPTY.getCity();
    }

    @Test
    public void should_correctly_build_account_instance() {
        Account account = new Account.AccountBuilder().setId(0)
                .setCity("").setFirstName("").setLastName("")
                .setMiddleName("").setRegion("").setId(12).build();

        Assert.assertEquals(12, account.getId(), 0);
        Assert.assertNotNull(account.getCity());
        Assert.assertNotNull(account.getFirstName());
        Assert.assertNotNull(account.getLastName());
        Assert.assertNotNull(account.getLastName());
        Assert.assertNotNull(account.getMiddleName());
        Assert.assertNotNull(account.getRegion());

    }
}
