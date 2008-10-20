package com.db4o.objectManager.v2.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class EncryptionProviderComboBoxModel extends AbstractListModel
		implements ComboBoxModel {

	private List<Class> dataSource;
	Class current;

	public EncryptionProviderComboBoxModel(List<Class> dataSource) {
		this.dataSource = dataSource;
	}

	public Object getElementAt(int index) {
		return getDataSource().get(index);
	}

	public int getSize() {
		return getDataSource().size();
	}

	public List<Class> getDataSource() {
		if (dataSource == null)
			dataSource = new ArrayList<Class>();
		return dataSource;
	}

	public Object getSelectedItem() {
		return current;
	}

	public void setSelectedItem(Object selectedItem) {
		current = (Class) selectedItem;
		fireContentsChanged(selectedItem, -1, -1);
	}

}
