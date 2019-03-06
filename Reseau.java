import java.util.ArrayList;
import java.util.LinkedList;

public class Reseau {
	private ArrayList<Voiture> listeVoiture;
	private int[][] map; // 0 : vide  /  1 : route  /  2 : station
	private boolean[][] mapVoiture; // true si il y a une voiture
	private ArrayList<Station> listeStation;
	private LinkedList<int[]> coordVoiture; // inutile car coord dans voiture ?
	private LinkedList<boolean[][]> listeMapCalculEnAvance;
	
	public Reseau() {
		map = new int[15][15];
		mapVoiture = new boolean[15][15];
		listeVoiture = new ArrayList<Voiture>();
		listeStation = new ArrayList<Station>();
		listeMapCalculEnAvance = new LinkedList<boolean[][]>();
		coordVoiture = new LinkedList<int[]>();
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
	
	public int actualiseMapVoiture() { // renvoie true si il n'y a pas de collision(s) dans la prévision
		//peut etre renvoyer l'indice de la voiture qui pose problème, et -1 si c'est bon ?
		for(Voiture v : listeVoiture) {
			v.avance(); // la voiture pourrait avoir un attribut char qui donne la direction
		}
		// intéressant d'avoir les coords dans la classe Voiture
		for(int i=0;i<listeVoiture.size();i++) {
			for(int j=i+1;j<listeVoiture.size();j++) { // pas besoin de commencer à 0 car on a déjà vérifier
				if(listeVoiture.get(i).getX() == listeVoiture.get(j).getX() && listeVoiture.get(i).getY() == listeVoiture.get(j).getY()) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public LinkedList<int[]> calculPlusCourtChemin(Voiture v, Station s) {
		LinkedList<int[]> trajectoire = new LinkedList<int[]>();
		while(v.getX()!=s.getXStation() && v.getY()!=s.getYStation()) {
			int xDiff = s.getXStation() - v.getX();
			int yDiff = s.getYStation() - v.getY();
			
			if(xDiff==0) {
				if(yDiff<0) { // station en haut
					if(map[v.getY()-v.getVitesse()][v.getX()]==1) { // il y a une route
						int[] temp = {v.getX(),v.getY()-v.getVitesse()};
						trajectoire.add(temp);
					}
				}
				else { // station en bas
					if(map[v.getY()+v.getVitesse()][v.getX()]==1) { // il y a une route
						int[] temp = {v.getX(),v.getY()+v.getVitesse()};
						trajectoire.add(temp);
					}
				}
			}
			else if(xDiff<0) {
				if(yDiff==0) { // station a gauche
					if(map[v.getY()][v.getX()-v.getVitesse()]==1) { // il y a une route
						int[] temp = {v.getX()-v.getVitesse(),v.getY()};
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
					if(map[v.getY()][v.getX()+v.getVitesse()]==1) { // il y a une route
						int[] temp = {v.getX()+v.getVitesse(),v.getY()};
						trajectoire.add(temp);
					}
				}
				else if(yDiff<0) { // station en haut a droite
					
				}
				else { // station en bas a droite
					
				}
			}
			
		}
		
		
		
		return trajectoire;
	}
}
