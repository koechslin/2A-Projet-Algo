import java.util.ArrayList;
import java.util.LinkedList;

public class Reseau {
	private ArrayList<Voiture> listeVoiture;
	// 0 : vide  /  1 : route  /  2 : station
	// true si il y a une voiture
	
	
	public int[][] map = {{0,0,0,0,0,0,2,2,0,0,0,0,0},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {2,1,1,1,1,1,1,1,1,1,1,1,2},
					   	  {2,1,1,1,1,1,1,1,1,1,1,1,2},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {0,0,0,0,0,0,1,1,0,0,0,0,0},
					   	  {0,0,0,0,0,0,2,2,0,0,0,0,0}};
	
	public boolean[][] mapVoiture = {{false,false,false,false,false,false,true,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,true},
						             {true,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,true,false,false,false,false,false}};
	
	public boolean[][] mapCarrefour;
	
	// sauvegarde afin de comparer avant/après et lancer si besoin la méthode changerVoie de voiture
	private ArrayList<Voiture> listeSauvegardeVoiture;
	
	
	
	private ArrayList<Station> listeStation;
	public LinkedList<LinkedList<int[]>> trajectoireVoitures;
	private LinkedList<boolean[][]> listeMapCalculEnAvance;
	
	public Reseau() {
		listeVoiture = new ArrayList<Voiture>();
		listeSauvegardeVoiture = new ArrayList<Voiture>();
		listeStation = new ArrayList<Station>();
		listeMapCalculEnAvance = new LinkedList<boolean[][]>();
		trajectoireVoitures = new LinkedList<LinkedList<int[]>>();
		mapCarrefour = new boolean[map.length][map[0].length];
		
		//création des stations
		int k=0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j]==2) {
					listeStation.add(new Station(k,j,i)); // Comment détecter départ et arrivée ?
					k++;
				}
			}
		}
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
	
	public int actualiseMapVoiture() { // renvoie -1 si il n'y a pas de collision(s) dans la prévision sinon renvoie l'indice de la voiture qui pose problème
		
		for(Voiture v : listeVoiture) {
			mapVoiture[v.getY()][v.getX()]=false;
			v.avance();
		}
		sauvegardeVoiture();
		
		//gérer la collision
		
		// PROBLEME SI VITESSE ELEVEE
		
		for(int i=0;i<listeVoiture.size();i++) {
			for(int j=i+1;j<listeVoiture.size();j++) { // pas besoin de commencer à 0 car on a déjà vérifié
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
	
	public void calculPlusCourtChemin(Voiture v, Station s) { // 3 directions possibles : avant gauche droite
		
		// A MODIFIER : VERIFIER LE SENS
		
		//gérer les virages
		// Voir si ça marche car peut ne pas fonctionner à cause du sens de la voiture
		
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
	}
	
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
	
	public boolean detecteCarrefour(Voiture v) { // regarde à l'avance si la voiture va arriver dans un carrefour pour adapter sa vitesse
		//Voiture(int v,int n,String StatDep, String StatArr,int x, int y,String s)
		Voiture voitTemp = new Voiture(v.getVitesse(),v.getNumero(),v.getStatDep(),v.getStatArr(),v.getX(),v.getY(),v.getSens());
		// si detecte carrefour : vitesse--
		
		//on considère qu'on est en ligne droite
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
	
	
	// calcul chemin le plus court : le fait au tout début et stocke les trajectoires dans un tableau
	
	public void calculCheminCourt() {
		String sensParcours="";
		//n(n-1) : nombre de trajectoires
		ArrayList<ArrayList<String>> trajectoires = new ArrayList<ArrayList<String>>(); // format : .get(i).get(j) : pour aller de i à j
		for(int i=0;i<listeStation.size();i++) {
			//lorsque on a calculé i->j pas besoin de recalculer j->i : se fait en sens inverse
			//on se fixe le fait qu'il n'y ait qu'une seule arrivée/départ d'une station ?
			
			//pour l'instant station sur les bords et pas dans les coins
			
			if(listeStation.get(i).getXDepart()==0) { // station sur le bord gauche
				
			}
			else if(listeStation.get(i).getXDepart()==this.map[0].length-1) { // station sur le bord droit
				
			}
			else if(listeStation.get(i).getYDepart()==0) {
				
			}
			else if(listeStation.get(i).g)
		}
		
		
		
	}
}
