
public class Station {
	private int numStation;
	private int xDepartStation;
	private int xArriveStation;
	private int yDepartStation;
	private int yArriveStation;
	
	public Station(int c, int xD, int yD,int xA, int yA) { // comment détecter départ et arrivée ?
		this.numStation=c;
		this.xArriveStation=xA;
		this.xDepartStation=xD;
		this.yArriveStation=yA;
		this.yDepartStation=yD;
	}
	
	public int getNumStation() {
		return this.numStation;
	}
	public int getXDepart() {
		return this.xDepartStation;
	}
	public int getXArrive() {
		return this.xArriveStation;
	}
	public int getYDepart() {
		return this.yDepartStation;
	}
	public int getYArrive() {
		return this.yArriveStation;
	}
	
	public void setXDepart(int x) {
		this.xDepartStation=x;
	}
	public void setXArrive(int x) {
		this.xArriveStation=x;
	}
	public void setYDepart(int y) {
		this.yDepartStation=y;
	}
	public void setYArrive(int y) {
		this.yArriveStation=y;
	}
	
	public String toString() {
		return "Station numéro : "+this.numStation+"\n coord départ : x = "+this.xDepartStation+"  y = "+this.yDepartStation+"\n  coord d'arrivé : x = "+this.xArriveStation+" y = "+this.yArriveStation;
	}

}
