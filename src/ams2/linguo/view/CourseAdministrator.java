package ams2.linguo.view;

import java.awt.BorderLayout;
import java.awt.Component;
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
import ams2.linguo.controller.LessonListCellRenderer;
import ams2.linguo.controller.LessonListModel;
import ams2.linguo.model.Course;
import ams2.linguo.model.Lesson;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;
import javax.swing.JList;

public class CourseAdministrator extends JFrame {

	private static final long serialVersionUID = -135188154738318638L;

	private JList<Course> courseList;
	private JList<Lesson> lessonList;

	private JButton btnCrearCurso;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 500);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));

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

		JComboBox<Language> comboBox1 = new JComboBox<Language>(languageArray);
		comboBox1.insertItemAt(new Language(" ", ""), 0);
		comboBox1.setSelectedIndex(0);
		comboBox1.setRenderer(new LanguageListCellRenderer());
		panel_1.add(comboBox1);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		headerPanel.add(panel_2);

		JLabel lblIdiomaDeDestino = new JLabel("Idioma de destino");
		panel_2.add(lblIdiomaDeDestino);

		JComboBox<Language> comboBox2 = new JComboBox<Language>(languageArray);
		comboBox2.insertItemAt(new Language(" ", ""), 0);
		comboBox2.setSelectedIndex(0);
		comboBox2.setRenderer(new LanguageListCellRenderer());
		panel_2.add(comboBox2);

		JPanel panel_3 = new JPanel();
		headerPanel.add(panel_3);

		JButton btnAplicarFiltro = new JButton("Aplicar filtro");
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
					DefaultListModel model = new DefaultListModel();
					model.clear();
					courseList.setModel(model);
					btnCrearCurso.setEnabled(false);
				}
			}
		});
		panel_3.add(btnAplicarFiltro);

		btnCrearCurso = new JButton("Crear curso");
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
				if (!arg0.getValueIsAdjusting()) {
					if (courseList.getSelectedValue() != null) {
						lessonList.setModel(LessonListModel.modelList(courseList.getSelectedValue().getId()));
					} else {
						DefaultListModel model = new DefaultListModel();
						model.clear();
						lessonList.setModel(model);
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

		lessonList = new JList<Lesson>();
		lessonList.setCellRenderer(new LessonListCellRenderer());
		panel_5.add(lessonList, BorderLayout.CENTER);

		JButton btnAadirCategoria = new JButton("Añadir categoria");
		panel_5.add(btnAadirCategoria, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		middlePanel.add(panel_6);
		TitledBorder title4 = BorderFactory.createTitledBorder("Niveles de la categoria seleccionada");
		panel_6.setBorder(title4);
		panel_6.setLayout(new BorderLayout(0, 0));

		JList<?> list_2 = new JList<>();
		panel_6.add(list_2, BorderLayout.CENTER);

		JButton btnAadirNivel = new JButton("Añadir nivel");
		panel_6.add(btnAadirNivel, BorderLayout.SOUTH);

		JPanel lowerPanel = new JPanel();
		contentPane.add(lowerPanel);
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAadirPregunta = new JButton("AÑADIR PREGUNTA");
		lowerPanel.add(btnAadirPregunta);

		JButton btnVisualizarPreguntas = new JButton("VISUALIZAR PREGUNTAS");
		lowerPanel.add(btnVisualizarPreguntas);
	}

}
