package pidvn.modules.spare_part.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pidvn.entities.one.*;
import pidvn.exceptions.ConflictException;
import pidvn.mappers.one.spare_part.SparePartMapper;
import pidvn.modules.spare_part.models.RowExcelErrorVo;
import pidvn.modules.spare_part.models.SearchVo;
import pidvn.modules.spare_part.models.SparePartRecordVo;
import pidvn.repositories.one.*;

import java.io.IOException;
import java.util.*;

@Service
public class SparePartSvc implements ISparePartSvc {

    Logger logger = LoggerFactory.getLogger(SparePartSvc.class);

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private SparePartRepo sparePartRepo;

    @Autowired
    private SparePartRecordRepo sparePartRecordRepo;

    @Autowired
    private SparePartInventoryRequestRepo sparePartInventoryRequestRepo;

    @Autowired
    private SparePartInventoryDataRepo sparePartInventoryDataRepo;

    @Autowired
    private SparePartMapper sparePartMapper;

    @Autowired
    private SparePartLineStandardRepo sparePartLineStandardRepo;

    @Autowired
    private SparePartMachineStandardRepo sparePartMachineStandardRepo;

    @Override
    public List<Users> getUsers() {
        return this.usersRepo.findAllByOrderByIdDesc();
    }

    @Override
    public List<SparePart> getSpareParts() {
        return this.sparePartRepo.findAllByOrderByIdDesc();
    }

    @Override
    public SparePart saveSparePart(SparePart sparePart) throws Exception {

        if (this.sparePartRepo.findByPartNumber(sparePart.getPartNumber()) != null) {
            throw new Exception("Part Number đã tồn tại");
        }

        if (this.sparePartRepo.findByGalileoName(sparePart.getGalileoName()) != null) {
            throw new Exception("Galileo Name đã tồn tại");
        }

        return this.sparePartRepo.save(sparePart);
    }

    @Override
    public List<SparePartRecordVo> getSparePartRecords(SearchVo searchVo) {
        return this.sparePartMapper.getSparePartRecords(searchVo);
    }

    @Override
    public List<SparePartRecord> saveSparePartRecords(List<SparePartRecord> sparePartRecords) {

        List<SparePartRecord> data = new ArrayList<>();

        for (SparePartRecord item : sparePartRecords) {
            if (item.getQty() > 0) {
                data.add(item);
            }
        }

        return this.sparePartRecordRepo.saveAll(data);
    }

//    @Override
//    public SparePartRecord saveSparePartRecord(SparePartRecord sparePartRecord) {
//        return this.sparePartRecordRepo.save(sparePartRecord);
//    }


    @Override
    public SparePartInventoryRequest saveSparePartInventoryRequest(SparePartInventoryRequest request) throws ResponseStatusException, ConflictException {

        /**
         * Nếu đã có phiếu kiểm kê trong tháng rồi thì ko tạo nữa
         */
        List<SparePartInventoryRequest> requests = this.sparePartInventoryRequestRepo.findByCurrentMonth();

        if (requests.size() > 0) {
            // throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã có phiếu kiểm kê tháng này");
            throw new ConflictException("Đã có phiếu kiểm kê tháng này");
        }

        return this.sparePartInventoryRequestRepo.save(request);
    }

    @Override
    public List<SparePartInventoryRequest> getSparePartInventoryRequests() {
        return this.sparePartInventoryRequestRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Map saveInventoryData(List<SparePartInventoryData> sparePartInventoryDataList) {

        List<SparePartInventoryData> resultOK = new ArrayList<>();
        List<SparePartInventoryData> resultNG = new ArrayList<>();

        for (SparePartInventoryData item : sparePartInventoryDataList) {
            try {
                SparePartInventoryData ivtData = this.sparePartInventoryDataRepo.save(item);
                resultOK.add(ivtData);
            } catch (Exception e) {
                logger.debug(e.toString());
                resultNG.add(item);
            }
        }

        Map result = new HashMap();
        result.put("resultOK", resultOK);
        result.put("resultNG", resultNG);

        return result;
    }

    @Override
    public Map uploadExcel(MultipartFile file, String recordType) throws IOException {

        Map result = null;

        if (recordType.equals("OUT")) {
            result = this.readExcelSparePartOutput(file);
        }

        List<SparePartRecord> data = (List<SparePartRecord>) result.get("data");

        this.sparePartRecordRepo.saveAll(data);


        return result;
    }

    @Override
    public List<SparePartLineStandard> getLineStandard() {
        return this.sparePartLineStandardRepo.findAll();
    }

    @Override
    public List<SparePartMachineStandard> getMachineStandard() {
        return this.sparePartMachineStandardRepo.findAll();
    }


    /**
     * Đọ dữ liệu xuất spare part từ excel
     *
     * @param file
     * @return
     */
    private Map readExcelSparePartOutput(MultipartFile file) {

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheetAt(0);


        List<SparePartRecord> data = new ArrayList<>();
        List<RowExcelErrorVo> rowNG = new ArrayList<>();

        int lastRowNum = sheet.getPhysicalNumberOfRows();

        for (int i = 6; i <= lastRowNum; i++) {
            SparePartRecord obj = new SparePartRecord();
            try {
                XSSFRow row = sheet.getRow(i);

                if (row.getCell(2).getStringCellValue().trim().equals("")
                        || row.getCell(2).getStringCellValue() == null
                ) {
                    Integer rowNum = i + 1;
                    rowNG.add(new RowExcelErrorVo(rowNum,"Không có PartNumber"));
                    continue;
                }

                if ((int) row.getCell(4).getNumericCellValue() <= 0) {
                    Integer rowNum = i + 1;
                    rowNG.add(new RowExcelErrorVo(rowNum,"Qty <= 0"));
                    continue;
                }

                obj.setPartNumber(row.getCell(2).getStringCellValue());
                obj.setWhUserCode(row.getCell(5).getStringCellValue());
                obj.setReceiveUserCode(row.getCell(7).getStringCellValue());
                obj.setQty((float) row.getCell(4).getNumericCellValue());
                obj.setMachine(row.getCell(8).getStringCellValue());
                obj.setLine(row.getCell(9).getStringCellValue());
                obj.setDate(row.getCell(0).getDateCellValue());
                obj.setInsertType("excel");
                obj.setType("OUT");
                obj.setFactoryCode("RE");
                obj.setCreatedAt(new Date());
                obj.setUpdatedAt(new Date());

                data.add(obj);

            } catch (Exception e) {
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
}
