import java.util.ArrayList;
import java.util.LinkedList;

import org.omg.CORBA.SystemException;

public class Reseau {
	private ArrayList<Voiture> listeVoiture;
	// 0 : vide  /  1 : route  /  2 : station
	// true si il y a une voiture
	
	
	public int[][] map = 	{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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

	public boolean[][] mapVoiture = new boolean[15][15];

	public boolean[][] mapCarrefour;
	
	// sauvegarde afin de comparer avant/après et lancer si besoin la méthode changerVoie de voiture
	private ArrayList<Voiture> listeSauvegardeVoiture;
	
	
	
	private ArrayList<Station> listeStation;
	public LinkedList<LinkedList<int[]>> trajectoireVoitures;
	private LinkedList<boolean[][]> listeMapCalculEnAvance;
	public ArrayList<ArrayList<ArrayList<String>>> trajectoires;
	
	public Reseau() {
		listeVoiture = new ArrayList<Voiture>();
		listeSauvegardeVoiture = new ArrayList<Voiture>();
		listeStation = new ArrayList<Station>();
		listeMapCalculEnAvance = new LinkedList<boolean[][]>();
		trajectoireVoitures = new LinkedList<LinkedList<int[]>>();
		mapCarrefour = new boolean[map.length][map[0].length];
		
		///////////////////////////// CREATION STATION
		int compteur = 0;
		for (int i = 0; i < map.length ; i++) {
			for (int j = 0 ; j < map[i].length ; j++) {
				if (!((i > 1) && (i < map.length-2) && (j > 1) && (j < map[i].length-2))) {
					if (i == 1) {
						if((map[i][j] == 2) && (map[i][j+1] == 2) && (map[i][j+2] == 2) && (map[i][j+3] == 2)) {
							System.out.println("rentre 1");
							/*setNumStation(compteur);
							setXDepart(j);
							setYDepart(i);
							setXArrive(j+3);
							setYArrive(i);*/
							addStation(new Station(compteur, j, i, j+3, i));
							compteur++;
						}
					}
					if (j == 1) {
						if((map[i][j] == 2) && (map[i+1][j] == 2) && (map[i+2][j] == 2) && (map[i+3][j] == 2)) {
							System.out.println("rentre 2");
							/*setNumStation(compteur);
							setXDepart(j);
							setYDepart(i+3);
							setXArrive(j);
							setYArrive(i);*/
							addStation(new Station(compteur, j, i+3, j, i));
							compteur++;
						}
					}
					if (j == map.length-2) {
						if((map[i][j] == 2) && (map[i+1][j] == 2) && (map[i+2][j] == 2) && (map[i+3][j] == 2)) {
							System.out.println("rentre 3");
							/*setNumStation(compteur);
							setXDepart(j);
							setYDepart(i);
							setXArrive(j);
							setYArrive(i+3);*/
							addStation(new Station(compteur, j, i, j, i+3));
							compteur++;
						}
					}
					if (i == map.length-2) {
						if((map[i][j] == 2) && (map[i][j+1] == 2) && (map[i][j+2] == 2) && (map[i][j+3] == 2)) {
							System.out.println("rentre 4");
							/*setNumStation(compteur);
							setXDepart(j+3);
							setYDepart(i);
							setXArrive(j);
							setYArrive(i);*/
							addStation(new Station(compteur, j+3, i, j, i));
							compteur++;
						}
					}
					
					
				}
			}
				
		}
		System.out.println("compteur = "+compteur);
		///////////////////////////////
		
		
		// Ajout voiture
		for(Station s:listeStation) {
			listeVoiture.add(new Voiture(1,s.getXDepart(),s.getYDepart(),0));
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
		sauvegardeVoiture();
		
		//gérer la collision
		
		// PROBLEME SI VITESSE ELEVEE
		
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
	
	/*public void calculPlusCourtChemin(Voiture v, Station s) { // 3 directions possibles : avant gauche droite
		
		// A MODIFIER : VERIFIER LE SENS
		
		//gérer les virages
		// Voir si ça marche car peut ne pas fonctionner à cause du sens de la voiture
		
		int coordX = v.getX();
		int coordY = v.getY();
		
		LinkedList<String> trajectoire = new LinkedList<String>();
		while(coordX!=s.getXStation() || coordY!=s.getYStation()) {
			int xDiff = s.getXStation() - coordX;
			int yDiff = s.getYStation() - coordY;
			
			if(xDiff==0) {
				if(yDiff<0) { // station en haut
					if(map[coordY-v.getVitesse()][coordX]>=1) { // il y a une route ou une station
						coordY-=v.getVitesse();
						// VERIFIER LE SENS
						trajectoire.add("haut");
					}
				}
				else { // station en bas
					if(map[coordY+v.getVitesse()][coordX]>=1) { // il y a une route ou une station
						int[] temp = {coordX,coordY+v.getVitesse()};
						coordY+=v.getVitesse();
						trajectoire.add("bas");
					}
				}
			}
			else if(xDiff<0) {
				if(yDiff==0) { // station a gauche
					if(map[coordY][coordX-v.getVitesse()]>=1) { // il y a une route ou une station
						int[] temp = {coordX-v.getVitesse(),coordY};
						coordX-=v.getVitesse();
						trajectoire.add("gauche");
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
						trajectoire.add("droite");
					}
				}
				else if(yDiff<0) { // station en haut a droite
					
				}
				else { // station en bas a droite
					
				}
			}
			
		}
		
		
		v.setTrajectoire(trajectoire);
		//this.trajectoireVoitures.add(v.getNumero(), trajectoire);
	}*/
	
	/*public String transformTrajectToDirection(Voiture v) {
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
	}*/
	
	public boolean detecteCarrefour(Voiture v) { // regarde à l'avance si la voiture va arriver dans un carrefour pour adapter sa vitesse
		//Voiture(int v,int n,String StatDep, String StatArr,int x, int y,String s)
		Voiture voitTemp = new Voiture(v.getVitesse(),v.getNumero(),v.getStatDep(),v.getStatArr(),v.getX(),v.getY(),v.getSens());
		// si detecte carrefour : vitesse--
		
		//on considère qu'on est en ligne droite
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
	
	public void checkChangementVoie() {
		
	}
	
	
	// calcul chemin le plus court : le fait au tout début et stocke les trajectoires dans un tableau
	
	//rajouter conditions vérification des limites
	
	public void calculCheminCourt() { // on considere qu'il y a assez de routes etc...
		//n(n-1) : nombre de trajectoires
		System.out.println("se lance");
		
		trajectoires = new ArrayList<ArrayList<ArrayList<String>>>(); // format : .get(i).get(j) : pour aller de i à j
		
		//test remplissage
		for(int a=0;a<listeStation.size();a++) {
			trajectoires.add(new ArrayList<ArrayList<String>>());
		}
		for(int a=0;a<listeStation.size();a++) {
			for(int k=0;k<listeStation.size();k++) {
				trajectoires.get(a).add(new ArrayList<String>());
			}
		}
		
		for(int i=0;i<listeStation.size();i++) {
			//lorsque on a calculé i->j pas besoin de recalculer j->i : se fait en sens inverse
			//on se fixe le fait qu'il n'y ait qu'une seule arrivée/départ d'une station ?
			for(int j=0;j<listeStation.size();j++) {
				if(i!=j) {
					if(false) { // deja calculé i->j ?     // !trajectoires.get(i).get(j).isEmpty()
						for(String s : trajectoires.get(i).get(j)) {
							trajectoires.get(j).get(i).add(s);
						}
					}
					else{
						//pour l'instant station sur les bords et pas dans les coins
						Voiture vVirtuel = new Voiture(1,listeStation.get(i).getXDepart(),listeStation.get(i).getYDepart(),0);
						System.out.println("vVirtuel créée en x = "+vVirtuel.getX()+"  y = "+vVirtuel.getY());
						
						if(listeStation.get(i).getXDepart()==1) { // station sur le bord gauche
							vVirtuel.setSens(3);
						}
						else if(listeStation.get(i).getXDepart()==this.map[0].length-2) { // station sur le bord droit
							vVirtuel.setSens(1);
						}
						else if(listeStation.get(i).getYDepart()==1) { // station sur le bord haut
							vVirtuel.setSens(2);
						}
						else if(listeStation.get(i).getYDepart()==this.map.length-2) { // station sur le bord bas
							vVirtuel.setSens(0);
						}
						System.out.println("sens voiture : "+vVirtuel.getSens());
						
						Voiture temp = new Voiture(1,vVirtuel.getX(),vVirtuel.getY(),vVirtuel.getSens());
						temp.getTrajectoire().add("avant");
						temp.avance(); // a voir si utilise tableau de trajectoire
						while(temp.getX()>0 && temp.getX()<map[0].length && temp.getY()>0 && temp.getY()<map.length && !(temp.getX()==listeStation.get(j).getXArrive() && temp.getY()==listeStation.get(j).getYArrive())) {
							System.out.println("boucle pour i = "+i+"   j = "+j);
							System.out.println("temp x = "+temp.getX()+"   temp y = "+temp.getY());
							if(mapCarrefour[temp.getY()][temp.getX()]) {//il y a un carrefour
								boolean peutContinuer =false;
								System.out.println("carrefour");
								int xDiff=listeStation.get(j).getXArrive()-temp.getX();
								int yDiff=listeStation.get(j).getYArrive()-temp.getY();
								
								if(Math.abs(xDiff)==0 || Math.abs(xDiff)==3 && false) { // directement en haut ou en bas
									if(yDiff>0) { // en bas
										switch(vVirtuel.getSens()) {
										case 0: // normalement ce cas n'arrive pas
											
											break;
										case 1:
											vVirtuel.virage("gauche");
											break;
										case 2:
											vVirtuel.virage("avant");
											break;
										case 3:
											vVirtuel.virage("droite");
											break;
										}
									}
									else { // en haut
										switch(vVirtuel.getSens()) {
										case 0:
											vVirtuel.virage("avant");
											break;
										case 1:
											vVirtuel.virage("droite");
											break;
										case 2:
											
											break;
										case 3:
											vVirtuel.virage("gauche");
											break;
										}
									}
								}
								else if(Math.abs(yDiff)==0 || Math.abs(yDiff)==3 && false) { // directement à droite ou à gauche
									if(xDiff>0) { // à droite
										switch(vVirtuel.getSens()) {
										case 0:
											vVirtuel.virage("droite");
											break;
										case 1:
											
											break;
										case 2:
											vVirtuel.virage("gauche");
											break;
										case 3:
											vVirtuel.virage("avant");
											break;
										}
									}
									else { // à gauche
										switch(vVirtuel.getSens()) {
										case 0:
											vVirtuel.virage("gauche");
											break;
										case 1:
											vVirtuel.virage("avant");
											break;
										case 2:
											vVirtuel.virage("droite");
											break;
										case 3:
											
											break;
										}
									}
								}
								// station pas directement "en ligne droite"
								
								else if(xDiff>=0 && yDiff>=0) { // en bas à droite
									switch(vVirtuel.getSens()) {
									case 0:
										//juste vérifier à droite
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()+1<map[0].length &&  map[vVirtuel.getY()-1][vVirtuel.getX()+1]==1) { // il y a une route qui continue
											//vérifier si il y a un autre carrefour
											for(int a=vVirtuel.getX()+1;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) { // il y a un autre carrefour
													vVirtuel.virage("droite");
													break;
												}
											}
										}
										break;
									case 1:
										//juste vérifier en bas
										if(vVirtuel.getY()+4<map.length && vVirtuel.getX()-1 >0 && map[vVirtuel.getY()+4][vVirtuel.getX()-1]==1) {
											for(int a=vVirtuel.getY()+4;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("gauche"); // voir bookmark
													break;
												}
											}
										}
										break;
										
									case 2:
										if(vVirtuel.getY()+5<map.length && map[vVirtuel.getY()+5][vVirtuel.getX()]==1) { // en bas
											for(int a=vVirtuel.getY()+5;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
										}
										else if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+4<map[0].length && map[vVirtuel.getY()+1][vVirtuel.getX()+4]==1) { // à droite
											for(int a=vVirtuel.getX()+4;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
										}
										break;
										
									case 3:
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+1<map[0].length &&map[vVirtuel.getY()+1][vVirtuel.getX()+1]==1) { // en bas
											for(int a=vVirtuel.getY()+1;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()+1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
										}
										else if(vVirtuel.getX()+5<map[0].length && map[vVirtuel.getY()][vVirtuel.getX()+5]==1) { // à droite
											for(int a=vVirtuel.getX()+5;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
										}
										break;
									}
								}
								else if(xDiff<=0 && yDiff>=0) { // en bas à gauche
									switch(vVirtuel.getSens()) {
									case 0:
										//vérifier à gauche
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()-4>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-4]==1) {
											for(int a=vVirtuel.getX()-4;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
										}
										break;
									case 1:
										if(vVirtuel.getY()+4<map.length && vVirtuel.getX()-1>0 && map[vVirtuel.getY()+4][vVirtuel.getX()-1]==1) { // en bas
											for(int a=vVirtuel.getY()+4;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getX()-5>0 && map[vVirtuel.getY()][vVirtuel.getX()-5]==1) { // à gauche
											for(int a=vVirtuel.getX()-5;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										break;
									case 2:
										if(vVirtuel.getY()+5<map.length && map[vVirtuel.getY()+5][vVirtuel.getX()]==1) { // en bas
											for(int a=vVirtuel.getY()+5;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getY()+1<map.length && vVirtuel.getX()-1>0 && map[vVirtuel.getY()+1][vVirtuel.getX()-1]==1) { // à gauche
											for(int a=vVirtuel.getX()-1;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 3:
										//vérifier en bas
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()+1][vVirtuel.getX()+1]==1) {
											for(int a=vVirtuel.getY()+1;a<map.length;a++) {
												if(mapCarrefour[a][vVirtuel.getX()+1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									}
								}
								else if(xDiff>=0 && yDiff<=0) { // en haut à droite
									switch(vVirtuel.getSens()) {
									case 0:
										if(vVirtuel.getY()-5>0 && map[vVirtuel.getY()-5][vVirtuel.getX()]==1) { // en haut
											for(int a=vVirtuel.getY()-5;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getY()-1>0 && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()-1][vVirtuel.getX()+1]==1) { // à droite
											for(int a=vVirtuel.getX()+1;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 1:
										//vérifier en haut
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()-1>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-1]==1) {
											for(int a=vVirtuel.getY()-1;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 2:
										//vérifier à droite
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()+4<map[0].length && map[vVirtuel.getY()+1][vVirtuel.getX()+4]==1) {
											for(int a=vVirtuel.getX()+4;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													System.out.println("BON VIRAGE");
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										break;
									case 3:
										if(vVirtuel.getY()-4>0 && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()-4][vVirtuel.getX()+1]==1) { // en haut
											for(int a=vVirtuel.getY()-4;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()+1]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getX()+5<map[0].length && map[vVirtuel.getY()][vVirtuel.getX()+5]==1) { // à droite
											for(int a=vVirtuel.getX()+5;a<map[0].length;a++) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										break;
									}
								}
								else if(xDiff<=0 && yDiff<=0) { // en haut à gauche
									switch(vVirtuel.getSens()) {
									case 0:
										if(vVirtuel.getY()-5>0 && map[vVirtuel.getY()-5][vVirtuel.getX()]==1) { // en haut
											for(int a=vVirtuel.getY()-5;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getY()-1>0 && vVirtuel.getX()-4>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-4]==1) { // à gauche
											for(int a=vVirtuel.getX()-4;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()-1][a]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										break;
									case 1:
										if(vVirtuel.getY()-1>0 && vVirtuel.getX()-1>0 && map[vVirtuel.getY()-1][vVirtuel.getX()-1]==1) { // en haut
											for(int a=vVirtuel.getY()-1;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()-1]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										else if(vVirtuel.getX()-5>0 && map[vVirtuel.getY()][vVirtuel.getX()-5]==1) { // à gauche
											for(int a=vVirtuel.getX()-5;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()][a]) {
													vVirtuel.virage("avant");
													break;
												}
											}
											break;
										}
										break;
									case 2:
										//vérifier à gauche
										if(vVirtuel.getY()+1<map.length && vVirtuel.getX()-1>0 && map[vVirtuel.getY()+1][vVirtuel.getX()-1]==1) {
											for(int a=vVirtuel.getX()-1;a>0;a--) {
												if(mapCarrefour[vVirtuel.getY()+1][a]) {
													vVirtuel.virage("droite");
													break;
												}
											}
											break;
										}
										break;
									case 3:
										//vérifier en haut
										if(vVirtuel.getY()-4>0 && vVirtuel.getX()+1<map[0].length && map[vVirtuel.getY()-4][vVirtuel.getX()+1]==1) {
											for(int a=vVirtuel.getY()-4;a>0;a--) {
												if(mapCarrefour[a][vVirtuel.getX()+1]) {
													vVirtuel.virage("gauche");
													break;
												}
											}
											break;
										}
										break;
									}
								}
								// on a les trajectoires stockées dans vVirtuel
								//penser à changer le sens
								
								boolean avant = true;
								boolean gauche = false;
								boolean droite = false;
								System.out.println("trajectoire stockée :");
								for(int a=0;a<vVirtuel.getTrajectoire().size();a++) {
									System.out.print(vVirtuel.getTrajectoire().get(a)+" ");
								}
								System.out.println();
								
								for(int a=0;a<vVirtuel.getTrajectoire().size();a++) {
									if(vVirtuel.getTrajectoire().get(a)=="avant") {
										trajectoires.get(i).get(j).add("avant");
									}
									else if(vVirtuel.getTrajectoire().get(a)=="gauche") {
										trajectoires.get(i).get(j).add("gauche");
										//trajectoires.get(i).get(j).add("avant");
										gauche = true;
										avant = false;
									}
									else if(vVirtuel.getTrajectoire().get(a)=="droite") {
										trajectoires.get(i).get(j).add("droite");
										droite = true;
										avant = false;
									}
								}
								vVirtuel.getTrajectoire().clear();
								if(avant) {
									switch(vVirtuel.getSens()) {
									case 0:
										vVirtuel.setY(vVirtuel.getY()-5);
										break;
									case 1:
										vVirtuel.setX(vVirtuel.getX()-5);
										break;
									case 2:
										vVirtuel.setY(vVirtuel.getY()+5);
										break;
									case 3:
										vVirtuel.setX(vVirtuel.getX()+5);
										break;
									}
								}
								else if(gauche) {
									switch(vVirtuel.getSens()) {
									case 0:
										vVirtuel.setX(vVirtuel.getX()-4);
										vVirtuel.setY(vVirtuel.getY()-4);
										break;
									case 1:
										vVirtuel.setX(vVirtuel.getX()-4);
										vVirtuel.setY(vVirtuel.getY()+4);
										break;
									case 2:
										vVirtuel.setX(vVirtuel.getX()+4);
										vVirtuel.setY(vVirtuel.getY()+4);
										break;
									case 3:
										vVirtuel.setX(vVirtuel.getX()+4);
										vVirtuel.setY(vVirtuel.getY()-4);
										break;
									}
									//change le sens
									vVirtuel.setSens((vVirtuel.getSens()+1)%4);
								}
								else if(droite) {
									switch(vVirtuel.getSens()) {
									case 0:
										vVirtuel.setX(vVirtuel.getX()+1);
										vVirtuel.setY(vVirtuel.getY()-1);
										break;
									case 1:
										vVirtuel.setX(vVirtuel.getX()-1);
										vVirtuel.setY(vVirtuel.getY()-1);
										break;
									case 2:
										vVirtuel.setX(vVirtuel.getX()-1);
										vVirtuel.setY(vVirtuel.getY()+1);
										break;
									case 3:
										vVirtuel.setX(vVirtuel.getX()+1);
										vVirtuel.setY(vVirtuel.getY()+1);
										break;
									}
									//change le sens
									vVirtuel.setSens(vVirtuel.getSens()-1);
									if(vVirtuel.getSens()<0) {
										vVirtuel.setSens(vVirtuel.getSens()+4);
									}
								}
								System.out.println("avant recopie :  x = "+vVirtuel.getX()+"   y = "+vVirtuel.getY());
								temp = new Voiture(1,vVirtuel.getX(),vVirtuel.getY(),vVirtuel.getSens());
								temp.getTrajectoire().add("avant");
								temp.avance();
							}
							else {
								System.out.println(trajectoires.size());
								System.out.println(trajectoires.get(i).size());
								trajectoires.get(i).get(j).add("avant");
								vVirtuel.getTrajectoire().add("avant");
								vVirtuel.avance();
								temp.getTrajectoire().add("avant");
								temp.avance();
							}
							
							
							
							
							/*while(!mapCarrefour[temp.getY()][temp.getX()] && !(temp.getX()==listeStation.get(j).getXArrive() && temp.getY()==listeStation.get(j).getYArrive())) {
								//tant qu'il n'y a pas un carrefour ou que l'on n'a pas atteint la station
								trajectoires.get(i).get(j).add("avant");
								vVirtuel.avance();
								temp.avance();
							}*/	
							
						}
						
					}
				}
			}
		}
		
		for(int i=0;i<trajectoires.size();i++) {
			System.out.println();
			System.out.println("Station de départ : "+i);
			for(int j=0;j<trajectoires.get(i).size();j++) {
				System.out.println();
				System.out.println("Station d'arrivée : "+j);
				System.out.println();
				for(int k=0;k<trajectoires.get(i).get(j).size();k++) {
					System.out.print(trajectoires.get(i).get(j).get(k)+" ");
				}
			}
		}
		System.out.println("fin");
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
	public void adapteSensVoiture(Voiture v) { // a changer si station pas uniquement sur le bord
		if(map[v.getY()][v.getX()]==2) { // la voiture est sur une station
			if(v.getX()==1) { //bord gauche
				v.setSens(3);
			}
			else if(v.getX()==map[0].length-2) { // bord droit
				v.setSens(1);
			}
			else if(v.getY()==1) { //bord haut
				v.setSens(2);
			}
			else if(v.getY()==map.length-2) { //bord bas
				v.setSens(0);
			}
		}
	}
	
	public char[][] reconnaissanceRoute() { // on peut également s'en servir pour détecter les routes horizontales et verticales ?
		char[][] mapC = new char[map.length][map[0].length];
		boolean routeEnHaut = false;
		boolean routeEnBas = false;
		boolean routeAGauche = false;
		boolean routeADroite = false;
		int compteurRoute=0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j]==1) {
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
						break;
					case 4 :
						mapC[i][j]='c';
						break;
						default :
							break;
					}
				}
			}
		}
		
		for(int i=0;i<mapC.length;i++) {
			for(int j=0;j<mapC[i].length;j++) {
				System.out.print(mapC[i][j]+" ");
			}
			System.out.println();
		}
		return mapC;
	}
	
	public void simuleCoup() {
		ArrayList<Voiture> listeTemp = new ArrayList<Voiture>();
		for(Voiture v : listeVoiture) {
			Voiture temp = new Voiture(1,v.getX(),v.getY(),v.getSens());
		}
	}
}
