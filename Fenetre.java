
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;



public class Fenetre extends JFrame /*implements ActionListener*/{
	private JPanel panelPrincipal;
	private JPanel panelGauche;
	private JPanel panelDroite;
	private JPanel panelHautDroite;
	private JPanel panelBasDroite;
	private JLabel labelNbVoitures;
	private JTextField textNbVoitures;
	private JLabel labelMode;
	private JCheckBox Manuel;
	private JCheckBox Automatique;
	private JTextArea TextAreaVoiture;
	private JTextArea TextAreaStation;
	private JLabel labelControle;
	private JButton BoutonGo;
	
	
	Reseau r;
	Panel pan;
	//Timer time;
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
			
			/*construction des elements gtaphiques de la fenetre*/
			panelPrincipal = new JPanel(new BorderLayout());
			panelGauche = new JPanel();
			panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.PAGE_AXIS));
			
			panelDroite = new JPanel();
			panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.PAGE_AXIS));
			panelBasDroite = new JPanel();
			panelBasDroite.setLayout(new BoxLayout(panelBasDroite, BoxLayout.PAGE_AXIS));
			panelHautDroite = new JPanel();
			panelHautDroite.setLayout(new BoxLayout(panelHautDroite, BoxLayout.PAGE_AXIS));
			
			pan = new Panel(r);
			this.setBounds(50,50,pan.reseau.getMap()[0].length*pan.t,pan.reseau.getMap().length*pan.t+40);
			
			//ajout panel de droite
			
			panelDroite.add(panelHautDroite);
			panelDroite.add(panelBasDroite);
			//ajout panel Haut droite
			labelMode = new JLabel("Mode:");
			Manuel = new JCheckBox("Manuel");
			Automatique = new JCheckBox("Automatique");
			panelHautDroite.add(labelMode);
			panelHautDroite.add(Manuel);
			panelHautDroite.add(Automatique);
			//ajout panel Bas droite
			BoutonGo = new JButton("GO");
			labelControle = new JLabel("Controle:");
			TextAreaVoiture = new JTextArea("Voiture");
			TextAreaStation = new JTextArea("Station");
			panelBasDroite.add(TextAreaStation);
			panelBasDroite.add(TextAreaVoiture);
			panelBasDroite.add(labelControle);
			panelBasDroite.add(BoutonGo);
			
			//ajout panel de gauche
			labelNbVoitures = new JLabel("nombre de voitures: ");
			textNbVoitures = new JTextField("entrer nombre de voitures", 18);
			panelGauche.add(labelNbVoitures);
			panelGauche.add(textNbVoitures);
			
			/*ajout au panel principal*/
			panelPrincipal.add(pan, BorderLayout.CENTER);
			panelPrincipal.add(panelGauche, BorderLayout.WEST);
			panelPrincipal.add(panelDroite, BorderLayout.EAST);
			
			
			
			
			this.add(panelPrincipal, BorderLayout.CENTER);
			this.setSize(1300,900);
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
			r.convertionMapGraphe();
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
			
			
			//time = new Timer(DELAI_BOUCLE,this);
			//time.start();
			
		
		public void paint(Graphics g) {
			this.pan.repaint();
			this.panelGauche.repaint();
			this.panelDroite.repaint();
			this.panelHautDroite.repaint();
			this.panelBasDroite.repaint();
		}
}
		
		/*public void actionPerformed(ActionEvent e) {
			if(e.getSource()==time) {
				//r.actualiseMapVoiture();
				for(int i=0;i<r.trajectoireVoitures.get(0).size();i++) {
					for(int j=0;j<r.trajectoireVoitures.get(0).get(i).length;j++) {
						System.out.print(r.trajectoireVoitures.get(0).get(i)[j]+" ");
					}
					System.out.println();
				}
				
				r.actualiseMapVoiture();
				
				
				//repaint();
			}
			
		}*/
		

