
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel{
	// 0 vide 1 route 2 station, dynamique : 0 rien 1 voiture
	public Reseau reseau;
	public int t = 10; //taille d'une case
	private BufferedImage herbe=null;
	private BufferedImage station=null;
	private BufferedImage voiture_1=null;
	private char[][] mapDessin;
	
	public Panel(Reseau res){
		
		try {
			herbe = ImageIO.read(new File("C:/Users/Killian/Desktop/Images_projet/Herbe.PNG"));
			station = ImageIO.read(new File("C:/Users/Killian/Desktop/Images_projet/station.jpg"));
			voiture_1 = ImageIO.read(new File("C:/Users/Killian/Desktop/Images_projet/test.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		reseau = res;
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		t = Math.min(hauteur/(res.map.length+5),largeur/(res.map[0].length+5));
		
		this.setBounds(0,0,(reseau.map.length+1)*t,(reseau.map[0].length+1)*t);
		mapDessin = res.reconnaissanceRoute();
		
	}
	public void paint(Graphics g){
		boolean horizontal = false;
		boolean vertical = false;
		boolean carrefour = false;
		
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
					//tracé des lignes
					g.setColor(Color.yellow);
					if(vertical && !horizontal){
						verticale(g,i,j,t);
					}else if(horizontal && !vertical){
						horizontale(g,i,j,t);
					}
					*/
					
					// Nouveau tracé ligne (test)
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
						g.setColor(Color.WHITE);
						Graphics2D g2d = (Graphics2D) g;
						double rotation=0;
						switch(v.getSens()) {
						case 0:
							rotation =Math.PI/2;
							break;
						case 1:
							rotation =0;
							break;
						case 2:
							rotation=-Math.PI/2;
							break;
						case 3:
							rotation=Math.PI;
							break;
						}
						AffineTransform tx = AffineTransform.getRotateInstance(rotation, voiture_1.getWidth()/2, voiture_1.getHeight()/2);
						AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

						// Drawing the rotated image at the required drawing locations
						//g2d.drawImage(op.filter(voiture_1, null), v.getX()*t, v.getY()*t,t,t, null);
						
						//g2d.rotate(45);
						g2d.drawImage(voiture_1, v.getX()*t, v.getY()*t, t, t, null);
						//g2d.rotate(-45);
						//g.fillRect(v.getX()*t, v.getY()*t, t, t);
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
	public static void horizontale(Graphics g,int i, int j,int t){
		g.setColor(Color.yellow);
		g.fillRect(j*t,i*t+1,t,2);
		g.fillRect(j*t,(i+1)*t-3,t,2);
	}
	public static void verticale(Graphics g,int i, int j, int t){
		g.setColor(Color.yellow);
		g.fillRect(j*t+1,i*t,2,t);
		g.fillRect((j+1)*t-3,i*t,2,t);
	}
}

