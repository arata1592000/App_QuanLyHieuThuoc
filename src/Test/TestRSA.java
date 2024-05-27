package test;

import java.math.BigInteger;
import java.util.Random;

public class TestRSA {

    public static void main(String[] args) {
        // Tạo cặp khóa
        KeyPair keyPair = generateKeyPair(1024); // Thay đổi kích thước khóa ở đây nếu cần

        // Dữ liệu cần mã hóa
        String plainText = "Hello, this is a secret message!";
        System.out.println("Plaintext: " + plainText);

        // Mã hóa
        BigInteger encrypted = encrypt(plainText.getBytes(), keyPair.getPublicKey(), keyPair.getModulus());
        System.out.println("Encrypted: " + encrypted);

        // Giải mã
        byte[] decrypted = decrypt(encrypted, keyPair.getPrivateKey(), keyPair.getModulus());
        System.out.println("Decrypted: " + new String(decrypted));
    }

    // Tạo cặp khóa RSA
    public static KeyPair generateKeyPair(int bitLength) {
        Random rnd = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength, rnd);
        BigInteger q = BigInteger.probablePrime(bitLength, rnd);
        BigInteger n = p.multiply(q); // modulus
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // Euler's totient function
        BigInteger e = BigInteger.valueOf(65537); // public exponent (commonly used value)
        BigInteger d = e.modInverse(phi); // private exponent
        return new KeyPair(n, e, d);
    }

    // Mã hóa dữ liệu
    public static BigInteger encrypt(byte[] data, BigInteger e, BigInteger n) {
        BigInteger plainText = new BigInteger(data);
        return plainText.modPow(e, n);
    }

    // Giải mã dữ liệu
    public static byte[] decrypt(BigInteger encrypted, BigInteger d, BigInteger n) {
        BigInteger decrypted = encrypted.modPow(d, n);
        return decrypted.toByteArray();
    }

    // Lớp để lưu trữ cặp khóa
    static class KeyPair {
        private BigInteger modulus;
        private BigInteger publicKey;
        private BigInteger privateKey;

        public KeyPair(BigInteger modulus, BigInteger publicKey, BigInteger privateKey) {
            this.modulus = modulus;
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public BigInteger getModulus() {
            return modulus;
        }

        public BigInteger getPublicKey() {
            return publicKey;
        }

        public BigInteger getPrivateKey() {
            return privateKey;
        }
    }
}
