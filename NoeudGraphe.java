import java.util.ArrayList;

public class NoeudGraphe implements Comparable<NoeudGraphe>{
	private String type; // station ou carrefour
	private ArrayList<NoeudGraphe> noeudAtteignable;
	private ArrayList<Integer> distanceNoeud;
	private int xNoeud;
	private int yNoeud;
	private int numeroNoeud;
	private int distanceActuellePourRejoindre;
	private int[] numeroNoeudPourRejoindre;
	
	public NoeudGraphe(String t,int x,int y,int num) {
		this.type = t;
		this.noeudAtteignable = new ArrayList<NoeudGraphe>();
		this.distanceNoeud = new ArrayList<Integer>();
		this.xNoeud = x;
		this.yNoeud = y;
		this.numeroNoeud = num;
		this.distanceActuellePourRejoindre = 0; // signifie qu'il n'est pas encore atteint
		
	}
	
	
	public String getType() {
		return this.type;
	}
	public void setType(String t) {
		this.type = t;
	}
	public ArrayList<NoeudGraphe> getNoeudAtteignable(){
		return this.noeudAtteignable;
	}
	public void setNoeudAtteignable(ArrayList<NoeudGraphe> na) {
		this.noeudAtteignable = na;
	}
	public ArrayList<Integer> getDistanceNoeud(){
		return this.distanceNoeud;
	}
	public void setDistanceNoeud(ArrayList<Integer> dn) {
		this.distanceNoeud = dn;
	}
	public int getX() {
		return this.xNoeud;
	}
	public void setX(int x) {
		this.xNoeud = x;
	}
	public int getY() {
		return this.yNoeud;
	}
	public void setY(int y) {
		this.yNoeud = y;
	}
	public int getNumeroNoeud() {
		return this.numeroNoeud;
	}
	public void setNumeroNoeud(int nn) {
		this.numeroNoeud = nn;
	}
	public int getDistanceActuelle() {
		return this.distanceActuellePourRejoindre;
	}
	public void setDistanceActuelle(int d) {
		this.distanceActuellePourRejoindre = d;
	}
	public int[] getNumeroPourRejoindre() {
		return this.numeroNoeudPourRejoindre;
	}
	public void setNumeroPourRejoindre(int numNoeudSource, int numNoeudPrecedent) { // numNoeudSource : noeud duquel on part au tout d�but / numNoeudPrecedent : noeud depuis lequel il faut arriver pour le chemin le plus court
		this.numeroNoeudPourRejoindre[numNoeudSource]=numNoeudPrecedent;
	}
	
	
	
	public void ajouterNoeudAtteignable(NoeudGraphe n, int distance) {
		this.noeudAtteignable.add(n);
		this.distanceNoeud.add(distance);
	}
	
	public void initNumeroPourRejoindre(int nbNoeud) {
		this.numeroNoeudPourRejoindre=new int[nbNoeud];
		for(int i = 0 ; i<nbNoeud ; i ++) {
			this.numeroNoeudPourRejoindre[i] = -1; // signifie qu'on ne l'a pas atteint
		} 
	}
	
	public int compareTo(NoeudGraphe n) {
		if(this.numeroNoeud > n.numeroNoeud) {
			return 1;
		}
		else if(this.numeroNoeud == n.numeroNoeud) {
			return 0;
		}
		else{
			return -1;
		}
	}
	
	public String toString() {
		return "Noeud num�ro "+this.numeroNoeud+" de type : " + this.type + ", de coordonn�es : x = " + this.xNoeud+", y = " + this.yNoeud;
	}
	
}