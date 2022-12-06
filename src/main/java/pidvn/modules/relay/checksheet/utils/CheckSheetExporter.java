package pidvn.modules.relay.checksheet.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pidvn.modules.relay.checksheet.models.DetailVo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CheckSheetExporter {
    private XSSFWorkbook workbook;
    private Sheet sheet;
    private List<DetailVo> detailsList;

    public CheckSheetExporter(List<DetailVo> detailsList) {
        this.detailsList = detailsList;
        this.workbook = new XSSFWorkbook();
    }


    public ByteArrayInputStream export() throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        this.writeExcel();

        workbook.write(out);

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void writeExcel() {

        CreationHelper creationHelper = workbook.getCreationHelper();

        String[] columns = {
                "Line", "Công đoạn", "Hạng mục", "Giá trị Min", "Giá trị Max", "Loại hạng mục", "Đơn vị", "Giá trị thực", "Người thực hiện", "Ngày tạo"
        };

        this.createHeader(columns);

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("YYYY-MM-dd HH:mm:ss"));

        int rowNum = 1;
        for (DetailVo dataVo : detailsList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataVo.getLine());
            row.createCell(1).setCellValue(dataVo.getProcessName());
            row.createCell(2).setCellValue(dataVo.getItemName());
            row.createCell(3).setCellValue(dataVo.getMinValue() == null ? "" : dataVo.getMinValue().toString());
            row.createCell(4).setCellValue(dataVo.getMaxValue() == null ? "" : dataVo.getMaxValue().toString());
            row.createCell(5).setCellValue(dataVo.getItemType());
            row.createCell(6).setCellValue(dataVo.getItemUnit());
            row.createCell(7).setCellValue(dataVo.getActualValue() == null ? "" : dataVo.getActualValue().toString());
            row.createCell(8).setCellValue(dataVo.getEmployeeName());

            Cell date = row.createCell(9);
            date.setCellValue(dataVo.getCreatedAt());
            date.setCellStyle(dateCellStyle);
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
