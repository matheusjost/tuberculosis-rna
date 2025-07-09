package br.unisc.tuberculosis_rna.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class AESEncryptionService {

    private static final String ALGORITHM = "AES";
    private final SecretKey secretKey;

    public AESEncryptionService(@Value("${br.unisc.tuberculosis_rna.encryption.secret-key}") String secretKey) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
    }

    public String encrypt(String filePath) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(filePath.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedPath) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPath));
        return new String(decryptedBytes);
    }
}