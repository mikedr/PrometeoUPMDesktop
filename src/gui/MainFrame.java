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

public class MainFrame extends JFrame implements ActionListener{
	
	private ChartPanel chartPanel;
	private ResultsContainer resultsContainer;
	private VisibleManager visibleManager;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	
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

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu medicionesMenu = new JMenu("Mediciones");
		JMenu dispositivoMenu = new JMenu("Dispositivo");
		JMenu aplicacionMenu = new JMenu("Prometeo UPM");
		
		JMenuItem importarMenuItem = new JMenuItem("Importar mediciones");
		JMenu exportarMenuItem = new JMenu("Exportar resultados");
		JMenuItem medicionesXlsMenuItem = new JMenuItem("Mediciones");
		JMenuItem reporteMenuItem = new JMenuItem("Reporte");
		JMenuItem aboutMenuItem = new JMenuItem("Acerca de");
		JMenuItem salirMenuItem = new JMenuItem("Salir");

		exportarMenuItem.add(medicionesXlsMenuItem);
		exportarMenuItem.add(reporteMenuItem);
		
		medicionesMenu.add(importarMenuItem);
		medicionesMenu.add(exportarMenuItem);
		
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
						"¿Desea cerrar la aplicación?", 
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
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new MedicionFileFilter());
		this.setVisibleManager(new VisibleManager() {
			public void visibilizador(boolean setVisible) {
				resultsContainer.setVisible(setVisible);
			}
		});
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
	
	public void setVisibleManager(VisibleManager visibleManager) {
		this.visibleManager = visibleManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object itemClickeado = e.getSource();
		if (itemClickeado instanceof JMenuItem) {
			JMenuItem clikedMenuItem = (JMenuItem)itemClickeado;
			if(clikedMenuItem.getText().equals(new String("Importar mediciones"))) {
				importarMediciones();
			}
		}
	}

	private void importarMediciones() {
//		visibleManager.visibilizador(true);
		if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
			System.out.println(fileChooser.getSelectedFile());
		}
	}
	
}
