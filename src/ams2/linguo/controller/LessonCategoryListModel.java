package ams2.linguo.controller;

import java.util.List;

import javax.swing.DefaultListModel;

import ams2.linguo.interfaces.ILessonCategoryQueries;
import ams2.linguo.model.LessonCategory;
import ams2.linguo.queries.LessonCategoryQueries;

public class LessonCategoryListModel {

	public static DefaultListModel<LessonCategory> modelList(Long courseId) {
		ILessonCategoryQueries lessonCategoryQueries = new LessonCategoryQueries();
		List<LessonCategory> lessonCategories = lessonCategoryQueries.getLessonsCategoryByCourseId(courseId);
		DefaultListModel<LessonCategory> model = new DefaultListModel<LessonCategory>();
		for (LessonCategory lessonCategory : lessonCategories)
			model.addElement(lessonCategory);
		return model;
	}
	
}
