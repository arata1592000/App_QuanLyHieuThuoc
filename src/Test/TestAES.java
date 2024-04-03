package Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestAES {

    private static final int BLOCK_SIZE = 16;

    // S-box substitution table
    private static final int[] SBox = {
            0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
            0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
            0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
            0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
            0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
            0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
            0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
            0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
            0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
            0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
            0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
            0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
            0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
            0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
            0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
            0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
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

    private static final int[] InvSBox = {
            0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
            0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
            0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
            0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
            0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
            0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
            0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
            0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
            0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
            0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
            0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
            0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
            0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
            0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
            0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
            0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d
    };

    
    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        byte[][] state = new byte[4][4];
        byte[][] roundKey = keyExpansion(key);
        byte[] cipher = new byte[plaintext.length];
        int numBlocks = plaintext.length / BLOCK_SIZE;

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
        int nr = nk + 6; // Số vòng lặp (rounds) tùy thuộc vào kích thước khóa

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

        for (int round = 1; round < roundKey.length - 1; round++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, roundKey, round);
        }

        subBytes(state);
        shiftRows(state);
        addRoundKey(state, roundKey, roundKey.length - 1);
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
        addRoundKey(state, roundKey, roundKey.length - 1);

        for (int round = roundKey.length - 2; round > 0; round--) {
            invShiftRows(state);
            invSubBytes(state);
            addRoundKey(state, roundKey, round);
            invMixColumns(state);
        }

        invShiftRows(state);
        invSubBytes(state);
        addRoundKey(state, roundKey, 0);
    }
    public static void main(String[] args) {
        try {
            String originalText = "a";
            System.out.println("Original: " + originalText);

            String key = "ThisIsASecretKey"; // 16-byte key for AES-128

            byte[] originalBytes = originalText.getBytes(StandardCharsets.UTF_8);
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

            byte[] encryptedText = encrypt(originalBytes, keyBytes);
            System.out.println("Encrypted: " + Arrays.toString(encryptedText));

            byte[] decryptedText = decrypt(encryptedText, keyBytes);
            String decryptedString = new String(decryptedText, StandardCharsets.UTF_8);
            System.out.println("Decrypted: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Các phương thức cần triển khai: subBytes, shiftRows, mixColumns, addRoundKey, invSubBytes, invShiftRows, invMixColumns
}
