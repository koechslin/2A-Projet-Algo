import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import org.omg.CORBA.SystemException;

public class Reseau {
	public ArrayList<NoeudGraphe> noeuds;
	public ArrayList<NoeudGraphe> noeudsStation;
	private ArrayList<Voiture> listeVoiture;
	// 0 : vide  /  1 : route  /  2 : station
	
	
	public int[][] map= 	{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	/*public int[][] map = 	{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0},
							{0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};*/

	public boolean[][] mapVoiture = new boolean[15][15];

	public boolean[][] mapCarrefour;
	private ArrayList<Voiture> listeSauvegardeVoiture;
	
	
	
	private ArrayList<Station> listeStation;
	public LinkedList<LinkedList<int[]>> trajectoireVoitures;
	private LinkedList<boolean[][]> listeMapCalculEnAvance;
	public ArrayList<ArrayList<ArrayList<String>>> trajectoires;
	public Lecteur_Fichier lecteur;
	
	public Reseau() {
		lecteur = new Lecteur_Fichier();
		noeuds = new ArrayList<NoeudGraphe>();
		noeudsStation = new ArrayList<NoeudGraphe>();
		listeVoiture = new ArrayList<Voiture>();
		listeSauvegardeVoiture = new ArrayList<Voiture>();
		listeStation = new ArrayList<Station>();
		listeMapCalculEnAvance = new LinkedList<boolean[][]>();
		trajectoireVoitures = new LinkedList<LinkedList<int[]>>();
		mapCarrefour = new boolean[map.length][map[0].length];
		
		///////////////////////////// CREATION STATION
		creationStation();
		///////////////////////////////
		
		
		// Ajout voiture
		listeVoiture.add(new Voiture(1,listeStation.get(0).getXDepart(),listeStation.get(0).getYDepart(),0,0));
		listeVoiture.get(0).setStatDep(0);
		listeVoiture.get(0).setStatArr(0);
		listeVoiture.add(new Voiture(1,listeStation.get(2).getXDepart(),listeStation.get(2).getYDepart(),0,1));
		listeVoiture.get(1).setStatDep(2);
		listeVoiture.get(1).setStatArr(2);
		/*for(Station s:listeStation) {
			listeVoiture.add(new Voiture(1,s.getXDepart(),s.getYDepart(),0));
		}*/
	}
	
	public void setMapRoute(int[][] m) {
		this.map=m;
		this.mapCarrefour = new boolean[m.length][m[0].length];
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
	public void addStation(Station s) {
		listeStation.add(s);
	}
	public boolean[][] getMapVoiture(){
		return this.mapVoiture;
	}
	public int[][] getMap(){
		return this.map;
	}
	
	public int actualiseMapVoiture() { // renvoie -1 si il n'y a pas de collision(s) dans la prï¿½vision sinon renvoie l'indice de la voiture qui pose problï¿½me
		
		for(Voiture v : listeVoiture) {
			mapVoiture[v.getY()][v.getX()]=false;
			v.avance();
		}
		sauvegardeVoiture();
		
		for(int i=0;i<listeVoiture.size();i++) {
			for(int j=i+1;j<listeVoiture.size();j++) { // pas besoin de commencer ï¿½ 0 car on a dï¿½jï¿½ vï¿½rifiï¿½
				if(listeVoiture.get(i).getX() == listeVoiture.get(j).getX() && listeVoiture.get(i).getY() == listeVoiture.get(j).getY()) {
					System.out.println("Collision ! pour : "+i);
					return i;
				}
			}
		}
		for(Voiture v : this.listeVoiture) {
			mapVoiture[v.getY()][v.getX()]=true;
		}
		return -1;
	}
	
	public boolean detecteCarrefour(Voiture v) { // regarde a l'avance si la voiture va arriver dans un carrefour pour adapter sa vitesse
		//Voiture(int v,int n,String StatDep, String StatArr,int x, int y,String s)
		Voiture voitTemp = new Voiture(v.getVitesse(),v.getNumero(),v.getStatDep(),v.getStatArr(),v.getX(),v.getY(),v.getSens());
		// si detecte carrefour : vitesse--
		
		//on considere qu'on est en ligne droite
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
	
	public void sortieCarrefour(Voiture v) {
		switch(v.getSens()) {
		case 0:
			v.setSortCarrefour(this.mapCarrefour[v.getY()+1][v.getX()] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		case 1:
			v.setSortCarrefour(this.mapCarrefour[v.getY()][v.getX()+1] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		case 2:
			v.setSortCarrefour(this.mapCarrefour[v.getY()-1][v.getX()] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		case 3:
			v.setSortCarrefour(this.mapCarrefour[v.getY()][v.getX()-1] && !this.mapCarrefour[v.getY()][v.getX()]);
			break;
		}
	}
	public void changeMap(int[][] m) {
		this.map=m;
	}
	public void adapteSensVoiture(Voiture v) {
		if(map[v.getY()][v.getX()]==2) { // la voiture est sur une station
			if(map[v.getY()-1][v.getX()]==1) {
				v.setSens(0);
			}
			else if(map[v.getY()+1][v.getX()]==1) {
				v.setSens(2);
			}
			else if(map[v.getY()][v.getX()-1]==1) {
				v.setSens(1);
			}
			else if(map[v.getY()][v.getX()+1]==1) {
				v.setSens(3);
			}
		}
	}
	
	public char[][] reconnaissanceRoute() {
		mapCarrefour = new boolean[map.length][map[0].length];
		char[][] mapC = new char[map.length][map[0].length];
		boolean routeEnHaut = false;
		boolean routeEnBas = false;
		boolean routeAGauche = false;
		boolean routeADroite = false;
		int compteurRoute=0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j]==2) {
					mapC[i][j]='s';
				}
				else if(map[i][j]==1) {
					compteurRoute=0;
					routeEnHaut=false;
					routeEnBas=false;
					routeAGauche=false;
					routeADroite=false;
					if(i>=4) {
						routeEnHaut=true;
						for(int k=1;k<=4;k++) {
							if(map[i-k][j]!=1) {
								routeEnHaut=false;
								break;
							}
						}
						if(routeEnHaut) {
							compteurRoute++;
						}
					}
					if(i<=map.length-5) {
						routeEnBas=true;
						for(int k=1;k<=4;k++) {
							if(map[i+k][j]!=1) {
								routeEnBas=false;
								break;
							}
						}
						if(routeEnBas) {
							compteurRoute++;
						}
					}
					if(j>=4) {
						routeAGauche = true;
						for(int k=1;k<=4;k++) {
							if(map[i][j-k]!=1) {
								routeAGauche = false;
								break;
							}
						}
						if(routeAGauche) {
							compteurRoute++;
						}
					}
					if(j<=map[i].length-5) {
						routeADroite =true;
						for(int k=1;k<=4;k++) {
							if(map[i][j+k]!=1) {
								routeADroite =false;
								break;
							}
						}
						if(routeADroite) {
							compteurRoute++;
						}
					}
					
					switch(compteurRoute) {
					case 0:
						// Impossible ?
						break;
					case 1:
						// Cas lorsque on est proche d'une station ?
						if(routeADroite || routeAGauche) {
							mapC[i][j]='h';
						}
						else if(routeEnHaut || routeEnBas) {
							mapC[i][j]='v';
						}
						break;
					case 2:
						if((routeEnHaut&&routeAGauche)||(routeEnHaut&&routeADroite)||(routeEnBas&&routeAGauche)||(routeEnBas&&routeADroite)) {
							mapC[i][j]='c';
							mapCarrefour[i][j]=true;
						}
						else if(routeEnHaut && routeEnBas) {
							mapC[i][j]='v';
						}
						else if(routeADroite && routeAGauche) {
							mapC[i][j]='h';
						}
						break;
					case 3:
						mapC[i][j]='c';
						mapCarrefour[i][j]=true;
						break;
					case 4 :
						mapC[i][j]='c';
						mapCarrefour[i][j]=true;
						break;
						default :
							break;
					}
				}
			}
		}
		
		return mapC;
	}
	
	public void conversionMapGraphe() {
		this.noeuds = new ArrayList<NoeudGraphe>();
		this.noeudsStation = new ArrayList<NoeudGraphe>();
		int num=0;
		char[][] detectionRoute = this.reconnaissanceRoute();
		for(int i=0;i<detectionRoute.length;i++) {
			for(int j=0;j<detectionRoute[i].length;j++) {
				if(detectionRoute[i][j]=='s') {
					this.noeuds.add(new NoeudGraphe("station",j,i,num));
					num++;
					if(detectionRoute[i][j+1]=='s') { // station "horizontale"
						for(int k=0;k<=3;k++) {
							detectionRoute[i][j+k]=' ';
						}
					}
					else { // station "verticale"
						for(int k=0;k<=3;k++) {
							detectionRoute[i+k][j]=' ';
						}
					}
				}
				else if(detectionRoute[i][j]=='c') {
					this.noeuds.add(new NoeudGraphe("carrefour",j,i,num));
					num++;
					for(int a=0;a<=3;a++) {
						for(int b=0;b<=3;b++) {
							detectionRoute[i+a][j+b]=' ';
						}
					}
				}
			}
		}
		
		for(NoeudGraphe n : noeuds) {
			n.initNumeroPourRejoindre(noeuds.size());
			if(n.type=="station") {
				this.noeudsStation.add(n);
			}
		}
		
		// calcul des distances : 
		
		detectionRoute = this.reconnaissanceRoute();
		
		for(NoeudGraphe n : noeuds) {
			if(n.getType()=="station") {
				int compteurDistance = 1;
				if(detectionRoute[n.getY()+1][n.getX()]=='v') { // en bas
					while(detectionRoute[n.getY()+compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					// on cherche le noeud atteint :
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				else if(detectionRoute[n.getY()-1][n.getX()]=='v') { // en haut
					while(detectionRoute[n.getY()-compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()-compteurDistance-3) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				else if(detectionRoute[n.getY()][n.getX()+1]=='h') { // a droite
					while(detectionRoute[n.getY()][n.getX()+compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
					
				}
				else if(detectionRoute[n.getY()][n.getX()-1]=='h') { // a gauche
					while(detectionRoute[n.getY()][n.getX()-compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()-compteurDistance-3) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
			}
			else { // carrefour
				int compteurDistance = 1;
				if(detectionRoute[n.getY()+4][n.getX()]=='v') { // en bas
					while(detectionRoute[n.getY()+3+compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()+3+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				
				compteurDistance =1;
				if(detectionRoute[n.getY()-1][n.getX()]=='v') { // en haut
					while(detectionRoute[n.getY()-compteurDistance][n.getX()]=='v') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()-compteurDistance-3) { // si c'est un carrefour
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
						else if(nTemp.getX()==n.getX() && nTemp.getY()==n.getY()-compteurDistance) { // si c'est une station
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				
				compteurDistance=1;
				if(detectionRoute[n.getY()][n.getX()-1]=='h') { // à gauche
					while(detectionRoute[n.getY()][n.getX()-compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()-compteurDistance-3) { // si c'est un carrefour
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
						else if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()-compteurDistance) { // si c'est une station 
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
				
				compteurDistance=1;
				if(detectionRoute[n.getY()][n.getX()+4]=='h') { // à droite
					while(detectionRoute[n.getY()][n.getX()+3+compteurDistance]=='h') {
						compteurDistance++;
					}
					for(NoeudGraphe nTemp : noeuds) {
						if(nTemp.getY()==n.getY() && nTemp.getX()==n.getX()+3+compteurDistance) {
							n.ajouterNoeudAtteignable(nTemp, compteurDistance-1);
							break;
						}
					}
				}
			}
		}
	}
	
	public void calculCheminGraphe() {
		//Algorithme de Dijkstra
		for(NoeudGraphe n : noeuds) {
			//réinitialisation des distances
			for(NoeudGraphe temp : noeuds) {
				temp.setDistanceActuelle(0);
			}
			TreeSet<NoeudGraphe> noeudsTraites = new TreeSet<NoeudGraphe>();
			LinkedList<NoeudGraphe> aTraite = new LinkedList<NoeudGraphe>();
			aTraite.add(n); // n est le noeud "source"
			while(!aTraite.isEmpty()) {
				noeudsTraites.add(aTraite.getFirst());
				
				for(int i=0;i<aTraite.getFirst().getNoeudAtteignable().size();i++) {
					if(!noeudsTraites.contains(aTraite.getFirst().getNoeudAtteignable().get(i)) && !aTraite.contains(aTraite.getFirst().getNoeudAtteignable().get(i))) {
						aTraite.add(aTraite.getFirst().getNoeudAtteignable().get(i));
					}
					if(aTraite.getFirst().getNoeudAtteignable().get(i)!=n && (aTraite.getFirst().getNoeudAtteignable().get(i).distanceActuellePourRejoindre==0 || aTraite.getFirst().getNoeudAtteignable().get(i).distanceActuellePourRejoindre>aTraite.getFirst().distanceActuellePourRejoindre+aTraite.getFirst().distanceNoeud.get(i))) {
						aTraite.getFirst().getNoeudAtteignable().get(i).distanceActuellePourRejoindre=aTraite.getFirst().distanceActuellePourRejoindre+aTraite.getFirst().distanceNoeud.get(i);
						aTraite.getFirst().getNoeudAtteignable().get(i).setNumeroPourRejoindre(n.numeroNoeud, aTraite.getFirst().numeroNoeud);
					}
				}
				aTraite.removeFirst();
			}
			
		}
	}
	
	public void conversionTrajectoire() {
		trajectoires = new ArrayList<ArrayList<ArrayList<String>>>(); // format : .get(i).get(j) : pour aller de i a j
		for(int a=0;a<listeStation.size();a++) {
			trajectoires.add(new ArrayList<ArrayList<String>>());
		}
		for(int a=0;a<listeStation.size();a++) {
			for(int k=0;k<listeStation.size();k++) {
				trajectoires.get(a).add(new ArrayList<String>());
			}
		}
		for(int i=0;i<noeudsStation.size();i++) {
			NoeudGraphe noeudAAtteindre = noeudsStation.get(i);
			for(int j=0;j<noeudsStation.size();j++) {
				boolean premiereLigneDroite = true; // on met en place le boolean car dans la premiere ligne droite on ajoute un nombre de "avant" correspondant à la distance alors qu'apres c'est distance-1
				if(noeudAAtteindre!=noeudsStation.get(j)) {
					NoeudGraphe noeudActuel = noeudsStation.get(j);
					NoeudGraphe noeudDepart = noeudsStation.get(j);
					while(noeudActuel!=noeudAAtteindre) {
						NoeudGraphe noeudProchaineEtape = noeuds.get(noeudActuel.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]);
						
						//Recherche de la distance :
						int distance=0;
						for(int k=0;k<noeudActuel.noeudAtteignable.size();k++) {
							if(noeudActuel.noeudAtteignable.get(k)==noeudProchaineEtape) {
								distance = noeudActuel.distanceNoeud.get(k);
							}
						}
						
						if(premiereLigneDroite) {
							for(int k=0;k<distance;k++) {
								trajectoires.get(j).get(i).add("avant");
							}
							premiereLigneDroite=false;
						}
						else {
							for(int k=0;k<distance-1;k++) {
								trajectoires.get(j).get(i).add("avant");
							}
						}
						
						// Recherche direction virage :
						if(noeudProchaineEtape.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]!=-1) {
							Voiture temp = new Voiture();
							int x1=noeudProchaineEtape.getX()-noeudActuel.getX();
							int y1=noeudProchaineEtape.getY()-noeudActuel.getY();
							int x2=noeuds.get(noeudProchaineEtape.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]).getX()-noeudProchaineEtape.getX();
							int y2=noeuds.get(noeudProchaineEtape.getNumeroPourRejoindre()[noeudAAtteindre.getNumeroNoeud()]).getY()-noeudProchaineEtape.getY();
							
							if(x1>0) { // 1 : avant -> pendant       2 : pendant -> après
								if(x2>0) {
									temp.virage("avant");
								}
								else if(x2<0) {
									// normalement impossible
								}
								else if(y2>0) {
									temp.virage("droite");
								}
								else if(y2<0) {
									temp.virage("gauche");
								}
							}
							else if(x1<0) {
								if(x2>0) {
									// normalement impossible
								}
								else if(x2<0) {
									temp.virage("avant");
								}
								else if(y2>0) {
									temp.virage("gauche");
								}
								else if(y2<0) {
									temp.virage("droite");
								}
							}
							else if(y1>0) {
								if(x2>0) {
									temp.virage("gauche");
								}
								else if(x2<0) {
									temp.virage("droite");
								}
								else if(y2>0) {
									temp.virage("avant");
								}
								else if(y2<0) {
									//normalement impossible
								}
							}
							else if(y1<0) {
								if(x2>0) {
									temp.virage("droite");	
								}
								else if(x2<0) {
									temp.virage("gauche");
								}
								else if(y2>0) {
									//normalement impossible
								}
								else if(y2<0) {
									temp.virage("avant");
								}
							}
							for(String s : temp.getTrajectoire()) {
								trajectoires.get(j).get(i).add(s);
							}
						}
						
						noeudActuel = noeudProchaineEtape;
					}
				}
			}
		}
	}
	
	public void recharge(int nVoit) {
		this.listeVoiture = new ArrayList<Voiture>();
		for(int i=0;i<nVoit;i++) {
			int s = (int)(Math.random()*this.listeStation.size());
			listeVoiture.add(new Voiture(1,this.listeStation.get(s).getXDepart(),listeStation.get(s).getYDepart(),0,i));
			listeVoiture.get(i).setStatArr(s);
			listeVoiture.get(i).setStatDep(s);
		}
	}
	public void creationStation() {
		listeStation = new ArrayList<Station>();
		int compteur = 0;
		for (int i = 0; i < map.length ; i++) {
			for (int j = 0 ; j < map[i].length ; j++) {
					if((map[i][j] == 2) && (map[i][j+1] == 2) && (map[i][j+2] == 2) && (map[i][j+3] == 2)) {
						if((map[i+1][j] == 1)) {
							addStation(new Station(compteur, j, i, j+3, i));
						}else if(map[i-1][j] == 1){
							addStation(new Station(compteur, j+3, i, j, i));
						}
						compteur++;
						
					}
					if((map[i][j] == 2) && (map[i+1][j] == 2) && (map[i+2][j] == 2) && (map[i+3][j] == 2)) {
						if((map[i][j+1] == 1)) {
							addStation(new Station(compteur, j, i+3, j, i));
						}else if(map[i][j-1] == 1){
							addStation(new Station(compteur, j, i, j, i+3));
						}
						compteur++;
					}	
			}
				
		}
	}
	public int predictionCollision(ArrayList<Voiture> v,int coup) { // entier renvoye : numéro de la 1ere voiture qui entre en collision (si -1 : c'est bon)
		if(coup == 0) {
			return -1;
		}
		//Copie des voitures :
		ArrayList<Voiture> copieVoit = new ArrayList<Voiture>();
		for(int i=0;i<v.size();i++) {
			Voiture voitTemp = new Voiture(v.get(i).getVitesse(),v.get(i).getX(),v.get(i).getY(),v.get(i).getSens(),v.get(i).getNumero());
			voitTemp.setTrajectoire(new ArrayList<String>(v.get(i).getTrajectoire()));
			voitTemp.setVoie(v.get(i).getVoie());
			copieVoit.add(voitTemp);
		}
		
		//On les fait avancer d'un coup :
		for(Voiture voit : copieVoit) {
			if(v.get(voit.getNumero()).getAttente()>0) {
				continue;
			}
			if(map[voit.getY()][voit.getX()]!=2) {
					sortieCarrefour(voit);
					if(voit.getSortCarrefour()) {
						boolean doitChanger = voit.doitChangerVoie();
						if(doitChanger) {
							boolean peutChanger = true;
							if(voit.getVoie()=="droite") {
								//System.out.println("rentre droite");
								switch(voit.getSens()) {
								case 0:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY() && temp.getX()==voit.getX()-1) {
											if(temp.getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()-1&&temp.getX()==voit.getX()-1) {
											if(temp.getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								case 1:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY()+1 && temp.getX()==voit.getX()) {
											if(temp.getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()+1&&temp.getX()==voit.getX()-1) {
											if(temp.getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								case 2:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY() && temp.getX()==voit.getX()+1) {
											if(temp.getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()-1&&temp.getX()==voit.getX()+1) {
											if(temp.getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								case 3:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY()-1 && temp.getX()==voit.getX()) {
											if(temp.getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()-1&&temp.getX()==voit.getX()+1) {
											if(temp.getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								}
							}
							else if(voit.getVoie()=="gauche") {
								//System.out.println("rentre gauche");
								switch(voit.getSens()) {
								case 0:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY() && temp.getX()==voit.getX()+1) {
											if(temp.getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()-1&&temp.getX()==voit.getX()+1) {
											if(temp.getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								case 1:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY()-1 && temp.getX()==voit.getX()) {
											if(v.get(temp.getNumero()).getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()-1&&temp.getX()==voit.getX()-1) {
											if(v.get(temp.getNumero()).getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								case 2:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY() && temp.getX()==voit.getX()-1) {
											if(temp.getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()-1&&temp.getX()==voit.getX()-1) {
											if(temp.getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								case 3:
									for(Voiture temp : copieVoit) {
										if(temp.getY()==voit.getY()+1 && temp.getX()==voit.getX()) {
											if(temp.getAttente()==0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
										else if(temp.getY()==voit.getY()+1&&temp.getX()==voit.getX()+1) {
											if(temp.getAttente()!=0) {
												v.get(voit.getNumero()).setAttente(1);
												v.get(voit.getNumero()).ralentit(1);
												peutChanger = false;
												break;
											}
										}
									}
									break;
								}
							}
							if(peutChanger) {
								voit.change_voie();
								voit.avance();
							}
							else {
								return voit.getNumero();
							}
						}
						else {
							voit.change_voie();
							voit.avance();	
						}
					}
					else {
						voit.avance();
					}
						
			}
			else {
				// on cherche toutes les autres voitures sur la meme station et on met leur vitesse a 0
				for(Voiture v2 : copieVoit) {
					if(v2!=voit) {
						if(v2.getX()==voit.getX() && v2.getY() == voit.getY()) { // voiture differente sur la meme station 
							v.get(v2.getNumero()).ralentit(1);
							v.get(v2.getNumero()).setAttente(v.get(v2.getNumero()).getAttente()+1);
						}
					}
				}
				voit.setSortCarrefour(true);
				voit.change_voie();
				voit.avance();
				
			}
			
		}
		
		// On vérifie les collisions
		for(Voiture v1 : copieVoit) {
			//System.out.println("coord v1 : x = "+v1.getX()+"  y = "+v1.getY());
			for(Voiture v2 : copieVoit) {
				if(v1!=v2) {
					//System.out.println("coord v2 : x = "+v2.getX()+"  y = "+v2.getY());
					if(v1.getX()==v2.getX() && v1.getY() == v2.getY() && map[v1.getY()][v1.getX()]!=2) { // collision
						if(v1.getVitesse()>0) {
							v.get(v1.getNumero()).ralentit(1);
							v.get(v1.getNumero()).setAttente(v.get(v1.getNumero()).getAttente()+1);
							return v1.getNumero();
						}
						else {
							v.get(v2.getNumero()).ralentit(1);
							v.get(v2.getNumero()).setAttente(v.get(v2.getNumero()).getAttente()+1);
							return v2.getNumero();
						}
					}
				}
			}
		}
		// Si on arrive la c'est qu'il n'y a pas de collision
		int evaluation = predictionCollision(copieVoit,coup-1);
		while(evaluation!=-1) {//tant qu'il y a collision
			evaluation = predictionCollision(copieVoit,coup-1);
		}
		
		
		return -1;
	}
}