package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.BoardCell;
import experiment.IntBoard;

class IntBoardTests {
	IntBoard board = new IntBoard();
	@BeforeEach
    public void beforeAll() {
		board = new IntBoard();
    }
	
	@Test
	public void testAdjacency0_0()
	{
		BoardCell testCell = board.getCell(0,0);
		Set<BoardCell> testList =  board.getAdjList(testCell);
		assertTrue(testList.contains(board.getCell(1,0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2,testList.size());
	}
	
	@Test
	public void testAdjacency23_23() {
		BoardCell testCell = board.getCell(23, 23);
		Set<BoardCell> testList =  board.getAdjList(testCell);
		assertTrue(testList.contains(board.getCell(22,23)));
		assertTrue(testList.contains(board.getCell(23, 22)));
		assertEquals(2,testList.size());
	}
	
	@Test
	public void testAdjacencyRightEdge() {
		BoardCell testCell = board.getCell(23, 3);
		Set<BoardCell> testList =  board.getAdjList(testCell);
		assertTrue(testList.contains(board.getCell(23,2)));
		assertTrue(testList.contains(board.getCell(23,4)));
		assertTrue(testList.contains(board.getCell(22,3)));
		assertEquals(3,testList.size());
	}
	
	@Test
	public void testAdjacencyLeftEdge() {
		BoardCell testCell = board.getCell(0, 3);
		Set<BoardCell> testList =  board.getAdjList(testCell);
		assertTrue(testList.contains(board.getCell(0,2)));
		assertTrue(testList.contains(board.getCell(0,4)));
		assertTrue(testList.contains(board.getCell(1,3)));
		assertEquals(3,testList.size());
	}
	
	@Test
	public void testAdjacency2_10() {
		BoardCell testCell = board.getCell(2, 10);
		Set<BoardCell> testList =  board.getAdjList(testCell);
		assertTrue(testList.contains(board.getCell(2,11)));
		assertTrue(testList.contains(board.getCell(2,9)));
		assertTrue(testList.contains(board.getCell(3,10)));
		assertTrue(testList.contains(board.getCell(1,10)));
		assertEquals(4,testList.size());
	}
	
	@Test
	public void testAdjacency22_10() {
		BoardCell testCell = board.getCell(22, 10);
		Set<BoardCell> testList =  board.getAdjList(testCell);
		assertTrue(testList.contains(board.getCell(22,11)));
		assertTrue(testList.contains(board.getCell(22,9)));
		assertTrue(testList.contains(board.getCell(23,10)));
		assertTrue(testList.contains(board.getCell(21,10)));
		assertEquals(4,testList.size());
	}
	
}
