package IO;

import Value.Result;

public class SaveFile {

	private static String FILENAME = "平均";
	
	public void saveFile(String fileName) {
		Result result = Result.getInstance();
		WriteExcel writeExcel = new WriteExcel();
		writeExcel.createNewExcel(fileName + SaveFile.FILENAME);
		writeExcel.createNewSheet(fileName, 0);
		for (int r=0;r<result.getRow();r++) {
			for (int c=0;c<result.getColumn();c++) {
				writeExcel.setCellContent(c, r, result.getContent(c, r));
			}
		}
		writeExcel.writeData();
		writeExcel.closeWorkbook();
	}
}
