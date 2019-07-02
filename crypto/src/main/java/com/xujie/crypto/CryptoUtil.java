package com.xujie.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;


/**
 * Utility class for Cryptographic functions. To be shared with client code.
 * Will need to be compatitble with JDK1.4
 * Due to the strength of the encryption, it would need to be augmented with the unlimited strength security pollicy for the JDK.
 *
 * @author raymondaw
 * @version 1.0
 */
public class CryptoUtil {
    public static final String CRYPTO_CHARSET = "UTF-8";
    public static final String CRYPTO_ALGORITHM_CIPHER = "AES";
    public static final String CRYPTO_SEED = "crypto.seed";
    public static final String CRYPTO_SALT ="crypto.salt";
    public static final String KEY_ALGORITHM = "AES";
    public static final String KEY_PROVIDER = "SunJCE";
    public static final String SPI_PROVIDER="SHA1PRNG";
    public static final String PROVIDER="SUN";
    public static final int KEY_SIZE=256;
    public static final String SIGNATURE_ALGO="SHA1withRSA";
    public static final String HASH_ALGO="SHA-256";
    public static final String ENCODING_CHARSET="UTF-8";

    private static CryptoUtil instance;

    /**
     * Access method to retrieve the singleton instance
     *
     * @return Singleton instance of the crypto util
     */
    private static CryptoUtil getInstance() {
        if (instance == null) {
            synchronized (CryptoUtil.class) {
                instance = new CryptoUtil();
            }
        }
        return instance;
    }


    /**
     * Generates the cipher key to be used in the encryption/decryption
     *
     * @param seed seed value used to generate the key
     * @return SecretKeySpec from the seed
     */
    private SecretKeySpec getSecretKeySpec(String seed) {

        SecretKey oSecretKey = null;
        SecretKeySpec oSecretKeySpec = null;
        try {
            SecureRandom oSecureRandom = SecureRandom.getInstance(SPI_PROVIDER, PROVIDER);
            oSecureRandom.setSeed(seed.getBytes());
            KeyGenerator oKeyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM, KEY_PROVIDER);
            oKeyGenerator.init(KEY_SIZE, oSecureRandom);
            oSecretKey = oKeyGenerator.generateKey();
            byte[] bytes = oSecretKey.getEncoded();
            oSecretKeySpec = new SecretKeySpec(bytes, KEY_ALGORITHM);

            return oSecretKeySpec;

        } catch (NullPointerException npe) {
            throw new RuntimeException("Unable to generate secret key", npe);
        } catch (IllegalArgumentException iae) {
            throw new RuntimeException("Unable to generate secret key", iae);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("Unable to generate secret key", nsae);
        } catch (NoSuchProviderException nspe) {
            throw new RuntimeException("Unable to generate secret key", nspe);
        }
    }


    /**
     * Encrypts the plain text using the salt and seed value
     *
     * @param psPlainText string to be encrypted
     * @param psSalt      salt value to be used to pad the plain text
     * @param seed        seed to be used to generate the key
     * @return
     */
    public static String encrypt(String psPlainText, String psSalt, String seed) {

        String sCipherText = "";
        if (StringUtils.isBlank(psPlainText)) {
            throw new RuntimeException("Plain text is empty!! Unable to encrypt an empty plain text");
        }
        if (StringUtils.isBlank(psSalt)) {
            throw new RuntimeException("Salt value is empty!! Unable to encrypt the plain text without a salt value");
        }
        if (StringUtils.isBlank(seed)) {
            throw new RuntimeException("Seed value is empty!! Unable to encrypt the plain text without a seed value");
        }

        try {
            SecretKeySpec oSecretKeySpec = getInstance().getSecretKeySpec(seed);

            // Instantiate the cipher
            Cipher oCipher = Cipher.getInstance(CRYPTO_ALGORITHM_CIPHER);
            oCipher.init(Cipher.ENCRYPT_MODE, oSecretKeySpec);

            byte[] bArrCipherText = oCipher.doFinal((psSalt + psPlainText).getBytes(CRYPTO_CHARSET));
            sCipherText = Base64.encodeBase64String(bArrCipherText);

            System.out.println("Encrypted string is [" + sCipherText + "], enclosed within square brackets");
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("Unable to encrypt the plain text", nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new RuntimeException("Unable to encrypt the plain text", nspe);
        } catch (InvalidKeyException ike) {
            throw new RuntimeException("Unable to encrypt the plain text", ike);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Unable to encrypt the plain text", uee);
        } catch (IllegalBlockSizeException ibse) {
            throw new RuntimeException("Unable to encrypt the plain text", ibse);
        } catch (BadPaddingException bpe) {
            throw new RuntimeException("Unable to encrypt the plain text", bpe);
        }
        return sCipherText;
    }

    /**
     * Decrypts the cipher text using the salt and seed value
     *
     * @param psCipherText String to be decrypted
     * @param psSalt       salt value
     * @param seed         seed value
     * @return
     * @throws RuntimeException
     */
    public static String decrypt(String psCipherText, String psSalt, String seed) throws RuntimeException {
        String sPlainText = "";

        if (StringUtils.isBlank(psCipherText)) {
            throw new RuntimeException("Cipher text is empty!! Unable to decrypt an empty cipher text");
        }
        if (StringUtils.isBlank(psSalt)) {
            throw new RuntimeException("Salt value is empty!! Unable to decrypt the cipher text without a salt value");
        }
        if (StringUtils.isBlank(seed)) {
            throw new RuntimeException("Seed value is empty!! Unable to decrypt the cipher text without a seed value");
        }

        try {
            SecretKeySpec oSecretKeySpec = getInstance().getSecretKeySpec(seed);
            // Instantiate the cipher
            Cipher oCipher = Cipher.getInstance(CRYPTO_ALGORITHM_CIPHER);
            oCipher.init(Cipher.DECRYPT_MODE, oSecretKeySpec);

            byte[] bArrDecodedText = Base64.decodeBase64(psCipherText);

            byte[] bArrPlainText = oCipher.doFinal(bArrDecodedText);
            sPlainText = new String(bArrPlainText, CRYPTO_CHARSET).substring(psSalt.length());
//            logger.debug("Decrypted string is [" + sPlainText + "], enclosed within square brackets");

        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("Unable to decrypt the cipher text", nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new RuntimeException("Unable to decrypt the cipher text", nspe);
        } catch (InvalidKeyException ike) {
            throw new RuntimeException("Unable to decrypt the cipher text", ike);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Unable to decrypt the cipher text", uee);
        } catch (IllegalBlockSizeException ibse) {
            throw new RuntimeException("Unable to decrypt the cipher text", ibse);
        } catch (BadPaddingException bpe) {
            throw new RuntimeException("Unable to decrypt the cipher text", bpe);
        }
        return sPlainText;
    }

    /**
     * Generates a hash on the text passed in.
     *
     * @param textToHash
     * @return hash value
     * @throws RuntimeException
     */
    public static String generateHash(String textToHash) throws RuntimeException {
        if (StringUtils.isBlank(textToHash)) {
            throw new RuntimeException("No string to hash");
        }
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGO);
            md.update(textToHash.getBytes());
            byte[] hashArr = md.digest();
            hash = Base64.encodeBase64String(hashArr);
            System.out.println("Generated Hash [ " + hash + "] ");
            return hash;
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("Unable to generate hash value!", nsae);
        }
    }
}