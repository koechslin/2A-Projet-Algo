

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
			
			/*for(int i=0;i<r.getMapVoiture().length;i++) {
				for(int j=0;j<r.getMapVoiture()[i].length;j++) {
					if(r.getMapVoiture()[i][j]) {
						r.addVoiture(new Voiture(1,j,i));
					}
				}
			}
			r.getVoitures().get(0).setDirection("bas");
			r.getVoitures().get(1).setDirection("gauche");
			r.getVoitures().get(2).setDirection("droite");
			r.getVoitures().get(3).setDirection("haut");*/
			
			pan = new Panel(r);
			this.setBounds(50,50,pan.reseau.getMap()[0].length*pan.t,pan.reseau.getMap().length*pan.t+40);
			this.add(pan);
			this.setVisible(true);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/*Lecteur_Fichier fileReader =new Lecteur_Fichier();
			fileReader.ouvertureFichier();
			r.changeMap(fileReader.traitementFichier());
			for(int i=0;i<r.map.length;i++) {
				for(int j=0;j<r.map[i].length;j++) {
					System.out.print(r.map[i][j]+" ");
				}
				System.out.println();
			}
			this.setVisible(false);
			this.setBounds(50,50,pan.reseau.getMap()[0].length*pan.t,pan.reseau.getMap().length*pan.t+40);
			repaint();
			this.setVisible(true);
			repaint();*/
			
			r.calculCheminCourt();
			
			//IL FAUT AJOUTER UN DERNIER AVANT A TOUTES LES TRAJECTOIRES
			for(int i=0;i<r.trajectoires.size();i++) {
				for(int j=0;j<r.trajectoires.get(i).size();j++) {
					r.trajectoires.get(i).get(j).add("avant");
				}
			}
			
			
			r.getVoitures().get(0).setTrajectoire(r.trajectoires.get(0).get(1));
			r.getVoitures().get(1).setTrajectoire(r.trajectoires.get(1).get(2));
			r.getVoitures().get(2).setTrajectoire(r.trajectoires.get(2).get(3));
			r.getVoitures().get(3).setTrajectoire(r.trajectoires.get(3).get(0));
			
			for(Voiture v : r.getVoitures()) {
				r.adapteSensVoiture(v);
				v.setSortCarrefour(true);
				v.change_voie();
			}
			
			while(true) {
				for(Voiture v : r.getVoitures()) {
					r.sortieCarrefour(v);
					v.change_voie();
					if(!v.getTrajectoire().isEmpty()) {
						v.avance();
					}
				}
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			//time = new Timer(DELAI_BOUCLE,this);
			//time.start();
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
				
				r.actualiseMapVoiture();
				
				
				repaint();
			}
			
		}
		
	
}

