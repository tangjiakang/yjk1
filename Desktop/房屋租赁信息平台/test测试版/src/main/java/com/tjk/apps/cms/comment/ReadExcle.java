package com.tjk.apps.cms.comment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tjk.apps.cms.bean.Basehouse;

public class ReadExcle {
	
	private static final String XLS = "xls";
	private static final String XLSX = "xlsx";
	//根据文件类型获取工作簿对象
	public static Workbook getWorkBook(InputStream inputStream,String fileType) throws IOException {
		 Workbook workbook = null;
	        if (fileType.equalsIgnoreCase(XLS)) {
	            workbook = new HSSFWorkbook(inputStream);
	        } else if (fileType.equalsIgnoreCase(XLSX)) {
	            workbook = new XSSFWorkbook(inputStream);
	        }
	        return workbook;
	}
	
	public static List<Basehouse> readExcel(String fileName){
		Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
            	System.out.println("文件不存在");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkBook(inputStream, fileType);

            // 读取excel中的数据
            List<Basehouse> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
        	System.out.println(e.getMessage());
           System.out.println("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                return null;
            }
        }
	}
	
	private static List<Basehouse> parseExcel(Workbook workbook) {
	       List<Basehouse> resultDataList = new ArrayList<>();
	        // 解析sheet
	        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
	            Sheet sheet = workbook.getSheetAt(sheetNum);

	            // 校验sheet是否合法
	            if (sheet == null) {
	                continue;
	            }

	            // 获取第一行数据
	            int firstRowNum = sheet.getFirstRowNum();
	            Row firstRow = sheet.getRow(firstRowNum);
	            if (null == firstRow) {
	                System.out.println("解析Excel失败，在第一行没有读取到任何数据！");
	            }

	            // 解析每一行的数据，构造数据对象
	            int rowStart = firstRowNum + 1;
	            int rowEnd = sheet.getPhysicalNumberOfRows();
	            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
	                Row row = sheet.getRow(rowNum);

	                if (null == row) {
	                    continue;
	                }

	                Basehouse resultData = convertRowToData(row);
	                if (null == resultData) {
	                    System.out.println("第 " + row.getRowNum() + "行数据不合法，已忽略！");
	                    continue;
	                }
	                resultDataList.add(resultData);
	            }
	        }

	        return resultDataList;
	    }
	
	 private static Basehouse convertRowToData(Row row) {
	        Basehouse resultData = new Basehouse();

	        Cell cell;
	        int cellNum = 0;
	        // 获取姓名
	        cell = row.getCell(cellNum++);
	        String temp = convertCellValueToString(cell);
	         System.out.println(temp+"zifu");
	         
	        String hid = temp.substring(0, temp.lastIndexOf("."));
	        int hId = Integer.parseInt(hid);
	        resultData.sethId(hId);
	    
	        cell = row.getCell(cellNum++);
	        String address = convertCellValueToString(cell);
	       
	        resultData.setDetailedaddress(address);
	        
	        cell = row.getCell(cellNum++);
	        String strarea = convertCellValueToString(cell);
	        
	        BigDecimal area = new BigDecimal(strarea);
	        resultData.setArea(area);
	        
	        cell = row.getCell(cellNum++);
	        String orientation = convertCellValueToString(cell);
	      
	        resultData.setOrientation(orientation);
	        cell = row.getCell(cellNum++);
	        
	        String bulding = convertCellValueToString(cell);
	        resultData.setBulding(bulding);
	        cell = row.getCell(cellNum++);
	       
	        String state = convertCellValueToString(cell);
	        resultData.setState(state);
	        
	        cell = row.getCell(cellNum++);     
	        String ower = convertCellValueToString(cell);
	        String master = ower.substring(0, ower.lastIndexOf("."));
	        resultData.setOwner(Integer.parseInt(master));
	        
	        cell = row.getCell(cellNum++);     
	        String strprice = convertCellValueToString(cell);
	        BigDecimal price = new BigDecimal(strprice);
	        resultData.setPrice(price);
	        return resultData;
	     
	        
	    }
	 private static String convertCellValueToString(Cell cell) {
	        if(cell==null){
	            return null;
	        }
	        String returnValue = null;
	        switch (cell.getCellType()) {
	            case NUMERIC:   //数字
	                Double doubleValue = cell.getNumericCellValue();

	                
	                returnValue = doubleValue.toString();
	                break;
	            case STRING:    //字符串
	                returnValue = cell.getStringCellValue();
	                break;
	            case BOOLEAN:   //布尔
	                Boolean booleanValue = cell.getBooleanCellValue();
	                returnValue = booleanValue.toString();
	                break;
	            case BLANK:     // 空值
	                break;
	            case FORMULA:   // 公式
	                returnValue = cell.getCellFormula();
	                break;
	            case ERROR:     // 故障
	                break;
	            default:
	                break;
	        }
	        return returnValue;
	    }


}
