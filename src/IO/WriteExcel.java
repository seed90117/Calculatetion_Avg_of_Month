package IO;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class WriteExcel {
	
	// 1.建立Workbook
		// 也就是整份的Excel檔案，可指定檔名。
	// 2.建立Sheet
		// 也就是每個Excel檔案下面可以跳tag的sheet表。
	// 3.設定Cell格式(可省略，就會是預設的格式)
		// Cell就是我們在Excel中看到的一格一格的那個就叫做Cell，字型部分我們必須先另外設定
	// 4.增加一個文字儲存格Cell
		// 第一個和第二個參數為儲存格位址，(0,1)表示第一行(直) 第二列(橫)的位置，都是從0開始計算
	private String filePath = "";
	private String fileType = ".xls";
	private WritableWorkbook workbook = null;
	private WritableSheet sheet = null;
	
	public void createNewExcel(String excelName) {
		try {
			// 1.建立Workbook
			// 也就是整份的Excel檔案，可指定檔名。
			this.workbook = Workbook.createWorkbook(new File(filePath + excelName + fileType));
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void createNewSheet(String sheetName, int sheetPage) {
		try {
			// 2.建立Sheet
			// 也就是每個Excel檔案下面可以跳tag的sheet表。
			this.sheet = this.workbook.createSheet(sheetName, sheetPage);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public void setCellContent(int column, int row, String value) {
		try {
			// 4.增加一個文字儲存格Cell
			// 第一個和第二個參數為儲存格位址，(0,1)表示第一行(直)、第二列(橫)的位置，都是從0開始計算
			// 第三個參數就是儲存格的內容
			Label label = new Label(column, row, value);
			this.sheet.addCell(label);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void writeData() {
		try {
			this.workbook.write();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void closeWorkbook() {
		try {
			this.workbook.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
}
