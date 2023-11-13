package pidvn.modules.spare_part.services;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SparePartInventorySvc implements ISparePartInventorySvc {
    @Override
    public Map uploadExcelInventoryData(MultipartFile file) {




        return null;
    }

    /**
     *
     * @param file
     * @param requestId mã kiểm kê
     * @return
     */
    private Map readExcelSparePartOutput(MultipartFile file, Integer requestId) {

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheetAt(0);

        int lastRowNum = sheet.getPhysicalNumberOfRows();












        Map result = new HashMap();

        result.put("data", "data");
        result.put("error", "rowNG");

        return result;
    }


}
