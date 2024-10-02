package pidvn.modules.schedule_task.relay.defect_record.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pidvn.entities.one.DefectRecord;
import pidvn.modules.ie.drawing_control.models.UserDto;
import pidvn.modules.schedule_task.relay.defect_record.models.DefectRecordDto;
import pidvn.repositories.one.DefectRecordRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelayDefectRecordSvc {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DefectRecordRepo defectRecordRepo;

    private static final String FILE_NAME = "D:\\PIDVN\\WorkSpace\\1. RelayDefectRecord\\NKSX 6.xlsx";

    // @Scheduled(fixedRate = 3600000)
    public void readExcel() throws IOException {

        int START_ROW = 8;
        int END_ROW = 11;
        int START_COL = 8;
        int DEFECT_CODE_ROW = 4;

        int DATE_COL = 1;
        int LINE_COL = 2;
        int MODEL_COL = 3;
        int DATE_CODE_COL = 4;
        int CUSTOMER_CODE_COL = 5;
        int EMPLOYEE_ID_COL = 6;
        int SHIFT_COL = 7;
        int INPUT_TIME_COL = 8;


        XSSFWorkbook workbook = new XSSFWorkbook(FILE_NAME);

        XSSFSheet sheet = workbook.getSheetAt(1);

        List<DefectRecordDto> defectRecords = new ArrayList<>();

        for (int i = START_ROW; i < END_ROW; i++) {
            System.out.println("Loop row: " + i);

            XSSFRow row = sheet.getRow(i);

            for (int j = START_COL; j < row.getLastCellNum() - 1; j++) {
                DefectRecordDto obj = new DefectRecordDto();
                try {

                    if (row.getCell(j) == null || row.getCell(j).getNumericCellValue() == 0) {
                        continue;
                    }

                    obj.setDate(row.getCell(DATE_COL).getDateCellValue());
                    obj.setLine(row.getCell(LINE_COL).getStringCellValue());
                    obj.setModel(row.getCell(MODEL_COL).getStringCellValue());
                    obj.setLotNo(row.getCell(DATE_CODE_COL).getStringCellValue());
                    obj.setCustomerCode(row.getCell(CUSTOMER_CODE_COL).getStringCellValue());
                    obj.setUsername(row.getCell(EMPLOYEE_ID_COL).getStringCellValue());
                    obj.setShift(row.getCell(SHIFT_COL).getStringCellValue());
                    obj.setRemark(row.getCell(INPUT_TIME_COL).getStringCellValue());
                    obj.setDefectCode(sheet.getRow(DEFECT_CODE_ROW).getCell(j).getStringCellValue());
                    obj.setQty((float) row.getCell(j).getNumericCellValue());
                    obj.setUserId(665);
                    obj.setCreatedAt(new Date());
                    obj.setUpdatedAt(new Date());
                    obj.setRecordType("RELAY");

                    defectRecords.add(obj);

                } catch (Exception e) {
                    continue;
                }

            }

        }

        List<DefectRecord> data = defectRecords.stream().map(item -> modelMapper.map(item, DefectRecord.class)).collect(Collectors.toList());

        for (DefectRecord obj : data) {
            System.out.println(obj.toString());
        }

        this.defectRecordRepo.saveAll(data);
    }


}
