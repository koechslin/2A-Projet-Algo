
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel{
	public Reseau reseau;
	private int t; //taille d'une case
	private BufferedImage herbe=null;
	private BufferedImage station=null;
	private BufferedImage vgauche=null;
	private BufferedImage vdroite=null;
	private BufferedImage vhaut=null;
	private BufferedImage vbas=null;
	private int h;
	private int l;
	private int voitSurbrillance=-1;
	private int statSurbrillance=-1;
	private String mode="Automatique";
	private boolean defmap = false;
	public char[][] mapDessin;
	
	public Panel(Reseau res,int width, int height){
		
		try {
			herbe = ImageIO.read(new File("Herbe.PNG"));		//importation des images nécessaires
			station = ImageIO.read(new File("station.png"));
			vgauche = ImageIO.read(new File("vgauche.png"));
			vdroite = ImageIO.read(new File("vdroite.png"));
			vhaut = ImageIO.read(new File("vhaut.png"));
			vbas = ImageIO.read(new File("vbas.png"));
		
		} catch (IOException e) {								// affiche une erreur si le programme ne trouve pas une image
			e.printStackTrace();
		}
		
		reseau = res;
		h = height;
		l = width;

		t = Math.min(h/(res.getMap().length),l/(res.getMap()[0].length)); // détermine la taille d'une case en fonction de la taille de l'écran
		mapDessin = res.reconnaissanceRoute();					// copie le tableau des routes précisant si elles sont horizontales ou verticales
		
	}
	public void paint(Graphics g){		//on dessine la map
		boolean horizontal = false;
		boolean vertical = false;
		int k = 0;
		//			***HERBE***
		for(int i=0;i<reseau.getMap().length;i++){
			for (int j=0;j<reseau.getMap()[0].length;j++){
					g.setColor(Color.green);
					g.drawImage(herbe, j*t, i*t, t, t, null);
				}
		}
				
		for(int i=1;i<reseau.getMap().length-1;i++){
			for (int j=1;j<reseau.getMap()[0].length-1;j++){
				horizontal = false;
				vertical = false;
				
					//***ROUTE***
					
					if(reseau.getMap()[i][j]==1){			
					g.setColor(Color.gray);
					g.fillRect(j*t,i*t,t,t);
					
					// Tracé des lignes jaunes 
					g.setColor(Color.yellow);
					if(mapDessin[i][j]=='v'){
						verticale(g,i,j,t);
					}else if(mapDessin[i][j]=='h'){
						horizontale(g,i,j,t);
					}
					
					//	***STATION***
					
				}else if(reseau.getMap()[i][j]==2){		
					g.setColor(Color.red);
					g.drawImage(station, j*t, i*t, t, t, null);
				}	
				
					// ***VOITURE***
					for(Voiture v : this.reseau.getVoitures()) {
						if(mapDessin[v.getY()][v.getX()]!='c' &&mapDessin[v.getY()][v.getX()]!='s' && mapDessin[v.getY()][v.getX()]!='h'&&mapDessin[v.getY()][v.getX()]!='v') {
							g.setColor(Color.RED);
							g.drawRect(v.getX()*t,v.getY()*t,t,t);
							g.drawRect(v.getX()*t-1,v.getY()*t-1,t+2,t+2);
							g.drawRect(v.getX()*t-2,v.getY()*t-2,t+4,t+4);
						}
						
						//Surbrillance de la voiture selectionnee dans le mode manuel
						if(voitSurbrillance!=-1 && v.getNumero()==this.voitSurbrillance && mode=="Manuel") {
							g.setColor(Color.BLUE);
							g.drawRect(v.getX()*t,v.getY()*t,t,t);
							g.drawRect(v.getX()*t-1,v.getY()*t-1,t+2,t+2);
							g.drawRect(v.getX()*t-2,v.getY()*t-2,t+4,t+4);
						}
						
						// Voiture encadrée en rouge s'il y a une collision
						for(Voiture v2 : this.reseau.getVoitures()) {
							if(this.reseau.getMap()[v.getY()][v.getX()]!=2 && v!=v2) {
								if(v.getX()==v2.getX()&&v.getY()==v2.getY()) {
									g.setColor(Color.RED);
									g.drawRect(v.getX()*t,v.getY()*t,t,t);
									g.drawRect(v.getX()*t-1,v.getY()*t-1,t+2,t+2);
									g.drawRect(v.getX()*t-2,v.getY()*t-2,t+4,t+4);
								}
							}
						}
						if(!(mapDessin[v.getY()][v.getX()]=='s')) { // gestion de l'orientation de l'image de la voiture
							Graphics2D g2d = (Graphics2D) g;
							double rotation=0;
							switch(v.getSens()) {
							case 0:
								g2d.drawImage(vhaut, v.getX()*t, v.getY()*t, t, t, null);
								break;
							case 1:
								g2d.drawImage(vgauche, v.getX()*t, v.getY()*t, t, t, null);
								break;
							case 2:
								g2d.drawImage(vbas, v.getX()*t, v.getY()*t, t, t, null);
								break;
							case 3:
								g2d.drawImage(vdroite, v.getX()*t, v.getY()*t, t, t, null);
								break;
							}
						}
					}
					
					//Surbrillance Station selectionnee dans le mode manuel et affichage du numero de la station

					String numAffiche="";
					int ii = 0;
					int jj=0;
					for( Station s : reseau.getStations()){
						g.setColor(Color.white);
						g.setFont(new Font("impact", Font.BOLD, 20));
						numAffiche = " "+Integer.toString(s.getNumStation());
						ii=s.getXDepart();
						jj=s.getYDepart();
					
						if(ii+1<reseau.getMap()[0].length && reseau.getMap()[jj][ii+1]==1){	//si route a droite
							ii-=1;
						}else if(ii-1>0 && reseau.getMap()[jj][ii-1]==1){	//si route a gauche
							ii+=1;
							jj+=2;
						}else if(jj+1<reseau.getMap().length && reseau.getMap()[jj+1][ii]==1){	//si route en bas
							ii+=2;
						}else if(jj-1>0&& reseau.getMap()[jj-1][ii]==1){	//si route en haut
							ii-=2;
							jj+=2;
						}	
						g.drawString(numAffiche,(ii)*t,(jj)*t);
						if(statSurbrillance!=-1 && s.getNumStation() == this.statSurbrillance){
							g.setColor(Color.BLUE);
							jj-=1;
							g.drawRect(ii*t,jj*t,t,t);
							g.drawRect(ii*t-1,jj*t-1,t+2,t+2);
							g.drawRect(ii*t-2,jj*t-2,t+4,t+4);
						}
						
					}
	
			}
		}
			
	}
	public void horizontale(Graphics g,int i, int j,int t){		//trace des lignes jaunes horizontales
		g.setColor(Color.yellow);
		g.fillRect(j*t,i*t+1,t,t/12);
		
		if(i+3<mapDessin.length && mapDessin[i+3][j]=='h'  && mapDessin[i+1][j]=='h' && mapDessin[i+2][j]=='h'  ){
		g.fillRect(j*t,(i+2)*t-t/12,t,t/12);
		}
		
		if(i-3>0 && mapDessin[i-3][j]=='h'  && mapDessin[i-1][j]=='h' && mapDessin[i-2][j]=='h'  ){
			g.fillRect(j*t,(i-1)*t-t/12,t,t/12);
			g.fillRect(j*t,(i+1)*t-t/12,t,t/12);
				
		}
		
	}
	public void verticale(Graphics g,int i, int j, int t){	//trace des lignes jaunes verticales
		g.setColor(Color.yellow);
		g.fillRect(j*t+1,i*t,t/12,t);
		if(j+3<mapDessin[0].length && mapDessin[i][j+3]=='v'  && mapDessin[i][j+1]=='h' && mapDessin[i][j+2]=='h'  ){
		g.fillRect((j+2)*t-t/12,i*t,t,t/12);
		}
		
		if(j-3>0 && mapDessin[i][j-3]=='v'  && mapDessin[i][j-1]=='v' && mapDessin[i][j-2]=='v'  ){
			g.fillRect((j-1)*t-t/12,i*t,t/12,t);
			g.fillRect((j+1)*t-t/12,i*t,t/12,t);
				
		}
	}
	public void actualiseMapDessin() {						// changer la mapDessin en cas de changement de map
		this.mapDessin = this.reseau.reconnaissanceRoute();
	}
	public void recalculT() {								// recalculer la taille d'une case (quand on change de map)
		t = Math.min(h/(reseau.getMap().length),l/(reseau.getMap()[0].length));
	}
	public int getT() {
		return t;
	}
	public int getVoitSurbrillance() {
		return this.voitSurbrillance;
	}
	public void setVoitSurbrillance(int s) {
		this.voitSurbrillance = s;
	}
	public void setStatSurbrillance(int s){
		this.statSurbrillance =s;
	}
	public String getMode() {
		return this.mode;
	}
	public void setMode(String m) {
		this.mode=m;
	}
}

