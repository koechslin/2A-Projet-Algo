
public class Voiture {
	
	private int vitesse;
	private int numero;
	private String StatDep;
	private String StatArr;
	private String direction;
	private int x;
	private int y;
	
	
	public Voiture(int v, int n, String StatDep, String StatArr, String d, int x, int y){
		this.vitesse = v;
		this.numero = n;
		this.StatDep = StatDep;
		this.StatArr = StatArr;
		this.direction = d;
		this.x = x;
		this.y = y;
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
		case "haut": y -= vitesse;
		case "bas": y += vitesse;
		case "gauche": x -= vitesse;
		case "droite": x += vitesse;
		}
	}
	
	public void ralentit() {
		this.vitesse--;
	}
	public void accelere() {
		this.vitesse++;
	}

}
