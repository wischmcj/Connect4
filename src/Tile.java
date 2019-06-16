/*
 * Ideally this will be rewritten as a helper class, but I forgot how to write those so..
 * 
 * Functions to create a tree like structure to search for wins
 */
public class Tile {
	
	int col, row;
	public Tile(int col, int row) {
		super();
		this.col = col;
		this.row = row;
	}
	public Tile() {
		super();
	}
	public int getCol() {
		return col;
	}
	public void setCol(int column) {
		this.col = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
}