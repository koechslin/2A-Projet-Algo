
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
			herbe = ImageIO.read(new File("Herbe.PNG"));
			station = ImageIO.read(new File("station.png"));
			vgauche = ImageIO.read(new File("vgauche.png"));
			vdroite = ImageIO.read(new File("vdroite.png"));
			vhaut = ImageIO.read(new File("vhaut.png"));
			vbas = ImageIO.read(new File("vbas.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		reseau = res;
		h = height;
		l = width;

		t = Math.min(h/(res.map.length),l/(res.map[0].length));
		mapDessin = res.reconnaissanceRoute();
		
	}
	public void paint(Graphics g){
		boolean horizontal = false;
		boolean vertical = false;
		boolean carrefour = false;
		int k = 0;
		//			***HERBE***
		for(int i=0;i<reseau.map.length;i++){
			for (int j=0;j<reseau.map[0].length;j++){
					g.setColor(Color.green);
					g.drawImage(herbe, j*t, i*t, t, t, null);
					//g.fillRect(j*t,i*t,t,t);
				}
		}
				
		for(int i=1;i<reseau.map.length-1;i++){
			for (int j=1;j<reseau.map[0].length-1;j++){
				horizontal = false;
				vertical = false;
				
				//test vertical/ horizontal
					
					if(i-5>0){
						if(reseau.map[i-4][j]==1 || reseau.map[i-4][j]==2){
							vertical = true;
						}
					}else{
						if(reseau.map[i+4][j]==1 || reseau.map[i+4][j]==2){
							vertical = true;
						}
					}
					
					if(j-5>0){
						if(reseau.map[i][j-4]==1 || reseau.map[i][j-4]==2 ){
							horizontal=true;
							}
						}else{
							if(reseau.map[i][j+4]==1 || reseau.map[i][j+4]==2 ){
								horizontal=true;
							}
						}
				
				
				/*if(reseau.map[i][j]==0){				
					g.setColor(Color.green);
					g.fillRect(j*t,i*t,t,t);
				
				//			
					
				}else*/
					
					//***ROUTE***
					
					if(reseau.map[i][j]==1){			
					g.setColor(Color.gray);
					g.fillRect(j*t,i*t,t,t);
					
					/*
					//trace des lignes
					g.setColor(Color.yellow);
					if(vertical && !horizontal){
						verticale(g,i,j,t);
					}else if(horizontal && !vertical){
						horizontale(g,i,j,t);
					}
					*/
					
					// Nouveau trace ligne (test)
					g.setColor(Color.yellow);
					if(mapDessin[i][j]=='v'){
						verticale(g,i,j,t);
					}else if(mapDessin[i][j]=='h'){
						horizontale(g,i,j,t);
					}
					
					//	***STATION***
					
				}else if(reseau.map[i][j]==2){		
					g.setColor(Color.red);
					g.drawImage(station, j*t, i*t, t, t, null);
					//g.fillRect(j*t,i*t,t,t);
				}
					
					//CARREFOUR :
					/*
					if(horizontal && vertical) {
						reseau.mapCarrefour[i][j]=true;
					}*/
					
					if(mapDessin[i][j]=='c') {
						reseau.mapCarrefour[i][j]=true;
					}
					
				
					// ***VOITURE***
					for(Voiture v : this.reseau.getVoitures()) {
						if(mapDessin[v.getY()][v.getX()]!='c' &&mapDessin[v.getY()][v.getX()]!='s' && mapDessin[v.getY()][v.getX()]!='h'&&mapDessin[v.getY()][v.getX()]!='v') {
							System.out.println("bizarre");
							System.out.println("de "+v.getStatDep()+" a "+v.getStatArr());
							g.setColor(Color.RED);
							g.drawRect(v.getX()*t,v.getY()*t,t,t);
							g.drawRect(v.getX()*t-1,v.getY()*t-1,t+2,t+2);
							g.drawRect(v.getX()*t-2,v.getY()*t-2,t+4,t+4);
						}
						//Surbrillance de la voiture selectionnee
						if(voitSurbrillance!=-1 && v.getNumero()==this.voitSurbrillance && mode=="Manuel") {
							g.setColor(Color.BLUE);
							g.drawRect(v.getX()*t,v.getY()*t,t,t);
							g.drawRect(v.getX()*t-1,v.getY()*t-1,t+2,t+2);
							g.drawRect(v.getX()*t-2,v.getY()*t-2,t+4,t+4);
						}
						for(Voiture v2 : this.reseau.getVoitures()) {
							if(this.reseau.map[v.getY()][v.getX()]!=2 && v!=v2) {
								if(v.getX()==v2.getX()&&v.getY()==v2.getY()) {
									g.setColor(Color.RED);
									g.drawRect(v.getX()*t,v.getY()*t,t,t);
									g.drawRect(v.getX()*t-1,v.getY()*t-1,t+2,t+2);
									g.drawRect(v.getX()*t-2,v.getY()*t-2,t+4,t+4);
								}
							}
						}
						if(!(mapDessin[v.getY()][v.getX()]=='s')) {
							//g.setColor(Color.WHITE);
							Graphics2D g2d = (Graphics2D) g;
							double rotation=0;
							switch(v.getSens()) {
							case 0:
								//rotation =Math.PI/2;
								g2d.drawImage(vhaut, v.getX()*t, v.getY()*t, t, t, null);
								
								break;
							case 1:
								//rotation =0;
								g2d.drawImage(vgauche, v.getX()*t, v.getY()*t, t, t, null);
								break;
							case 2:
								//rotation=-Math.PI/2;
								g2d.drawImage(vbas, v.getX()*t, v.getY()*t, t, t, null);
								break;
							case 3:
								//rotation=Math.PI;
								g2d.drawImage(vdroite, v.getX()*t, v.getY()*t, t, t, null);
								break;
							}
							//AffineTransform tx = AffineTransform.getRotateInstance(rotation, voiture_1.getWidth()/2, voiture_1.getHeight()/2);
							//AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

							// Drawing the rotated image at the required drawing locations
							//g2d.drawImage(op.filter(voiture_1, null), v.getX()*t, v.getY()*t,t,t, null); //rotation
							
							//g2d.rotate(90, mapDessin[0].length*t/2,mapDessin.length*t/2);
							//g2d.drawImage(voiture_1, v.getX()*t, v.getY()*t, t, t, null);   // a utiliser
							//g2d.rotate(-90,mapDessin[0].length*t/2,mapDessin[0].length*t/2);
							//g.fillRect(v.getX()*t, v.getY()*t, t, t);
						}
					}
					//Surbrillance Station

					String numAffiche="";
					int ii = 0;
					int jj=0;
					for( Station s : reseau.getStations()){
						g.setColor(Color.white);
						g.setFont(new Font("impact", Font.BOLD, 20));
						numAffiche = " "+Integer.toString(s.getNumStation());
						ii=s.getXDepart();
						jj=s.getYDepart();
						//System.out.println("taille array: "+reseau.listeStation.size()+" k: "+k+ " num�ro station affich� : "+reseau.listeStation.get(k).numStation);
						if(ii+1<reseau.map[0].length && reseau.map[jj][ii+1]==1){	//si route � droite
							ii-=1;
						}else if(ii-1>0 && reseau.map[jj][ii-1]==1){	//si route � gauche
							ii+=1;
							jj+=2;
						}else if(jj+1<reseau.map.length && reseau.map[jj+1][ii]==1){	//si route en bas
							ii+=2;
						}else if(jj-1>0&& reseau.map[jj-1][ii]==1){	//si route en haut
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
					
					
				/*if(reseau.mapVoiture[i][j]){
					
					g.setColor(Color.white);
					
					if(horizontal && !vertical){		
						g.fillRect(j*t,(int)((i+0.25)*t),t,(int) (0.5*t));
					} else if(vertical && !horizontal){		
						g.fillRect((int)((j+0.25)*t),i*t,(int)(0.5*t),t);
					}else{
						g.fillRect(j*t,i*t,(int)(0.5*t),t);		//si horizontal ET vertical (croisement) qu'est ce qu'on fait?
						if(carrefour == false){
							reseau.mapCarrefour[i][j]=true;
							System.out.println("-------------------------------");
						}
					}
				}*/
			}
		}
		/*for(int a=0;a<reseau.mapCarrefour.length;a++) {
			for(int b=0;b<reseau.mapCarrefour[a].length;b++) {
				System.out.print(reseau.mapCarrefour[a][b]+" ");
			}
			System.out.println();
		}*/
		
		carrefour= true;
			
	}
	public void horizontale(Graphics g,int i, int j,int t){
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
	public void verticale(Graphics g,int i, int j, int t){
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
	public void actualiseMapDessin() {
		this.mapDessin = this.reseau.reconnaissanceRoute();
	}
	public void recalculT() {
		t = Math.min(h/(reseau.map.length),l/(reseau.map[0].length));
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

