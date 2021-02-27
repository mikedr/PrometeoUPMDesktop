import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GraphPanel extends JPanel{
	
	public GraphPanel() {
		setup();
	}

	private void setup() {
		 setLayout(new BorderLayout());
		 setBorder(BorderFactory.createEtchedBorder());		
	}
	
}

