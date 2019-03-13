import java.util.LinkedList;

public class Voiture {
	
	private int vitesse;
	private int numero;
	private String StatDep;
	private String StatArr;
	private String direction;
	private int x;
	private int y;
	private final int VITESSE_MAX = 5;
	private LinkedList<String> trajectoire;
	private String sensVoiture;
	//mettre le tableau de trajectoire ici, la méthode de "transcription", la méthode pour les virages
	//méthode qui prend en paramètre une linkedList et le copie
	//méthode virage qui prend en paramètre une direction et ajoute les directions successives à prendre dans la linkedList trajectoire
	
	public Voiture(int v,int n,String StatDep, String StatArr, String d, int x, int y,String s){
		this.vitesse = v;
		this.numero = n;
		this.StatDep = StatDep;
		this.StatArr = StatArr;
		this.direction = d;
		this.x = x;
		this.y = y;
		this.trajectoire = new LinkedList<String>();
		sensVoiture = s;
		
	}
	
	public Voiture(int vit,int xV,int yV) {
		this.x = xV;
		this.y = yV;
		this.vitesse = vit;
	}
	
	public int getVitesse() {
		return this.vitesse;
	}
	public void setVitesse (int v) {
		this.vitesse = v;
	}
	public int getNumero() {
		return this.numero;
	}
	public void setNumero (int n) {
		this.numero = n;
	}
	public String getStatDep() {
		return this.StatDep;
	}
	public void setStatDep (String StatDep) {
		this.StatDep = StatDep;
	}
	public String getStatArr() {
		return this.StatArr;
	}
	public void setStatArr (String StatArr) {
		this.StatArr = StatArr;
	}
	public String getDirection() {
		return this.direction;
	}
	public void setDirection (String d) {
		this.direction = d;
	}
	public int getX() {
		return this.x;
	}
	public void setX (int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY (int y) {
		this.y = y;
	}
	public void avance() {
		switch(direction) {
		case "haut": 
			y -= vitesse;
			break;
		case "bas": 
			y += vitesse;
			break;
		case "gauche": 
			x -= vitesse;
			break;
		case "droite": 
			x += vitesse;
			break;
		default :
			break;
		}
	}
	
	public void ralentit(int deceleration) {
		if(this.vitesse - deceleration >= 0) {
			this.vitesse -= deceleration;
		}
				
	}
	public void accelere(int acceleration) {
		if(this.vitesse + acceleration <= VITESSE_MAX) {
			this.vitesse += acceleration;
		}
	}
	
	public void setTrajectoire(LinkedList<String> t) {
		//voir si ça marche ou besoin de faire une "hard" copie
		this.trajectoire=t;
	}
	public void virage (String direction) {
		this.trajectoire.add(direction);
	}

}
