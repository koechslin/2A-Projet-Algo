
public class Station {
	private char nomStation;
	private int xStation;
	private int yStation;
	
	public Station(char c) {
		this.nomStation=c;
	}
	
	public char getNomStation() {
		return this.nomStation;
	}
	public int getXStation() {
		return this.xStation;
	}
	public int getYStation() {
		return this.yStation;
	}
	public void setXStation(int newX) {
		this.xStation = newX;
	}
	public void setYStation(int newY) {
		this.yStation = newY;
	}

}
