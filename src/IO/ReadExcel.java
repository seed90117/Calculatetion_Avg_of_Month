package IO;

import java.io.File;
import java.io.FileInputStream;

import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {

	// 1.指定Workbook
		//就是指定要打開哪份文件檔
	// 2. 指定Sheet
		// 就是指定要讀取哪個Sheet
	// 3.取得Cell中的資料
		// 指定要讀取哪一個位置的資料
	private Workbook workbook = null;
	private Sheet sheet = null;
	
	public void chooseExcel(String excelPath) {
		try {
			// 1.指定Workbook
			//就是指定要打開哪份文件檔
			this.workbook = Workbook.getWorkbook(new FileInputStream(new File(excelPath)));
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void chooseExcel(File excel) {
		try {
			// 1.指定Workbook
			//就是指定要打開哪份文件檔
			this.workbook = Workbook.getWorkbook(new FileInputStream(excel));
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void chooseSheet(String sheetName) {
		try {
			// 2. 指定Sheet
			// 就是指定要讀取哪個Sheet
			this.sheet = this.workbook.getSheet(sheetName);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public String getData(int column, int row) {
		// 3.取得Cell中的資料
		// 指定要讀取哪一個位置的資料
		return this.sheet.getCell(column, row).getContents();
	}
	
	public void closeWorkbook() {
		try {
			this.workbook.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public int getSheetColumns() {
		return this.sheet.getColumns();
	}
	
	public int getSheetRow() {
		return this.sheet.getRows();
	}
	
	public String[] getSheetName() {
		return this.workbook.getSheetNames();
	}
}
