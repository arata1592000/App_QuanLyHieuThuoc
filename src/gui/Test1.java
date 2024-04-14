package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import utils.AES;

import java.util.Base64;
import java.util.Scanner;

public class Test1 {

    static Cipher cipher;

    public static void main(String[] args) {
    	try {
			System.out.println((new AES()).encrypt("1111", "NV241001"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}