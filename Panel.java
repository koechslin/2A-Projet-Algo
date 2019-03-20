
import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{
	// 0 vide 1 route 2 station, dynamique : 0 rien 1 voiture
	public Reseau reseau;
	public int t = 10; //taille d'une case
	
	public Panel(Reseau res){
		
		reseau = res;
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		t = Math.min(hauteur/(res.map.length+5),largeur/(res.map[0].length+5));
		
		this.setBounds(0,0,(reseau.map.length+1)*t,(reseau.map[0].length+1)*t);
		
	}
	public void paint(Graphics g){
		boolean horizontal = false;
		boolean vertical = false;
		boolean carrefour = false;
		
		//			***HERBE***
		for(int i=0;i<reseau.map.length;i++){
			for (int j=0;j<reseau.map[0].length;j++){
					g.setColor(Color.green);
					g.fillRect(j*t,i*t,t,t);
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
					
					//tracé des lignes
					g.setColor(Color.yellow);
					if(vertical && !horizontal){
						verticale(g,i,j,t);
					}else if(horizontal && !vertical){
						horizontale(g,i,j,t);
					}
				
					//	***STATION***
					
				}else if(reseau.map[i][j]==2){		
					g.setColor(Color.red);
					g.fillRect(j*t,i*t,t,t);
				}
				
					// ***VOITURE***
					
				if(reseau.mapVoiture[i][j]){
					
					g.setColor(Color.white);
					
					if(horizontal && !vertical){		
						g.fillRect(j*t,(int)((i+0.25)*t),t,(int) (0.5*t));
					} else if(vertical && !horizontal){		
						g.fillRect((int)((j+0.25)*t),i*t,(int)(0.5*t),t);
					}else{
						g.fillRect(j*t,i*t,(int)(0.5*t),t);		//si horizontal ET vertical (croisement) qu'est ce qu'on fait?
						if(carrefour == false){
							reseau.mapCarrefour[i][j]=true;
						}
					}
				}
			}
		}
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

