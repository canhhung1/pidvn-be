package pidvn.modules.sales.update_psi_price.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.two.sales.update_psi_price.UpdatePsiPriceMapper;
import pidvn.modules.sales.update_psi_price.modes.PsiPriceVo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UpdatePsiPriceSvc implements IUpdatePsiPriceSvc {

    @Autowired
    private UpdatePsiPriceMapper updatePsiPriceMapper;


    @Override
    public Map updatePsiPriceData() throws IOException {

        List<PsiPriceVo> stdPriceList = this.updatePsiPriceMapper.getPsiPriceData("std_price");
        List<PsiPriceVo> originalPriceList = this.updatePsiPriceMapper.getPsiPriceData("original_price");
        List<PsiPriceVo> stdCostList = this.updatePsiPriceMapper.getPsiPriceData("std_cost");

        this.writeExcelStdPrice(stdPriceList);
        this.writeExcelOriginalPrice(originalPriceList);
        this.writeExcelStdCost(stdCostList);

        Map result = new HashMap();
        result.put("Result: ","Export PSI price Success");
        return result;
    }

    private void writeExcelStdPrice(List<PsiPriceVo> dataSource) throws IOException {

        String path = "Y:\\Public\\ThangIT\\cron gia cho Sales\\std_price.xlsx";
        String[] columns = {"CDFC", "PNSASM", "YMD8ST", "YMD8ED", "SAPSTDI"};

        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Export Worksheet");

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Create Other rows and cells with employees data
        int rowNum = 1;

        for (PsiPriceVo item: dataSource) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCDFC());
            row.createCell(1).setCellValue(item.getPNSASM());
            row.createCell(2).setCellValue(item.getYMD8ST());
            row.createCell(3).setCellValue(item.getYMD8ED());
            row.createCell(4).setCellValue(item.getSAPSTDI());
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        System.out.println("writeExcelStdPrice: ===========>>>>>> DONE");
    }

    private void writeExcelOriginalPrice(List<PsiPriceVo> dataSource) throws IOException {
        String path = "Y:\\Public\\ThangIT\\cron gia cho Sales\\original_price.xlsx";
        String[] columns = {"CDFC", "KEY", "CDCUR", "ABRPYRE", "CDTTR", "YMD8ST", "YMD8ED", "SAP", "CDSAPUM"};

        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Export Worksheet");

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        int rowNum = 1;

        for (PsiPriceVo item: dataSource) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCDFC());
            row.createCell(1).setCellValue(item.getKEY());
            row.createCell(2).setCellValue(item.getCDCUR());
            row.createCell(3).setCellValue(item.getABRPYRE());
            row.createCell(4).setCellValue(item.getCDTTR());
            row.createCell(5).setCellValue(item.getYMD8ST());
            row.createCell(6).setCellValue(item.getYMD8ED());
            row.createCell(7).setCellValue(item.getSAP());
            row.createCell(8).setCellValue(item.getCDSAPUM());
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        System.out.println("writeExcelOriginalPrice: ===========>>>>>> DONE");
    }

    private void writeExcelStdCost(List<PsiPriceVo> dataSource) throws IOException {
        String path = "Y:\\Public\\ThangIT\\cron gia cho Sales\\std_cost.xlsx";
        String[] columns = {"CDFC", "PNCSTSM", "YMD8ST", "YMD8ED", "CSTTL"};

        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Export Worksheet");

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Create Other rows and cells with employees data
        int rowNum = 1;

        for (PsiPriceVo item: dataSource) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCDFC());
            row.createCell(1).setCellValue(item.getPNCSTSM());
            row.createCell(2).setCellValue(item.getYMD8ST());
            row.createCell(3).setCellValue(item.getYMD8ED());
            row.createCell(4).setCellValue(item.getCSTTL());
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        System.out.println("writeExcelStdCost: ===========>>>>>> DONE");
    }

}
