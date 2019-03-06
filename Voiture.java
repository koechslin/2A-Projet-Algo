
public class Voiture {
	
	private int vitesse;
	private int numero;
	private String StatDep;
	private String StatArr;
	
	public Voiture(int v, int n, String StatDep, String StatArr){
		this.vitesse = v;
		this.numero = n;
		this.StatDep = StatDep;
		this.StatArr = StatArr;
	}
	
	public int getVitesse() {
		return this.vitesse;
	}
	public void setVitesse(int v) {
		this.vitesse = v;
	}
	public int getNumero() {
		return this.numero;
	}
	public void setNumero(int n) {
		this.numero = n;
	}
	public String getStatDep() {
		return this.StatDep;
	}
	public void setStatDep(String StatDep) {
		this.StatDep = StatDep;
	}
	

}
