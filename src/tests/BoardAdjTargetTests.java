package tests;

/*
 * This program tests that adjacencies and targets are calculated correctly.
 */

import java.util.Set;

//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instances
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("Layout1.csv", "Legend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	//GREEN
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(2, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(6, 5);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(21, 2);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(18, 5);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(23, 10);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are ORAGNE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(8, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 4)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(13, 21);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 20)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(3, 14);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(4, 14)));
		//TEST DOORWAY UP
		testList = board.getAdjList(19, 14);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(18, 14)));
		//TEST DOORWAY UP, WHERE THERE'S A WALKWAY LEFT
		testList = board.getAdjList(20, 7);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(19, 7)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are PURPLE in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(8, 4);
		assertTrue(testList.contains(board.getCellAt(9, 4)));
		assertTrue(testList.contains(board.getCellAt(7, 4)));
		assertTrue(testList.contains(board.getCellAt(8, 3)));
		assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(19, 22);
		assertTrue(testList.contains(board.getCellAt(19, 21)));
		assertTrue(testList.contains(board.getCellAt(19, 23)));
		assertTrue(testList.contains(board.getCellAt(20, 22)));
		assertTrue(testList.contains(board.getCellAt(18, 22)));
		assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(0, 18);
		assertTrue(testList.contains(board.getCellAt(1, 18)));
		assertTrue(testList.contains(board.getCellAt(0, 17)));
		assertTrue(testList.contains(board.getCellAt(0, 19)));
		assertEquals(3, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(18, 14);
		assertTrue(testList.contains(board.getCellAt(17, 14)));
		assertTrue(testList.contains(board.getCellAt(18, 15)));
		assertTrue(testList.contains(board.getCellAt(19, 14)));
		assertEquals(3, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are GRAY on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board
		Set<BoardCell> testList = board.getAdjList(0, 11);
		assertTrue(testList.contains(board.getCellAt(0, 10)));
		assertTrue(testList.contains(board.getCellAt(1, 11)));
		assertTrue(testList.contains(board.getCellAt(0, 12)));
		assertEquals(3, testList.size());
		
		// Test on left edge of board, two walkway pieces
		testList = board.getAdjList(4, 0);
		assertTrue(testList.contains(board.getCellAt(4, 1)));
		assertTrue(testList.contains(board.getCellAt(3, 0)));
		assertEquals(2, testList.size());

		// Test between two rooms, walkways up and down
		testList = board.getAdjList(1, 5);
		assertTrue(testList.contains(board.getCellAt(0, 5)));
		assertTrue(testList.contains(board.getCellAt(2, 5)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(11,19);
		assertTrue(testList.contains(board.getCellAt(12, 19)));
		assertTrue(testList.contains(board.getCellAt(10, 19)));
		assertTrue(testList.contains(board.getCellAt(11, 18)));
		assertTrue(testList.contains(board.getCellAt(11, 20)));
		assertEquals(4, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(12, 7);
		assertTrue(testList.contains(board.getCellAt(13, 7)));
		assertTrue(testList.contains(board.getCellAt(11, 7)));
		assertEquals(2, testList.size());
	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are WHITE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(4, 18, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 18)));
		assertTrue(targets.contains(board.getCellAt(3, 18)));
		assertTrue(targets.contains(board.getCellAt(4, 17)));
		
		board.calcTargets(18, 17, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(17, 17)));
		assertTrue(targets.contains(board.getCellAt(18, 16)));				
	}
	
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(16, 11, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		
		board.calcTargets(7, 23, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());		
	}
	
	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(0, 13, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(16, 9, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(3, 20, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 23)));
		assertTrue(targets.contains(board.getCellAt(0, 19)));
	}

}
