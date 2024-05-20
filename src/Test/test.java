package Test;

import javax.swing.SwingUtilities;

import entity.NhanVien;
import gui.Gui_Chinh;
import gui.Gui_DangNhap;
import gui.Gui_TaiKhoan;

public class test {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			private NhanVien nv;

			public void run() {
		        new Gui_DangNhap();   
//		        new Gui_Chinh(nv);
		    }
		});	
	}
}
