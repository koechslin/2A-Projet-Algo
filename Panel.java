
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class Panel extends JPanel{
	// 0 vide 1 route 2 station, r.getMapVoiture() : 0 rien 1 voiture
	public int t = 50; //taille d'une case
	public Reseau r;
	
	public Panel(Reseau res){
		r=res;
		
		this.setBounds(0,0,r.getMap().length*t,r.getMap()[0].length*t);
		
	}
	public void paint(Graphics g){
		
		for(int i=0;i<r.getMap().length;i++){
			for (int j=0;j<r.getMap()[0].length;j++){
				
				if(r.getMap()[i][j]==0){				//herbe
					g.setColor(Color.green);
					g.fillRect(j*t,i*t,t,t);
						
					
				}else if(r.getMap()[i][j]==1){		//route		
					g.setColor(Color.gray);
					g.fillRect(j*t,i*t,t,t);
					
					g.setColor(Color.yellow);
					if(i-2>0){
						if(r.getMap()[i-2][j]==1 || r.getMap()[i-2][j]==2 ){
						g.fillRect(j*t,i*t,2,t);
						g.fillRect((j+1)*t-5,i*t,2,t);
						//System.out.println("i: "+i+" ; j: "+j+" pas bord");
						}
					}else{
						if(r.getMap()[i+2][j]==1 || r.getMap()[i-2][j]==2){
						g.fillRect(j*t,i*t,2,t);
						g.fillRect((j+1)*t-5,i*t,2,t);
						//System.out.println("i: "+i+" ; j: "+j+ " bord");
						}
					}

					
				}else if(r.getMap()[i][j]==2){		//station
					g.setColor(Color.red);
					g.fillRect(j*t,i*t,t,t);
				}
				if(r.getMapVoiture()[i][j]){
					g.setColor(Color.white);		//voiture
					g.fillRect(j*t,i*t,t,t);
				}
			}
		}
			
	}
}

