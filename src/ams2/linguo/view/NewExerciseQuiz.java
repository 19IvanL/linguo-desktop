package ams2.linguo.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ams2.linguo.interfaces.IExerciseOptionsQueries;
import ams2.linguo.interfaces.IExerciseQueries;
import ams2.linguo.model.Exercise;
import ams2.linguo.model.ExerciseOptions;
import ams2.linguo.model.ExerciseType;
import ams2.linguo.model.Lesson;
import ams2.linguo.queries.ExerciseOptionsQueries;
import ams2.linguo.queries.ExerciseQueries;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class NewExerciseQuiz extends JFrame {

	private static final long serialVersionUID = 4749810092912651039L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the frame.
	 */
	public NewExerciseQuiz(Lesson lesson, ExerciseType exerciseType) {
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		centerPanel.add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblFraseATraducir = new JLabel("Frase a traducir:");
		panel.add(lblFraseATraducir);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		centerPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblOpcinCorrecta = new JLabel("Opción correcta:");
		panel_1.add(lblOpcinCorrecta);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		centerPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblOpcinIncorrecta = new JLabel("Opción incorrecta 1:");
		panel_2.add(lblOpcinIncorrecta);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		centerPanel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblOpcinIncorrecta_1 = new JLabel("Opción incorrecta 2:");
		panel_3.add(lblOpcinIncorrecta_1);
		
		textField_3 = new JTextField();
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rootPaneCheckingEnabled) {
					
				}
				// Insert the exercise
				IExerciseQueries exerciseQueries = new ExerciseQueries();
				Exercise exercise = exerciseQueries.insertExercise(lesson, exerciseType, textField.getText());
				
				// Insert the exercise options
				IExerciseOptionsQueries exerciseOptionsQueries = new ExerciseOptionsQueries();
				ExerciseOptions[] exerciseOptions = exerciseOptionsQueries.insertExerciseOptions(exercise, textField_1.getText(), textField_2.getText(), textField_3.getText());
				
				dispose();
			}
		});
		contentPane.add(btnGuardar, BorderLayout.SOUTH);
	}

}
