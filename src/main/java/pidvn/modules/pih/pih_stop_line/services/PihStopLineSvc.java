package pidvn.modules.pih.pih_stop_line.services;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.mappers.one.pih.pih_stop_line.PihStopLineMapper;
import pidvn.modules.pih.pih_stop_line.models.LineVo;
import pidvn.modules.pih.pih_stop_line.models.SearchVo;
import pidvn.modules.pih.pih_stop_line.models.StopTime;
import pidvn.modules.pih.pih_stop_line.models.UserVo;
import pidvn.repositories.one.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class PihStopLineSvc implements IPihStopLineSvc {

    @Autowired
    private PihStopLineMapper pihStopLineMapper;

    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Autowired
    private ShiftsRepo shiftsRepo;

    @Autowired
    private StopTypesRepo stopTypesRepo;

    @Autowired
    private StopGroupsRepo stopGroupsRepo;

    @Autowired
    private StopItemsRepo stopItemsRepo;

    @Autowired
    private StopTimesRepo stopTimesRepo;

    @Autowired
    private DefaultRepo defaultRepo;

    @Override
    public List<ProductType> getProductTypes(Integer productId) {

        List<Integer> productIds = Arrays.asList(4,6);

        return this.productTypeRepo.findByProductIdIn(productIds);

    }

    @Override
    public List<LineVo> getLines() {
        return this.pihStopLineMapper.getLines();
    }

    @Override
    public List<Shifts> getShifts() {
        return this.shiftsRepo.findAll();
    }

    @Override
    public List<StopTypes> getStopTypes() {
        return this.stopTypesRepo.findAll();
    }

    @Override
    public List<StopGroups> getStopGroups() {
        return this.stopGroupsRepo.findAll();
    }

    @Override
    public List<StopItems> getStopItems() {
        return this.stopItemsRepo.findAll();
    }


    @Override
    public StopTimes createStopTime(StopTimes stopTime) {
        return this.stopTimesRepo.save(stopTime);
    }

    @Override
    public StopTimes updateStopTime(StopTimes stopTimes) {
        StopTimes stopTime = this.stopTimesRepo.findById(stopTimes.getId()).get();
        stopTime.setStopItemId(stopTimes.getStopItemId());
        stopTime.setDate(stopTimes.getDate());
        stopTime.setStartTime(stopTimes.getStartTime());
        stopTime.setStopTime(stopTimes.getStopTime());
        stopTime.setLine(stopTimes.getLine());
        stopTime.setShift(stopTimes.getShift());
        stopTime.setRemark(stopTimes.getRemark());
        return this.stopTimesRepo.save(stopTime);
    }

    @Override
    public List<StopTime> getStopTimes(SearchVo searchVo) {
        return this.pihStopLineMapper.getStopTimes(searchVo);
    }

    @Override
    public Map deleteStopTime(Integer id) {
        Map result = new HashMap();

        this.stopTimesRepo.deleteById(id);
        result.put("response", "Deleted Stop Time Id : " + id);
        return result;
    }

    /**
     * Đọc dữ liệu từ file
     * @param username
     * @return
     * @throws IOException
     */
    @Override
    public Map getProductTypeIdByUser(String username) throws IOException {

        //String rootURL = "\\\\27497-vm-dfs\\PVG-Data\\PIDVN\\Pidvn_new\\Public\\CanhHung\\Project\\PIH\\UserNhapDungMay\\DanhSach.xlsx";

        String rootURL = "E:\\(C) Save File FDCS\\FDCS-Server-2\\PIH\\StopLine\\UserNhapDungMay\\DanhSach.xlsx";

        File file = new File(rootURL);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<UserVo> userVoList = new ArrayList<>();

        try {
            for (int i = 6; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                UserVo userVo = new UserVo();
                userVo.setUsername(row.getCell(1).getRawValue());
                userVo.setProductTypeId((int) row.getCell(5).getNumericCellValue());
                userVoList.add(userVo);
            }
        } catch (Exception e) {
            inputStream.close();
        }

        inputStream.close();

        Map result = new HashMap();
        result.put("user",username);
        for (UserVo user : userVoList) {
            if (user.getUsername().equals(username)) {
                result.put("productTypeId",user.getProductTypeId());
                break;
            }
        }

        return result;
    }


    private boolean checkTotalStopTime(StopTimes stopTime) {

        





        return false;
    }
}
