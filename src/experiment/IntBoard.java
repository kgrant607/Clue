package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	BoardCell[][] board;
	Map<BoardCell, HashSet<BoardCell>> adjacencies;
	HashSet<BoardCell> targets;
	public IntBoard(){
		targets = new HashSet<BoardCell>();
		adjacencies = new HashMap<BoardCell,HashSet<BoardCell>>();
		board = new BoardCell[24][24];
		for(int i=0;i<24;i++) {
			for(int j=0;j<24;j++) {
				BoardCell temp = new BoardCell();
				temp.column=i;
				temp.row=j;
				board[i][j]=temp;
			}
		}
		calcAdjacencies();
	}
	
	private void calcAdjacencies() {
		for(int i=0;i<board.length;i++){
			for(int j =0; j<board[i].length;j++) {
				HashSet<BoardCell> temp = new HashSet<BoardCell>();
				if(board[i][j].row-1>=0) {
					BoardCell add = new BoardCell();
					add.row=board[i][j].row-1;
					add.column=board[i][j].column;
					temp.add(add);
				}
				if(board[i][j].row+1<=23) {
					BoardCell add = new BoardCell();
					add.row=board[i][j].row+1;
					add.column=board[i][j].column;
					temp.add(add);
				}
				if(board[i][j].column-1>=0) {
					BoardCell add = new BoardCell();
					add.row=board[i][j].row;
					add.column=board[i][j].column-1;
					temp.add(add);
				}
				if(board[i][j].column+1<=23) {
					BoardCell add = new BoardCell();
					add.row=board[i][j].row;
					add.column=board[i][j].column+1;
					temp.add(add);
				}
				adjacencies.put(board[i][j], temp);
			}
		}
	}
	public Set<BoardCell> getAdjList(BoardCell b) {
		return adjacencies.get(b);
	}
	
	private void calcTargets(BoardCell startCell, int pathLength) {
		
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}

	public  BoardCell getCell(int i, int j) {
		return board[i][j];
	}
	
	public static void main(String[] args) {
        IntBoard board = new IntBoard();
        BoardCell testCell = board.getCell(0, 0);
    }
}
