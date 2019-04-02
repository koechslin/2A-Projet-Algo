import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import org.omg.CORBA.SystemException;

public class Reseau {
	public ArrayList<NoeudGraphe> noeuds;
	public ArrayList<NoeudGraphe> noeudsStation;
	private ArrayList<Voiture> listeVoiture;
	// 0 : vide  /  1 : route  /  2 : station
	// true si il y a une voiture
	
	
	public int[][] map; 	/*{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};*/
	
	/*public int[][] map = 	{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};*/

	public boolean[][] mapVoiture = new boolean[15][15];

	public boolean[][] mapCarrefour;
	
	// sauvegarde afin de comparer avant/aprï¿½s et lancer si besoin la mï¿½thode changerVoie de voiture
	private ArrayList<Voiture> listeSauvegardeVoiture;
	
	
	
	private ArrayList<Station> listeStation;
	public LinkedList<LinkedList<int[]>> trajectoireVoitures;
	private LinkedList<boolean[][]> listeMapCalculEnAvance;
	public ArrayList<ArrayList<ArrayList<String>>> trajectoires;
	public Lecteur_Fichier lecteur;
	
	public Reseau() {
		lecteur = new Lecteur_Fichier();
		lecteur.ouvertureFichier();
		map = lecteur.traitementFichier();
		noeuds = new ArrayList<NoeudGraphe>();
		noeudsStation = new ArrayList<NoeudGraphe>();
		listeVoiture = new ArrayList<Voiture>();
		listeSauvegardeVoiture = new ArrayList<Voiture>();
		listeStation = new ArrayList<Station>();
		listeMapCalculEnAvance = new LinkedList<boolean[][]>();
		trajectoireVoitures = new LinkedList<LinkedList<int[]>>();
		mapCarrefour = new boolean[map.length][map[0].length];
		
		///////////////////////////// CREATION STATION
		int compteur = 0;
		for (int i = 0; i < map.length ; i++) {
			for (int j = 0 ; j < map[i].length ; j++) {
					if((map[i][j] == 2) && (map[i][j+1] == 2) && (map[i][j+2] == 2) && (map[i][j+3] == 2)) {
						System.out.println("rentre 1");
						if((map[i+1][j] == 1)) {
							addStation(new Station(compteur, j, i, j+3, i));
						}else if(map[i-1][j] == 1){
							addStation(new Station(compteur, j+3, i, j, i));
						}
						compteur++;
						
					}
					if((map[i][j] == 2) && (map[i+1][j] == 2) && (map[i+2][j] == 2) && (map[i+3][j] == 2)) {
						System.out.println("rentre 2");
						if((map[i][j+1] == 1)) {
							addStation(new Station(compteur, j, i+3, j, i));
						}else if(map[i][j-1] == 1){
							addStation(new Station(compteur, j, i, j, i+3));
						}
						compteur++;
					}	
			}
				
		}
		System.out.println("compteur = "+compteur);
		///////////////////////////////
		
		
		// Ajout voiture
		listeVoiture.add(new Voiture(1,listeStation.get(5).getXDepart(),listeStation.get(5).getYDepart(),0));
		listeVoiture.add(new Voiture(1,listeStation.get(1).getXDepart(),listeStation.get(1).getYDepart(),0));
		listeVoiture.add(new Voiture(1,listeStation.get(3).getXDepart(),listeStation.get(3).getYDepart(),0));
		/*for(Station s:listeStation) {
			listeVoiture.add(new Voiture(1,s.getXDepart(),s.getYDepart(),0));
		}*/
	}
	
	public void setMapRoute(int[][] m) {
		this.map=m;
	}
	public void setMapVoiture(boolean[][] m) {
		this.mapVoiture=m;
	}
	
	public ArrayList<Voiture> getVoitures(){
		return this.listeVoiture;
	}
	public ArrayList<Station> getStations(){
		return this.listeStation;
	}
	public void addVoiture(Voiture v) {
		listeVoiture.add(v);
		v.setNumero(listeVoiture.size()-1);
	}
	public void addStation(Station s) { // chaque station a une lettre
		listeStation.add(s);
	}
	public boolean[][] getMapVoiture(){
		return this.mapVoiture;
	}
	public int[][] getMap(){
		return this.map;
	}
	
	public int actualiseMapVoiture() { // renvoie -1 si il n'y a pas de collision(s) dans la prï¿½vision sinon renvoie l'indice de la voiture qui pose problï¿½me
		
		for(Voiture v : listeVoiture) {
			mapVoiture[v.getY()][v.getX()]=false;
			v.avance();
		}
		sauvegardeVoiture();
		
		//gï¿½rer la collision
		
		// PROBLEME SI VITESSE ELEVEE
		
		for(int i=0;i<listeVoiture.size();i++) {
			for(int j=i+1;j<listeVoiture.size();j++) { // pas besoin de commencer ï¿½ 0 car on a dï¿½jï¿½ vï¿½rifiï¿½
				if(listeVoiture.get(i).getX() == listeVoiture.get(j).getX() && listeVoiture.get(i).getY() == listeVoiture.get(j).getY()) {
					System.out.println("Collision ! pour : "+i);
					return i;
				}
			}
		}
		for(Voiture v : this.listeVoiture) {
			mapVoiture[v.getY()][v.getX()]=true;
		}
		
		/*for(int i=0;i<mapVoiture.length;i++) {
			for(int j=0;j<mapVoiture.length;j++) {
				System.out.print(mapVoiture[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();*/
		return -1;
	}
	
	/*public void calculPlusCourtChemin(Voiture v, Station s) { // 3 directions possibles : avant gauche droite
		
		// A MODIFIER : VERIFIER LE SENS
		
		//gï¿½rer les virages
		// Voir si ï¿½a marche car peut ne pas fonctionner ï¿½ cause du sens de la voiture
		
		int coordX = v.getX();
		int coordY = v.getY();
		
		LinkedList<String> trajectoire = new LinkedList<String>();
		while(coordX!=s.getXStation() || coordY!=s.getYStation()) {
			int xDiff = s.getXStation() - coordX;
			int yDiff = s.getYStation() - coordY;
			
			if(xDiff==0) {
				if(yDiff<0) { // station en haut
					if(map[coordY-v.getVitesse()][coordX]>=1) { // il y a une route ou une station
						coordY-=v.getVitesse();
						// VERIFIER LE SENS
						trajectoire.add("haut");
					}
				}
				else { // station en bas
					if(map[coordY+v.getVitesse()][coordX]>=1) { // il y a une route ou une station
						int[] temp = {coordX,coordY+v.getVitesse()};
						coordY+=v.getVitesse();
						trajectoire.add("bas");
					}
				}
			}
			else if(xDiff<0) {
				if(yDiff==0) { // station a gauche
					if(map[coordY][coordX-v.getVitesse()]>=1) { // il y a une route ou une station
						int[] temp = {coordX-v.getVitesse(),coordY};
						coordX-=v.getVitesse();
						trajectoire.add("gauche");
					}
				}
				else if(yDiff<0) { // station en haut a gauche
					
				}
				else { // station en bas a gauche
					
				}
			}
			else {
				if(yDiff==0) { // station a droite
					if(map[coordY][coordX+v.getVitesse()]>=1) { // il y a une route ou une station
						int[] temp = {coordX+v.getVitesse(),coordY};
						coordX+=v.getVitesse();
						trajectoire.add("droite");
					}
				}
				else if(yDiff<0) { // station en haut a droite
					
				}
				else { // station en bas a droite
					
				}
			}
			
		}
		
		
		v.setTrajectoire(trajectoire);
		//this.trajectoireVoitures.add(v.getNumero(), trajectoire);
	}*/
	
	/*public String transformTrajectToDirection(Voiture v) {
		String dir="";
		if(this.trajectoireVoitures.get(v.getNumero()).isEmpty()) {
			return dir;
		}
		int xDiff = v.getX() - this.trajectoireVoitures.get(v.getNumero()).get(0)[0];
		int yDiff = v.getY() - this.trajectoireVoitures.get(v.getNumero()).get(0)[1];
		this.trajectoireVoitures.get(v.getNumero()).removeFirst();
		
		if(xDiff == 0) {
			if(yDiff>0) {
				return "haut";
			}
			else if(yDiff<0) {
				return "bas";
			}
		}
		else if (yDiff ==0) {
			if(xDiff>0) {
				return "gauche";
			}
			else if(xDiff<0) {
				return "droite";
			}
		}
		
		
		
		return dir;
	}*/
	
	public boolean detecteCarrefour(Voiture v) { // regarde ï¿½ l'avance si la voiture va arriver dans un carrefour pour adapter sa vitesse
		//Voiture(int v,int n,String StatDep, String StatArr,int x, int y,String s)
		Voiture voitTemp = new Voiture(v.getVitesse(),v.getNumero(),v.getStatDep(),v.getStatArr(),v.getX(),v.getY(),v.getSens());
		// si detecte carrefour : vitesse--
		
		//on considï¿½re qu'on est en ligne droite
		switch(voitTemp.getSens()) {
		case 0 : // haut
			for(int i=1;i<=6;i++) {
				if(v.getY()-i>=0 && mapCarrefour[v.getY()-i][v.getX()]) {
					return true;
				}
			}
			
			break;
		case 1 : // gauche
			for(int i=1;i<=6;i++) {
				if(v.getX()-i>=0 && mapCarrefour[v.getY()][v.getX()-i]) {
					return true;
				}
			}
			break;
		case 2 : //bas
			for(int i=1;i<=6;i++) {
				if(v.getY()+i<mapCarrefour.length && mapCarrefour[v.getY()+i][v.getX()]) {
					return true;
				}
			}
			
			break;
		case 3 : //droite
			for(int i=1;i<=6;i++) {
				if(v.getX()+i<mapCarrefour[0].length && mapCarrefour[v.getY()][v.getX()+i]) {
					return true;
				}
			}
			break;
		}
		
		return false;
	}
	
	public boolean[][] getMapCarrefour(){
		return this.mapCarrefour;
	}
	
	public void sauvegardeVoiture() {
		for(Voiture voit : this.listeVoiture) {
			this.listeSauvegardeVoiture.add(new Voiture(voit.getVitesse(),voit.getNumero(),voit.getStatDep(),voit.getStatArr(),voit.getX(),voit.getY(),voit.getSens()));
		}
	}
	
	public void checkChangementVoie() {
		
	}
	
	
	// calcul chemin le plus court : le fait au tout dï¿½but et stocke les trajectoires dans un tableau
	
	//rajouter conditions vï¿½rification des limites
	
	public void calculCheminCourt() { // on considere qu'il y a assez de routes etc...
		//n(n-1) : nombre de trajectoires
		System.out.println("se lance");
		
		trajectoires = new ArrayList<ArrayList<ArrayList<String>>>(); // format : .get(i).get(j) : pour aller de i ï¿½ j
		
		//test remplissage
		for(int a=0;a<listeStation.size();a++) {
			trajectoires.add(new ArrayList<ArrayList<String>>());
		}
		for(int a=0;a<listeStation.size();a++) {
			for(int k=0;k<listeStation.size();k++) {
				trajectoires.get(a).add(new ArrayList<String>());
			}
		}
		
		for(int i=0;i<listeStation.size();i++) {
			//lorsque on a calculï¿½ i->j pas besoin de recalculer j->i : se fait en sens inverse
			//on se fixe le fait qu'il n'y ait qu'une seule arrivï¿½e/dï¿½part d'une station ?
			for(int j=0;j<listeStation.size();j++) {
				if(i!=j) {
					if(false) { // deja calculï¿½ i->j ?     // !trajectoires.get(i).get(j).isEmpty()
						for(String s : trajectoires.get(i).get(j)) {
							trajectoires.get(j).get(i).add(s);
						}
					}
					else{
						//pour l'instant station sur les bords et pas dans les coins
						Voiture vVirtuel = new Voiture(1,listeStation.get(i).getXDepart(),listeStation.get(i).getYDepart(),0);
						System.out.println("vVirtuel crï¿½ï¿½e en x = "+vVirtuel.getX()+"  y = "+vVirtuel.getY());
						
						if(listeStation.get(i).getXDepart()==1) { // station sur le bord gauche
							vVirtuel.setSens(3);
						}
						else if(listeStation.get(i).getXDepart()==this.map[0].length-2) { // station sur le bord droit
							vVirtuel.setSens(1);
						}
						else if(listeStation.get(i).getYDepart()==1) { // station sur le bord haut
							vVirtuel.setSens(2);
						}
						else if(listeStation.get(i).getYDepart()==this.map.length-2) { // station sur le bord bas
							vVirtuel.setSens(0);
						}
						System.out.println("sens voiture : "+vVirtuel.getSens());
						
						Voiture temp = new Voiture(1,vVirtuel.getX(),vVirtuel.getY(),vVirtuel.getSens());
						temp.getTrajectoire().add("avant");
						temp.avance(); // a voir si utilise tableau de trajectoire
						while(temp.getX()>0 && temp.getX()<map[0].length && temp.getY()>0 && temp.getY()<map.length && !(temp.getX()==listeStation.get(j).getXArrive() && temp.getY()==listeStation.get(j).getYArrive())) {
							System.out.println("boucle pour i = "+i+"   j = "+j);
							System.out.println("temp x = "+temp.getX()+"   temp y = "+temp.getY());
							if(mapCarrefour[temp.getY()][temp.getX()]) {//il y a un carrefour
								boolean peutContinuer =false;
								System.out.println("carrefour");
								int xDiff=listeStation.get(j).getXArrive()-temp.getX();
								int yDiff=listeStation.get(j).getYArrive()-temp.getY();
								
								if(Math.abs(xDiff)==0 || Math.abs(xDiff)==3 && false) { // directement en haut ou en bas
									if(yDiff>0) { // en bas
										switch(vVirtuel.getSens()) {
										case 0: // normalement ce cas n'arrive pas
											
											break;
										case 1:
											vVirtuel.virage("gauche");
											break;
										case 2:
											vVirtuel.virage("avant");
											break;
										case 3:
											vVirtuel.virage("droite");
											break;
										}
									}
									else { // en haut
										switch(vVirtuel.getSens()) {
										case 0:
											vVirtuel.virage("avant");
											break;
										case 1:
											vVirtuel.virage("droite");
											break;
										case 2:
											
											break;
										case 3:
											vVirtuel.virage("gauche");
											break;
										}
									}
								}
								else if(Math.abs(yDiff)==0 || Math.abs(yDiff)==3 && false) { // directement ï¿½ droite ou ï¿½ gauche
									if(xDiff>0) { // ï¿½ droite
										switch(vVirtuel.getSens()) {
										case 0:
											vVirtuel.virage("droite");
											break;
										case 1:
											
											break;
										case 2:
											vVirtuel.virage("gauche");
											break;
										case 3:
											vVirtuel.virage("avant");
											break;
										}
									}
									else { // ï¿½ gauche
										switch(vVirtuel.getSens()) {
										case 0:
											vVirtuel.virage("gauche");
											break;
										case 1:
											vVirtuel.virage("avant");
											break;
										case 2:
											vVirtuel.virage("droite");
											break;
										case 3:
											
											break;
										}
									}
								}
								// station pas directement "en ligne droite"
								
								else if(xDiff>=0 && yDiff>=0) { // en bas ï¿½ droite
									switch(vVirtuel.getSens()) {
									case 0:
										//juste vï¿½rifier ï¿½ droite
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()+1<map[0].length &&  map[vVirtuel.getY()-1][vVirtuel.getX()+1]==1) { // il y a une route qui continue
											//vï¿½rifier si il y a un autre carrefour
											for(int a=vVirtuel.getX()+1;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) { // il y a un autre carrefour
													vVirtuel.virage("droite");
													break;
												}
											}
										}
										break;
									case 1:
										//juste vï¿½rifier en bas
										if(vVirtuel.getY()+4<map.length && vVirtuel.getX()-1 >0 && map[vVirtuel.getY()+4][vVirtuel.getX()-1]==1) {
											for(int a=vVirtuel.getY()+4;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("gauche"); // voir bookmark
													break;
												}
											}
										}
										break;
										
									case 2:
										if(vVirtuel.getY()+5<map.length && map[vVirtuel.getY()+5][vVirtuel.getX()]==1) { // en bas
											for(int a=vVirtuel.getY()+5;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
										}
										else if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+4<map[0].length && map[vVirtuel.getY()+1][vVirtuel.getX()+4]==1) { // ï¿½ droite
											for(int a=vVirtuel.getX()+4;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
										}
										break;
										
									case 3:
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+1<map[0].length &&map[vVirtuel.getY()+1][vVirtuel.getX()+1]==1) { // en bas
											for(int a=vVirtuel.getY()+1;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()+1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
										}
										else if(vVirtuel.getX()+5<map[0].length && map[vVirtuel.getY()][vVirtuel.getX()+5]==1) { // ï¿½ droite
											for(int a=vVirtuel.getX()+5;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
										}
										break;
									}
								}
								else if(xDiff<=0 && yDiff>=0) { // en bas ï¿½ gauche
									switch(vVirtuel.getSens()) {
									case 0:
										//vï¿½rifier ï¿½ gauche
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()-4>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-4]==1) {
											for(int a=vVirtuel.getX()-4;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
										}
										break;
									case 1:
										if(vVirtuel.getY()+4<map.length && vVirtuel.getX()-1>0 && map[vVirtuel.getY()+4][vVirtuel.getX()-1]==1) { // en bas
											for(int a=vVirtuel.getY()+4;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getX()-5>0 && map[vVirtuel.getY()][vVirtuel.getX()-5]==1) { // ï¿½ gauche
											for(int a=vVirtuel.getX()-5;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										break;
									case 2:
										if(vVirtuel.getY()+5<map.length && map[vVirtuel.getY()+5][vVirtuel.getX()]==1) { // en bas
											for(int a=vVirtuel.getY()+5;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getY()+1<map.length && vVirtuel.getX()-1>0 && map[vVirtuel.getY()+1][vVirtuel.getX()-1]==1) { // ï¿½ gauche
											for(int a=vVirtuel.getX()-1;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 3:
										//vï¿½rifier en bas
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()+1][vVirtuel.getX()+1]==1) {
											for(int a=vVirtuel.getY()+1;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()+1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									}
								}
								else if(xDiff>=0 && yDiff<=0) { // en haut ï¿½ droite
									switch(vVirtuel.getSens()) {
									case 0:
										if(vVirtuel.getY()-5>0 && map[vVirtuel.getY()-5][vVirtuel.getX()]==1) { // en haut
											for(int a=vVirtuel.getY()-5;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getY()-1>0 && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()-1][vVirtuel.getX()+1]==1) { // ï¿½ droite
											for(int a=vVirtuel.getX()+1;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 1:
										//vï¿½rifier en haut
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()-1>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-1]==1) {
											for(int a=vVirtuel.getY()-1;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 2:
										//vï¿½rifier ï¿½ droite
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+4<map[0].length && map[vVirtuel.getY()+1][vVirtuel.getX()+4]==1) {
											for(int a=vVirtuel.getX()+4;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													System.out.println("BON VIRAGE");
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										break;
									case 3:
										if(vVirtuel.getY()-4>0 && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()-4][vVirtuel.getX()+1]==1) { // en haut
											for(int a=vVirtuel.getY()-4;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()+1]||(listeStation.get(j).getXArrive()==vVirtuel.getX()+4&&listeStation.get(j).getYArrive()==a)) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getX()+5<map[0].length && map[vVirtuel.getY()][vVirtuel.getX()+5]==1) { // ï¿½ droite
											for(int a=vVirtuel.getX()+5;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										break;
									}
								}
								else if(xDiff<=0 && yDiff<=0) { // en haut à gauche
									switch(vVirtuel.getSens()) {
									case 0:
										if(vVirtuel.getY()-5>0 && map[vVirtuel.getY()-5][vVirtuel.getX()]==1) { // en haut
											for(int a=vVirtuel.getY()-5;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getY()-1>0 && vVirtuel.getX()-4>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-4]==1) { // ï¿½ gauche
											for(int a=vVirtuel.getX()-4;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										break;
									case 1:
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()-1>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-1]==1) { // en haut
											for(int a=vVirtuel.getY()-1;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getX()-5>0 && map[vVirtuel.getY()][vVirtuel.getX()-5]==1) { // ï¿½ gauche
											for(int a=vVirtuel.getX()-5;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										break;
									case 2:
										//vï¿½rifier ï¿½ gauche
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()-1>0 && map[vVirtuel.getY()+1][vVirtuel.getX()-1]==1) {
											for(int a=vVirtuel.getX()-1;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 3:
										//vï¿½rifier en haut
										if(vVirtuel.getY()-4>0 && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()-4][vVirtuel.getX()+1]==1) {
											for(int a=vVirtuel.getY()-4;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()+1]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										break;
									}
								}
								// on a les trajectoires stockï¿½es dans vVirtuel
								//penser ï¿½ changer le sens
								
								boolean avant = true;
								boolean gauche = false;
								boolean droite = false;
								System.out.println("trajectoire stockï¿½e :");
								for(int a=0;a<vVirtuel.getTrajectoire().size();a++) {
									System.out.print(vVirtuel.getTrajectoire().get(a)+" ");
								}
								System.out.println();
								
								for(int a=0;a<vVirtuel.getTrajectoire().size();a++) {
									if(vVirtuel.getTrajectoire().get(a)=="avant") {
										trajectoires.get(i).get(j).add("avant");
									}
									else if(vVirtuel.getTrajectoire().get(a)=="gauche") {
										trajectoires.get(i).get(j).add("gauche");
										//trajectoires.get(i).get(j).add("avant");
										gauche = true;
										avant = false;
									}
									else if(vVirtuel.getTrajectoire().get(a)=="droite") {
										trajectoires.get(i).get(j).add("droite");
										droite = true;
										avant = false;
									}
								}
								vVirtuel.getTrajectoire().clear();
								if(avant) {
									switch(vVirtuel.getSens()) {
									case 0:
										vVirtuel.setY(vVirtuel.getY()-5);
										break;
									case 1:
										vVirtuel.setX(vVirtuel.getX()-5);
										break;
									case 2:
										vVirtuel.setY(vVirtuel.getY()+5);
										break;
									case 3:
										vVirtuel.setX(vVirtuel.getX()+5);
										break;
									}
								}
								else if(gauche) {
									switch(vVirtuel.getSens()) {
									case 0:
										vVirtuel.setX(vVirtuel.getX()-4);
										vVirtuel.setY(vVirtuel.getY()-4);
										break;
									case 1:
										vVirtuel.setX(vVirtuel.getX()-4);
										vVirtuel.setY(vVirtuel.getY()+4);
										break;
									case 2:
										vVirtuel.setX(vVirtuel.getX()+4);
										vVirtuel.setY(vVirtuel.getY()+4);
										break;
									case 3:
										vVirtuel.setX(vVirtuel.getX()+4);
										vVirtuel.setY(vVirtuel.getY()-4);
										break;
									}
									//change le sens
									vVirtuel.setSens((vVirtuel.getSens()+1)%4);
								}
								else if(droite) {
									switch(vVirtuel.getSens()) {
									case 0:
										vVirtuel.setX(vVirtuel.getX()+1);
										vVirtuel.setY(vVirtuel.getY()-1);
										break;
									case 1:
										vVirtuel.setX(vVirtuel.getX()-1);
										vVirtuel.setY(vVirtuel.getY()-1);
										break;
									case 2:
										vVirtuel.setX(vVirtuel.getX()-1);
										vVirtuel.setY(vVirtuel.getY()+1);
										break;
									case 3:
										vVirtuel.setX(vVirtuel.getX()+1);
										vVirtuel.setY(vVirtuel.getY()+1);
										break;
									}
									//change le sens
									vVirtuel.setSens(vVirtuel.getSens()-1);
									if(vVirtuel.getSens()<0) {
										vVirtuel.setSens(vVirtuel.getSens()+4);
									}
								}
								System.out.println("avant recopie :  x = "+vVirtuel.getX()+"   y = "+vVirtuel.getY());
								temp = new Voiture(1,vVirtuel.getX(),vVirtuel.getY(),vVirtuel.getSens());
								temp.getTrajectoire().add("avant");
								temp.avance();
							}
							else {
								System.out.println(trajectoires.size());
								System.out.println(trajectoires.get(i).size());
								trajectoires.get(i).get(j).add("avant");
								vVirtuel.getTrajectoire().add("avant");
								vVirtuel.avance();
								temp.getTrajectoire().add("avant");
								temp.avance();
							}
							
							
							
							
							/*while(!mapCarrefour[temp.getY()][temp.getX()] && !(temp.getX()==listeStation.get(j).getXArrive() && temp.getY()==listeStation.get(j).getYArrive())) {
								//tant qu'il n'y a pas un carrefour ou que l'on n'a pas atteint la station
								trajectoires.get(i).get(j).add("avant");
								vVirtuel.avance();
								temp.avance();
							}*/	
							
						}
						
					}
				}
			}
		}
		
		for(int i=0;i<trajectoires.size();i++) {
			System.out.println();
			System.out.println("Station de dï¿½part : "+i);
			for(int j=0;j<trajectoires.get(i).size();j++) {
				System.out.println();
				System.out.println("Station d'arrivï¿½e : "+j);
				System.out.println();
				for(int k=0;k<trajectoires.get(i).get(j).size();k++) {
					System.out.print(trajectoires.get(i).get(j).get(k)+" ");
				}
			}
		}
		System.out.println("fin");
	}
	
	public void sortieCarrefour(Voiture v) {
		switch(v.getSens()) {
		case 0:
			v.setSortCarrefour(this.mapCarrefour[v.getY()+1][v.getX()] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		case 1:
			v.setSortCarrefour(this.mapCarrefour[v.getY()][v.getX()+1] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		case 2:
			v.setSortCarrefour(this.mapCarrefour[v.getY()-1][v.getX()] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		case 3:
			v.setSortCarrefour(this.mapCarrefour[v.getY()][v.getX()-1] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		}
	}
	public void changeMap(int[][] m) {
		this.map=m;
	}
	public void adapteSensVoiture(Voiture v) { // a changer si station pas uniquement sur le bord
		if(map[v.getY()][v.getX()]==2) { // la voiture est sur une station
			if(map[v.getY()-1][v.getX()]==1) {
				v.setSens(0);
			}
			else if(map[v.getY()+1][v.getX()]==1) {
				v.setSens(2);
			}
			else if(map[v.getY()][v.getX()-1]==1) {
				v.setSens(1);
			}
			else if(map[v.getY()][v.getX()+1]==1) {
				v.setSens(3);
			}
		}
	}
	
	public char[][] reconnaissanceRoute() { // on peut ï¿½galement s'en servir pour dï¿½tecter les routes horizontales et verticales ?
		char[][] mapC = new char[map.length][map[0].length];
		boolean routeEnHaut = false;
		boolean routeEnBas = false;
		boolean routeAGauche = false;
		boolean routeADroite = false;
		int compteurRoute=0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j]==2) {
					mapC[i][j]='s';
				}
				else if(map[i][j]==1) {
					compteurRoute=0;
					routeEnHaut=false;
					routeEnBas=false;
					routeAGauche=false;
					routeADroite=false;
					if(i>=4) {
						routeEnHaut=true;
						for(int k=1;k<=4;k++) {
							if(map[i-k][j]!=1) {
								routeEnHaut=false;
								break;
							}
						}
						if(routeEnHaut) {
							compteurRoute++;
						}
					}
					if(i<=map.length-5) {
						routeEnBas=true;
						for(int k=1;k<=4;k++) {
							if(map[i+k][j]!=1) {
								routeEnBas=false;
								break;
							}
						}
						if(routeEnBas) {
							compteurRoute++;
						}
					}
					if(j>=4) {
						routeAGauche = true;
						for(int k=1;k<=4;k++) {
							if(map[i][j-k]!=1) {
								routeAGauche = false;
								break;
							}
						}
						if(routeAGauche) {
							compteurRoute++;
						}
					}
					if(j<=map[i].length-5) {
						routeADroite =true;
						for(int k=1;k<=4;k++) {
							if(map[i][j+k]!=1) {
								routeADroite =false;
								break;
							}
						}
						if(routeADroite) {
							compteurRoute++;
						}
					}
					
					switch(compteurRoute) {
					case 0:
						// Impossible ?
						break;
					case 1:
						// Cas lorsque on est proche d'une station ?
						if(routeADroite || routeAGauche) {
							mapC[i][j]='h';
						}
						else if(routeEnHaut || routeEnBas) {
							mapC[i][j]='v';
						}
						break;
					case 2:
						if((routeEnHaut&&routeAGauche)||(routeEnHaut&&routeADroite)||(routeEnBas&&routeAGauche)||(routeEnBas&&routeADroite)) {
							mapC[i][j]='c';
						}
						else if(routeEnHaut && routeEnBas) {
							mapC[i][j]='v';
						}
						else if(routeADroite && routeAGauche) {
							mapC[i][j]='h';
						}
						break;
					case 3:
						mapC[i][j]='c';
						break;
					case 4 :
						mapC[i][j]='c';
						break;
						default :
							break;
					}
				}
			}
		}
		
		for(int i=0;i<mapC.length;i++) {
			for(int j=0;j<mapC[i].length;j++) {
				System.out.print(mapC[i][j]+" ");
			}
			System.out.println();
		}
		return mapC;
	}
	
	public void simuleCoup() {
		ArrayList<Voiture> listeTemp = new ArrayList<Voiture>();
		for(Voiture v : listeVoiture) {
			Voiture temp = new Voiture(1,v.getX(),v.getY(),v.getSens());
		}
	}
	
	public void convertionMapGraphe() {
		int num=0;
		char[][] detectionRoute = this.reconnaissanceRoute();
		for(int i=0;i<detectionRoute.length;i++) {
			for(int j=0;j<detectionRoute[i].length;j++) {
				if(detectionRoute[i][j]=='s') {
					this.noeuds.add(new NoeudGraphe("station",j,i,num));
					num++;
					if(detectionRoute[i][j+1]=='s') { // station "horizontale"
						for(int k=0;k<=3;k++) {
							detectionRoute[i][j+k]=' ';
						}
					}
					else { // station "verticale"
						for(int k=0;k<=3;k++) {
							detectionRoute[i+k][j]=' ';
						}
					}
				}
				else if(detectionRoute[i][j]=='c') {
					this.noeuds.add(new NoeudGraphe("carrefour",j,i,num));
					num++;
					for(int a=0;a<=3;a++) {
						for(int b=0;b<=3;b++) {
							detectionRoute[i+a][j+b]=' ';
						}
					}
				}
			}
		}
		
		for(NoeudGraphe n : noeuds) {
			n.initNumeroPourRejoindre(noeuds.size());
			if(n.type=="station") {
				this.noeudsStation.add(n);
			}
		}
		
		// calcul des distances : 
		
		detectionRoute = this.reconnaissanceRoute(); // voir si nécessaire ?
		
		for(NoeudGraphe n : noeuds) {
			if(n.getType()=="station") {
				int compteurDistance = 1;
				if(detectionRoute[n.getY()+1][n.getX()]=='v') { // en bas
					while(detectionRoute[n.getY()+compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					// on cherche le noeud atteint :
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				else if(detectionRoute[n.getY()-1][n.getX()]=='v') { // en haut
					while(detectionRoute[n.getY()-compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()-compteurDistance-3) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				else if(detectionRoute[n.getY()][n.getX()+1]=='h') { // a droite
					while(detectionRoute[n.getY()][n.getX()+compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
					
				}
				else if(detectionRoute[n.getY()][n.getX()-1]=='h') { // a gauche
					while(detectionRoute[n.getY()][n.getX()-compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()-compteurDistance-3) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
			}
			else { // carrefour
				int compteurDistance = 1;
				if(detectionRoute[n.getY()+4][n.getX()]=='v') { // en bas
					while(detectionRoute[n.getY()+3+compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()+3+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				
				compteurDistance =1;
				if(detectionRoute[n.getY()-1][n.getX()]=='v') { // en haut
					while(detectionRoute[n.getY()-compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()-compteurDistance-3) { // si c'est un carrefour
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
						else if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()-compteurDistance) { // si c'est une station
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				
				compteurDistance=1;
				if(detectionRoute[n.getY()][n.getX()-1]=='h') { // à gauche
					while(detectionRoute[n.getY()][n.getX()-compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()-compteurDistance-3) { // si c'est un carrefour
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
						else if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()-compteurDistance) { // si c'est une station 
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				
				compteurDistance=1;
				if(detectionRoute[n.getY()][n.getX()+4]=='h') { // à droite
					while(detectionRoute[n.getY()][n.getX()+3+compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()+3+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
			}
		}
		
		for(NoeudGraphe n : noeuds) {
			System.out.println();
			System.out.println("-------------------");
			System.out.println(n);
			System.out.println("Noeuds atteignables : ");
			for(int i=0;i<n.noeudAtteignable.size();i++) {
				System.out.println(n.noeudAtteignable.get(i));
				System.out.println("Distance : "+n.distanceNoeud.get(i));
			}
		}
	}
	
	public void calculCheminGraphe() { // -1 signifiera que il n'y a pas besoin de parcourir
		for(NoeudGraphe n : noeuds) {
			//réinitialisation des distances
			for(NoeudGraphe temp : noeuds) {
				temp.setDistanceActuelle(0);
			}
			TreeSet<NoeudGraphe> noeudsTraites = new TreeSet<NoeudGraphe>();
			LinkedList<NoeudGraphe> aTraite = new LinkedList<NoeudGraphe>();
			aTraite.add(n); // n est le noeud "source"
			while(!aTraite.isEmpty()) {
				noeudsTraites.add(aTraite.getFirst());
				
				for(int i=0;i<aTraite.getFirst().getNoeudAtteignable().size();i++) {
					if(!noeudsTraites.contains(aTraite.getFirst().getNoeudAtteignable().get(i)) && !aTraite.contains(aTraite.getFirst().getNoeudAtteignable().get(i))) {
						aTraite.add(aTraite.getFirst().getNoeudAtteignable().get(i));
					}
					if(aTraite.getFirst().getNoeudAtteignable().get(i)!=n && (aTraite.getFirst().getNoeudAtteignable().get(i).distanceActuellePourRejoindre==0 || aTraite.getFirst().getNoeudAtteignable().get(i).distanceActuellePourRejoindre>aTraite.getFirst().distanceActuellePourRejoindre+aTraite.getFirst().distanceNoeud.get(i))) {
						aTraite.getFirst().getNoeudAtteignable().get(i).distanceActuellePourRejoindre=aTraite.getFirst().distanceActuellePourRejoindre+aTraite.getFirst().distanceNoeud.get(i);
						aTraite.getFirst().getNoeudAtteignable().get(i).setNumeroPourRejoindre(n.numeroNoeud, aTraite.getFirst().numeroNoeud);
					}
				}
				aTraite.removeFirst();
			}
			
		}
	}
	
	public void conversionTrajectoire() {
		trajectoires = new ArrayList<ArrayList<ArrayList<String>>>(); // format : .get(i).get(j) : pour aller de i a j
		
		for(int a=0;a<listeStation.size();a++) {
			trajectoires.add(new ArrayList<ArrayList<String>>());
		}
		for(int a=0;a<listeStation.size();a++) {
			for(int k=0;k<listeStation.size();k++) {
				trajectoires.get(a).add(new ArrayList<String>());
			}
		}
		
		for(int i=0;i<noeudsStation.size();i++) {
			NoeudGraphe noeudAAtteindre = noeudsStation.get(i);
			for(int j=0;j<noeudsStation.size();j++) {
				boolean premiereLigneDroite = true; // on met en place le boolean car dans la premiere ligne droite on ajoute un nombre de "avant" correspondant à la distance alors qu'apres c'est distance-1
				if(noeudAAtteindre!=noeudsStation.get(j)) {
					NoeudGraphe noeudActuel = noeudsStation.get(j);
					NoeudGraphe noeudDepart = noeudsStation.get(j);
					while(noeudActuel!=noeudAAtteindre) {
						NoeudGraphe noeudProchaineEtape = noeuds.get(noeudActuel.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]);
						
						//Recherche de la distance :
						int distance=0;
						for(int k=0;k<noeudActuel.noeudAtteignable.size();k++) {
							if(noeudActuel.noeudAtteignable.get(k)==noeudProchaineEtape) {
								distance = noeudActuel.distanceNoeud.get(k);
							}
						}
						
						if(premiereLigneDroite) {
							for(int k=0;k<distance;k++) {
								//System.out.println("NoeudAAtteindre : "+noeudAAtteindre.getNumeroNoeud()+"   noeudDepart : "+noeudDepart.getNumeroNoeud());
								//test
								//System.out.println("ajout avant pour i = "+i+"  j = "+j);
								trajectoires.get(j).get(i).add("avant");
							}
							premiereLigneDroite=false;
						}
						else {
							for(int k=0;k<distance-1;k++) {
								trajectoires.get(j).get(i).add("avant");
							}
						}
						
						// Recherche direction virage :
						if(noeudProchaineEtape.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]!=-1) {
							Voiture temp = new Voiture();
							int x1=noeudProchaineEtape.getX()-noeudActuel.getX();
							int y1=noeudProchaineEtape.getY()-noeudActuel.getY();
							int x2=noeuds.get(noeudProchaineEtape.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]).getX()-noeudProchaineEtape.getX();
							int y2=noeuds.get(noeudProchaineEtape.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]).getY()-noeudProchaineEtape.getY();
							
							if(x1>0) { // 1 : avant -> pendant       2 : pendant -> après
								if(x2>0) {
									temp.virage("avant");
								}
								else if(x2<0) {
									System.out.println("normalement impossible");
								}
								else if(y2>0) {
									temp.virage("droite");
								}
								else if(y2<0) {
									temp.virage("gauche");
								}
							}
							else if(x1<0) {
								if(x2>0) {
									System.out.println("normalement impossible");
								}
								else if(x2<0) {
									temp.virage("avant");
								}
								else if(y2>0) {
									temp.virage("gauche");
								}
								else if(y2<0) {
									temp.virage("droite");
								}
							}
							else if(y1>0) {
								if(x2>0) {
									//System.out.println("rentre pour i = "+i+" j = "+j);
									temp.virage("gauche");
								}
								else if(x2<0) {
									temp.virage("droite");
								}
								else if(y2>0) {
									temp.virage("avant");
								}
								else if(y2<0) {
									System.out.println("normalement impossible");
								}
							}
							else if(y1<0) {
								if(x2>0) {
									temp.virage("droite");	
								}
								else if(x2<0) {
									temp.virage("gauche");
								}
								else if(y2>0) {
									System.out.println("normalement impossible");
								}
								else if(y2<0) {
									temp.virage("avant");
								}
							}
							for(String s : temp.getTrajectoire()) {
								trajectoires.get(j).get(i).add(s);
							}
						}
						
						noeudActuel = noeudProchaineEtape;
					}
				}
			}
		}
	}
}