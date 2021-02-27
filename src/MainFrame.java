import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame{
	
	private GraphPanel textPanel;
	private Toolbar toolbar;
	private ResultsContainer resultsContainer;
	
	public MainFrame () {
		super("Prometeo UP Meter");
		setup();
		instaciateComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(resultsContainer,BorderLayout.WEST);
		add(textPanel,BorderLayout.CENTER);
		add(toolbar,BorderLayout.NORTH);
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

		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		
		menuBar.add(medicionesMenu);
		menuBar.add(dispositivoMenu);
		menuBar.add(aplicacionMenu);
		
		return menuBar;
	}

	private void instaciateComponents() {
		textPanel = new GraphPanel();
		toolbar = new Toolbar();
		resultsContainer = new ResultsContainer();
		toolbar.setVisibleManager(new VisibleManager() {
			@Override
			public void visibilizador(boolean setVisible) {
				resultsContainer.setVisible(setVisible);
			}
		});
	}

	private void setup() {
		setLayout(new BorderLayout());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
