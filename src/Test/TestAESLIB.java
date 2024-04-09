package Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class TestAESLIB {

	private static final int BLOCK_SIZE = 16;

	public static byte[] addPadding(byte[] data, int blockSize) {
        int paddingLength = blockSize - (data.length % blockSize);
        byte paddingByte = (byte) paddingLength;
        byte[] paddedData = Arrays.copyOf(data, data.length + paddingLength);
        Arrays.fill(paddedData, data.length, paddedData.length, paddingByte);
        return paddedData;
    }
    
    public static byte[] removePadding(byte[] paddedData) {
        int paddingLength = paddedData[paddedData.length - 1];
        return Arrays.copyOfRange(paddedData, 0, paddedData.length - paddingLength);
    }
    
    public static void main(String[] args) {
        try {
            String originalText = "Hello, world!";

            String key = "Key"; // 16-byte key for AES-128

            byte[] originalBytes = addPadding(originalText.getBytes(StandardCharsets.UTF_8), BLOCK_SIZE);
            byte[] keyBytes = addPadding(key.getBytes(StandardCharsets.UTF_8), BLOCK_SIZE);

            byte[] encryptedText = encrypt(originalBytes, keyBytes);
            String encryptedBase64String = Base64.getEncoder().encodeToString(encryptedText);
            System.out.println("Encrypted (Base64): " + encryptedBase64String);
            System.out.println("Encrypted (Bytes): " + Arrays.toString(encryptedText));


            String decryptedText = decrypt(encryptedText, keyBytes);
//            String decryptedString = new String(removePadding(decryptedText), StandardCharsets.UTF_8);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(byte[] originalBytes, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(originalBytes);
    }

    public static String decrypt(byte[] cipherText, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes, "UTF-8");
    }
//    xEGS9yp0SvBN1IxH1rSScQ==
}
