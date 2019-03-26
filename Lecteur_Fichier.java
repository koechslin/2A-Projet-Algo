import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Lecteur_Fichier {
	File file;
	
	public Lecteur_Fichier() {
		
	}
	/*public static void main(String[] args) {
		ouvertureFichier();
		traitementFichier(file);
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}*/
	
	public int[][] traitementFichier() {
		int[][] m=new int[0][0];
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.file));
			String line;
			int k=0;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				
				// On considère taille carré
				if(k==0) {
					m = new int[line.length()][line.length()];
				}
				for(int i=0;i<line.length();i++) {
					m[k][i]=Character.getNumericValue(line.charAt(i));
				}
				k++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public void ouvertureFichier() {
		JFileChooser choix = new JFileChooser();
		int retour=choix.showOpenDialog(null);
		if(retour==JFileChooser.APPROVE_OPTION){
		   // un fichier a été choisi (sortie par OK)
			file=choix.getSelectedFile();
		}else {
			// pas de fichier choisi
		}
	}
}
