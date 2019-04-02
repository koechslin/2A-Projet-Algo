import java.util.ArrayList;

public class NoeudGraphe implements Comparable<NoeudGraphe>{
	String type; // station ou carrefour
	ArrayList<NoeudGraphe> noeudAtteignable;
	ArrayList<Integer> distanceNoeud;
	int xNoeud;
	int yNoeud;
	int numeroNoeud;
	int distanceActuellePourRejoindre;
	int[] numeroNoeudPourRejoindre;
	
	public NoeudGraphe(String t,int x,int y,int num) {
		this.type=t;
		this.noeudAtteignable=new ArrayList<NoeudGraphe>();
		this.distanceNoeud=new ArrayList<Integer>();
		this.xNoeud=x;
		this.yNoeud=y;
		this.numeroNoeud=num;
		this.distanceActuellePourRejoindre = 0; // signifie qu'il n'est pas encore atteint
		
	}
	
	public ArrayList<NoeudGraphe> getNoeudAtteignable(){
		return this.noeudAtteignable;
	}
	public ArrayList<Integer> getDistanceNoeud(){
		return this.distanceNoeud;
	}
	public String getType() {
		return this.type;
	}
	public int getX() {
		return this.xNoeud;
	}
	public int getY() {
		return this.yNoeud;
	}
	public void ajouterNoeudAtteignable(NoeudGraphe n, int distance) {
		this.noeudAtteignable.add(n);
		this.distanceNoeud.add(distance);
	}
	
	public String toString() {
		return "Noeud numéro "+this.numeroNoeud+" de type : "+this.type+", de coordonnées : x = "+this.xNoeud+", y = "+this.yNoeud;
	}
	public int getNumeroNoeud() {
		return this.numeroNoeud;
	}
	public int getDistanceActuelle() {
		return this.distanceActuellePourRejoindre;
	}
	public void setNumeroNoeud(int n) {
		this.numeroNoeud=n;
	}
	public void setDistanceActuelle(int d) {
		this.distanceActuellePourRejoindre=d;
	}
	public int[] getNumeroPourRejoindre() {
		return this.numeroNoeudPourRejoindre;
	}
	public void initNumeroPourRejoindre(int nbNoeud) {
		this.numeroNoeudPourRejoindre=new int[nbNoeud];
		for(int i=0;i<nbNoeud;i++) {
			this.numeroNoeudPourRejoindre[i]=-1; // signifie qu'on ne l'a pas atteint
		}
	}
	public void setNumeroPourRejoindre(int numNoeudSource,int numNoeudPrecedent) { // numNoeudSource : noeud duquel on part au tout début / numNoeudPrecedent : noeud depuis lequel il faut arriver pour le chemin le plus court
		this.numeroNoeudPourRejoindre[numNoeudSource]=numNoeudPrecedent;
	}
	public int compareTo(NoeudGraphe n) {
		if(this.numeroNoeud>n.numeroNoeud) {
			return 1;
		}
		else if(this.numeroNoeud==n.numeroNoeud) {
			return 0;
		}
		else{
			return -1;
		}
	}
}
