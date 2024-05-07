package com.kepler.dabaicai.poker.util;

import com.kepler.dabaicai.poker.newUtil.BaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DegreeExportUtil {
    private static Logger logger = LoggerFactory.getLogger(DegreeExportUtil.class);
    private Workbook wb;
    private Sheet sheet;
    private Row row;
    private BaseUtil baseUtil;

    public DegreeExportUtil(String filepath) {
        baseUtil = new BaseUtil();
        wb = baseUtil.initUtil(filepath);
    }

    /**
     * 读取Excel数据内容
     *
     * @author zengwendong
     */
    public void readExcelContentToTable(HttpServletResponse response) throws Exception{
        baseUtil.sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = baseUtil.sheet.getLastRowNum();
        Map<String, Map<Integer, List<String>>> excelMap = new HashMap<>();
        Map<Integer, List<String>> rowMap = new HashMap<>();
        //开头============================================


        List<List<String>> dataRowList = new ArrayList<>();
        // 正文内容应该从第二行开始,第一行为表头的标题
        // 这个文件需要获取标题，所以从序号0开始拿
        for (int i = 0; i <= rowNum; i++) {
            baseUtil.row = baseUtil.sheet.getRow(i);

            List<String> rowList = new ArrayList<>();
            for (int j = 0; j <= 37; j++){
                rowList.add(baseUtil.getCS(j));
            }

            dataRowList.add(rowList);
        }

        //中段===============================================

        for (int i = 0; i < dataRowList.size() ; i++){
            List<String> rowDataList = dataRowList.get(i);
            List<String> rowList = new ArrayList<>();
            for (int j = 0; j < 18; j++){
                rowList.add(rowDataList.get(j));
            }
            for (int j = 18; j <= 37; j++){
                String data = rowDataList.get(j);
                if (StringUtils.isNotBlank(data)){
                    String[] dataS = data.split("；");
                    data = dataS[0];
                }else {
                    data = "";
                }
                rowList.add(data);
            }
            rowMap.put(i, rowList);
        }

        //结尾============================================
        excelMap.put("sheet1", rowMap);
        List<String> titleArrays =  rowMap.get(0);
        baseUtil.createExcel(response, excelMap, titleArrays,"非车文档责任险附件导出.xls");

    }
}
