package com.xujie.crypto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CryptoTest {

    @Test
    public void testDecrypt() {
        String encrypted = "2cUv/5a+IaVaAKPqtaL3GQ==";
        String salt = "salty";
        String seed = "seedy";
        String decrypted = "secret";

        assertEquals(CryptoUtil.decrypt(encrypted, salt, seed), decrypted);
    }

    @Test
    public void testEncrypt() {
        String encrypted = "2cUv/5a+IaVaAKPqtaL3GQ==";
        String salt = "salty";
        String seed = "seedy";
        String decrypted = "secret";

        assertEquals(CryptoUtil.encrypt(decrypted, salt, seed), encrypted);
    }
}
