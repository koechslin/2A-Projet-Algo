import java.util.ArrayList;
import java.util.LinkedList;

public class Reseau {
	private ArrayList<Voiture> listeVoiture;
	// 0 : vide  /  1 : route  /  2 : station
	// true si il y a une voiture
	
	
	private int[][] map = {{0,0,0,0,0,0,2,2,0,0,0,0,0},
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
	
	private boolean[][] mapVoiture = {{false,false,false,false,false,false,true,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,true},
						             {true,false,false,false,false,true,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,false,false,false,false,false,false},
						             {false,false,false,false,false,false,false,true,false,false,false,false,false}};
	
	
	
	
	
	private ArrayList<Station> listeStation;
	public LinkedList<LinkedList<int[]>> trajectoireVoitures;
	private LinkedList<boolean[][]> listeMapCalculEnAvance;
	
	public Reseau() {
		listeVoiture = new ArrayList<Voiture>();
		listeStation = new ArrayList<Station>();
		listeMapCalculEnAvance = new LinkedList<boolean[][]>();
		trajectoireVoitures = new LinkedList<LinkedList<int[]>>();
		
		//création des stations
		int k=0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j]==2) {
					listeStation.add(new Station(k,j,i));
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
		
		//gérer la collision
		
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
	
	public void calculPlusCourtChemin(Voiture v, Station s) {
		//gérer les virages
		int coordX = v.getX();
		int coordY = v.getY();
		
		LinkedList<int[]> trajectoire = new LinkedList<int[]>();
		while(coordX!=s.getXStation() || coordY!=s.getYStation()) {
			int xDiff = s.getXStation() - coordX;
			int yDiff = s.getYStation() - coordY;
			//System.out.println("xDiff = "+xDiff+"  yDiff = "+yDiff);
			
			if(xDiff==0) {
				if(yDiff<0) { // station en haut
					if(map[coordY-v.getVitesse()][coordX]>=1) { // il y a une route ou une station
						int[] temp = {coordX,coordY-v.getVitesse()};
						coordY-=v.getVitesse();
						trajectoire.add(temp);
					}
				}
				else { // station en bas
					if(map[coordY+v.getVitesse()][coordX]>=1) { // il y a une route ou une station
						int[] temp = {coordX,coordY+v.getVitesse()};
						coordY+=v.getVitesse();
						trajectoire.add(temp);
					}
				}
			}
			else if(xDiff<0) {
				if(yDiff==0) { // station a gauche
					if(map[coordY][coordX-v.getVitesse()]>=1) { // il y a une route ou une station
						int[] temp = {coordX-v.getVitesse(),coordY};
						coordX-=v.getVitesse();
						trajectoire.add(temp);
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
						trajectoire.add(temp);
					}
				}
				else if(yDiff<0) { // station en haut a droite
					
				}
				else { // station en bas a droite
					
				}
			}
			
		}
		
		
		
		this.trajectoireVoitures.add(v.getNumero(), trajectoire);
	}
	
	public String transformTrajectToDirection(Voiture v) {
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
	}
}
