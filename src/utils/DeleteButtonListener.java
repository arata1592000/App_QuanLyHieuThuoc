package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class DeleteButtonListener implements ActionListener {
 
	        private int row;
	        private JTable table;
 
	        public void setRow(int row){
	        	this.row = row;
	        }
	        public void setTable(JTable table){
	        	this.table = table;
	        }
 
	        public void actionPerformed(ActionEvent event) {
	        	if(table.getRowCount() > 0){
	            ((DefaultTableModel)table.getModel()).removeRow(this.row);
            	((DefaultTableModel)table.getModel()).fireTableDataChanged();
        		table.setRowHeight(table.getRowCount()-1, 1);
	        	}
	      }
	   }        