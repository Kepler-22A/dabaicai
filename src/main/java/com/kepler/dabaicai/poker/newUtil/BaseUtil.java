package com.kepler.dabaicai.poker.newUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseUtil {
    private static Logger logger = LoggerFactory.getLogger(BaseUtil.class);
    private Workbook wb;
    public Sheet sheet;
    public Row row;

    public Workbook initUtil(String filepath) {
        if(filepath==null){
            return null;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return wb;
    }

    /**
     *
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author zengwendong
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    public String getCS(int i){
        System.out.println(i);
        return getStr(getCellFormatValue(row.getCell(i)));
    }

    private String getStr(Object val){
        String str = "";
        if (val != null){ str = val.toString(); }
        return str;
    }

    /**
     * @功能：手工构建一个简单格式的Excel并用response返回（下载）
     *
     * @param response      response对象
     * @param filePath        文件名字
     */
    public void createExcel(HttpServletResponse response, Map<String, Map<Integer, List<String>>> excelMap, List<String> strArray, String filePath) throws IOException {
        OutputStream out = null;
        // 下面几行是为了解决文件名乱码的问题
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filePath.getBytes(), "iso-8859-1"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        out = response.getOutputStream();

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        for (String key : excelMap.keySet()){
            Map<Integer, List<String>> rowMap = excelMap.get(key);

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(key);
            sheet.setDefaultColumnWidth(20);// 默认列宽
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow((int) 0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            // 创建一个居中格式
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

            // 添加excel title
            HSSFCell cell = null;

            for (int i = 0; i < strArray.size(); i++) {
                cell = row.createCell((short) i);
                cell.setCellValue(strArray.get(i));
                cell.setCellStyle(style);
            }

            // 第五步，写入实体数据 实际应用中这些数据从数据库得到,list中字符串的顺序必须和数组strArray中的顺序一致
            for (int index = 0; index < rowMap.keySet().size();index++) {
                row = sheet.createRow((int) index+1);
                List<String> list = rowMap.get(index);

                // 第四步，创建单元格，并设置值
                for (int j = 0; j < strArray.size(); j++) {
                    String value;
                    if (j >= list.size()){
                        value = "";
                    }else {
                        value = list.get(j);
                    }
                    row.createCell((short) j).setCellValue(value);
                }
            }
        }

        // 第六步，将文件存到指定位置
        try {
            wb.write(out);//将Excel用response返回
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
