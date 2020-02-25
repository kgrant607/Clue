package clueGame;

public class BoardCell {
	int row;
	int column;
	String initial;
	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isRoom() {
		return false;
		
	}
	
	public boolean isWalkway() {
		return false;
	}
	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getInitial() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
}
