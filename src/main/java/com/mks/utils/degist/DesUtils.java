package com.mks.utils.degist;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
public final class DesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DesUtils.class);
    
    private DesUtils() {
    }
    
    public static String encrypt(String content, String key, byte[] iv) {
        try {
            if (StringUtils.length(key) != 8) {
                throw new IllegalArgumentException("Key must be 8 byte");
            }
            SecretKeySpec secretkey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes());
            return Base64.encodeBase64String(encryptedData);
        }
        catch (Exception e) {
            LOGGER.error("encrypt:", e);
            return null;
        }
    }
    
    public static String decrypt(String content, String key, byte[] iv) {
        try {
            if (StringUtils.length(key) != 8) {
                throw new IllegalArgumentException("Key must be 8 byte");
            }
            byte[] contentBytes = Base64.decodeBase64(content);
            SecretKeySpec secretkey = new SecretKeySpec(key.getBytes(), "DES");
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretkey, zeroIv);
            byte[] decryptedData = cipher.doFinal(contentBytes);
            return new String(decryptedData);
        }
        catch (Exception e) {
            LOGGER.error("decrypt:", e);
            return null;
        }
    }
}
