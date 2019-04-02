

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
	JTextField textNbVoit;
	
		public Fenetre(){
			
			
			this.setTitle("Simulation réseau voitures autonomes");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLayout(null);
			r = new Reseau();
			
			
			pan = new Panel(r);
			//pan.setLocation(200, 200);
			//this.setSize(1000,1000);
			
			//Creation des composants de la fenetre :
			this.textNbVoit = new JTextField("Nombre de voitures :");
			this.textNbVoit.setBounds(100,100,200,200);
			
			
			this.setBounds(50,50,pan.reseau.getMap()[0].length*pan.t,pan.reseau.getMap().length*pan.t+40);
			this.add(pan);
			this.setVisible(true);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/*
			this.setVisible(false);
			this.setBounds(50,50,pan.reseau.getMap()[0].length*pan.t,pan.reseau.getMap().length*pan.t+40);
			repaint();
			this.setVisible(true);
			repaint();*/
			
			//r.calculCheminCourt();
			r.convertionMapGraphe();
			System.out.println("Il y a "+r.noeuds.size()+" noeuds");
			r.calculCheminGraphe();
			r.conversionTrajectoire();
			
			
			//IL FAUT AJOUTER UN DERNIER AVANT A TOUTES LES TRAJECTOIRES
			for(int i=0;i<r.trajectoires.size();i++) {
				for(int j=0;j<r.trajectoires.get(i).size();j++) {
					r.trajectoires.get(i).get(j).add("avant");
				}
			}
			
			Voiture v = r.getVoitures().get(0);
			v.setTrajectoire(r.trajectoires.get(5).get(0));
			v.setStatDep(5);
			v.setStatArr(0);
			
			/*for(Voiture v : r.getVoitures()) {
				r.adapteSensVoiture(v);
				v.setSortCarrefour(true);
				v.change_voie();
			}*/
			r.adapteSensVoiture(v);
			v.setSortCarrefour(true);
			v.change_voie();
			
			while(true) {
				/*for(Voiture v : r.getVoitures()) {
					r.sortieCarrefour(v);
					v.change_voie();
					if(!v.getTrajectoire().isEmpty()) {
						v.avance();
					}
				}*/
				r.sortieCarrefour(v);
				v.change_voie();
				if(!v.getTrajectoire().isEmpty()) {
					v.avance();
				}
				else {
					v.setStatDep(v.getStatArr());
					v.setX(r.getStations().get(v.getStatDep()).getXDepart());
					v.setY(r.getStations().get(v.getStatDep()).getYDepart());
					int nouvelleDestination = (int)(Math.random()*r.getStations().size());
					while(nouvelleDestination == v.getStatDep()) {
						System.out.println("test");
						nouvelleDestination = (int)(Math.random()*r.getStations().size());
					}
					v.setStatArr(nouvelleDestination);
					v.setTrajectoire(r.trajectoires.get(v.getStatDep()).get(v.getStatArr()));
					r.adapteSensVoiture(v);
					v.setSortCarrefour(true);
					v.change_voie();
				}
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void paint(Graphics g) {
			this.pan.repaint();
		}
		
		public void actionPerformed(ActionEvent e) {
			
		}
		
	
}

