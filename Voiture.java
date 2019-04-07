import java.util.ArrayList;
import java.util.LinkedList;

public class Voiture {
	
	private int vitesse;
	private int numero;
	private int StatDep;
	private int StatArr;
	private String direction;
	private int x;
	private int y;
	private final int VITESSE_MAX = 3;
	private LinkedList<String> trajectoire;
	private int sensVoiture;
	private boolean sortCarrefour=false;
	private String voie="droite";
	private boolean enCirculation = false;
	private boolean commandeManuelle = false;
	private int stationManuelle;
	private int attente=0;
	
	public Voiture(int v,int n,int StatDep, int StatArr, int x, int y, int s){
		this.vitesse = v;
		this.numero = n;
		this.StatDep = StatDep;
		this.StatArr = StatArr;
		this.x = x;
		this.y = y;
		this.trajectoire = new LinkedList<String>();
		this.sensVoiture = s;
	}
	
	public Voiture(int vit,int xV,int yV,int s,int n) {
		this.x = xV;
		this.y = yV;
		this.vitesse = vit;
		this.sensVoiture=s;
		this.trajectoire = new LinkedList<String>();
		this.numero=n;
	}
	public Voiture() {
		this.trajectoire = new LinkedList<String>();
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
	public int getStatDep() {
		return this.StatDep;
	}
	public void setStatDep (int StatDep) {
		this.StatDep = StatDep;
	}
	public int getStatArr() {
		return this.StatArr;
	}
	public void setStatArr (int StatArr) {
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
	public int getSens() {
		return this.sensVoiture;
	}
	public void setSens( int s) {
		this.sensVoiture = s;
	}
	public void avance() {
		if(attente>0) {
			attente--;
			vitesse++;
			return;
		}
		if(!this.trajectoire.isEmpty()) {
			if(this.trajectoire.getFirst()=="fin carrefour") {
				this.trajectoire.removeFirst();
			}
			if(this.trajectoire.isEmpty()) {
				enCirculation = false;
				return;
			}
			
			if(this.trajectoire.getFirst() == "droite") {
				sensVoiture --;
				if(sensVoiture < 0) {
					sensVoiture += 4;
				}
			}
			else if(this.trajectoire.getFirst() == "gauche") {
				sensVoiture ++;
				if(sensVoiture > 3) {
					sensVoiture -= 4;
				}
			}
			switch (sensVoiture) {
			case 0:
				y -= vitesse;
				break;
			case 1:
				x -= vitesse;
				break;
			case 2:
				y += vitesse;
				break;
			case 3:
				x += vitesse;
				break;
			}
			this.trajectoire.removeFirst();
		}
		if(this.trajectoire.isEmpty()) {
			enCirculation = false;
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
	
	public void setTrajectoire(ArrayList<String> t) {
		this.trajectoire = new LinkedList<String>();
		for(String s : t) {
			trajectoire.add(s);
		}
	}
	public void virage (String direction) {
		switch(direction) {
		case "gauche":
			this.trajectoire.add("avant");this.trajectoire.add("avant");this.trajectoire.add("avant");
			this.trajectoire.add("gauche");
			this.trajectoire.add("avant");this.trajectoire.add("avant");
			break;
		case "droite":
			this.trajectoire.add("avant");
			this.trajectoire.add("droite");
			break;
		case "avant":
			this.trajectoire.add("avant");this.trajectoire.add("avant");this.trajectoire.add("avant");this.trajectoire.add("avant");this.trajectoire.add("avant");
			break;
		}
		this.trajectoire.add("fin carrefour");
	}
	public void change_voie() {
		if(this.sortCarrefour) {
			String DirProchCarr="";
			for(int i = 0 ; i < trajectoire.size() ; i++) {
				if (trajectoire.get(i) != "avant") {
					DirProchCarr = trajectoire.get(i);
					break;
				}
			}
			if(DirProchCarr == "gauche"&&this.voie!="gauche") {
				switch (sensVoiture) {
				case 0:
					x --;
					break;
				case 1:
					y ++;
					break;
				case 2:
					x ++;
					break;
				case 3:
					y --;
					break;
				}
				this.voie="gauche";
			}
			else if(this.voie!="droite" && DirProchCarr!="gauche") {
				switch (sensVoiture) {
				case 0:
					x ++;
					break;
				case 1:
					y --;
					break;
				case 2:
					x --;
					break;
				case 3:
					y ++;
					break;
				}
				this.voie="droite";
			}
		}
	}
	
	public LinkedList<String> getTrajectoire(){
		return this.trajectoire;
	}
	public boolean getSortCarrefour() {
		return this.sortCarrefour;
	}
	public void setSortCarrefour(boolean b) {
		this.sortCarrefour=b;
	}
	public void setEnCirculation(boolean c) {
		this.enCirculation = c;
	}
	public boolean getEnCirculation() {
		return this.enCirculation;
	}
	public void setManuelle(boolean m) {
		this.commandeManuelle=m;
	}
	public boolean getManuelle() {
		return this.commandeManuelle;
	}
	public void setStationManuelle(int s) {
		this.stationManuelle=s;
	}
	public int getStationManuelle() {
		return this.stationManuelle;
	}
	public int getAttente() {
		return this.attente;
	}
	public void setAttente(int a) {
		this.attente = a;
	}

}
