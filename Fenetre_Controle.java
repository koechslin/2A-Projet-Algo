import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Fenetre_Controle extends JFrame implements ActionListener, KeyListener{
	private JLabel labelNbVoitures;
	private JTextField textNbVoitures;
	private JLabel labelMode;
	private JTextField TextFieldVoiture;
	private JTextField TextFieldStation;
	private JLabel labelControle;
	private JButton BoutonGo;
	private JLabel textStation;
	private JLabel textVoiture;
	private JRadioButton Automatique;
	private JRadioButton Manuel;
	private JButton importMap;
	private JButton recharge;
	private Lecteur_Fichier lecteur;
	private JPanel content;
	private Fenetre_Affichage affichage;
	private JTextArea erreurVoiture;
	private JLabel erreurStation;
	private boolean afficheErreurVoiture = false;
	private boolean afficheErreurStation = false;
	
	public Fenetre_Controle() {
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Contr�le");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((int)(0.4*tailleEcran.getWidth()), (int)(0.72*tailleEcran.getHeight()));
		this.setLocation((int)(0.03*tailleEcran.getWidth()), (int)(0.1*tailleEcran.getHeight()));

		//this.setSize(600, 600);
		//this.setLocation(100, 100);
		
		//Initialisation des elements
		//affichage = new Fenetre_Affichage(800,800);
		affichage = new Fenetre_Affichage((int)(0.80*tailleEcran.getWidth()),(int)(0.80*tailleEcran.getHeight()));
		
		erreurVoiture = new JTextArea("Attention : votre nombre d�passe le nombre de voitures !");
		erreurVoiture.setEditable(false);
		erreurVoiture.setFocusable(false);
		erreurVoiture.setLineWrap(true);
		erreurVoiture.setOpaque(false);
		erreurVoiture.setFont(new Font("Serif",Font.PLAIN,13));
		erreurVoiture.setBounds((int)(0.47*this.getWidth()), (int)(0.48*this.getWidth()), (int)(0.34*this.getWidth()), (int)(0.18*this.getWidth()));
		erreurVoiture.setVisible(false);
		
		labelNbVoitures = new JLabel("Nombre de voitures : ");
		labelNbVoitures.setBounds((int)(0.05*this.getWidth()),(int)(0.02*this.getHeight()),(int)(0.5*this.getWidth()),(int)(0.1*this.getHeight()));
		labelNbVoitures.setFont(new Font("Serif",Font.PLAIN,22));
		
		textNbVoitures = new JTextField();
		textNbVoitures.setBounds((int)(0.15*this.getWidth()),(int)(0.12*this.getHeight()),(int)(0.12*this.getHeight()),(int)(0.10*this.getHeight()));
		
		labelMode = new JLabel("Mode : ");
		labelMode.setFont(new Font("Serif",Font.PLAIN,22));
		labelMode.setBounds((int)(0.07*this.getWidth()),(int)(0.27*this.getHeight()),(int)(0.28*this.getWidth()),(int)(0.07*this.getWidth()));
		
		TextFieldVoiture = new JTextField();
		TextFieldVoiture.setBounds((int)(0.37*this.getWidth()),(int)(0.53*this.getWidth()),(int)(0.10*this.getWidth()),(int)(0.10*this.getWidth()));
		
		TextFieldStation = new JTextField();
		TextFieldStation.setBounds((int)(0.37*this.getWidth()),(int)(0.67*this.getWidth()),(int)(0.10*this.getWidth()),(int)(0.10*this.getWidth()));
		
		labelControle = new JLabel("Contr�le :");
		labelControle.setFont(new Font("Serif",Font.PLAIN,30));
		labelControle.setBounds((int)(0.41*this.getWidth()),(int)(0.39*this.getWidth()),(int)(0.35*this.getWidth()),(int)(0.10*this.getWidth()));
		
		BoutonGo = new JButton("Go");
		BoutonGo.setBounds((int)(0.38*this.getWidth()),(int)(0.82*this.getWidth()),(int)(0.30*this.getWidth()),(int)(0.07*this.getWidth()));
		BoutonGo.addActionListener(this);
		
		textStation = new JLabel("Station : ");
		textStation.setFont(new Font("Serif",Font.PLAIN,22));
		textStation.setBounds((int)(0.18*this.getWidth()),(int)(0.67*this.getWidth()),(int)(0.35*this.getWidth()),(int)(0.10*this.getWidth()));
		
		textVoiture = new JLabel("Voiture : ");
		textVoiture.setFont(new Font("Serif",Font.PLAIN,22));
		textVoiture.setBounds((int)(0.18*this.getWidth()),(int)(0.54*this.getWidth()),(int)(0.35*this.getWidth()),(int)(0.10*this.getWidth()));
		
		Automatique =  new JRadioButton("Automatique");
		Automatique.setFont(new Font("Serif",Font.PLAIN,21));
		Automatique.setBounds((int)(0.28*this.getWidth()),(int)(0.27*this.getHeight()),(int)(0.25*this.getWidth()),(int)(0.07*this.getWidth()));
		Automatique.addActionListener(this);
		Automatique.setSelected(true);
		
		BoutonGo.setVisible(false); // au debut tous ces elements ne sont pas visible car automatique est selectionne
		textStation.setVisible(false);
		textVoiture.setVisible(false);
		TextFieldVoiture.setVisible(false);
		TextFieldStation.setVisible(false);
		labelControle.setVisible(false);
		
		Manuel = new JRadioButton("Manuel");
		Manuel.setFont(new Font("Serif",Font.PLAIN,21));
		Manuel.setBounds((int)(0.64*this.getWidth()),(int)(0.27*this.getHeight()),(int)(0.25*this.getWidth()),(int)(0.07*this.getWidth()));
		Manuel.addActionListener(this);
		
		ButtonGroup groupB = new ButtonGroup();
		groupB.add(Manuel);
		groupB.add(Automatique);
		
		importMap = new JButton("Importer une map");
		importMap.setBounds((int)(0.56*this.getWidth()),(int)(0.15*this.getWidth()),(int)(0.31*this.getWidth()),(int)(0.07*this.getWidth()));
		importMap.addActionListener(this);
		
		recharge = new JButton("Recharger la simulation");
		recharge.setBounds((int)(0.56*this.getWidth()),(int)(0.05*this.getWidth()),(int)(0.31*this.getWidth()),(int)(0.07*this.getWidth()));
		recharge.addActionListener(this);
		
		lecteur = new Lecteur_Fichier();
		
		content = new JPanel();
		content.setLayout(null);
		
		this.setVisible(true); // on met la fenetre visible un peu avant pour avoir les Insets
		content.setBounds(0,0,this.getWidth()-this.getInsets().left-this.getInsets().right,this.getHeight()-this.getInsets().top-this.getInsets().bottom);
		
		// ajout des elements
		content.add(labelNbVoitures);
		content.add(textNbVoitures);
		content.add(labelMode);
		content.add(TextFieldVoiture);
		content.add(TextFieldStation);
		content.add(labelControle);
		content.add(BoutonGo);
		content.add(textStation);
		content.add(textVoiture);
		content.add(Automatique);
		content.add(Manuel);
		content.add(importMap);
		content.add(recharge);
		content.add(erreurVoiture);
		this.add(content);
		TextFieldVoiture.addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		content.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Manuel) {
			this.affichage.getPan().setMode("Manuel");
			BoutonGo.setVisible(true);
			textStation.setVisible(true);
			textVoiture.setVisible(true);
			TextFieldVoiture.setVisible(true);
			TextFieldStation.setVisible(true);
			labelControle.setVisible(true);
			if(afficheErreurVoiture) {
				erreurVoiture.setVisible(true);
			}
		}
		else if(e.getSource()==Automatique) {
			this.affichage.getPan().setMode("Automatique");
			BoutonGo.setVisible(false);
			textStation.setVisible(false);
			textVoiture.setVisible(false);
			TextFieldVoiture.setVisible(false);
			TextFieldStation.setVisible(false);
			labelControle.setVisible(false);
			erreurVoiture.setVisible(false);
		}
		else if(e.getSource()==importMap) {
			lecteur.ouvertureFichier();
			if(lecteur.file !=null) { // si on a bien choisi un fichier
				affichage.getReseau().setMapRoute(lecteur.traitementFichier());
				affichage.getPan().actualiseMapDessin();
				affichage.getPan().recalculT();
				affichage.actualiseTaille();
				affichage.getReseau().creationStation();
				//
				//MARCHE PAS ?
				affichage.getReseau().recharge(affichage.getReseau().getVoitures().size());
				affichage.getReseau().conversionMapGraphe();
				affichage.getReseau().calculCheminGraphe();
				affichage.getReseau().conversionTrajectoire();
				//
				affichage.repaint();
			}	
		}
		else if(e.getSource()==BoutonGo) {
			if((!this.TextFieldVoiture.getText().isEmpty()) && (!this.TextFieldStation.getText().isEmpty())) {
				if((TextFieldVoiture.getText().matches("-?\\d+(\\.\\d+)?")) && (TextFieldStation.getText().matches("-?\\d+(\\.\\d+)?"))) {
					int voiture = Integer.parseInt(TextFieldVoiture.getText());
					int station = Integer.parseInt(TextFieldStation.getText());
					if(voiture<this.affichage.getReseau().getVoitures().size() && voiture >=0 && station<this.affichage.getReseau().getStations().size() && station>=0) {
						//donner la trajectoire � la voiture
						this.affichage.getReseau().getVoitures().get(voiture).setManuelle(true);
						this.affichage.getReseau().getVoitures().get(voiture).setStationManuelle(station);
						
					}
					else if(voiture<this.affichage.getReseau().getVoitures().size() && voiture >=0 && (station>=this.affichage.getReseau().getStations().size() || station<0)) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de station entre 0 et " + (this.affichage.getReseau().getStations().size()-1));
					}
					else if((voiture>=this.affichage.getReseau().getVoitures().size() || voiture <0) && station<this.affichage.getReseau().getStations().size() && station>=0) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de voiture entre 0 et " + (this.affichage.getReseau().getVoitures().size()-1));
					}
					else {
						//aucun des deux
						JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de voiture entre 0 et " + (this.affichage.getReseau().getVoitures().size()-1)+" et un num�ro de station entre 0 et "+(this.affichage.getReseau().getStations().size()-1));
					}
					
					/*
					if((Integer.parseInt(TextFieldVoiture.getText()) < Integer.parseInt(textNbVoitures.getText())) && (Integer.parseInt(TextFieldVoiture.getText()) >= 0) 
							&& (Integer.parseInt(TextFieldStation.getText()) < getStations().size()) && (Integer.parseInt(TextFieldStation.getText()) >= 0)) {
						getVoitures()[Integer.parseInt(TextFieldVoiture.getText())].setStatArr(getStations()[Integer.parseInt(TextFieldStation.getText())]);
						getVoitures()[Integer.parseInt(TextFieldVoiture.getText())].setTrajectoire(ArrayList<String> t);
					}else {
						if(((Integer.parseInt(TextFieldVoiture.getText()) >= Integer.parseInt(textNbVoitures.getText())) || (Integer.parseInt(TextFieldVoiture.getText()) < 0)) 
								&& ((Integer.parseInt(TextFieldStation.getText()) >= getStations().size()) || (Integer.parseInt(TextFieldStation.getText()) < 0))) {
							JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de voiture entre 0 et " + (Integer.parseInt(textNbVoitures.getText())-1) + " et un num�ro de station entre 0 et " + (getStations().size()-1));
						}
						else if((Integer.parseInt(TextFieldVoiture.getText()) >= Integer.parseInt(textNbVoitures.getText())) || (Integer.parseInt(TextFieldVoiture.getText()) < 0)) {
							JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de voiture entre 0 et " + (Integer.parseInt(textNbVoitures.getText())-1));
						}
						else if((Integer.parseInt(TextFieldStation.getText()) >= getStations().size()) || (Integer.parseInt(TextFieldStation.getText()) < 0)) {
							JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de station entre 0 et " + (getStations().size()-1));
						}
					}
					System.out.println("remplir cette partie");    //REMPLIR CETTE PARTIE*/
				}
				else{
					if((!TextFieldVoiture.getText().matches("-?\\d+(\\.\\d+)?")) && (!TextFieldStation.getText().matches("-?\\d+(\\.\\d+)?"))) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer des num�ros de voiture et de station entiers et positifs");
					}
					else if(!TextFieldVoiture.getText().matches("-?\\d+(\\.\\d+)?")) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de voiture entier et positif");
					}
					else if(!TextFieldStation.getText().matches("-?\\d+(\\.\\d+)?")) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de station entier et positif");
					}
				}
			}
			else {
				if(this.TextFieldVoiture.getText().isEmpty() && this.TextFieldStation.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Veuillez entrer des num�ros de voiture et de station");
				}
				else if (this.TextFieldVoiture.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de voiture");
				}
				else if(this.TextFieldStation.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Veuillez entrer un num�ro de station");
				}
			}
		}
		else if(e.getSource()==recharge) {
			if(!this.textNbVoitures.getText().isEmpty()) {
				if(textNbVoitures.getText().matches("-?\\d+(\\.\\d+)?")) { // on verifie si c'est un entier
					int nbVoiture = Integer.parseInt(this.textNbVoitures.getText());
					if(nbVoiture <=0) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre strictement positif");
					}
					else {
						this.affichage.getReseau().recharge(nbVoiture);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre de voitures");
			}
		}
	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		if(!TextFieldVoiture.getText().isEmpty()) {
			if(TextFieldVoiture.getText().matches("-?\\d+(\\.\\d+)?")) { // on verifie si c'est un entier
				if(Integer.parseInt(TextFieldVoiture.getText())>=affichage.getReseau().getVoitures().size() || Integer.parseInt(TextFieldVoiture.getText())<0) {
					afficheErreurVoiture=true;
					erreurVoiture.setVisible(true);
					affichage.getPan().setVoitSurbrillance(-1);
					affichage.repaint();
				}
				else {
					afficheErreurVoiture=false;
					erreurVoiture.setVisible(false);
					affichage.getPan().setVoitSurbrillance(Integer.parseInt(TextFieldVoiture.getText()));
					affichage.repaint();
				}
			}
		}
		else {
			if(afficheErreurVoiture) {
				afficheErreurVoiture=false;
				erreurVoiture.setVisible(false);
			}
			affichage.getPan().setVoitSurbrillance(-1);
			affichage.repaint();
		}
	}
	public void keyTyped(KeyEvent e) {
		
	}
}