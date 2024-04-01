package Test;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
 
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
public class Test1 extends DefaultCellEditor {
 
	protected JButton button;
	private DeleteButtonListener bListener = new DeleteButtonListener();
 
	/**
	 * Constructeur avec une checkBox
	 * @param checkBox
	 * @param count
	 */
	@SuppressWarnings("deprecation")
	public Test1(JCheckBox checkBox) {
		//Par défaut, ce type d'objet travaille avec un JCheckBox
		super(checkBox);
	    //On crée à nouveau notre bouton
		button = new JButton();
	    button.setOpaque(true);
	    //On lui attribue un listener
	    button.addActionListener(bListener);
 
	}
 
	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		    //On précise le numéro de ligne à notre listener
		    bListener.setRow(row);
		    //Idem pour le numéro de colonne
		      //On passe aussi le tableau en paramètre pour des actions potentielles
		    bListener.setTable(table);
 
		    //On réaffecte le libelle au bouton
		    button.setText( (value == null) ? "" : value.toString() );
		    //On renvoie le bouton
		    return button;
		  }
 
	  class DeleteButtonListener implements ActionListener {
 
	        private int row;
	        private JTable table;
 
	        public void setRow(int row){this.row = row;}
	        public void setTable(JTable table){this.table = table;}
 
	        public void actionPerformed(ActionEvent event) {
	        	if(table.getRowCount() > 0){
	            //On affiche un message
	            System.out.println("coucou du bouton: "+ ((JButton)event.getSource()).getText() );
	            ((DefaultTableModel)table.getModel()).removeRow(this.row);
                    ((DefaultTableModel)table.getModel()).fireTableDataChanged();
	          	         }
	      }
	   }        
	}