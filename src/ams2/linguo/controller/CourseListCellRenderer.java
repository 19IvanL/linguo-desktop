package ams2.linguo.controller;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import ams2.linguo.model.Course;

public class CourseListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -506483475278131867L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		if (value instanceof Course) {
			value = "[ID " + ((Course)value).getId() + "] " + ((Course)value).getBaseLanguage() + " -> " + ((Course)value).getTargetLanguage();
		}
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return this;
	}
	
}
