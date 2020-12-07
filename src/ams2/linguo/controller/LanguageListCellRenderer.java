package ams2.linguo.controller;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class LanguageListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 7188617101175375690L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		if (value instanceof Language) {
			value = ((Language)value).getName();
		}
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return this;
	}

}
