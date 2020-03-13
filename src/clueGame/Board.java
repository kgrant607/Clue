package clueGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

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
	private HashSet<BoardCell> adjacenciesSet;
	private Map<Character, String> legendMap;
	public static Board getInstance() {
		return new Board();
	}

	public void setConfigFiles(String string, String string2) {
		boardConfigFile = string;
		roomConfigFile = string2;	
	}
	
	private void calcAdjacencies() {
		for(int i=0;i<board.length;i++){
			for(int j =0; j<board[i].length;j++) {
				adjacenciesSet = new HashSet<BoardCell>();
				if(board[i][j].getRow()-1>=0) {
					adjacenciesSet.add(board[i-1][j]);
				}
				if(board[i][j].getRow()+1<=23) {
					adjacenciesSet.add(board[i+1][j]);
				}
				if(board[i][j].getColumn()-1>=0) {
					adjacenciesSet.add(board[i][j-1]);
				}
				if(board[i][j].getColumn()+1<=23) {
					adjacenciesSet.add(board[i][j+1]);
				}
				adjMatrix.put(board[i][j], adjacenciesSet);
			}
		}
	}
	public void initialize() {

		try {
			loadBoardConfig();
			loadRoomConfig();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		
		
	}

	public Map<String, String> getLegendString(){
		return legend;
	}
	public Map<Character, String> getLegend() {
		legendMap = new HashMap<>();
		for (Map.Entry<String, String> entry : legend.entrySet()) {
			legendMap.put(entry.getKey().charAt(0), entry.getValue());
		}
		return legendMap;
	}

	public int getNumRows() {
		int i = 0;
		while(i < MAX_BOARD_SIZE & board[i][0]!=null) {
			i++;
		}
		return i;
	}

	public int getNumColumns() {
		int i = 0;
		while(i < MAX_BOARD_SIZE & board[0][i]!=null) {
			i++;
		}
		return i;
	}

	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}

	public void loadRoomConfig() throws BadConfigFormatException{
		legend = new HashMap<>();
		File legendFile = new File(roomConfigFile);
		try (Scanner sc = new Scanner(legendFile)){
			while(sc.hasNextLine()) {
				String raw = sc.nextLine();
				legend.put(raw.split(", ")[0], raw.split(", ")[1]);
				if(raw.split(", ")[2] == "Card" || raw.split(", ")[2] == "Other") {
					throw new BadConfigFormatException();
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Legend file could not be opened");
			e.printStackTrace();
		}
	}

	public void loadBoardConfig() throws BadConfigFormatException{
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		FileReader boardFile = null;
		try {
			boardFile = new FileReader(boardConfigFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(boardFile)){
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				for(int j = 0; j < values.length; j++) {
					if(!Character.isDigit(values[j].charAt(0))) {
						BoardCell b = new BoardCell(i,j);
						b.initial = values[j];
						board[i][j] =  b;
					}
				}
				i++;
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Set<BoardCell> getAdjList(int row, int column) {
		HashSet<BoardCell> s = new HashSet<BoardCell>();
		if(board[row][column].isWalkway()) {
			//check left
			if(column - 1 >= 0) {
				if(board[row][column-1].isWalkway() || (board[row][column-1].isDoorway() && board[row][column-1].getDir()=='R')) {
					s.add(board[row][column-1]);
				}
			}
			//check right
			if(column + 1 < this.getNumColumns()) {
				if(board[row][column+1].isWalkway() || (board[row][column+1].isDoorway() && board[row][column+1].getDir()=='L')) {
					s.add(board[row][column+1]);
				}
			}
			//check up
			if(row - 1 >= 0) {
				if(board[row-1][column].isWalkway() || (board[row-1][column].isDoorway() && board[row-1][column].getDir()=='D')) {
					s.add(board[row-1][column]);
				}
			}
			//check down
			if(row + 1 < this.getNumRows()) {
				if(board[row+1][column].isWalkway() || (board[row+1][column].isDoorway() && board[row+1][column].getDir()=='U')) {
					s.add(board[row+1][column]);
				}
			}
		}else {
			if(board[row][column].isDoorway()) {
				char dir = board[row][column].getDir();
				if(dir == 'R') {
					s.add(board[row][column+1]);
				}
				if(dir == 'L') {
					s.add(board[row][column-1]);
				}
				if(dir == 'U') {
					s.add(board[row-1][column]);
				}
				if(dir == 'D') {
					s.add(board[row+1][column]);
				}
			}
		}
		return s;
	}


	public Set<BoardCell> getTargets() {
		return null;
	}

}
