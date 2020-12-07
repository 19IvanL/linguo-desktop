package ams2.linguo.controller;

import java.util.List;

import javax.swing.DefaultListModel;

import ams2.linguo.interfaces.ILessonQueries;
import ams2.linguo.model.Lesson;
import ams2.linguo.queries.LessonQueries;

public class LessonListModel {

	public static DefaultListModel<Lesson> modelList(Long courseId) {
		ILessonQueries lessonQueries = new LessonQueries();
		List<Lesson> lessons = lessonQueries.getLessonsByCourseId(courseId);
		DefaultListModel<Lesson> model = new DefaultListModel<Lesson>();
		for (Lesson lesson : lessons)
			model.addElement(lesson);
		return model;
	}
	
}
