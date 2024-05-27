package main;

import javax.swing.SwingUtilities;

import gui.Gui_DangNhap;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
		        new Gui_DangNhap();   
		    }
		});	
	}
}
