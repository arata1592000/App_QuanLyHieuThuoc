package Test;

import javax.swing.SwingUtilities;

import gui.Gui_Chinh;

public class test {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        // Thực hiện các hoạt động giao diện người dùng ở đây
		        // Ví dụ: tạo và hiển thị một JFrame mới
		        new Gui_Chinh();
		    }
		});	}
}
