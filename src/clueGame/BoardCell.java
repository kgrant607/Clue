package clueGame;

public class BoardCell {
	int row;
	int column;
	String initial;
	
	BoardCell(int r, int c){
		row = r;
		column = c;
	}
	
	public boolean isDoorway() {
		if(initial.length() > 2) {
			return true;
		}else if (initial.length() == 2) {
			if(initial.charAt(1)=='D' | initial.charAt(1)=='R' | initial.charAt(1)=='L' | initial.charAt(1)=='U') {
				return true;
			}
		}
		return false;
	}
	public boolean isRoom() {
		if(this.initial != "W" && this.initial != "X") {
			return true;
		}
		return false;
	}
	
	public boolean isWalkway() {
		if(this.initial == "W") {
			return true;
		}
		return false;
	}
	public DoorDirection getDoorDirection() {
	if(initial.length()==3) {
		switch(initial.charAt(2)) {
			case 'D':
				return DoorDirection.DOWN;
			case 'U':
				return DoorDirection.UP;
			case 'L':
				return DoorDirection.LEFT;
			case 'R':
				return DoorDirection.RIGHT;
			}
			return null;
		}else {
			switch(initial.charAt(1)) {
			case 'D':
				return DoorDirection.DOWN;
			case 'U':
				return DoorDirection.UP;
			case 'L':
				return DoorDirection.LEFT;
			case 'R':
				return DoorDirection.RIGHT;
			}
			return null;
		}
	}
	public String getInitialString() {
		return initial;
	}
	public char getInitial() {
		return initial.charAt(0);
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
}
