
import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{
	// 0 vide 1 route 2 station, dynamique : 0 rien 1 voiture
	public int[][] statique;
	public boolean [][] dynamique;
	public int t = 20; //taille d'une case
	
	public Panel(int[][] stat, boolean[][]dyn){
		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		t = Math.min(hauteur/stat.length,largeur/stat[0].length);
		
		statique = new int[stat.length+4][stat[0].length+4];
		dynamique = new boolean[dyn.length+4][dyn[0].length+4];
		for(int i=2;i<statique.length-2;i++){
			for (int j=2;j<statique[0].length-2;j++){
				statique[i][j]=stat[i-2][j-2];
			}
		}
		for(int i=2;i<dynamique.length-2;i++){
			for (int j=2;j<dynamique[0].length-2;j++){
				dynamique[i][j]=dyn[i-2][j-2];
			}
		}
		this.setBounds(0,0,statique.length*t,statique[0].length*t);
		
	}
	public void paint(Graphics g){
		boolean horizontal = false;
		boolean vertical = false;
		
		for(int i=1;i<statique.length-1;i++){
			for (int j=1;j<statique[0].length-1;j++){
				horizontal = false;
				vertical = false;
				
				//test vertical/ horizontal
					
					if(i-5>0){
						if(statique[i-4][j]==1 || statique[i-4][j]==2){
							vertical = true;
						}
					}else{
						if(statique[i+4][j]==1 || statique[i+4][j]==2){
							vertical = true;
						}
					}
					
					if(j-5>0){
						if(statique[i][j-4]==1 || statique[i][j-4]==2 ){
							horizontal=true;
							}
						}else{
							if(statique[i][j+4]==1 || statique[i][j+4]==2 ){
								horizontal=true;
							}
						}
				//			***HERBE***
				
				if(statique[i][j]==0){				
					g.setColor(Color.green);
					g.fillRect(j*t,i*t,t,t);
				
				//			***ROUTE***
					
				}else if(statique[i][j]==1){			
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
					
				}else if(statique[i][j]==2){		
					g.setColor(Color.red);
					g.fillRect(j*t,i*t,t,t);
				}
				
					// ***VOITURE***
					
				if(dynamique[i][j]){
					
					g.setColor(Color.white);
					
					if(horizontal && !vertical){		
						g.fillRect(j*t,(int)((i+0.25)*t),t,(int) (0.5*t));
					} else if(vertical && !horizontal){		
						g.fillRect((int)((j+0.25)*t),i*t,(int)(0.5*t),t);
					}else{
						g.fillRect(j*t,i*t,(int)(0.5*t),t);		//si horizontal ET vertical (croisement) qu'est ce qu'on fait?
					}
				}
			}
		}
			
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

