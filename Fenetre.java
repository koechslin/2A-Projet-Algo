

import javax.swing.*;
public class Fenetre extends JFrame{

		public Fenetre(Panel pan){
			this.setBounds(50,50,(pan.statique[0].length+1)*pan.t,(pan.statique.length+2)*pan.t);
			this.add(pan);
			this.setVisible(true);
		}
		
	
}

