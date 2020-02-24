package experiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	BoardCell[][] board;
	Map<BoardCell, HashSet<BoardCell>> adjacencies;
	ArrayList<BoardCell> targets;
	ArrayList<BoardCell> visited;
	public IntBoard(){
		adjacencies = new HashMap<BoardCell,HashSet<BoardCell>>();
		board = new BoardCell[24][24];
		for(int i=0;i<24;i++) {
			for(int j=0;j<24;j++) {
				BoardCell temp = new BoardCell();
				temp.column=j;
				temp.row=i;
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
					temp.add(board[i-1][j]);
				}
				if(board[i][j].row+1<=23) {
					temp.add(board[i+1][j]);
				}
				if(board[i][j].column-1>=0) {
					temp.add(board[i][j-1]);
				}
				if(board[i][j].column+1<=23) {
					temp.add(board[i][j+1]);
				}
				adjacencies.put(board[i][j], temp);
			}
		}
	}
	public Set<BoardCell> getAdjList(BoardCell b) {
		return adjacencies.get(b);
	}
	
	private void calcTargets(BoardCell startCell, int pathLength) {
		visited = new ArrayList<BoardCell>();
		targets = new ArrayList<BoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}
	
	private void findAllTargets(BoardCell thisCell,int numSteps) {
		for(BoardCell temp: adjacencies.get(thisCell)) {
			if(!visited.contains(temp)) {
				visited.add(temp);
				if(numSteps==1) {
					targets.add(temp);
				}
				else {
					findAllTargets(temp,numSteps-1);
				}
				visited.remove(temp);
			}
		}
		
	}

	public ArrayList<BoardCell> getTargets(){
		return targets;
	}

	public  BoardCell getCell(int i, int j) {
		return board[i][j];
	}
	
	public static void main(String[] args) {
        IntBoard board = new IntBoard();
        BoardCell testCell = board.getCell(23, 2);
        BoardCell testCell2 = board.getCell(0, 1);
        Set<BoardCell> testList =  board.getAdjList(testCell);
    }
}
