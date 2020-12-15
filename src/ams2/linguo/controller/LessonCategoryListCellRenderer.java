package ams2.linguo.controller;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import ams2.linguo.model.LessonCategory;

public class LessonCategoryListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 4135853797141357698L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		if (value instanceof LessonCategory)
			value = "[ID " + ((LessonCategory)value).getId() + "] " + ((LessonCategory)value).getTitle();
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return this;
	}
	
}
