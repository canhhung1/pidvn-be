package pidvn.modules.spare_part.services;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pidvn.entities.one.SparePartRecord;
import pidvn.modules.spare_part.models.RowExcelErrorVo;
import pidvn.repositories.one.SparePartRecordRepo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SparePartCronSvc implements ISparePartCronSvc {

    @Autowired
    private SparePartRecordRepo sparePartRecordRepo;

    /**
     * Cron chạy hàng ngày lúc 11h30 và 14h30
     * @return
     */
    //@Scheduled(cron = "0 30 11,14 * * *")
    @Override
    public List<SparePartRecord> insertDataOutputSparePart() {
        List<SparePartRecord> result = new ArrayList<>();

        // B1: Xóa data ngày hiện tại
        List<SparePartRecord> dataDelete = this.sparePartRecordRepo.findByCurrentDate();
        this.sparePartRecordRepo.deleteAll(dataDelete);

        // B2: Đọc data excel và insert vào database
        String fileName = this.getFileName();
        String relayPath = "C:\\Users\\70M5558\\Desktop\\SparePart\\Working\\Relay\\" + fileName;
        String speakerPath = "C:\\Users\\70M5558\\Desktop\\SparePart\\Working\\Speaker\\" + fileName;
        String vrPath = "C:\\Users\\70M5558\\Desktop\\SparePart\\Working\\VR\\" + fileName;

        List<String> paths = Arrays.asList(new String[] {relayPath,speakerPath,vrPath});

        for (String path: paths) {
            List<SparePartRecord> data = (List<SparePartRecord>) this.readDataExcelOutputSparePart(path).get("data");
            List<SparePartRecord> savedData = this.sparePartRecordRepo.saveAll(data);
            result.addAll(savedData);
        }

        return result;

    }


    /**
     * Đọc dữ liệu từ file excel
     * @return
     */
    private Map readDataExcelOutputSparePart(String path) {

        File file = new File(path);

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            Map result = new HashMap();
            result.put("data", new ArrayList<>());
            result.put("error", new ArrayList<>());
            return result;
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

        List<SparePartRecord> data = new ArrayList<>();
        List<RowExcelErrorVo> rowNG = new ArrayList<>();

        XSSFSheet sheet = workbook.getSheet("XuatKho");

        int lastRowNum = sheet.getLastRowNum();
        for (int i = 6; i <= lastRowNum; i++) {

            SparePartRecord obj = new SparePartRecord();

            try {
                XSSFRow row = sheet.getRow(i);

                obj.setWarehouse(row.getCell(1).getStringCellValue());
                obj.setFactoryCode(row.getCell(2).getStringCellValue());
                obj.setDate(row.getCell(3).getDateCellValue());
                obj.setRequestNo(row.getCell(4).getStringCellValue());
                obj.setPartNumber(row.getCell(5).getStringCellValue());
                obj.setQty((float) row.getCell(7).getNumericCellValue());
                obj.setWhUserCode(row.getCell(8).getStringCellValue());
                obj.setReceiveUserCode(row.getCell(10).getStringCellValue());
                //obj.setMachine(row.getCell(11).getStringCellValue());
                obj.setLine(row.getCell(13).getStringCellValue());
                obj.setInsertType("excel");
                obj.setType("OUTPUT");
                obj.setCreatedAt(new Date());
                obj.setUpdatedAt(new Date());

                data.add(obj);
            }catch (Exception e) {
                Integer rowNum = i + 1;
                RowExcelErrorVo item = new RowExcelErrorVo(rowNum, e.toString());
                rowNG.add(item);
            }
        }

        Map result = new HashMap();

        result.put("data", data);
        result.put("error", rowNG);

        return result;
    }

    private String getFileName() {
        Calendar cal = Calendar.getInstance();
        String month = new SimpleDateFormat("MM").format(cal.getTime());
        String year = new SimpleDateFormat("YYYY").format(cal.getTime());
        String fileName = year+"-"+month + ".xlsx";
        return fileName;
    }
}
