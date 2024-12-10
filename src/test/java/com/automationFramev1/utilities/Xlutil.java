package com.automationFramev1.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xlutil {
	public static  FileInputStream fis;
	public static XSSFWorkbook wk;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cel ;
	public static FileInputStream fo;
	
	public Xlutil(String xlfile, String xlsheet) {
		// TODO Auto-generated constructor stub
	}
	public static int getRowCoun(String xlfile,String xlsheet) throws IOException {
		
		fis=new FileInputStream(xlfile);
		wk=new XSSFWorkbook(fis);
		ws = wk.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wk.close();
		fis.close();
		return rowcount;
		}
public static int getcellCoun(String xlfile,String xlsheet,int rownum) throws IOException {
		
		fis=new FileInputStream(xlfile);
		wk=new XSSFWorkbook(fis);
		ws = wk.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int rowcount = row.getLastCellNum();
		wk.close();
		fis.close();
		return rowcount;
		}

public static String  getcelldata(String xlfile,String xlsheet,int rownum,int colnum ) throws IOException {

	fis=new FileInputStream(xlfile);
	wk=new XSSFWorkbook(fis);
	ws = wk.getSheet(xlsheet);
	row = ws.getRow(rownum);
	cel = row.getCell(colnum);
	String data;
	try {
		DataFormatter df=new DataFormatter();
		String celldata=df.formatCellValue(cel);
		return celldata;
	}catch(Exception e){
		data="";
		
	}
	fis.close();
	wk.close();
	return data;
	
	
}

public static void setcelldata(String xlfile,String xlsheet,int rownum,int colnum,String data ) throws IOException {
	
	fis=new FileInputStream(xlfile);
	wk=new XSSFWorkbook(fis);
	ws = wk.getSheet(xlsheet);
	row = ws.getRow(rownum);
	cel = row.createCell(colnum);
	cel.setCellValue( data);
	 fo= new FileInputStream(xlfile);
	 fo.close();
	fis.close();
	wk.close();
	
	
	
}


}
