//package wrkday_pom;
//
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

package wrkday_pom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



public class TestData {

		public boolean setCellData(String path,String sheetName,String colName,int rowNum, String data){
			try{
				
			File file = new File(path);
					
			FileInputStream fis = new FileInputStream(file);
			
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
		/*
				
			File fs=Res.getFile(path);
			File file = new File(fs.getPath());

			FileInputStream fis = new FileInputStream(file); 
		//	FileInputStream fis = new FileInputStream(path); 
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
*/			if(rowNum<=0)
				return false;
			
			int index = workbook.getSheetIndex(sheetName);
			index = workbook.getSheetIndex(sheetName.trim());
		
			int colNum=-1;
			if(index==-1)
				return false;
			
			HSSFSheet sheet = workbook.getSheetAt(index);
			HSSFRow row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
			
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=i;
			}
			if(colNum==-1)
				return false;

			sheet.autoSizeColumn(colNum); 
			row = sheet.getRow(rowNum-1);
			if (row == null)
				row = sheet.createRow(rowNum-1);
			
			HSSFCell cell = row.getCell(colNum);	
			if (cell == null)
		        cell = row.createCell(colNum);

			System.out.println(path);
			System.out.println(sheetName);
			System.out.println(colName);
			System.out.println(data);
			
		    cell.setCellValue(data);

		    FileOutputStream fileOut = new FileOutputStream(file);

			workbook.write(fileOut);
			
		    fileOut.close();	

			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
				public String getCellData(String path,String sheetName,String colName,int rowNum){
			try{


				/*File fs=Res.getFile(path);
				File file = new File(fs.getPath());
			//	File file = new File(path);

				File file = new File(path);
				
				FileInputStream fis = new FileInputStream(file);
				
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				
				//File fs=Res.getFile(path);
				//File file = new File(fs.getPath());
>>>>>>> master*/

				File file = new File(path);
				FileInputStream fis = new FileInputStream(file); 
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
			
				if(rowNum <=0)
					return "";
			
			int index = workbook.getSheetIndex(sheetName);
			int col_Num=-1;
			if(index==-1)
				return "";
			
			HSSFSheet sheet = workbook.getSheetAt(index);
			HSSFRow row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
			}
			if(col_Num==-1)
				return "";
			
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";
			HSSFCell cell = row.getCell(col_Num);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			
			if(cell==null)
				return "";
			//System.out.println(cell.getCellType());
			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				  return cell.getStringCellValue();
			else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
				
				  String cellText  = String.valueOf(cell.getNumericCellValue());
				  if (HSSFDateUtil.isCellDateFormatted(cell)) {
			           // format in form of M/D/YY
					  double d = cell.getNumericCellValue();

					  Calendar cal =Calendar.getInstance();
					  cal.setTime(HSSFDateUtil.getJavaDate(d));
			            cellText =
			             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
			           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
			                      cal.get(Calendar.MONTH)+1 + "/" + 
			                      cellText;
			           //System.out.println(cellText);
			         }
				  return cellText;
			  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
			      return ""; 
			  else 
				  return String.valueOf(cell.getBooleanCellValue());
			
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "row "+rowNum+" or column "+colName +" does not exist in xls";
			}
		}
		private boolean createFile(File report) {
			boolean fileCreated = false;

			if (report.exists()) {
				fileCreated = true;
			} else {
				File parentDirectory = new File(report.getParent());
				if (parentDirectory.exists() || parentDirectory.mkdirs()) {
					try {
						fileCreated = report.mkdir();
					} catch (Exception errorCreatingRoport) {
						errorCreatingRoport.printStackTrace();
					}
				}
			}
			return fileCreated;
		}
		
		public int getRowCount(String path,String sheetName) throws IOException
		  {
			// 	File fs=Res.getFile(path);
			//	File file = new File(fs.getPath());
						
			//	FileInputStream fis = new FileInputStream(file); 
			//HSSFWorkbook workbook = new HSSFWorkbook(fis);	
			FileInputStream fis = new FileInputStream(path); 
			HSSFWorkbook workbook = new HSSFWorkbook(fis);			
				
		   int index = workbook.getSheetIndex(sheetName);
		   HSSFSheet sheet = workbook.getSheetAt(index);
		   if(index==-1)
		    return 0;
		   else
		   {
			   sheet = workbook.getSheetAt(index);
			   int number=sheet.getLastRowNum()+1;
			   return number;
		   }
		  }
}







//import java.util.Date;
//
//public class TestData {
//	
//	public FileInputStream fis = null;
//    public XSSFWorkbook workbook = null;
//    public XSSFSheet sheet = null;
//    public XSSFRow row = null;
//    public XSSFCell cell = null;
//
//    public TestData(String xlFilePath) throws Exception
//    {	File file = new File(xlFilePath);
//        fis = new FileInputStream(file);
//        workbook = new XSSFWorkbook(fis);
//        fis.close();
//    }
//
//    public String getCellData(String sheetName, String colName, int rowNum)
//    {
//        try
//        {
//            int col_Num = -1;
//            sheet = workbook.getSheet(sheetName);
//            row = sheet.getRow(0);
//
//            for(int i = 0; i < row.getLastCellNum(); i++)
//            {
//                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
//                col_Num = i;
//            }
//
//            row = sheet.getRow(rowNum - 1);
//            cell = row.getCell(col_Num);
//
//            if(cell.getCellTypeEnum() == CellType.STRING)
//
//                return cell.getStringCellValue();
//
//            else if(cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
//            {
//                String cellValue = String.valueOf(cell.getNumericCellValue());
//
//                if(HSSFDateUtil.isCellDateFormatted(cell))
//                {
//                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
//                    Date date = cell.getDateCellValue();
//                    cellValue = df.format(date);
//                }
//                return cellValue;
//            }
//            else if(cell.getCellTypeEnum() == CellType.BLANK)
//                return "";
//            else
//                return String.valueOf(cell.getBooleanCellValue());
//        }
//
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return "row "+rowNum+" or column "+colName +" does not exist  in Excel";
//        }
//    }
//}
