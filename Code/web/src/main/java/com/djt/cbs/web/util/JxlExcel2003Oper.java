package com.djt.cbs.web.util;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PaperSize;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlExcel2003Oper {

    /**
     * 创建一个excel,包含标题
     * @param os 输出文件流
     * @param templateName 文件sheet名
     * @param totalHeaders 标题头
     * @param fitHeight
     * @param fitWidth
     * @throws Exception
     */
    public static void createExcelIncludeHeader(OutputStream os, String templateName,
                                                String[] totalHeaders, int fitHeight, int fitWidth)
                                                                                                   throws Exception {

        fitHeight = fitHeight <= 0 ? 297 : fitHeight;
        fitWidth = fitWidth <= 0 ? 21 : fitWidth;

        WritableWorkbook book = Workbook.createWorkbook(os);// 创建可以写的book文件对象
        WritableSheet sheet = book.createSheet(templateName, 0);// 在book3.xls中创建一个sheet,名称为templateName,从第一列开始插入
        sheet.getSettings().setPaperSize(PaperSize.A4);//设置纸张大小
        sheet.getSettings().setFitHeight(fitHeight);
        sheet.getSettings().setFitWidth(fitWidth);
        //            sheet.getSettings().setHorizontalCentre(true);
        int temp = 0;
        setExcelHeader(sheet, temp, totalHeaders);
        // 写入到服务器
        book.write();
        // 一定要关闭，否则不写入
        book.close();
        os.flush();
        os.close();
    }

    /**
     * 创建一个excel,包含标题和数据
     * @param os 输出文件流
     * @param templateName 文件sheet名
     * @param totalHeaders 标题头
     * @param dataKey 数据key值
     * @param datalist 表格数据
     * @param fitHeight
     * @param fitWidth
     * @throws Exception
     */
    public static void createExcelIncludeHeaderAndData(OutputStream os, String templateName,
                                                       String[] totalHeaders, String[] dataKey,
                                                       List<Map<String, Object>> datalist,
                                                       int fitHeight, int fitWidth)
                                                                                   throws Exception {

        fitHeight = fitHeight <= 0 ? 297 : fitHeight;
        fitWidth = fitWidth <= 0 ? 21 : fitWidth;

        WritableWorkbook book = Workbook.createWorkbook(os);// 创建可以写的book文件对象
        WritableSheet sheet = book.createSheet(templateName, 0);// 在book3.xls中创建一个sheet,名称为templateName,从第一列开始插入
        sheet.getSettings().setPaperSize(PaperSize.A4);//设置纸张大小
        sheet.getSettings().setFitHeight(fitHeight);
        sheet.getSettings().setFitWidth(fitWidth);
        //            sheet.getSettings().setHorizontalCentre(true);
        int temp = 0;
        setExcelHeader(sheet, temp, totalHeaders);
        int temp2 = 1;
        setExcelBody(sheet, temp2, dataKey, datalist);
        // 写入到服务器
        book.write();
        // 一定要关闭，否则不写入
        book.close();
        os.flush();
        os.close();
    }

    /**
     * 设置excel的标题头
     * @param sheet
     * @param temp 起始行
     * @param headers 标题
     * @throws Exception
     */
    public static void setExcelHeader(WritableSheet sheet, int temp, String[] headers)
                                                                                      throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,
            UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setAlignment(Alignment.CENTRE);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        if (headers != null && headers.length > 0) {
            for (int col = 0; col < headers.length; col++) {
                Label label = new Label(col, temp, headers[col], format);
                sheet.setColumnView(col, 25);
                sheet.addCell(label);
            }
        }
    }

    /**
     * 设置excel的数据
     * @param sheet
     * @param temp 起始行
     * @param headers 标题
     * @param dataKey 表格取数的Key
     * @param datalist 表格数据
     * @throws Exception
     */
    public static void setExcelBody(WritableSheet sheet, int temp, String[] dataKey,
                                    List<Map<String, Object>> datalist) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false,
            UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setAlignment(Alignment.CENTRE);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        if (datalist != null && datalist.size() > 0) {
            for (int row = 0; row < datalist.size(); row++) {
                Map<String, Object> tempmap = datalist.get(row);
                if (dataKey != null && dataKey.length > 0 && tempmap != null) {
                    for (int col = 0; col < dataKey.length; col++) {
                        Object obj = tempmap.get(dataKey[col]);
                        Label label = new Label(col, row + temp, obj == null ? "" : obj.toString(),
                            format);
                        sheet.setColumnView(col, 25);
                        sheet.addCell(label);
                    }
                }
            }
        }
    }

}
