

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
public class Fenetre extends JFrame implements ActionListener{
	
	Reseau r;
	Panel pan;
	Timer time;
	final int DELAI_BOUCLE = 1000;
	
		public Fenetre(){
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
			r = new Reseau();
			
			for(int i=0;i<r.getMapVoiture().length;i++) {
				for(int j=0;j<r.getMapVoiture()[i].length;j++) {
					if(r.getMapVoiture()[i][j]) {
						r.addVoiture(new Voiture(1,j,i));
					}
				}
			}
			r.getVoitures().get(0).setDirection("bas");
			r.getVoitures().get(1).setDirection("gauche");
			r.getVoitures().get(2).setDirection("droite");
			r.getVoitures().get(3).setDirection("haut");
			
			pan = new Panel(r);
			this.setBounds(50,50,pan.r.getMap()[0].length*pan.t,pan.r.getMap().length*pan.t+40);
			this.add(pan);
			this.setVisible(true);
			
			r.calculPlusCourtChemin(r.getVoitures().get(0), r.getStations().get(6));
			r.calculPlusCourtChemin(r.getVoitures().get(1), r.getStations().get(2));
			r.calculPlusCourtChemin(r.getVoitures().get(2), r.getStations().get(5));
			r.calculPlusCourtChemin(r.getVoitures().get(3), r.getStations().get(1));
			
			/*for(LinkedList<int[]> a : r.trajectoireVoitures) {
				for(int i=0;i<a.size();i++) {
					for(int j=0;j<a.get(i).length;j++) {
						System.out.print(a.get(i)[j]+" ");
					}
					System.out.println();
				}
				System.out.println();
			}*/
			
			time = new Timer(DELAI_BOUCLE,this);
			time.start();
		}
		
		public void paint(Graphics g) {
			this.pan.repaint();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==time) {
				//r.actualiseMapVoiture();
				/*for(int i=0;i<r.trajectoireVoitures.get(0).size();i++) {
					for(int j=0;j<r.trajectoireVoitures.get(0).get(i).length;j++) {
						System.out.print(r.trajectoireVoitures.get(0).get(i)[j]+" ");
					}
					System.out.println();
				}*/
				
				for(Voiture v : r.getVoitures()) {
					v.setDirection(r.transformTrajectToDirection(v));
				}
				r.actualiseMapVoiture();
				
				
				repaint();
			}
			
		}
		
	
}

