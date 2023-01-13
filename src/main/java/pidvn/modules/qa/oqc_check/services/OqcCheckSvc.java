package pidvn.modules.qa.oqc_check.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.OqcDataFile;
import pidvn.entities.one.OqcRequest;
import pidvn.mappers.one.qa.oqc_check.OqcCheckMapper;
import pidvn.modules.qa.oqc_check.models.OqcDataVo;
import pidvn.modules.qa.oqc_check.models.OqcRequestVo;
import pidvn.modules.relay.measurement.utils.FileUploadUtil;
import pidvn.repositories.one.OqcDataFileRepo;
import pidvn.repositories.one.OqcRequestRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OqcCheckSvc implements IOqcCheckSvc {

    private String FILE_PATH_ROOT = "P:\\IS\\CanhHung\\FDCS\\QA\\OQC\\Relay";

    @Autowired
    private OqcRequestRepo oqcRequestRepo;

    @Autowired
    private OqcDataFileRepo oqcDataFileRepo;

    @Autowired
    private OqcCheckMapper oqcCheckMapper;

    @Override
    public List<OqcDataVo> getOqcMasterData(String reqNo, String qaCard) {
        return this.oqcCheckMapper.getOqcMasterData(reqNo,qaCard);
    }

    @Override
    public List<OqcRequestVo> getOqcRequests(String reqNo) {
        return this.oqcCheckMapper.getOqcRequests(reqNo);
    }

    @Override
    public OqcRequest updateOqcRequest(OqcRequestVo oqcRequestVo) {

        OqcRequest oqcRequest = this.oqcRequestRepo.findByReqNo(oqcRequestVo.getReqNo()).get(0);

        oqcRequest.setPriority(oqcRequestVo.getPriority() == null ? oqcRequest.getPriority() : oqcRequestVo.getPriority());
        oqcRequest.setOqcRequestStatus(oqcRequestVo.getRequestStatusId() == null ? oqcRequest.getOqcRequestStatus() : oqcRequestVo.getRequestStatusId());

        return this.oqcRequestRepo.save(oqcRequest);
    }

    @Override
    public OqcRequest oqcRequestHandle(OqcRequestVo oqcRequestVo) {
        OqcRequest oqcRequest = this.oqcRequestRepo.findById(oqcRequestVo.getId()).get();
        oqcRequest.setOqcRequestStatus(2);
        return this.oqcRequestRepo.save(oqcRequest);
    }

    @Override
    public OqcDataFile uploadFile(MultipartFile file, String createdBy, String filePathRoot, String reqNo) throws IOException {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String strDate = formatter.format(date);

        String fileName = "Data_" + strDate + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        String uploadDir = filePathRoot + "\\" + reqNo + "\\";
        FileUploadUtil.saveFile(uploadDir, fileName, file);

        OqcDataFile oqcDataFile = new OqcDataFile();
        oqcDataFile.setReqNo(reqNo);
        oqcDataFile.setFileFormat(file.getContentType());
        oqcDataFile.setUrl(uploadDir);
        oqcDataFile.setFileName(fileName);
        oqcDataFile.setCreatedBy(createdBy);

        // TODO: Lưu vào database
        OqcDataFile result = this.oqcDataFileRepo.save(oqcDataFile);

        // TODO: Cập nhật Judgment cho Request
        String finalJudgment = this.getFinalJudgment(file);
        OqcRequest oqcRequest = this.oqcRequestRepo.findByReqNo(reqNo).get(0);
        oqcRequest.setJudgment(finalJudgment == "" ? null : finalJudgment);
        oqcRequest.setOqcRequestStatus(3);
        oqcRequest.setOqcDate(new Date());

        this.oqcRequestRepo.save(oqcRequest);

        return result;
    }

    /**
     * Lấy Judgment từ attach file
     * @param file
     * @return
     */
    private String getFinalJudgment(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        String result = workbook.getSheetAt(0).getRow(36).getCell(21).getStringCellValue();
        return result;
    }

    @Override
    public List<OqcDataFile> getOqcDataFiles(String reqNo) {
        return this.oqcDataFileRepo.findByReqNoOrderByIdDesc(reqNo);
    }
}
