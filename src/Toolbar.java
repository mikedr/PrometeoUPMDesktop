import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{
	private JButton buttonDispositivo;
	private JButton buttonCargarMediciones;
	private StringListener textListener;
	private VisibleManager visibleManager;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		buttonCargarMediciones = new JButton("Cargar Mediciones");
		buttonDispositivo = new JButton("Dispositivo");
		buttonCargarMediciones.addActionListener(this);
		buttonDispositivo.addActionListener(this);
		add(buttonCargarMediciones);
		add(buttonDispositivo);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
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
