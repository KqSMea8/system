package com.system.service.excle;


/**
 * 名称: ExcelReaderUtil.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年7月5日 上午10:10:20<br>
 *
 * @since 2016年7月5日
 * @author “”
 */
public class ExcelReaderUtil {

    public static final String EXCEL07_EXTENSION = ".xlsx";

    /**
     * 读取Excel文件，可能是03也可能是07版本
     *
     * @param fileName
     * @throws Exception
     */
    public static void readExcel(IExcelRowReader reader, String fileName) throws Exception {
        ExcelXlsxReader exceXlsx = new ExcelXlsxReader();
        exceXlsx.setRowReader(reader);
        exceXlsx.process(fileName);

    }

}
