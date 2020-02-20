package experiment;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	BoardCell[] board;
	Map<BoardCell, HashSet<BoardCell>> adjacencies;
	HashSet<BoardCell> targets;
	IntBoard(){
		calcAdjacencies();
	}
	
	private void calcAdjacencies() {
		
	}
	public Set<BoardCell> getAdjList(BoardCell b) {
		return adjacencies.get(b);
	}
	
	private void calcTargets(BoardCell startCell, int pathLength) {
		
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
}
