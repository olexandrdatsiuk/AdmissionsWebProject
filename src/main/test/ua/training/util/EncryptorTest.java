package ua.training.util;

import org.junit.Assert;
import org.junit.Test;

public class EncryptorTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_when_param_is_null() {
        Encryptor.cryptWithMD5(null);
    }

    @Test()
    public void should_crypt_with_MD5() {
        Assert.assertEquals("F7BD616B6C841D2538735F76D1E02B57", Encryptor.cryptWithMD5("crypt"));
    }
}
