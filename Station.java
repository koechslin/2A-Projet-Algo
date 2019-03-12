
public class Station {
	private int numStation;
	private int xStation;
	private int yStation;
	
	public Station(int c, int x, int y) {
		this.numStation=c;
		this.xStation = x;
		this.yStation = y;
	}
	
	public int getNumStation() {
		return this.numStation;
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
