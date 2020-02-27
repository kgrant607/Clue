package tests;

/*
 * This program tests that config files are loaded properly.
 */

// Doing a static import allows me to write assertEquals rather than
// Assert.assertEquals
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class FileInitTest {
	// Constants that I will use to test whether the file was loaded correctly
	public static final int LEGEND_SIZE = 12;
	public static final int NUM_ROWS = 23;
	public static final int NUM_COLUMNS = 23;

	// NOTE: I made Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	@Test
	public void testRooms() {
		// Get the map of initial => room 
		Map<Character, String> legend = board.getLegend();
		// Ensure we read the correct number of rooms
		assertEquals(LEGEND_SIZE, legend.size());
		// To ensure data is correctly loaded, test retrieving a few rooms 
		// from the hash, including the first and last in the file and a few others
		assertEquals("Brown Building", legend.get("BB"));
		assertEquals("Berthoud Hall", legend.get("BE"));
		assertEquals("Gugenheim Hall", legend.get("GH"));
		assertEquals("Library", legend.get("Li"));
		assertEquals("Chauvenet Hall", legend.get("CH"));
		assertEquals("Green Center", legend.get("GC"));
		assertEquals("Marquez", legend.get("MZ"));
		assertEquals("CTLM", legend.get("CT"));
		assertEquals("Coortek", legend.get("CK"));
		assertEquals("Alderson", legend.get("AH"));
		assertEquals("Walkway", legend.get("W"));
		assertEquals("Kafardar", legend.get("X"));
	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	// Test a doorway in each direction (RIGHT/LEFT/UP/DOWN), plus 
	// two cells that are not a doorway.
	// These cells are white on the planning spreadsheet
	@Test
	public void FourDoorDirections() {
		BoardCell room = board.getCellAt(7, 2);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(8, 2);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(14, 1); 
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());

		room = board.getCellAt(19, 0);
		assertTrue(room.isDoorway()); 
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());

		room = board.getCellAt(14, 3);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(6, 7);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());

		room = board.getCellAt(23, 6);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(3, 9); 
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());

		room = board.getCellAt(3, 10);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		 
		room = board.getCellAt(5, 9); 
		assertTrue(room.isDoorway()); 
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());

		room = board.getCellAt(22, 9);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(0, 0);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(0, 3);
		assertFalse(cell.isDoorway());		

	}
	
	// Test that we have the correct number of doors
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(22, numDoors);
	}

	// Test a few room cells to ensure the room initial is correct.
	@Test
	public void testRoomInitials() {
		// Test first cell in room
		assertEquals("BB", board.getCellAt(0, 0).getInitial());
		assertEquals("BE", board.getCellAt(6,0).getInitial());
		assertEquals("GH", board.getCellAt(13,1).getInitial());
		assertEquals("Li", board.getCellAt(20,0).getInitial());
		assertEquals("CH", board.getCellAt(21,9).getInitial());
		assertEquals("GC", board.getCellAt(13,17).getInitial());
		assertEquals("MZ", board.getCellAt(7,21).getInitial());
		assertEquals("CT", board.getCellAt(3,16).getInitial());
		assertEquals("CK", board.getCellAt(5,6).getInitial());
		assertEquals("AH", board.getCellAt(0, 5).getInitial());
		// Test last cell in room
		assertEquals("BB", board.getCellAt(4, 2).getInitial());
		assertEquals("BE", board.getCellAt(9,2).getInitial());
		assertEquals("GH", board.getCellAt(16,4).getInitial());
		assertEquals("Li", board.getCellAt(22,6).getInitial());
		assertEquals("CH", board.getCellAt(23,18).getInitial());
		assertEquals("GC", board.getCellAt(18,23).getInitial());
		assertEquals("MZ", board.getCellAt(10,23).getInitial());
		assertEquals("CT", board.getCellAt(3,23).getInitial());
		assertEquals("CK", board.getCellAt(5,12).getInitial());
		assertEquals("AH", board.getCellAt(3,12).getInitial());
		// Test a walkway
		assertEquals('W', board.getCellAt(7,16).getInitial());
		// Test the closet
		assertEquals('X', board.getCellAt(10,10).getInitial());
	}
	

}