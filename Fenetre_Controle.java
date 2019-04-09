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
		this.setTitle("Contrôle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((int)(0.3*tailleEcran.getWidth()),(int)(0.6*tailleEcran.getHeight()));
		this.setLocation((int)(0.03*tailleEcran.getWidth()), (int)(0.1*tailleEcran.getHeight()));
		//this.setSize(600, 600);
		//this.setLocation(100, 100);
		
		//Initialisation des elements
		//affichage = new Fenetre_Affichage(800,800);
		affichage = new Fenetre_Affichage((int)(0.80*tailleEcran.getWidth()),(int)(0.80*tailleEcran.getHeight()));
		
		erreurVoiture = new JTextArea("Attention : votre nombre dépasse le nombre de voitures !");
		erreurVoiture.setEditable(false);
		erreurVoiture.setFocusable(false);
		erreurVoiture.setLineWrap(true);
		erreurVoiture.setOpaque(false);
		erreurVoiture.setFont(new Font("Serif",Font.PLAIN,13));
		erreurVoiture.setBounds(270, 277, 195, 100);
		erreurVoiture.setVisible(false);
		
		labelNbVoitures = new JLabel("Nombre de voitures : ");
		labelNbVoitures.setBounds(20,2,200,50);
		labelNbVoitures.setFont(new Font("Serif",Font.PLAIN,22));
		
		textNbVoitures = new JTextField();
		textNbVoitures.setBounds(80,60,60,50);
		
		labelMode = new JLabel("Mode : ");
		labelMode.setFont(new Font("Serif",Font.PLAIN,22));
		labelMode.setBounds(40,150,200,50);
		
		TextFieldVoiture = new JTextField();
		TextFieldVoiture.setBounds(210,303,50,50);
		
		TextFieldStation = new JTextField();
		TextFieldStation.setBounds(210,383,50,50);
		
		labelControle = new JLabel("Contrôle :");
		labelControle.setFont(new Font("Serif",Font.PLAIN,30));
		labelControle.setBounds(235,220,200,50);
		
		BoutonGo = new JButton("Go");
		BoutonGo.setBounds(215,470,170,40);
		BoutonGo.addActionListener(this);
		
		textStation = new JLabel("Station : ");
		textStation.setFont(new Font("Serif",Font.PLAIN,22));
		textStation.setBounds(100,380,200,50);
		
		textVoiture = new JLabel("Voiture : ");
		textVoiture.setFont(new Font("Serif",Font.PLAIN,22));
		textVoiture.setBounds(100,300,200,50);
		
		Automatique =  new JRadioButton("Automatique");
		Automatique.setFont(new Font("Serif",Font.PLAIN,21));
		Automatique.setBounds(160,153,145,50);
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
		Manuel.setBounds(370,153,145,50);
		Manuel.addActionListener(this);
		
		ButtonGroup groupB = new ButtonGroup();
		groupB.add(Manuel);
		groupB.add(Automatique);
		
		importMap = new JButton("Importer une map");
		importMap.setBounds(320,85,180,40);
		importMap.addActionListener(this);
		
		recharge = new JButton("Recharger la simulation");
		recharge.setBounds(320,30,180,40);
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
						//donner la trajectoire à la voiture
						this.affichage.getReseau().getVoitures().get(voiture).setManuelle(true);
						this.affichage.getReseau().getVoitures().get(voiture).setStationManuelle(station);
						
					}
					else if(voiture<this.affichage.getReseau().getVoitures().size() && voiture >=0 && (station>=this.affichage.getReseau().getStations().size() || station<0)) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de station entre 0 et " + (this.affichage.getReseau().getStations().size()-1));
					}
					else if((voiture>=this.affichage.getReseau().getVoitures().size() || voiture <0) && station<this.affichage.getReseau().getStations().size() && station>=0) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de voiture entre 0 et " + (this.affichage.getReseau().getVoitures().size()-1));
					}
					else {
						//aucun des deux
						JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de voiture entre 0 et " + (this.affichage.getReseau().getVoitures().size()-1)+" et un numéro de station entre 0 et "+(this.affichage.getReseau().getStations().size()-1));
					}
					
					/*
					if((Integer.parseInt(TextFieldVoiture.getText()) < Integer.parseInt(textNbVoitures.getText())) && (Integer.parseInt(TextFieldVoiture.getText()) >= 0) 
							&& (Integer.parseInt(TextFieldStation.getText()) < getStations().size()) && (Integer.parseInt(TextFieldStation.getText()) >= 0)) {
						getVoitures()[Integer.parseInt(TextFieldVoiture.getText())].setStatArr(getStations()[Integer.parseInt(TextFieldStation.getText())]);
						getVoitures()[Integer.parseInt(TextFieldVoiture.getText())].setTrajectoire(ArrayList<String> t);
					}else {
						if(((Integer.parseInt(TextFieldVoiture.getText()) >= Integer.parseInt(textNbVoitures.getText())) || (Integer.parseInt(TextFieldVoiture.getText()) < 0)) 
								&& ((Integer.parseInt(TextFieldStation.getText()) >= getStations().size()) || (Integer.parseInt(TextFieldStation.getText()) < 0))) {
							JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de voiture entre 0 et " + (Integer.parseInt(textNbVoitures.getText())-1) + " et un numéro de station entre 0 et " + (getStations().size()-1));
						}
						else if((Integer.parseInt(TextFieldVoiture.getText()) >= Integer.parseInt(textNbVoitures.getText())) || (Integer.parseInt(TextFieldVoiture.getText()) < 0)) {
							JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de voiture entre 0 et " + (Integer.parseInt(textNbVoitures.getText())-1));
						}
						else if((Integer.parseInt(TextFieldStation.getText()) >= getStations().size()) || (Integer.parseInt(TextFieldStation.getText()) < 0)) {
							JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de station entre 0 et " + (getStations().size()-1));
						}
					}
					System.out.println("remplir cette partie");    //REMPLIR CETTE PARTIE*/
				}
				else{
					if((!TextFieldVoiture.getText().matches("-?\\d+(\\.\\d+)?")) && (!TextFieldStation.getText().matches("-?\\d+(\\.\\d+)?"))) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer des numéros de voiture et de station entiers et positifs");
					}
					else if(!TextFieldVoiture.getText().matches("-?\\d+(\\.\\d+)?")) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de voiture entier et positif");
					}
					else if(!TextFieldStation.getText().matches("-?\\d+(\\.\\d+)?")) {
						JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de station entier et positif");
					}
				}
			}
			else {
				if(this.TextFieldVoiture.getText().isEmpty() && this.TextFieldStation.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Veuillez entrer des numéros de voiture et de station");
				}
				else if (this.TextFieldVoiture.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de voiture");
				}
				else if(this.TextFieldStation.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de station");
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
