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
	public static final int NUM_ROWS = 24;
	public static final int NUM_COLUMNS = 24;

	// NOTE: I made Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("Layout1.csv", "Legend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	@Test
	public void testRooms() {
		// Get the map of initial => room 
		Map<String, String> legend = board.getLegendString();
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
		assertEquals("Kafadar", legend.get("X"));
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
		BoardCell room = board.getCellAt(9, 3);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());

		room = board.getCellAt(2, 8);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(1, 14); 
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());

		room = board.getCellAt(0, 19);
		assertTrue(room.isDoorway()); 
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		
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
		for (int row=0; row < board.getNumRows(); row++)
			for (int col=0; col < board.getNumColumns(); col++) {
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
		assertEquals("BB", board.getCellAt(0, 0).getInitialString());
		assertEquals("BE", board.getCellAt(0,6).getInitialString());
		assertEquals("GH", board.getCellAt(1,13).getInitialString());
		assertEquals("Li", board.getCellAt(0,20).getInitialString());
		assertEquals("CH", board.getCellAt(9,21).getInitialString());
		assertEquals("GC", board.getCellAt(17,13).getInitialString());
		assertEquals("MZ", board.getCellAt(21,7).getInitialString());
		assertEquals("CT", board.getCellAt(16,3).getInitialString());
		assertEquals("CK", board.getCellAt(6,5).getInitialString());
		assertEquals("AH", board.getCellAt(5, 0).getInitialString());
		// Test last cell in room
		assertEquals("BB", board.getCellAt(2, 4).getInitialString());
		assertEquals("BE", board.getCellAt(2,9).getInitialString());
		assertEquals("GH", board.getCellAt(4,16).getInitialString());
		assertEquals("Li", board.getCellAt(6,22).getInitialString());
		assertEquals("CH", board.getCellAt(18,23).getInitialString());
		assertEquals("GC", board.getCellAt(23,18).getInitialString());
		assertEquals("MZ", board.getCellAt(23,10).getInitialString());
		assertEquals("CT", board.getCellAt(23,3).getInitialString());
		assertEquals("CK", board.getCellAt(12,5).getInitialString());
		assertEquals("AH", board.getCellAt(12,3).getInitialString());
		// Test a walkway
		assertEquals("W", board.getCellAt(6,16).getInitialString());
		// Test the closet
		assertEquals("X", board.getCellAt(10,10).getInitialString());
	}
	

}