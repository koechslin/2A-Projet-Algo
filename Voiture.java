import java.util.ArrayList;
import java.util.LinkedList;

public class Voiture {
	
	private int vitesse;
	private int numero;
	private int StatDep;
	private int StatArr;
	private int x;
	private int y;
	private final int VITESSE_MAX = 3;
	private int stationManuelle;
	private int attente = 0;
	private int sensVoiture;
	private boolean sortCarrefour = false;
	private boolean enCirculation = false;
	private boolean commandeManuelle = false;
	private boolean aChangeVoie = false;
	private String voie = "droite";
	private LinkedList<String> trajectoire;

	
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
	public Voiture(int vit,int n, int xV,int yV,int s) {
		this.vitesse = vit;
		this.numero = n;
		this.x = xV;
		this.y = yV;
		this.sensVoiture = s;
		this.trajectoire = new LinkedList<String>();
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
	public LinkedList<String> getTrajectoire(){
		return this.trajectoire;
	}
	public boolean getSortCarrefour() {
		return this.sortCarrefour;
	}
	public void setSortCarrefour(boolean b) {
		this.sortCarrefour = b;
	}
	public boolean getEnCirculation() {
		return this.enCirculation;
	}
	public void setEnCirculation(boolean c) {
		this.enCirculation = c;
	}
	public boolean getManuelle() {
		return this.commandeManuelle;
	}
	public void setManuelle(boolean m) {
		this.commandeManuelle = m;
	}
	public int getStationManuelle() {
		return this.stationManuelle;
	}
	public void setStationManuelle(int s) {
		this.stationManuelle = s;
	}
	public int getAttente() {
		return this.attente;
	}
	public void setAttente(int a) {
		this.attente = a;
	}
	public boolean getAChangeVoie() {
		return this.aChangeVoie;
	}
	public void setAChangeVoie(boolean b) {
		this.aChangeVoie = b;
	}
	public String getVoie() {
		return this.voie;
	}
	public void setVoie(String v) {
		this.voie = v;
	}
	
	
	
	public void avance() {
		if(attente>0) {
			attente --;
			if(attente == 0) {
				vitesse ++;
			}
			
			return;
		}
		if(!this.trajectoire.isEmpty()) {
			if(this.trajectoire.getFirst() == "fin carrefour") {
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
		this.voie = "droite";
	}
	
	public void virage (String direction) {
		switch(direction) {
		case "gauche":
			this.trajectoire.add("avant"); this.trajectoire.add("avant"); this.trajectoire.add("avant");
			this.trajectoire.add("gauche");
			this.trajectoire.add("avant"); this.trajectoire.add("avant");
			break;
		case "droite":
			this.trajectoire.add("avant");
			this.trajectoire.add("droite");
			break;
		case "avant":
			this.trajectoire.add("avant"); this.trajectoire.add("avant"); this.trajectoire.add("avant"); this.trajectoire.add("avant"); this.trajectoire.add("avant");
			break;
		}
	}
	
	public void change_voie() {
		if(this.attente != 0) {
			return;
		}
		if(this.sortCarrefour) {
			String DirProchCarr = "";
			for(int i = 0 ; i < trajectoire.size() ; i ++) {
				if (trajectoire.get(i) != "avant") {
					DirProchCarr = trajectoire.get(i);
					break;
				}
			}
			if(DirProchCarr == "gauche" && this.voie != "gauche") {
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
				this.voie = "gauche";
			}
			else if(this.voie != "droite" && DirProchCarr != "gauche") {
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
				this.voie = "droite";
			}
		}
	}
	
	public boolean doitChangerVoie() {
		// on considere qu'elle sort d'un carrefour
			String DirProchCarr="";
			for(int i = 0 ; i < trajectoire.size() ; i ++) {
				if (trajectoire.get(i) != "avant") {
					DirProchCarr = trajectoire.get(i);
					break;
				}
			}
			if(DirProchCarr == "gauche" && this.voie != "gauche") {
				return true;
			}
			else if(this.voie != "droite" && DirProchCarr != "gauche") {
				return true;
			}
			return false;
	}

}