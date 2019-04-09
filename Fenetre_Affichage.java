import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Fenetre_Affichage extends JFrame implements ActionListener{
	private Reseau res;
	private Panel pan;
	private int hauteur;
	private int largeur;
	private Timer timer;
	private int delai = 300;
	
	public Fenetre_Affichage(int h, int l) {
		hauteur = h;
		largeur = l;
		res = new Reseau();
		res.conversionMapGraphe();
		res.calculCheminGraphe();
		res.conversionTrajectoire();
		
		//on ajoute un dernier avant a toutes les trajectoires
		for(int i=0;i<res.trajectoires.size();i++) {
			for(int j=0; j<res.trajectoires.get(i).size();j++) {
				res.trajectoires.get(i).get(j).add("avant");
			}
		}
		
		timer = new Timer(delai,this);
		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Affichage du r�seau");
		setLocation((int)(0.45*tailleEcran.getWidth()),(int)(0.05*tailleEcran.getHeight()));
		
		setVisible(true); // on met la fenetre visible avant pour avoir les insets
		pan = new Panel(res,largeur-this.getInsets().left-this.getInsets().right,hauteur-this.getInsets().top-this.getInsets().bottom);
		pan.setBounds(0,0,largeur-this.getInsets().left-this.getInsets().right,hauteur-this.getInsets().top-this.getInsets().bottom);
		this.setSize(pan.getT()*res.getMap().length+this.getInsets().left+this.getInsets().right, pan.getT()*res.getMap()[0].length+this.getInsets().top+this.getInsets().bottom);
		this.add(pan);
		
		
		repaint();
		timer.start();
	}
	
	public Reseau getReseau() {
		return res;
	}
	public Panel getPan() {
		return pan;
	}
	public void paint(Graphics g) {
		if(pan!=null) {
			pan.repaint();
		}
		
	}
	public void actualiseTaille() {
		this.setSize(pan.getT()*res.getMap().length+this.getInsets().left+this.getInsets().right, pan.getT()*res.getMap()[0].length+this.getInsets().top+this.getInsets().bottom);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==timer) {
			this.res.predictionCollision(res.getVoitures(), 5);
			
			for(Voiture v : res.getVoitures()) {
				if(!v.getEnCirculation()) {
					v.setStatDep(v.getStatArr());
					v.setX(res.getStations().get(v.getStatArr()).getXDepart());
					v.setY(res.getStations().get(v.getStatArr()).getYDepart());
					if(v.getManuelle() && v.getStationManuelle()!= v.getStatDep()) {
						v.setStatArr(v.getStationManuelle());
						v.setManuelle(false);
					}
					else {
						// automatique
						int destination = (int)(Math.random()*res.getStations().size());
						while(destination == v.getStatDep()) {
							destination = (int)(Math.random()*res.getStations().size());
						}
						v.setStatArr(destination);
					}
					res.adapteSensVoiture(v);
					v.setEnCirculation(true);
					v.setSortCarrefour(true);
					v.setTrajectoire(res.trajectoires.get(v.getStatDep()).get(v.getStatArr()));
				}
				v.change_voie();
				v.avance();
				
				/*if(v.getEnCirculation()) {
						v.change_voie();
						v.avance();
				}*/
			}
			repaint();
		}
	}
}