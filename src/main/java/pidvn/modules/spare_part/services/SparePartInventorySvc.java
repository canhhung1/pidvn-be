package pidvn.modules.spare_part.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.SparePartInventoryData;
import pidvn.mappers.one.spare_part.SparePartMapper;
import pidvn.modules.spare_part.models.RowExcelErrorVo;
import pidvn.modules.spare_part.models.SparePartRecordVo;
import pidvn.repositories.one.SparePartInventoryDataRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SparePartInventorySvc implements ISparePartInventorySvc {

    @Autowired
    private SparePartInventoryDataRepo sparePartInventoryDataRepo;

    @Autowired
    private SparePartMapper sparePartMapper;


    @Override
    public List<SparePartRecordVo> getInventoryData(Integer requestId) {
        return this.sparePartMapper.getSparePartInventory(requestId);
    }

    @Override
    public Map uploadExcelInventoryData(MultipartFile file, Integer requestId ) {

        Map map = this.readExcelSparePartInventory(file, requestId);

        List<SparePartInventoryData> data = (List<SparePartInventoryData>) map.get("data");

        List<SparePartInventoryData> result = this.sparePartInventoryDataRepo.saveAll(data);

        return map;
    }

    /**
     *
     * @param file
     * @param requestId mã kiểm kê
     * @return
     */
    private Map readExcelSparePartInventory(MultipartFile file, Integer requestId) {

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheet("STOCK RL");

        List<SparePartInventoryData> data = new ArrayList<>();
        List<RowExcelErrorVo> rowNG = new ArrayList<>();

        int i = 8;
        int STT_CELL = 0;
        int ACTUAL_QTY = 9;
        int PART_NUMBER = 3;
        int POSITION = 6;


        while (true) {

            try {

                XSSFRow row = sheet.getRow(i);
                System.out.println("DATA: " + row.getCell(STT_CELL).getStringCellValue());

                if(row.getCell(STT_CELL).getStringCellValue().trim().equals("END")) {
                    break;
                }

                if (row.getCell(PART_NUMBER).getStringCellValue().trim().equals("")
                        || row.getCell(PART_NUMBER).getStringCellValue() == null) {
                    i = i + 1;
                    rowNG.add(new RowExcelErrorVo(i,"Không có PartNumber"));
                    continue;
                }

                SparePartInventoryData obj = new SparePartInventoryData();
                obj.setRequestId(requestId);
                obj.setPartNumber(row.getCell(PART_NUMBER).getStringCellValue());
                obj.setQty((int) row.getCell(ACTUAL_QTY).getNumericCellValue());
                obj.setPosition(row.getCell(POSITION).getStringCellValue());

                data.add(obj);
                i++;

            }catch (Exception e) {
                i = i+1;
                RowExcelErrorVo item = new RowExcelErrorVo(i, e.toString());
                rowNG.add(item);

            }

        }


        Map result = new HashMap();

        result.put("data", data);
        result.put("error", rowNG);

        return result;
    }


}
