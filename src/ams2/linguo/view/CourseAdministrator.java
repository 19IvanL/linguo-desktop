package ams2.linguo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ams2.linguo.controller.CourseListCellRenderer;
import ams2.linguo.controller.CourseListModel;
import ams2.linguo.controller.Language;
import ams2.linguo.controller.LanguageListCellRenderer;
import ams2.linguo.controller.LanguageParser;
import ams2.linguo.controller.LessonCategoryListCellRenderer;
import ams2.linguo.controller.LessonCategoryListModel;
import ams2.linguo.controller.LessonListCellRenderer;
import ams2.linguo.controller.LessonListModel;
import ams2.linguo.interfaces.ICourseQueries;
import ams2.linguo.interfaces.ILessonCategoryQueries;
import ams2.linguo.interfaces.ILessonQueries;
import ams2.linguo.model.Course;
import ams2.linguo.model.Lesson;
import ams2.linguo.model.LessonCategory;
import ams2.linguo.queries.CourseQueries;
import ams2.linguo.queries.LessonCategoryQueries;
import ams2.linguo.queries.LessonQueries;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class CourseAdministrator extends JFrame {

	private static final long serialVersionUID = -135188154738318638L;

	JComboBox<Language> comboBox1;
	JComboBox<Language> comboBox2;
	
	private JList<Course> courseList;
	private JList<LessonCategory> lessonCategoryList;
	private JList<Lesson> lessonList;

	private JButton btnCrearCurso;
	private JButton btnAadirNivel;
	private JButton btnAadirCategoria;
	private JButton btnAplicarFiltro;
	private JButton btnAadirPregunta;
	private JButton btnVisualizarPreguntas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseAdministrator frame = new CourseAdministrator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CourseAdministrator() {
		setTitle("Administrador de cursos");
		setIconImage(new ImageIcon(getClass().getResource("/linguo-icon.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 500);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		setVisible(true);

		JPanel headerPanel = new JPanel();
		TitledBorder title1 = BorderFactory.createTitledBorder("Cursos existentes (filtrar por origen y/o destino)");
		headerPanel.setBorder(title1);
		contentPane.add(headerPanel);
		headerPanel.setLayout(new GridLayout(1, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		headerPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblIdiomaDeOrigen = new JLabel("Idioma de origen");
		panel_1.add(lblIdiomaDeOrigen);

		LanguageParser languageParser = new LanguageParser();
		List<Language> languageList = languageParser.getLanguageList();
		Language[] languageArray = languageList.toArray(new Language[languageList.size()]);

		comboBox1 = new JComboBox<Language>(languageArray);
		comboBox1.insertItemAt(new Language(" ", ""), 0);
		comboBox1.setSelectedIndex(0);
		comboBox1.setRenderer(new LanguageListCellRenderer());
		comboBox1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED)
					btnCrearCurso.setEnabled(false);
			}
		});
		panel_1.add(comboBox1);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		headerPanel.add(panel_2);

		JLabel lblIdiomaDeDestino = new JLabel("Idioma de destino");
		panel_2.add(lblIdiomaDeDestino);

		comboBox2 = new JComboBox<Language>(languageArray);
		comboBox2.insertItemAt(new Language(" ", ""), 0);
		comboBox2.setSelectedIndex(0);
		comboBox2.setRenderer(new LanguageListCellRenderer());
		comboBox2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED)
					btnCrearCurso.setEnabled(false);
			}
		});
		panel_2.add(comboBox2);

		JPanel panel_3 = new JPanel();
		headerPanel.add(panel_3);

		btnAplicarFiltro = new JButton("Aplicar filtro");
		btnAplicarFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(((Language)comboBox1.getSelectedItem()).getName().isBlank() && ((Language)comboBox2.getSelectedItem()).getName().isBlank())) {
					courseList.setModel(CourseListModel.modelList(((Language)comboBox1.getSelectedItem()).getIsoCode(), ((Language)comboBox2.getSelectedItem()).getIsoCode()));
					if (courseList.getModel().getSize() == 0)
						btnCrearCurso.setEnabled(true);
					else
						btnCrearCurso.setEnabled(false);
				} else {
					DefaultListModel<Course> model = new DefaultListModel<Course>();
					courseList.setModel(model);
					btnCrearCurso.setEnabled(false);
				}
			}
		});
		panel_3.add(btnAplicarFiltro);

		btnCrearCurso = new JButton("Crear curso");
		btnCrearCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ICourseQueries courseQueries = new CourseQueries();
				String baseLanguage = ((Language)comboBox1.getSelectedItem()).getIsoCode();
				String targetLanguage = ((Language)comboBox2.getSelectedItem()).getIsoCode();
				Course course = courseQueries.insertCourseByBaseAndTargetLanguages(baseLanguage, targetLanguage);
				if (course != null) {
					// Course list is cleared and inserted course is shown
					DefaultListModel<Course> model = new DefaultListModel<Course>();
					model.addElement(course);
					courseList.setModel(model);
					btnCrearCurso.setEnabled(false);
				} else {
					// TODO Warn user about error
				}
				btnCrearCurso.setEnabled(false);
			}
		});
		btnCrearCurso.setEnabled(false);
		panel_3.add(btnCrearCurso);

		JPanel middlePanel = new JPanel();
		contentPane.add(middlePanel);
		middlePanel.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_4 = new JPanel();
		middlePanel.add(panel_4);
		TitledBorder title2 = BorderFactory.createTitledBorder("Cursos");
		panel_4.setBorder(title2);
		panel_4.setLayout(new BorderLayout(0, 0));

		courseList = new JList<Course>();
		courseList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnAadirCategoria.setEnabled(true);
				if (!arg0.getValueIsAdjusting()) {
					if (courseList.getSelectedValue() != null) {
						lessonCategoryList.setModel(LessonCategoryListModel.modelList(courseList.getSelectedValue().getId()));
					} else {
						DefaultListModel<LessonCategory> model = new DefaultListModel<LessonCategory>();
						model.clear();
						lessonCategoryList.setModel(model);
					}
				}
			}
		});
		courseList.setCellRenderer(new CourseListCellRenderer());
		panel_4.add(courseList, BorderLayout.CENTER);

		JPanel panel_5 = new JPanel();
		middlePanel.add(panel_5);
		TitledBorder title3 = BorderFactory.createTitledBorder("Categorias del curso seleccionado");
		panel_5.setBorder(title3);
		panel_5.setLayout(new BorderLayout(0, 0));

		lessonCategoryList = new JList<LessonCategory>();
		lessonCategoryList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnAadirNivel.setEnabled(true);
				if (!arg0.getValueIsAdjusting()) {
					if (lessonCategoryList.getSelectedValue() != null) {
						lessonList.setModel(LessonListModel.modelList(lessonCategoryList.getSelectedValue().getId()));
					} else {
						DefaultListModel<Lesson> model = new DefaultListModel<Lesson>();
						model.clear();
						lessonList.setModel(model);
					}
				}
			}
		});
		lessonCategoryList.setCellRenderer(new LessonCategoryListCellRenderer());
		panel_5.add(lessonCategoryList, BorderLayout.CENTER);

		btnAadirCategoria = new JButton("A\u00f1adir categoria");
		panel_5.add(btnAadirCategoria, BorderLayout.SOUTH);
		btnAadirCategoria.setEnabled(false);
		
		btnAadirCategoria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ILessonCategoryQueries lessonCategoryQueries = new LessonCategoryQueries();
				String selection = JOptionPane.showInputDialog(CourseAdministrator.this, "Nombre de la nueva categoria", JOptionPane.QUESTION_MESSAGE);
				if(selection != null) {
					LessonCategory lessonCategory = lessonCategoryQueries.insertLessonCategoryByTitle(selection, courseList.getSelectedValue());
					if (lessonCategory != null) {
						// Course list is cleared and inserted course is shown
						DefaultListModel<LessonCategory> model = LessonCategoryListModel.modelList(courseList.getSelectedValue().getId());
						lessonCategoryList.setModel(model);
					} else {
						// TODO Warn user about error
					}
				}
				
			}
			
		});

		JPanel panel_6 = new JPanel();
		middlePanel.add(panel_6);
		TitledBorder title4 = BorderFactory.createTitledBorder("Niveles de la categoria seleccionada");
		panel_6.setBorder(title4);
		panel_6.setLayout(new BorderLayout(0, 0));

		lessonList = new JList<Lesson>();
		
		lessonList.setCellRenderer(new LessonListCellRenderer());
		panel_6.add(lessonList, BorderLayout.CENTER);

		btnAadirNivel = new JButton("A\u00f1adir nivel");
		panel_6.add(btnAadirNivel, BorderLayout.SOUTH);
		btnAadirNivel.setEnabled(false);
		
		btnAadirNivel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ILessonQueries lessonQueries = new LessonQueries();
				String selection = JOptionPane.showInputDialog(CourseAdministrator.this, "Nombre de la nueva lecci\u00f3n", JOptionPane.QUESTION_MESSAGE);
				if(selection != null) {
					Lesson lesson = lessonQueries.insertLesonByNameAndLessonCategory(selection, lessonCategoryList.getSelectedValue());
					if (lesson != null) {
						DefaultListModel<Lesson> model = LessonListModel.modelList(lessonCategoryList.getSelectedValue().getId());
						lessonList.setModel(model);
					} else {
						// TODO Warn user about error
					}
				}
				
			}
			
		});
		
		

		JPanel lowerPanel = new JPanel();
		contentPane.add(lowerPanel);
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAadirPregunta = new JButton("A\u00d1ADIR PREGUNTA");
		lowerPanel.add(btnAadirPregunta);

		btnVisualizarPreguntas = new JButton("VISUALIZAR PREGUNTAS");
		lowerPanel.add(btnVisualizarPreguntas);
	}

}
