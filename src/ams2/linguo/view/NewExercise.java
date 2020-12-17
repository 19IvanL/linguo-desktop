package ams2.linguo.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import ams2.linguo.interfaces.IExerciseTypeQueries;
import ams2.linguo.model.ExerciseType;
import ams2.linguo.model.Lesson;
import ams2.linguo.queries.ExerciseTypeQueries;

import javax.swing.SwingConstants;

public class NewExercise extends JFrame {

	private static final long serialVersionUID = 8046323785530264118L;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public NewExercise(String baseLanguage, String targetLanguage, String lessonCategoryTitle, Lesson lesson) {
		setBounds(0, 0, 885, 437);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		final double dividerProportions = 0.3;
		splitPane.setDividerLocation(dividerProportions);
		splitPane.setResizeWeight(dividerProportions);
		splitPane.setEnabled(false);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		Font font = new Font("Serif", Font.BOLD, 20);
		
		JPanel panel = new JPanel();
		leftPanel.add(panel);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		
		JLabel lblBaselanguage = new JLabel("Idioma origen: " + baseLanguage);
		lblBaselanguage.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBaselanguage);
		lblBaselanguage.setFont(font);
		
		JPanel panel_1 = new JPanel();
		leftPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 1, 0, 0));
		
		JLabel lblTargetlanguage = new JLabel("Idioma destino: " + targetLanguage);
		lblTargetlanguage.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblTargetlanguage);
		lblTargetlanguage.setFont(font);
		
		JPanel panel_2 = new JPanel();
		leftPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 1, 0, 0));
		
		JLabel lblLessoncategory = new JLabel("Categoria: " + lessonCategoryTitle);
		lblLessoncategory.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblLessoncategory);
		lblLessoncategory.setFont(font);
		
		JPanel panel_3 = new JPanel();
		leftPanel.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 1, 0, 0));
		
		JLabel lblLesson = new JLabel("Lección: " + lesson.getName());
		lblLesson.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblLesson);
		lblLesson.setFont(font);
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JButton btnTraduccinAbierta = new JButton("Traducción abierta");
		rightPanel.add(btnTraduccinAbierta);
		
		JButton btnTraduccinDeOrdenacin = new JButton("Traducción de ordenación");
		rightPanel.add(btnTraduccinDeOrdenacin);
		
		JButton btnTraduccinAbiertaen = new JButton("Traducción abierta (en listening)");
		rightPanel.add(btnTraduccinAbiertaen);
		
		JButton btnTraduccionDeOrdenacin = new JButton("Traduccion de ordenación (en listening)");
		rightPanel.add(btnTraduccionDeOrdenacin);
		
		JButton btnEmparejamientoDePalabras = new JButton("Emparejamiento de palabras");
		rightPanel.add(btnEmparejamientoDePalabras);
		
		JButton btnRellenarLosHuecos = new JButton("Rellenar los huecos");
		rightPanel.add(btnRellenarLosHuecos);
		
		JButton btnTipoTest = new JButton("Tipo test");
		btnTipoTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IExerciseTypeQueries exerciseTypeQueries = new ExerciseTypeQueries();
				ExerciseType exerciseType = exerciseTypeQueries.getExerciseTypeById(1);
				NewExerciseQuiz newExerciseQuiz = new NewExerciseQuiz(lesson, exerciseType);
				newExerciseQuiz.setVisible(true);
			}
		});
		rightPanel.add(btnTipoTest);
	}

}
