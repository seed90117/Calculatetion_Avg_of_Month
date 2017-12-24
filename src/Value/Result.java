package Value;

public class Result {

	private int column = 0;
	private int row = 0;
	private String[][] content = null;
	
	private static Result instance = null;
	private Result(){}
	
	public static synchronized Result getInstance() {
		if (instance == null) {
			instance = new Result();
		}
		return instance;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public void setContentSize() {
		this.content = new String[this.row][this.column];
	}
	
	public void setContentSize(int column, int row) {
		this.column = column;
		this.row = row;
		this.content = new String[row][column];
	}
	
	public void setContent(int column, int row, String value) {
		this.content[row][column] = value;
	}
	
	public String getContent(int column, int row) {
		return this.content[row][column];
	}
}
