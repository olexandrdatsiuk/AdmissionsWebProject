package ua.training.model.entity;

import org.junit.Assert;
import org.junit.Test;
import ua.training.model.enumeration.UserRole;
import ua.training.model.enumeration.UserStatus;

public class UserTest {
    @Test
    public void user_empty_instance_should_not_throw_NullPointerException() {
        User.EMPTY.getAccount().getCity();
    }

    @Test
    public void should_correctly_build_user_instance() {
        User user = new User.UserBuilder().setId(12).setEmail("email").setAccount(Account.EMPTY)
                .setStudyAccount(StudyAccount.EMPTY).setRole(UserRole.GUEST)
                .setStatus(UserStatus.BLOCKED).setPassword("password").build();
        Assert.assertEquals(12, user.getId(), 0);
        Assert.assertEquals("email", user.getEmail());
        Assert.assertNotNull(user.getAccount());
        Assert.assertNotNull(user.getStudyAccount());
        Assert.assertEquals(UserRole.GUEST, user.getRole());
        Assert.assertEquals(UserStatus.BLOCKED, user.getStatus());
        Assert.assertEquals("password", user.getPassword());
    }

    @Test
    public void should_update_user_password() {
        Request request = new Request.RequestBuilder().setUser(User.EMPTY).build();
        request.getUser().updatePassword("updatedPass");
        Assert.assertEquals("updatedPass", request.getUser().getPassword());
    }
}
