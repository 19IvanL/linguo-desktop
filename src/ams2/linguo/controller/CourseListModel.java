package ams2.linguo.controller;

import java.util.List;

import javax.swing.DefaultListModel;

import ams2.linguo.interfaces.ICourseQueries;
import ams2.linguo.model.Course;
import ams2.linguo.queries.CourseQueries;

public class CourseListModel {

	public static DefaultListModel<Course> modelList(String baseLanguage, String targetLanguage) {
		ICourseQueries courseQueries = new CourseQueries();
		List<Course> courses = courseQueries.getCoursesByBaseAndTargetLanguage(baseLanguage, targetLanguage);
		DefaultListModel<Course> model = new DefaultListModel<Course>();
		for (Course course : courses)
			model.addElement(course);
		return model;
	}

}
