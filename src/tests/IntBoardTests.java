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
		assertTrue(testList.contains(board.getCell(0,1)));
		assertEquals(2,testList.size());
	}
	
	@Test
	public void testAdjacency23_23() {
		BoardCell testCell = board.getCell(23, 23);
		Set<BoardCell> testList =  board.getAdjList(testCell);
		assertTrue(testList.contains(board.getCell(22,23)));
		assertTrue(testList.contains(board.getCell(23,22)));
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
	
	
	@Test
	public void testCalcTarget0_0_3() {
		BoardCell testCell = board.getCell(0, 0);
		board.calcTargets(testCell,3);
		Set targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	@Test
	public void testCalcTarget1_1_2() {
		BoardCell testCell = board.getCell(1, 1);
		board.calcTargets(testCell,2);
		Set targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(0, 2)));
	}
	
	@Test
	public void testCalcTarget2_2_1() {
		BoardCell testCell = board.getCell(2, 2);
		board.calcTargets(testCell,1);
		Set targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(3,2)));
		assertTrue(targets.contains(board.getCell(2,1)));
		assertTrue(targets.contains(board.getCell(2,3)));
	}
	
	@Test
	public void testCalcTarget8_10_4() {
		BoardCell testCell = board.getCell(8, 10);
		board.calcTargets(testCell,4);
		Set targets = board.getTargets();
		assertEquals(24, targets.size());
		assertTrue(targets.contains(board.getCell(9, 13)));
		assertTrue(targets.contains(board.getCell(8, 12)));
		assertTrue(targets.contains(board.getCell(9, 7)));
		assertTrue(targets.contains(board.getCell(7, 13)));
		assertTrue(targets.contains(board.getCell(10, 8)));
		assertTrue(targets.contains(board.getCell(9, 11)));
		assertTrue(targets.contains(board.getCell(8, 8)));
		assertTrue(targets.contains(board.getCell(8, 6)));
		assertTrue(targets.contains(board.getCell(6, 10)));
		assertTrue(targets.contains(board.getCell(7, 11)));
		assertTrue(targets.contains(board.getCell(5, 9)));
		assertTrue(targets.contains(board.getCell(6, 8)));
		assertTrue(targets.contains(board.getCell(7, 7)));
		assertTrue(targets.contains(board.getCell(7, 9)));
		assertTrue(targets.contains(board.getCell(9, 9)));
		assertTrue(targets.contains(board.getCell(11, 11)));
		assertTrue(targets.contains(board.getCell(12, 10)));
		assertTrue(targets.contains(board.getCell(10, 12)));
		assertTrue(targets.contains(board.getCell(8, 14)));
		assertTrue(targets.contains(board.getCell(10, 10)));
		assertTrue(targets.contains(board.getCell(11, 9)));
		assertTrue(targets.contains(board.getCell(6, 12)));
		assertTrue(targets.contains(board.getCell(5, 11)));
		assertTrue(targets.contains(board.getCell(4, 10)));
	}
	
	@Test
	public void testCalcTargets4_4_2() {
		BoardCell testCell = board.getCell(4, 4);
		board.calcTargets(testCell,2);
		Set targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(2,4)));
		assertTrue(targets.contains(board.getCell(6,4)));
		assertTrue(targets.contains(board.getCell(4,2)));
		assertTrue(targets.contains(board.getCell(4,6)));
		assertTrue(targets.contains(board.getCell(3,3)));
		assertTrue(targets.contains(board.getCell(5,5)));
		assertTrue(targets.contains(board.getCell(3,5)));
		assertTrue(targets.contains(board.getCell(5,3)));
	}
	
	@Test
	public void testCalctargets5_5_3() {
		BoardCell testCell = board.getCell(5, 5);
		board.calcTargets(testCell,3);
		Set targets = board.getTargets();
		assertEquals(16, targets.size());
		assertTrue(targets.contains(board.getCell(2,5)));
		assertTrue(targets.contains(board.getCell(7,4)));
		assertTrue(targets.contains(board.getCell(8,5)));
		assertTrue(targets.contains(board.getCell(5,2)));
		assertTrue(targets.contains(board.getCell(6, 3)));
		assertTrue(targets.contains(board.getCell(5,8)));
		assertTrue(targets.contains(board.getCell(4,5)));
		assertTrue(targets.contains(board.getCell(5,6)));
		assertTrue(targets.contains(board.getCell(5,4)));
		assertTrue(targets.contains(board.getCell(7,6)));
		assertTrue(targets.contains(board.getCell(4,3)));
		assertTrue(targets.contains(board.getCell(6,5)));
		assertTrue(targets.contains(board.getCell(6,7)));
		assertTrue(targets.contains(board.getCell(3,4)));
		assertTrue(targets.contains(board.getCell(3,6)));
		assertTrue(targets.contains(board.getCell(4,7)));
	}
}
