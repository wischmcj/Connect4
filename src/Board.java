import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Board
{

  /**
   *  BOARD LAYOUT
   *  Any board object will always have a 2D array of integers structured as follows.
   *  
   *    [0][5]  [1][5]  [2][5]  [3][5]  [4][5]  [5][5]  [6][5]
   *    [0][4]  [1][4]  [2][4]  [3][4]  [4][4]  [5][5]  [6][4]
   *    [0][3]  [1][3]  [2][3]  [3][3]  [4][3]  [5][5]  [6][3]
   *    [0][2]  [1][2]  [2][2]  [3][2]  [4][2]  [5][5]  [6][2]
   *    [0][1]  [1][1]  [2][1]  [3][1]  [4][1]  [5][5]  [6][1]
   *    [0][0]  [1][0]  [2][0]  [3][0]  [4][0]  [5][5]  [6][0]
   *  
   */

  public static int[][] board = new int[7][6];
  public static int columnSelection = 0;
  public static int winner = 0;
  public static boolean[] isFull = new boolean[7];

  //=====================================================================================  CONSTRUCTOR

  //  Creating a Board object initializes the board array to a size of 7x6,
  //  columnSelect to 0, and the isFull array to a size of 7 (default values).
  public Board()
  {
//    board     
//    columnSelection = 0;
////    isFull = new boolean[7];

    for (int column = 0; column <= 6; column++)
    {
      for (int row = 0; row <= 5; row++)
      {
        board[column][row] = 0;
      }
    }
  }
  //======================================================================================== GAME PLAY METHOD
  public static void saveGame() throws FileNotFoundException, UnsupportedEncodingException {
	  PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
	  for(int i=0; i< 6;i++) {
		  for(int j=5; j> 0;i--) {
			 writer.print(board[i][j]);
		  }
	  }
	  	
	  writer.close();
  }
  public static void loadGame() throws FileNotFoundException, UnsupportedEncodingException {
	  Scanner scanner = new Scanner("save.txt");
	  for(int i=0; i< 6;i++) {
		  for(int j=5; j> 0;i--) {
			  if(scanner.hasNextInt()) {
				  board[i][j] = scanner.nextInt();
			  }
			  else {/*Do nothing */}
		  }
	  }
	  	
	  scanner.close();
  }
 
  //======================================================================================== GAME PLAY METHODS

  //  Add a chip to the lowest open cell in the specified column.
  public static void addChip(int column, int chip)
  {
    int row = 0;
    boolean added = false;
    //  Cycle through the column to find the first empty slot.
    while (row < 6 && !added)
    {
      // If the current slot is already occupied, move on to the next one.
      if (board[column][row] == 1 || board[column][row] == 2)
      {
        row++;
      }
      else
      {
        board[column][row] = chip;
        added = true;
        // If the chip is added to the fifth row, updates the isFull boolean for that row.
        if (board[column][5] != 0)
        {
          isFull[column] = true;
        }
      }
    }
    winner = winCheck();
  }

  public static boolean columnIsFull(int column)
  {
    return isFull[column];
  }

  public static void moveColumnSelectionLeft()
  {
    switch (columnSelection)
    {
    case 0:
      columnSelection = 6;
      break;
    case 1:
      columnSelection = 0;
      break;
    case 2:
      columnSelection = 1;
      break;
    case 3:
      columnSelection = 2;
      break;
    case 4:
      columnSelection = 3;
      break;
    case 5:
      columnSelection = 4;
      break;
    case 6:
      columnSelection = 5;
      break;
    }
    if (columnIsFull(columnSelection)) moveColumnSelectionLeft();
  }

  public static void moveColumnSelectionRight()
  {
    switch (columnSelection)
    {
    case 0:
      columnSelection = 1;
      break;
    case 1:
      columnSelection = 2;
      break;
    case 2:
      columnSelection = 3;
      break;
    case 3:
      columnSelection = 4;
      break;
    case 4:
      columnSelection = 5;
      break;
    case 5:
      columnSelection = 6;
      break;
    case 6:
      columnSelection = 0;
      break;
    }
    System.out.println("int columnSelection = " + columnSelection);
    if (columnIsFull(columnSelection)) moveColumnSelectionRight();

  }

  //Checks to see if the player or opponent has won.
  //returns 0 if neither player has won
  //returns 1 or 2 if someone has one
  public static int winCheck()
  {
    int ret = 0;
    //  check columns
    int row = 0;
    int column = 0;
    int count = 1;
    int current = 0;
    while (column < 7 && ret == 0) {
      row = 0;
      count = 0;
      current = 0;
      while (row < 6 && ret == 0) {
        if (board[column][row] == current)
          count++;
        else {
          current = board[column][row];
          count = 1;
        }
        if (count == 4) {
          ret = current;
        }
        row++;
      }
      column++;
    }
    //  check rows
    row = 0;
    column = 0;
    count = 1;
    current = 0;
    while (row < 6 && ret == 0) {
      column = 0;
      current = 0;
      count = 0;
      while (column < 7 && ret == 0) {
        if (board[column][row] == current)
          count++;
        else {
          current = board[column][row];
          count = 1;
        }
        if (count == 4) {
          ret = current;
        }
        column++;
      }
      row++;
    }    
    //  check down diagonals
    count = 1;
    current = 0;
    int rowStart = 3;
    int columnStart = 0;
    while (columnStart < 4 && ret == 0) {
      column = columnStart;
      row = rowStart;
      count = 0;
      current = 0;
      while (column < 7 && row >= 0 && ret == 0) {
        if (board[column][row] == current)
          count++;
        else {
          current = board[column][row];
          count = 1;
        }
        if (count == 4)
          ret = current;
        column++;
        row--;
      }
      if (rowStart != 5)
        rowStart++;
      else
        columnStart++;
    }
    //  check up diagonals
    count = 1;
    current = 0;
    rowStart = 2;
    columnStart = 0;
    while (columnStart < 4 && ret == 0) {
      column = columnStart;
      row = rowStart;
      count = 0;
      current = 0;
      while (column < 7 && row < 6 && ret == 0) {
        if (board[column][row] == current)
          count++;
        else {
          current = board[column][row];
          count = 1;
        }
        if (count == 4)
          ret = current;
        column++;
        row++;
      }
      if (rowStart != 0)
        rowStart--;
      else
        columnStart++;
    }
    return ret;
  }


}
