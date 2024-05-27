package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import test.RSATestUI.KeyPair;

public class AESTestUI extends JFrame{

    private static final int BLOCK_SIZE = 16;

    // S-box substitution table
    private static final byte[] SBox = {
            (byte) 0x63, (byte) 0x7c, (byte) 0x77, (byte) 0x7b, (byte) 0xf2, (byte) 0x6b, (byte) 0x6f, (byte) 0xc5,
            (byte) 0x30, (byte) 0x01, (byte) 0x67, (byte) 0x2b, (byte) 0xfe, (byte) 0xd7, (byte) 0xab, (byte) 0x76,
            (byte) 0xca, (byte) 0x82, (byte) 0xc9, (byte) 0x7d, (byte) 0xfa, (byte) 0x59, (byte) 0x47, (byte) 0xf0,
            (byte) 0xad, (byte) 0xd4, (byte) 0xa2, (byte) 0xaf, (byte) 0x9c, (byte) 0xa4, (byte) 0x72, (byte) 0xc0,
            (byte) 0xb7, (byte) 0xfd, (byte) 0x93, (byte) 0x26, (byte) 0x36, (byte) 0x3f, (byte) 0xf7, (byte) 0xcc,
            (byte) 0x34, (byte) 0xa5, (byte) 0xe5, (byte) 0xf1, (byte) 0x71, (byte) 0xd8, (byte) 0x31, (byte) 0x15,
            (byte) 0x04, (byte) 0xc7, (byte) 0x23, (byte) 0xc3, (byte) 0x18, (byte) 0x96, (byte) 0x05, (byte) 0x9a,
            (byte) 0x07, (byte) 0x12, (byte) 0x80, (byte) 0xe2, (byte) 0xeb, (byte) 0x27, (byte) 0xb2, (byte) 0x75,
            (byte) 0x09, (byte) 0x83, (byte) 0x2c, (byte) 0x1a, (byte) 0x1b, (byte) 0x6e, (byte) 0x5a, (byte) 0xa0,
            (byte) 0x52, (byte) 0x3b, (byte) 0xd6, (byte) 0xb3, (byte) 0x29, (byte) 0xe3, (byte) 0x2f, (byte) 0x84,
            (byte) 0x53, (byte) 0xd1, (byte) 0x00, (byte) 0xed, (byte) 0x20, (byte) 0xfc, (byte) 0xb1, (byte) 0x5b,
            (byte) 0x6a, (byte) 0xcb, (byte) 0xbe, (byte) 0x39, (byte) 0x4a, (byte) 0x4c, (byte) 0x58, (byte) 0xcf,
            (byte) 0xd0, (byte) 0xef, (byte) 0xaa, (byte) 0xfb, (byte) 0x43, (byte) 0x4d, (byte) 0x33, (byte) 0x85,
            (byte) 0x45, (byte) 0xf9, (byte) 0x02, (byte) 0x7f, (byte) 0x50, (byte) 0x3c, (byte) 0x9f, (byte) 0xa8,
            (byte) 0x51, (byte) 0xa3, (byte) 0x40, (byte) 0x8f, (byte) 0x92, (byte) 0x9d, (byte) 0x38, (byte) 0xf5,
            (byte) 0xbc, (byte) 0xb6, (byte) 0xda, (byte) 0x21, (byte) 0x10, (byte) 0xff, (byte) 0xf3, (byte) 0xd2,
            (byte) 0xcd, (byte) 0x0c, (byte) 0x13, (byte) 0xec, (byte) 0x5f, (byte) 0x97, (byte) 0x44, (byte) 0x17,
            (byte) 0xc4, (byte) 0xa7, (byte) 0x7e, (byte) 0x3d, (byte) 0x64, (byte) 0x5d, (byte) 0x19, (byte) 0x73,
            (byte) 0x60, (byte) 0x81, (byte) 0x4f, (byte) 0xdc, (byte) 0x22, (byte) 0x2a, (byte) 0x90, (byte) 0x88,
            (byte) 0x46, (byte) 0xee, (byte) 0xb8, (byte) 0x14, (byte) 0xde, (byte) 0x5e, (byte) 0x0b, (byte) 0xdb,
            (byte) 0xe0, (byte) 0x32, (byte) 0x3a, (byte) 0x0a, (byte) 0x49, (byte) 0x06, (byte) 0x24, (byte) 0x5c,
            (byte) 0xc2, (byte) 0xd3, (byte) 0xac, (byte) 0x62, (byte) 0x91, (byte) 0x95, (byte) 0xe4, (byte) 0x79,
            (byte) 0xe7, (byte) 0xc8, (byte) 0x37, (byte) 0x6d, (byte) 0x8d, (byte) 0xd5, (byte) 0x4e, (byte) 0xa9,
            (byte) 0x6c, (byte) 0x56, (byte) 0xf4, (byte) 0xea, (byte) 0x65, (byte) 0x7a, (byte) 0xae, (byte) 0x08,
            (byte) 0xba, (byte) 0x78, (byte) 0x25, (byte) 0x2e, (byte) 0x1c, (byte) 0xa6, (byte) 0xb4, (byte) 0xc6,
            (byte) 0xe8, (byte) 0xdd, (byte) 0x74, (byte) 0x1f, (byte) 0x4b, (byte) 0xbd, (byte) 0x8b, (byte) 0x8a,
            (byte) 0x70, (byte) 0x3e, (byte) 0xb5, (byte) 0x66, (byte) 0x48, (byte) 0x03, (byte) 0xf6, (byte) 0x0e,
            (byte) 0x61, (byte) 0x35, (byte) 0x57, (byte) 0xb9, (byte) 0x86, (byte) 0xc1, (byte) 0x1d, (byte) 0x9e,
            (byte) 0xe1, (byte) 0xf8, (byte) 0x98, (byte) 0x11, (byte) 0x69, (byte) 0xd9, (byte) 0x8e, (byte) 0x94,
            (byte) 0x9b, (byte) 0x1e, (byte) 0x87, (byte) 0xe9, (byte) 0xce, (byte) 0x55, (byte) 0x28, (byte) 0xdf,
            (byte) 0x8c, (byte) 0xa1, (byte) 0x89, (byte) 0x0d, (byte) 0xbf, (byte) 0xe6, (byte) 0x42, (byte) 0x68,
            (byte) 0x41, (byte) 0x99, (byte) 0x2d, (byte) 0x0f, (byte) 0xb0, (byte) 0x54, (byte) 0xbb, (byte) 0x16
    };



    // Round constants
    private static final byte[] Rcon = {
            (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10,
            (byte) 0x20, (byte) 0x40, (byte) 0x80, (byte) 0x1b, (byte) 0x36, (byte) 0x6c,
            (byte) 0xd8, (byte) 0xab, (byte) 0x4d, (byte) 0x9a, (byte) 0x2f, (byte) 0x5e,
            (byte) 0xbc, (byte) 0x63, (byte) 0xc6, (byte) 0x97, (byte) 0x35, (byte) 0x6a,
            (byte) 0xd4, (byte) 0xb3, (byte) 0x7d, (byte) 0xfa, (byte) 0xef, (byte) 0xc5,
            (byte) 0x91
    };

    private static final byte[] InvSBox = {
            (byte) 0x52, (byte) 0x09, (byte) 0x6a, (byte) 0xd5, (byte) 0x30, (byte) 0x36, (byte) 0xa5, (byte) 0x38,
            (byte) 0xbf, (byte) 0x40, (byte) 0xa3, (byte) 0x9e, (byte) 0x81, (byte) 0xf3, (byte) 0xd7, (byte) 0xfb,
            (byte) 0x7c, (byte) 0xe3, (byte) 0x39, (byte) 0x82, (byte) 0x9b, (byte) 0x2f, (byte) 0xff, (byte) 0x87,
            (byte) 0x34, (byte) 0x8e, (byte) 0x43, (byte) 0x44, (byte) 0xc4, (byte) 0xde, (byte) 0xe9, (byte) 0xcb,
            (byte) 0x54, (byte) 0x7b, (byte) 0x94, (byte) 0x32, (byte) 0xa6, (byte) 0xc2, (byte) 0x23, (byte) 0x3d,
            (byte) 0xee, (byte) 0x4c, (byte) 0x95, (byte) 0x0b, (byte) 0x42, (byte) 0xfa, (byte) 0xc3, (byte) 0x4e,
            (byte) 0x08, (byte) 0x2e, (byte) 0xa1, (byte) 0x66, (byte) 0x28, (byte) 0xd9, (byte) 0x24, (byte) 0xb2,
            (byte) 0x76, (byte) 0x5b, (byte) 0xa2, (byte) 0x49, (byte) 0x6d, (byte) 0x8b, (byte) 0xd1, (byte) 0x25,
            (byte) 0x72, (byte) 0xf8, (byte) 0xf6, (byte) 0x64, (byte) 0x86, (byte) 0x68, (byte) 0x98, (byte) 0x16,
            (byte) 0xd4, (byte) 0xa4, (byte) 0x5c, (byte) 0xcc, (byte) 0x5d, (byte) 0x65, (byte) 0xb6, (byte) 0x92,
            (byte) 0x6c, (byte) 0x70, (byte) 0x48, (byte) 0x50, (byte) 0xfd, (byte) 0xed, (byte) 0xb9, (byte) 0xda,
            (byte) 0x5e, (byte) 0x15, (byte) 0x46, (byte) 0x57, (byte) 0xa7, (byte) 0x8d, (byte) 0x9d, (byte) 0x84,
            (byte) 0x90, (byte) 0xd8, (byte) 0xab, (byte) 0x00, (byte) 0x8c, (byte) 0xbc, (byte) 0xd3, (byte) 0x0a,
            (byte) 0xf7, (byte) 0xe4, (byte) 0x58, (byte) 0x05, (byte) 0xb8, (byte) 0xb3, (byte) 0x45, (byte) 0x06,
            (byte) 0xd0, (byte) 0x2c, (byte) 0x1e, (byte) 0x8f, (byte) 0xca, (byte) 0x3f, (byte) 0x0f, (byte) 0x02,
            (byte) 0xc1, (byte) 0xaf, (byte) 0xbd, (byte) 0x03, (byte) 0x01, (byte) 0x13, (byte) 0x8a, (byte) 0x6b,
            (byte) 0x3a, (byte) 0x91, (byte) 0x11, (byte) 0x41, (byte) 0x4f, (byte) 0x67, (byte) 0xdc, (byte) 0xea,
            (byte) 0x97, (byte) 0xf2, (byte) 0xcf, (byte) 0xce, (byte) 0xf0, (byte) 0xb4, (byte) 0xe6, (byte) 0x73,
            (byte) 0x96, (byte) 0xac, (byte) 0x74, (byte) 0x22, (byte) 0xe7, (byte) 0xad, (byte) 0x35, (byte) 0x85,
            (byte) 0xe2, (byte) 0xf9, (byte) 0x37, (byte) 0xe8, (byte) 0x1c, (byte) 0x75, (byte) 0xdf, (byte) 0x6e,
            (byte) 0x47, (byte) 0xf1, (byte) 0x1a, (byte) 0x71, (byte) 0x1d, (byte) 0x29, (byte) 0xc5, (byte) 0x89,
            (byte) 0x6f, (byte) 0xb7, (byte) 0x62, (byte) 0x0e, (byte) 0xaa, (byte) 0x18, (byte) 0xbe, (byte) 0x1b,
            (byte) 0xfc, (byte) 0x56, (byte) 0x3e, (byte) 0x4b, (byte) 0xc6, (byte) 0xd2, (byte) 0x79, (byte) 0x20,
            (byte) 0x9a, (byte) 0xdb, (byte) 0xc0, (byte) 0xfe, (byte) 0x78, (byte) 0xcd, (byte) 0x5a, (byte) 0xf4,
            (byte) 0x1f, (byte) 0xdd, (byte) 0xa8, (byte) 0x33, (byte) 0x88, (byte) 0x07, (byte) 0xc7, (byte) 0x31,
            (byte) 0xb1, (byte) 0x12, (byte) 0x10, (byte) 0x59, (byte) 0x27, (byte) 0x80, (byte) 0xec, (byte) 0x5f,
            (byte) 0x60, (byte) 0x51, (byte) 0x7f, (byte) 0xa9, (byte) 0x19, (byte) 0xb5, (byte) 0x4a, (byte) 0x0d,
            (byte) 0x2d, (byte) 0xe5, (byte) 0x7a, (byte) 0x9f, (byte) 0x93, (byte) 0xc9, (byte) 0x9c, (byte) 0xef,
            (byte) 0xa0, (byte) 0xe0, (byte) 0x3b, (byte) 0x4d, (byte) 0xae, (byte) 0x2a, (byte) 0xf5, (byte) 0xb0,
            (byte) 0xc8, (byte) 0xeb, (byte) 0xbb, (byte) 0x3c, (byte) 0x83, (byte) 0x53, (byte) 0x99, (byte) 0x61,
            (byte) 0x17, (byte) 0x2b, (byte) 0x04, (byte) 0x7e, (byte) 0xba, (byte) 0x77, (byte) 0xd6, (byte) 0x26,
            (byte) 0xe1, (byte) 0x69, (byte) 0x14, (byte) 0x63, (byte) 0x55, (byte) 0x21, (byte) 0x0c, (byte) 0x7d
    };

	private JTextArea inputTextArea;

	private JTextArea outputTextArea;

	private JButton encryptButton;

	private JButton decryptButton;

	private JTextArea inputKeyArea;
    
    public AESTestUI() {
		// TODO Auto-generated constructor stub
            super("AES Test");

            // Tạo cặp khóa

            // Set up UI components
            inputTextArea = new JTextArea(5, 20);
            inputKeyArea = new JTextArea(1,10);
            outputTextArea = new JTextArea(5, 20);
            outputTextArea.setEditable(false);

            encryptButton = new JButton("Encrypt");
            decryptButton = new JButton("Decrypt");

            // Add action listeners
            encryptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String plainText = inputTextArea.getText();
                    String key = inputKeyArea.getText();
                    byte[] originalBytes = addPadding(plainText.getBytes(StandardCharsets.UTF_8), BLOCK_SIZE);
                    byte[] keyBytes = addPadding(key.getBytes(StandardCharsets.UTF_8), BLOCK_SIZE);

                    byte[] encryptedText = null;
					try {
						encryptedText = encryptt(originalBytes, keyBytes);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    String encryptedBase64String = Base64.getEncoder().encodeToString(encryptedText);
                    outputTextArea.setText(encryptedBase64String);
                }
            });

            decryptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String plainText = inputTextArea.getText();
                    String key = inputKeyArea.getText();
                    byte[] encryptedBytes = Base64.getDecoder().decode(plainText);
                    byte[] keyBytes = addPadding(key.getBytes(StandardCharsets.UTF_8), BLOCK_SIZE);
                    byte[] decryptedText = null;
					try {
						decryptedText = decryptt(encryptedBytes, keyBytes);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    String decryptedString = new String(removePadding(decryptedText), StandardCharsets.UTF_8);
                    outputTextArea.setText(decryptedString);
                }
            });

            // Set layout
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Input:"));
            panel.add(new JScrollPane(inputTextArea));
            panel.add(new JLabel("Key:"));
            panel.add(new JScrollPane(inputKeyArea));
            panel.add(new JLabel("Output:"));
            panel.add(new JScrollPane(outputTextArea));
            panel.add(encryptButton);
            panel.add(decryptButton);

            add(panel);
            setSize(500, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Center the window
            setVisible(true);
        }


    
    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        byte[][] state = new byte[4][4];
        byte[][] roundKey = keyExpansion(key);
        byte[] cipher = new byte[plaintext.length];
        int numBlocks = plaintext.length / BLOCK_SIZE ;
        
        for (int i = 0; i < numBlocks; i++) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    state[col][row] = plaintext[i * BLOCK_SIZE + row * 4 + col];
                }
            }
            
            aesCipher(state, roundKey);
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    cipher[i * BLOCK_SIZE + row * 4 + col] = state[col][row];
                }
            }
        }
        return cipher;
    }

    public static byte[] decrypt(byte[] ciphertext, byte[] key) {
        byte[][] state = new byte[4][4];
        byte[][] roundKey = keyExpansion(key);
        byte[] plain = new byte[ciphertext.length];
        int numBlocks = ciphertext.length / BLOCK_SIZE;

        for (int i = 0; i < numBlocks; i++) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    state[col][row] = ciphertext[i * BLOCK_SIZE + row * 4 + col];
                }
            }
            
            aesInvCipher(state, roundKey);
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    plain[i * BLOCK_SIZE + row * 4 + col] = state[col][row];
                }
            }
        }
        return plain;
    }

    public static byte[][] keyExpansion(byte[] key) {
        int nk = key.length / 4; // Số lượng từ khóa
        int nr = 10; // AES-128 có 10 vòng lặp
//        if (nk == 6) {
//            nr = 12; // AES-192 có 12 vòng lặp
//        } else if (nk == 8) {
//            nr = 14; // AES-256 có 14 vòng lặp
//        }
        byte[][] w = new byte[4 * (nr + 1)][4];

        // Copy khóa vào các từ của mảng w
        for (int i = 0; i < nk; i++) {
            w[i][0] = key[4 * i];
            w[i][1] = key[4 * i + 1];
            w[i][2] = key[4 * i + 2];
            w[i][3] = key[4 * i + 3];
        }
        
        

        // Generate các từ khóa mới bằng cách thực hiện phép XOR, Rijndael S-boxes và rót khóa
        for (int i = nk; i < 4 * (nr + 1); i++) {
            byte[] temp = Arrays.copyOf(w[i - 1], 4);
            if (i % nk == 0) {
                // Rót khóa
                temp = rotWord(temp);
                // Thực hiện Rijndael S-box
                for (int j = 0; j < 4; j++) {
                    temp[j] = (byte) (SBox[temp[j] & 0xff] & 0xff);
                }
                temp[0] ^= Rcon[i / nk];
            } else if (nk > 6 && i % nk == 4) {
                // Thực hiện Rijndael S-box cho mỗi byte của từ khóa
                for (int j = 0; j < 4; j++) {
                    temp[j] = (byte) (SBox[temp[j] & 0xff] & 0xff);
                }
            }
            // XOR từ khóa với temp và gán vào w[i]
            for (int j = 0; j < 4; j++) {
                w[i][j] = (byte) (w[i - nk][j] ^ temp[j]);
            }
        }     
        return w;
    }

    private static byte[] rotWord(byte[] word) {
        byte tmp = word[0];
        word[0] = word[1];
        word[1] = word[2];
        word[2] = word[3];
        word[3] = tmp;
        return word;
    }

    public static void subBytes(byte[][] state) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                state[row][col] = getSBoxValue(state[row][col] & 0xFF);
            }
        }
    }

    private static byte getSBoxValue(int num) {
        return (byte) SBox[num];
    }

    public static void shiftRows(byte[][] state) {
        for (int row = 1; row < 4; row++) {
            for (int col = 0; col < row; col++) {
                byte temp = state[row][0];
                for (int i = 0; i < 3; i++) {
                    state[row][i] = state[row][i + 1];
                }
                state[row][3] = temp;
            }
        }
    }

    public static void mixColumns(byte[][] state) {
        int[] temp = new int[4];
        for (int col = 0; col < 4; col++) {
            temp[0] = multiply(0x02, state[0][col]) ^ multiply(0x03, state[1][col]) ^ state[2][col] ^ state[3][col];
            temp[1] = state[0][col] ^ multiply(0x02, state[1][col]) ^ multiply(0x03, state[2][col]) ^ state[3][col];
            temp[2] = state[0][col] ^ state[1][col] ^ multiply(0x02, state[2][col]) ^ multiply(0x03, state[3][col]);
            temp[3] = multiply(0x03, state[0][col]) ^ state[1][col] ^ state[2][col] ^ multiply(0x02, state[3][col]);
            for (int row = 0; row < 4; row++) {
                state[row][col] = (byte) temp[row];
            }
        }
    }

    private static int multiply(int a, int b) {
        int result = 0;
        while (a != 0) {
            if ((a & 0x01) != 0) {
                result ^= b;
            }
            boolean highBitSet = (b & 0x80) != 0;
            b <<= 1;
            if (highBitSet) {
                b ^= 0x1B; // Xor with irreducible polynomial x^8 + x^4 + x^3 + x + 1
            }
            a >>= 1;
        }
        return result;
    }

    public static void addRoundKey(byte[][] state, byte[][] roundKey, int round) {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                state[row][col] ^= roundKey[round * 4 + col][row];
            }
        }
    }

    
    public static void aesCipher(byte[][] state, byte[][] roundKey) {
        addRoundKey(state, roundKey, 0);

        for (int round = 1; round < (int)((roundKey.length)/4 -1); round++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, roundKey, round);
        }

        subBytes(state);
        shiftRows(state);
        addRoundKey(state, roundKey, (int)((roundKey.length)/4 - 1));
    }

    public static void invShiftRows(byte[][] state) {
        for (int row = 1; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                byte temp = state[row][(col + row) % 4];
                state[row][(col + row) % 4] = state[row][col];
                state[row][col] = temp;
            }
        }
    }

    public static void invSubBytes(byte[][] state) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                state[row][col] = getInvSBoxValue(state[row][col] & 0xFF);
            }
        }
    }

    private static byte getInvSBoxValue(int num) {
        return (byte) InvSBox[num];
    }

    public static void invMixColumns(byte[][] state) {
        int[] temp = new int[4];
        for (int col = 0; col < 4; col++) {
            temp[0] = multiply(0x0e, state[0][col]) ^ multiply(0x0b, state[1][col]) ^ multiply(0x0d, state[2][col]) ^ multiply(0x09, state[3][col]);
            temp[1] = multiply(0x09, state[0][col]) ^ multiply(0x0e, state[1][col]) ^ multiply(0x0b, state[2][col]) ^ multiply(0x0d, state[3][col]);
            temp[2] = multiply(0x0d, state[0][col]) ^ multiply(0x09, state[1][col]) ^ multiply(0x0e, state[2][col]) ^ multiply(0x0b, state[3][col]);
            temp[3] = multiply(0x0b, state[0][col]) ^ multiply(0x0d, state[1][col]) ^ multiply(0x09, state[2][col]) ^ multiply(0x0e, state[3][col]);
            for (int row = 0; row < 4; row++) {
                state[row][col] = (byte) temp[row];
            }
        }
    }

    
    public static void aesInvCipher(byte[][] state, byte[][] roundKey) {
        addRoundKey(state, roundKey, (roundKey.length)/4 - 1);

        for (int round = (roundKey.length)/4 - 2; round > 0; round--) {
            invShiftRows(state);
            invSubBytes(state);
            addRoundKey(state, roundKey, round);
            invMixColumns(state);
        }

        invShiftRows(state);
        invSubBytes(state);
        addRoundKey(state, roundKey, 0);
    }
    
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
           new AESTestUI();

            

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Các phương thức cần triển khai: subBytes, shiftRows, mixColumns, addRoundKey, invSubBytes, invShiftRows, invMixColumns
//    xBPxeKDP3fsG7l86FT9lvQhbHbbIxwe+2DZekib+is=
//    Encrypted (Bytes): [-60, 65, -110, -9, 42, 116, 74, -16, 77, -44, -116, 71, -42, -76, -110, 113, -76, 111, 104, 20, -11, 37, 58, -112, -101, 52, -54, 82, -35, 13, 24, -101]

    
    
    
    	
    
    public static byte[] encryptt(byte[] originalBytes, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(originalBytes);
    }

    public static byte[] decryptt(byte[] cipherText, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return decryptedBytes;
    }
}
