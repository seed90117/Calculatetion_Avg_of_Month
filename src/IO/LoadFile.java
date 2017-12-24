package IO;

import java.io.File;
import javax.swing.JFileChooser;

import Value.Data;

public class LoadFile {

	private static String WINDOWS = "\\Desktop";

	public String loadfile(JFileChooser open)
	{
		String tmp =null;
		String loadPath = LoadFile.WINDOWS;
		String fileName = "";
		//*****預設路徑*****//
		open.setCurrentDirectory(new File(loadPath));
		
		//*****設定Title*****//
		open.setDialogTitle("Choose dataset");
		
		//*****是否按下Load*****//
		if(open.showDialog(open, "Load") == JFileChooser.APPROVE_OPTION)
		{
			//*****取得路徑*****//
			File filepath = open.getSelectedFile();
			fileName = filepath.getName();
			
			//*****路徑轉為String*****//
			tmp = filepath.getPath().toString();
			
			String[] tmpArray = fileName.split("\\.");
			switch (tmpArray[1]) {
			case "xls":
				getXlsData(tmp);
				break;
			case "xlsx":
				getXlsData(tmp);
				break;
			}
			return tmpArray[0];
		} else {
			return "";
		}
	}
	
	private void getXlsData(String filePath) {
		ReadExcel readExcel = new ReadExcel();
		Data data = Data.getInstance();
		readExcel.chooseExcel(filePath);
		String[] sheetName = readExcel.getSheetName();
		readExcel.chooseSheet(sheetName[0]);
		int column = readExcel.getSheetColumns();
		int row = readExcel.getSheetRow();
		data.setContentSize(column, row);
		for (int r=0;r<row;r++) {
			for (int c=0;c<column;c++) {
				data.setContent(c, r, readExcel.getData(c, r));
			}
		}
		readExcel.closeWorkbook();
	}
}
