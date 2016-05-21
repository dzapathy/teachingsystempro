package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
	/**
	 * POI处理excel数据
	 */
	public static void main(String[] args) {
		String filepath = "H:/2014-12.xls";
		File file = new File(filepath);
		try {
			ArrayList<String> list = ExcelUtil.getDatas(file);
			for(String l :list){
				System.out.println(l);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//获取学号
	public static ArrayList<String> getDatas(File file) throws IOException{		
		HSSFWorkbook workbook =new HSSFWorkbook(new FileInputStream(file));//获取excel	
		HSSFSheet sheet = workbook.getSheetAt(0);//获取工作表sheet
		ArrayList<String> list = new ArrayList<String>(); //存储数据
		HSSFRow row = null;//行	
		HSSFCell cell = null;//单元格
		for(int i = 9; i < sheet.getLastRowNum(); i++){
			row = sheet.getRow(i);//获取第i行
			cell = row.getCell(1);//获取第二列:学号
			if(((int)cell.getNumericCellValue())!=0)
				list.add((int)cell.getNumericCellValue()+"");
		}		
		return list;
	}
}
