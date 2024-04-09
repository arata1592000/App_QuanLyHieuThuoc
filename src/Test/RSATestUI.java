package Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;

public class RSATestUI extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton encryptButton;
    private JButton decryptButton;

    private static final int BIT_LENGTH = 1024;
    
    public RSATestUI() {
        super("RSA Test");

        // Tạo cặp khóa
        KeyPair keyPair = generateKeyPair(BIT_LENGTH); 

        // Set up UI components
        inputTextArea = new JTextArea(5, 20);
        outputTextArea = new JTextArea(5, 20);
        outputTextArea.setEditable(false);

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        // Add action listeners
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plainText = inputTextArea.getText();
                BigInteger encrypted = encrypt(new BigInteger(plainText.getBytes()), keyPair.getPublicKey(), keyPair.getModulus());
                outputTextArea.setText(encrypted.toString());
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encrypted = inputTextArea.getText();
                BigInteger encryptedBigInt = new BigInteger(encrypted);
                byte[] decrypted = decrypt(encryptedBigInt, keyPair.getPrivateKey(), keyPair.getModulus());
                String decryptedString = new String(decrypted);
                outputTextArea.setText(decryptedString);
            }
        });

        // Set layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Input:"));
        panel.add(new JScrollPane(inputTextArea));
        panel.add(encryptButton);
        panel.add(new JLabel("Output:"));
        panel.add(new JScrollPane(outputTextArea));
        panel.add(decryptButton);

        add(panel);
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public static KeyPair generateKeyPair(int bitLength) {
        Random rnd = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength, rnd);
        BigInteger q = BigInteger.probablePrime(bitLength, rnd);
        BigInteger n = p.multiply(q); // modulus
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // Euler's totient function
        BigInteger e = BigInteger.valueOf(65537); // public exponent (commonly used value)
        BigInteger d = modInverse(e, phi); // private exponent
        return new KeyPair(n, e, d);
    }

    // Mã hóa dữ liệu
    public BigInteger encrypt(BigInteger data, BigInteger e, BigInteger n) {
        return data.modPow(e, n);
    }

    // Giải mã dữ liệu
    public byte[] decrypt(BigInteger encrypted, BigInteger d, BigInteger n) {
        BigInteger decrypted = encrypted.modPow(d, n);
        return decrypted.toByteArray();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RSATestUI();
            }
        });
    }

    // Hàm tính nghịch đảo modulo (dựa trên thuật toán Euclid mở rộng)
    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        BigInteger m0 = m;
        BigInteger y = BigInteger.ZERO, x = BigInteger.ONE;

        while (a.compareTo(BigInteger.ONE) > 0) {
            BigInteger[] qr = a.divideAndRemainder(m);
            a = m;
            m = qr[1];
            BigInteger t = y;
            y = x.subtract(qr[0].multiply(y));
            x = t;
        }

        if (x.compareTo(BigInteger.ZERO) < 0)
            x = x.add(m0);

        return x;
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
