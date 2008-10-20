package com.db4o.objectManager.v2.configuration;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class EncryptionProviderComboBoxRenderer extends JLabel implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		 try {
	            if (value != null) {                
	                if(isSelected)
	                {
	                    this.setBackground(list.getSelectionBackground());
	                    this.setForeground(list.getSelectionForeground());
	                }
	                else
	                {
	                    this.setBackground(list.getBackground());
	                    this.setForeground(list.getSelectionForeground());
	                }                
	                this.setText(((Class)value).getCanonicalName());
	            }
	        } 
	        catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return this;
	}

	
}
