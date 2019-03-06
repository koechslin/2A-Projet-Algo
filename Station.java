
public class Station {
	private char nomStation;
	private int iStation;
	private int jStation;
	
	public Station(char c) {
		this.nomStation=c;
	}
	
	public char getNomStation() {
		return this.nomStation;
	}
	public int getIStation() {
		return this.iStation;
	}
	public int getJStation() {
		return this.jStation;
	}
	public void setIStation(int newI) {
		this.iStation = newI;
	}
	public void setJStation(int newJ) {
		this.jStation = newJ;
	}

}
