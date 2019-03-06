import java.util.ArrayList;
import java.util.LinkedList;

public class Reseau {
	private ArrayList<Voiture> listeVoiture;
	private int[][] map;
	private boolean[][] mapVoiture;
	//boolean pour les voitures
	// 0 vide 1 route 2 station
	private ArrayList<Station> listeStation;
	private LinkedList<int[]> coordVoiture;
	private LinkedList<boolean[][]> listeMapAvance;
	
	public Reseau() {
		map = new int[15][15];
		mapVoiture = new boolean[15][15];
		listeVoiture = new ArrayList<Voiture>();
		listeStation = new ArrayList<Station>();
		listeMapAvance = new LinkedList<boolean[][]>();
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
	
	public boolean actualiseMapVoiture() { // renvoie true si il n'y a pas de collision(s) dans la prévision
		//peut etre renvoyer l'indice de la voiture qui pose problème, et -1 si c'est bon ?
		for(Voiture v : listeVoiture) {
			v.avance(); 
		}
		// intéressant d'avoir les coords dans la classe Voiture
		for(Voiture vi : listeVoiture) {
			for(Voiture vj : listeVoiture) {
				if(vi!=vj) {
					if(vi.coord = vj.coord) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
