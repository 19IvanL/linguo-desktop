package ams2.linguo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ams2.linguo.controller.LanguageParser;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import javax.swing.JList;

public class CourseAdministrator extends JFrame {

	private static final long serialVersionUID = -135188154738318638L;
	
	private JPanel contentPane;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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
		List<String> languageList = languageParser.getLanguageList();
		String[] languageArray = languageList.toArray(new String[languageList.size()]);
		
		JComboBox<String> comboBox = new JComboBox<String>(languageArray);
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		headerPanel.add(panel_2);
		
		JLabel lblIdiomaDeDestino = new JLabel("Idioma de destino");
		panel_2.add(lblIdiomaDeDestino);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>(languageArray);
		panel_2.add(comboBox_1);
		
		JPanel panel_3 = new JPanel();
		headerPanel.add(panel_3);
		
		JButton btnAplicarFiltro = new JButton("Aplicar filtro");
		panel_3.add(btnAplicarFiltro);
		
		JButton btnCrearCurso = new JButton("Crear curso");
		panel_3.add(btnCrearCurso);
		
		JPanel middlePanel = new JPanel();
		contentPane.add(middlePanel);
		middlePanel.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel_4 = new JPanel();
		middlePanel.add(panel_4);
		TitledBorder title2 = BorderFactory.createTitledBorder("Cursos");
		panel_4.setBorder(title2);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JList<?> list = new JList<Object>();
		panel_4.add(list, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		middlePanel.add(panel_5);
		TitledBorder title3 = BorderFactory.createTitledBorder("Categorias del curso seleccionado");
		panel_5.setBorder(title3);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JList<?> list_1 = new JList<Object>();
		panel_5.add(list_1, BorderLayout.CENTER);
		
		JButton btnAadirCategoria = new JButton("Añadir categoria");
		panel_5.add(btnAadirCategoria, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		middlePanel.add(panel_6);
		TitledBorder title4 = BorderFactory.createTitledBorder("Niveles de la categoria seleccionada");
		panel_6.setBorder(title4);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JList<?> list_2 = new JList<Object>();
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
