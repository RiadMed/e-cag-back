package dz.gouv.ppas.web.cagapps.tools;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class AESUtils {

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int IV_SIZE = 128;
    private static final int IV_LENGTH = IV_SIZE / 4;
    private static AESTypeEnum cryptoType = AESTypeEnum.BASE64;

    private static int keySize = 128;
    private static int iterationCount = 333;

    private static Cipher cipher;
    private static int saltLength;
    private static final String secretKeys = "TEST@AES.COM";

    public AESUtils() {
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            saltLength = this.keySize / 4;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println(e);
        }
    }

    public AESUtils(int keySize, int iterationCount) {
        this.keySize = keySize;
        this.iterationCount = iterationCount;
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            saltLength = this.keySize / 4;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println(e);

        }
    }

    private String encrypt(String salt, String iv, String plainText) {
        try {
            SecretKey key = generateKey(salt, secretKeys);
            byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plainText.getBytes(StandardCharsets.UTF_8));
            String cipherText;
            if (cryptoType.equals(AESTypeEnum.HEX)) {
                cipherText = toHex(encrypted);
            } else {
                cipherText = toBase64(encrypted);
            }
            return cipherText;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    public String encrypt(String plainText) {
        try {
            String salt = toHex(generateRandom(keySize / 8));
            String iv = toHex(generateRandom(IV_SIZE / 8));
            String cipherText = encrypt(salt, iv, plainText);
            return salt + iv + cipherText;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    private String decrypt(String salt, String iv, String cipherText) {
        try {
            SecretKey key = generateKey(salt, secretKeys);
            byte[] encrypted;
            if (cryptoType.equals(AESTypeEnum.HEX)) {
                encrypted = fromHex(cipherText);
            } else {
                encrypted = fromBase64(cipherText);
            }
            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    public String decrypt(String cipherText) {
        try {
            String salt = cipherText.substring(0, saltLength);
            String iv = cipherText.substring(saltLength, saltLength + IV_LENGTH);
            String ct = cipherText.substring(saltLength + IV_LENGTH);
            return decrypt(salt, iv, ct);
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    private byte[] generateRandom(int length) {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return randomBytes;
    }

    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] fromHex = fromHex(iv);
            random.nextBytes(fromHex);
            cipher.init(encryptMode, key, new IvParameterSpec(fromHex));
            return cipher.doFinal(bytes);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
                | BadPaddingException e) {
            System.out.println(e);

            return null;
        }
    }

    private SecretKey generateKey(String salt, String passphrase) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), fromHex(salt), iterationCount, keySize);
            return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    private byte[] fromBase64(String str) {
        return DatatypeConverter.parseBase64Binary(str);
    }

    private String toBase64(byte[] ba) {
        return DatatypeConverter.printBase64Binary(ba);
    }

    private byte[] fromHex(String str) {
        return DatatypeConverter.parseHexBinary(str);
    }

    private String toHex(byte[] ba) {
        return DatatypeConverter.printHexBinary(ba);
    }


}
