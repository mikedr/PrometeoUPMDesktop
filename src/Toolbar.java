import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{
	
	private JButton buttonDispositivo;
	private JButton buttonCargarMediciones;
	private VisibleManager visibleManager;
	
	public Toolbar() {
		setup();
		instaciateComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(buttonCargarMediciones);
		add(buttonDispositivo);		
	}

	private void instaciateComponents() {
		buttonCargarMediciones = new JButton("Cargar Mediciones");
		buttonDispositivo = new JButton("Dispositivo");
		buttonCargarMediciones.addActionListener(this);
		buttonDispositivo.addActionListener(this);		
	}

	private void setup() {
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new FlowLayout(FlowLayout.LEFT));		
	}

	public void setVisibleManager(VisibleManager visibleManager) {
		this.visibleManager = visibleManager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClickeado = (JButton)e.getSource();
		if(buttonClickeado == buttonCargarMediciones) {
			if(visibleManager != null) {
				visibleManager.visibilizador(true);
			}
		}
	}
	
}
