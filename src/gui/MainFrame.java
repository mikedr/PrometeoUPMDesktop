package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;
import model.Pasteurization;

public class MainFrame extends JFrame {
	
	private ChartPanel chartPanel;
	private ResultsContainer resultsContainer;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private JMenuBar menuBar;
	
	public MainFrame () {
		super("Prometeo UP Meter");
		setup();
		instaciateComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(tablePanel,BorderLayout.WEST);
		add(chartPanel,BorderLayout.CENTER);
		add(resultsContainer,BorderLayout.EAST);
		setJMenuBar(createMenuBar());
	}
	
	private void addExportarMenuItem() {
		JMenu exportarMenuItem = new JMenu("Exportar resultados");
		JMenuItem medicionesXlsMenuItem = new JMenuItem("Mediciones");
		JMenuItem reporteMenuItem = new JMenuItem("Reporte");
		reporteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				i=0;
			}
		});
		exportarMenuItem.add(medicionesXlsMenuItem);
		exportarMenuItem.add(reporteMenuItem);
		menuBar.getMenu(0).add(exportarMenuItem);
	}

	private JMenuBar createMenuBar() {
		menuBar = new JMenuBar();
		
		JMenu medicionesMenu = new JMenu("Mediciones");
		JMenu dispositivoMenu = new JMenu("Dispositivo");
		JMenu aplicacionMenu = new JMenu("Prometeo UPM");
		
		JMenuItem importarMenuItem = new JMenuItem("Importar mediciones");

		JMenuItem aboutMenuItem = new JMenuItem("Acerca de");
		JMenuItem salirMenuItem = new JMenuItem("Salir");

		
		medicionesMenu.add(importarMenuItem);

		aplicacionMenu.add(aboutMenuItem);
		aplicacionMenu.addSeparator();
		aplicacionMenu.add(salirMenuItem);

		JMenuItem gestionarMenuItem = new JMenuItem("Gestionar");
		dispositivoMenu.add(gestionarMenuItem);

		salirMenuItem.setMnemonic(KeyEvent.VK_I);
		salirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		importarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
						resultsContainer.enableCalculadoraUP();
						resultsContainer.setMeasurementsParameters(controller.getPasteurization());
						resultsContainer.setController(controller);
						chartPanel.removeAll();
						chartPanel.revalidate();
						chartPanel.repaint();
						chartPanel.createChartTemp(controller.getMeasurements());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Couldnt not load data from file","Error",JOptionPane.ERROR_MESSAGE);
					}					
				}
			}
		});
		
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, 
						"�Desea cerrar la aplicaci�n?", 
						"Confirmar", JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_OPTION) {
					System.exit(0);		
				}
			}
		});
		
		menuBar.add(medicionesMenu);
		menuBar.add(dispositivoMenu);
		menuBar.add(aplicacionMenu);
		
		return menuBar;
	}

	private void instaciateComponents() {
		chartPanel = new ChartPanel();
		resultsContainer = new ResultsContainer();
		resultsContainer.setTempCorteListener(new PasteurizationListener(){
			public void pasteurizationEmitted(Pasteurization pasteurization) {
				chartPanel.removeAll();
				chartPanel.revalidate();
				chartPanel.repaint();
				chartPanel.createChartTempAndUP(pasteurization);
			}
			
		});
		resultsContainer.setAgregadorDeMenu(new AgregadorDeMenu() {
			public void agregarMenuExportar() {
				addExportarMenuItem();
			}
			
		});
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new MedicionFileFilter());
		controller = new Controller();
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getMeasurements());
	}

	private void setup() {
		setLayout(new BorderLayout());
		setSize(800, 600);
		setMinimumSize(new Dimension(1280,720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
