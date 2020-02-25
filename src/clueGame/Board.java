package clueGame;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public final int MAX_BOARD_SIZE=200;
	private BoardCell[][] board;
	private Map<String,String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	public static Board getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setConfigFiles(String string, String string2) {
		// TODO Auto-generated method stub
		
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	private void calcAdjacencies() {
		for(int i=0;i<board.length;i++){
			for(int j =0; j<board[i].length;j++) {
				HashSet<BoardCell> temp = new HashSet<BoardCell>();
				if(board[i][j].getRow()-1>=0) {
					temp.add(board[i-1][j]);
				}
				if(board[i][j].getRow()+1<=23) {
					temp.add(board[i+1][j]);
				}
				if(board[i][j].getColumn()-1>=0) {
					temp.add(board[i][j-1]);
				}
				if(board[i][j].getColumn()+1<=23) {
					temp.add(board[i][j+1]);
				}
				adjMatrix.put(board[i][j], temp);
			}
		}
	}
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

}
