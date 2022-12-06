package pidvn.modules.practice.controllers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pidvn.entities.one.IeMachineHistory;
import pidvn.repositories.one.IeMachineHistoryRepo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("Practice")
public class ReadExcelController {

    @Autowired
    private IeMachineHistoryRepo ieMachineHistoryRepo;

    @GetMapping("ReadExcel")
    public ResponseEntity<?> readExcel(@RequestParam Integer runner) throws ParseException, IOException {

        // TODO

        String pathFile = "P:\\IS\\CanhHung\\project IE\\HSSM 2022 v1.xlsx";

        File file = new File(pathFile);

        FileInputStream inputStream = new FileInputStream(file);

        //Creating workbook from input stream
//        Workbook workbook = WorkbookFactory.create(inputStream);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("IE");

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        List<IeMachineHistory> histories = new ArrayList<>();

        for (int i = 7; i < runner; i++) {

            XSSFRow row = sheet.getRow(i);

            IeMachineHistory obj = new IeMachineHistory();

            String line = row.getCell(2).getStringCellValue();

            String date = row.getCell(14).getStringCellValue();
            String startTimeStr = row.getCell(4).getDateCellValue() == null ? null : date + " " + dateFormat.format(row.getCell(4).getDateCellValue());
            String endTimeStr = row.getCell(5).getDateCellValue() == null ? null : date + " " + dateFormat.format(row.getCell(5).getDateCellValue());

            Date startTime = startTimeStr == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimeStr);
            Date endTime = endTimeStr == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr);

            Integer minutes = null;

            if (startTimeStr != null && endTimeStr != null) {
                long difference = endTime.getTime() - startTime.getTime();
                long differenceMinutes = difference / (60 * 1000) % 60;
                minutes = (int) differenceMinutes;
            }

            String incident = row.getCell(8).getStringCellValue() == " " ? null : row.getCell(8).getStringCellValue();
            String reason = row.getCell(9).getStringCellValue() == " " ? null : row.getCell(9).getStringCellValue();
            String action = row.getCell(10).getStringCellValue() == " " ? null : row.getCell(10).getStringCellValue();
            String machineCode = row.getCell(13).getStringCellValue();
            String username = row.getCell(7).getRawValue();

            obj.setLine(line);
            obj.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            obj.setStartTime(startTime);
            obj.setStopTime(endTime);
            obj.setMinutes(minutes);
            obj.setIncident(incident);
            obj.setReason(reason);
            obj.setAction(action);
            obj.setMachineCode(machineCode);
            obj.setUserCode(username);
            obj.setType("R");

            histories.add(obj);

        }


        String excelFilePath = "P:\\IS\\CanhHung\\project IE\\DataLink2.xls";
        this.writeExcel(histories,excelFilePath);



//        List<IeMachineHistory> result = this.ieMachineHistoryRepo.saveAll(histories);

        return new ResponseEntity<>(histories, HttpStatus.OK);
    }



    public void writeExcel(List<IeMachineHistory> records, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (IeMachineHistory item : records) {
            Row row = sheet.createRow(++rowCount);
            writeBook(item, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeBook(IeMachineHistory ieMachineHistory, Row row) {

        Cell cell = row.createCell(1);
        cell.setCellValue(ieMachineHistory.getStartTime());

        cell = row.createCell(2);
        cell.setCellValue(ieMachineHistory.getStopTime());

        cell = row.createCell(3);
        cell.setCellValue(ieMachineHistory.getAction());

        cell = row.createCell(4);
        cell.setCellValue(ieMachineHistory.getReason());

        cell = row.createCell(5);
        cell.setCellValue(ieMachineHistory.getIncident());

        cell = row.createCell(6);
        cell.setCellValue(ieMachineHistory.getMachineCode());

        cell = row.createCell(7);
        cell.setCellValue(ieMachineHistory.getDate());

        cell = row.createCell(8);
        cell.setCellValue(ieMachineHistory.getUserCode());

        cell = row.createCell(9);
        cell.setCellValue(ieMachineHistory.getMinutes());
    }
}
