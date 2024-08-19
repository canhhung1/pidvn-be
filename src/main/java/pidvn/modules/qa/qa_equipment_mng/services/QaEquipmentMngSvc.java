package pidvn.modules.qa.qa_equipment_mng.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.QaDevice;
import pidvn.entities.one.QaDeviceCalibrationLabel;
import pidvn.entities.one.QaDocDevice;
import pidvn.mappers.one.qa.qa_equipment_mng.QaEquipmentMngMapper;
import pidvn.modules.qa.qa_equipment_mng.models.LabelVo;
import pidvn.modules.qa.qa_equipment_mng.models.QaDeviceVo;
import pidvn.modules.qa.qa_equipment_mng.models.QaDocDeviceVo;
import pidvn.repositories.one.QaDeviceCalibrationLabelRepo;
import pidvn.repositories.one.QaDeviceRepo;
import pidvn.repositories.one.QaDocDeviceRepo;
import pidvn.repositories.one.QaDocTypeDeviceRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class QaEquipmentMngSvc implements IQaEquipmentMngSvc {

    @Autowired
    private QaEquipmentMngMapper qaEquipmentMngMapper;

    @Autowired
    private QaDocDeviceRepo qaDocDeviceRepo;

    @Autowired
    private QaDocTypeDeviceRepo qaDocTypeDeviceRepo;

    @Autowired
    private QaDeviceRepo qaDeviceRepo;

    @Autowired
    private QaDeviceCalibrationLabelRepo qaDeviceCalibrationLabelRepo;

    private String ROOT_FOLDER = "\\\\10.92.152.55\\pvg-data$\\PIDVN-Data\\Public Drive\\IS\\CanhHung\\FDCS\\QA\\QC\\DocumentDevice";

    /**
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public List<LabelVo> createLabel(MultipartFile file) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<LabelVo> result = new ArrayList<>();

        for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            LabelVo label = new LabelVo();
            label.setIdNo(row.getCell(0).getStringCellValue());
            label.setBy(row.getCell(1).getStringCellValue());
            label.setDate(row.getCell(2).getDateCellValue());
            label.setDue(row.getCell(3).getDateCellValue());
            result.add(label);
        }

        return result;
    }

    @Override
    public Map uploadDocument(MultipartFile file, String controlNo, Integer deviceId, Integer fileType, String createdBy) throws IOException {

        String fileTypeName = this.qaDocTypeDeviceRepo.findById(fileType).get().getName();

        String fileName = file.getOriginalFilename();
        String url = this.ROOT_FOLDER + "\\" + controlNo + "\\" + fileTypeName + "\\" + fileName;

        /**
         * Kiểm tra file đã tồn tại chưa
         */
        if (Files.exists(Paths.get(url))) {
            Map result = new HashMap();
            result.put("status", "ERROR");
            result.put("message", "File đã tồn tại");
            return result;
        }

        /**
         * Nếu file chưa tồn tại
         * Upload file vào folder
         */
        // Path uploadPath = Paths.get(this.ROOT_FOLDER + "\\" + fileTypeName + "\\" + controlNo);

        Path uploadPath = Paths.get(this.ROOT_FOLDER + "\\" + controlNo + "\\" + fileTypeName);


        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        /**
         * Lưu vào bảng qa_doc_device
         */

        QaDocDevice obj = new QaDocDevice();

        obj.setDeviceId(deviceId);
        obj.setFileType(fileType);
        obj.setRootFolder(this.ROOT_FOLDER);
        obj.setFileName(fileName);
        obj.setFileFormat(file.getContentType());
        obj.setCreatedBy(createdBy);

        QaDocDevice doc = this.qaDocDeviceRepo.save(obj);

        Map result = new HashMap();
        result.put("status", "OK");
        result.put("message", "Upload thành công");
        result.put("data", doc);

        return result;
    }

    @Override
    public List<QaDocDeviceVo> getQaDocDevices(Integer deviceId) {
        return this.qaEquipmentMngMapper.getQaDocDevices(deviceId);
    }

    /**
     * Lấy các thông tin về thiết bị, các report ....
     * @param controlNo
     * @return
     */
    @Override
    public Map getDeviceInfo(String controlNo) throws Exception {

        QaDeviceVo qaDeviceVo = this.qaEquipmentMngMapper.getDeviceInfo(controlNo).get(0);


        if (qaDeviceVo == null) {
            throw new Exception("Thiết bị không tồn tại");
        }
        List<QaDocDeviceVo> documents = this.getQaDocDevices(qaDeviceVo.getId());

        Map result = new HashMap();
        result.put("info", qaDeviceVo);
        result.put("documents", documents);

        return result;
    }

    @Override
    public List<QaDeviceCalibrationLabel> printLabel(List<LabelVo> labelVos, Integer userId) {

        List<QaDeviceCalibrationLabel> arrLabels = new ArrayList<>();

        // Số lần đã in tem
        int printTimes = this.qaEquipmentMngMapper.getPrintCodeInDay().size();
        int sequenceNo = printTimes+=1;

        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String printCode = "PrintLabel-" + strDate + "-" + sequenceNo;

        for (LabelVo item: labelVos) {
            QaDeviceCalibrationLabel label = new QaDeviceCalibrationLabel();
            label.setControlNo(item.getIdNo());
            label.setCalibrationUnit(item.getBy());
            label.setDate(item.getDate());
            label.setDue(item.getDue());
            label.setUserId(userId);
            label.setPrintCode(printCode);
            arrLabels.add(label);
        }

        return this.qaDeviceCalibrationLabelRepo.saveAll(arrLabels);
    }

    @Override
    public QaDocDevice deleteQaDocDevice(Integer docDeviceId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        QaDocDevice qaDocDevice = this.qaDocDeviceRepo.findById(docDeviceId).get();
        qaDocDevice.setIsDelete(1);
        qaDocDevice.setDeleteBy(auth.getName());
        return this.qaDocDeviceRepo.save(qaDocDevice);
    }

    @Override
    public QaDevice saveDeviceInfo(QaDevice qaDevice) throws Exception {

        if (qaDevice.getId() == null) {

            List<QaDevice> data = this.qaDeviceRepo.findByControlNo(qaDevice.getControlNo());

            if (data.size() > 0) {
                throw new Exception("Thiết bị đã tồn tại !");
            }



            return this.qaDeviceRepo.save(qaDevice);

        }


        return this.qaDeviceRepo.save(qaDevice);
    }


}
