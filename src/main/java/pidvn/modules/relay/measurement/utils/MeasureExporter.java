package pidvn.modules.relay.measurement.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pidvn.modules.relay.measurement.models.ExportParams;
import pidvn.modules.relay.measurement.models.MeasureDetailDataVo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class MeasureExporter {

    private XSSFWorkbook workbook;
    private Sheet sheet;
    private List<MeasureDetailDataVo> detailsList;
    private ExportParams exportParams;

    public MeasureExporter(List<MeasureDetailDataVo> detailsList, ExportParams exportParams) {
        this.detailsList = detailsList;
        this.exportParams = exportParams;
        this.workbook = new XSSFWorkbook();
    }

    public ByteArrayInputStream export() throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        switch (this.exportParams.getItemType()) {
            case 1:
                this.writeExcelType1();
                break;
            case 2:
                this.writeExcelType2();
                break;
            case 3:
                this.writeExcelType3();
                break;
            case 4:
                this.writeExcelType4();
                break;
            case 5:
                this.writeExcelType5();
                break;
        }
        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    public void writeExcelType1() {
        CreationHelper creationHelper = workbook.getCreationHelper();

        String[] columns = {
                "Line", "Thời gian", "Shift", "Model", "Reason",
                "Nhân viên", "Value"
        };

        this.createHeader(columns);

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("YYYY-MM-dd HH:mm:ss"));

        int rowNum = 1;
        for (MeasureDetailDataVo dataVo : detailsList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataVo.getLine());

            Cell date = row.createCell(1);
            date.setCellValue(dataVo.getCreatedAt());
            date.setCellStyle(dateCellStyle);
            row.createCell(2).setCellValue(dataVo.getShift());
            row.createCell(3).setCellValue(dataVo.getModel());
            row.createCell(4).setCellValue(dataVo.getReasonName());
            row.createCell(5).setCellValue(dataVo.getUser());
            row.createCell(6).setCellValue(dataVo.getA1());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

    }

    public void writeExcelType2() {
        CreationHelper creationHelper = workbook.getCreationHelper();

        String[] valueNames = this.exportParams.getValueName().split(";");

        String[] columns = {
                "Line", "Thời gian", "Shift", "Model", "Reason",
                "Nhân viên", valueNames[0], valueNames[1]
        };

        this.createHeader(columns);

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("YYYY-MM-dd HH:mm:ss"));

        int rowNum = 1;
        for (MeasureDetailDataVo dataVo : detailsList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataVo.getLine());

            Cell date = row.createCell(1);
            date.setCellValue(dataVo.getCreatedAt());
            date.setCellStyle(dateCellStyle);
            row.createCell(2).setCellValue(dataVo.getShift());
            row.createCell(3).setCellValue(dataVo.getModel());
            row.createCell(4).setCellValue(dataVo.getReasonName());
            row.createCell(5).setCellValue(dataVo.getUser());
            row.createCell(6).setCellValue(dataVo.getB1());
            row.createCell(7).setCellValue(dataVo.getB2());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void writeExcelType3() {

        CreationHelper creationHelper = workbook.getCreationHelper();

        String[] valueNames = this.exportParams.getValueName().split(";");

        String[] columns = {
                "Line", "Thời gian", "Shift", "Model", "Reason",
                "Nhân viên", valueNames[0], valueNames[1], valueNames[2]
        };

        this.createHeader(columns);

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("YYYY-MM-dd HH:mm:ss"));

        int rowNum = 1;
        for (MeasureDetailDataVo dataVo : detailsList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataVo.getLine());

            Cell date = row.createCell(1);
            date.setCellValue(dataVo.getCreatedAt());
            date.setCellStyle(dateCellStyle);
            row.createCell(2).setCellValue(dataVo.getShift());
            row.createCell(3).setCellValue(dataVo.getModel());
            row.createCell(4).setCellValue(dataVo.getReasonName());
            row.createCell(5).setCellValue(dataVo.getUser());
            row.createCell(6).setCellValue(dataVo.getC1());
            row.createCell(7).setCellValue(dataVo.getC2());
            row.createCell(8).setCellValue(dataVo.getC3());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void writeExcelType4() {

        CreationHelper creationHelper = workbook.getCreationHelper();

        String[] valueNames = this.exportParams.getValueName().split(";");

        String[] columns = {
                "Line", "Thời gian", "Shift", "Model", "Reason",
                "Nhân viên", valueNames[0], valueNames[1], valueNames[2], valueNames[3]
        };

        this.createHeader(columns);

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("YYYY-MM-dd HH:mm:ss"));

        int rowNum = 1;
        for (MeasureDetailDataVo dataVo : detailsList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataVo.getLine());

            Cell date = row.createCell(1);
            date.setCellValue(dataVo.getCreatedAt());
            date.setCellStyle(dateCellStyle);
            row.createCell(2).setCellValue(dataVo.getShift());
            row.createCell(3).setCellValue(dataVo.getModel());
            row.createCell(4).setCellValue(dataVo.getReasonName());
            row.createCell(5).setCellValue(dataVo.getUser());
            row.createCell(6).setCellValue(dataVo.getD1());
            row.createCell(7).setCellValue(dataVo.getD2());
            row.createCell(8).setCellValue(dataVo.getD3());
            row.createCell(9).setCellValue(dataVo.getD3());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void writeExcelType5() {
        CreationHelper creationHelper = workbook.getCreationHelper();

        String[] valueNames = this.exportParams.getValueName().split(";");

        String[] columns = {
                "Line", "Thời gian", "Shift", "Model", "Reason",
                "Nhân viên", valueNames[0], valueNames[1], valueNames[2], valueNames[3], valueNames[4]
        };

        this.createHeader(columns);

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("YYYY-MM-dd HH:mm:ss"));

        int rowNum = 1;
        for (MeasureDetailDataVo dataVo : detailsList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataVo.getLine());

            Cell date = row.createCell(1);
            date.setCellValue(dataVo.getCreatedAt());
            date.setCellStyle(dateCellStyle);
            row.createCell(2).setCellValue(dataVo.getShift());
            row.createCell(3).setCellValue(dataVo.getModel());
            row.createCell(4).setCellValue(dataVo.getReasonName());
            row.createCell(5).setCellValue(dataVo.getUser());
            row.createCell(6).setCellValue(dataVo.getE1());
            row.createCell(7).setCellValue(dataVo.getE2());
            row.createCell(8).setCellValue(dataVo.getE3());
            row.createCell(9).setCellValue(dataVo.getE3());
            row.createCell(10).setCellValue(dataVo.getE3());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void createHeader(String[] columns) {
        sheet = workbook.createSheet("Data");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        // Row for header
        Row headerRow = sheet.createRow(0);

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }


    }

}
