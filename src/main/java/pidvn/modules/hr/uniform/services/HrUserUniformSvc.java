package pidvn.modules.hr.uniform.services;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.UserUniform;
import pidvn.entities.one.UserUniformUpload;
import pidvn.mappers.one.hr.uniform.HrUserUniformMapper;
import pidvn.modules.hr.uniform.models.UserUniformTypeVo;
import pidvn.modules.hr.uniform.models.UserUniformUploadVo;
import pidvn.modules.hr.uniform.models.UserUniformVo;
import pidvn.modules.hr.uniform.models.UserVo;
import pidvn.repositories.one.UserUniformRepo;
import pidvn.repositories.one.UserUniformUploadRepo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HrUserUniformSvc implements IHrUserUniformSvc {

    @Autowired
    private HrUserUniformMapper hrUserUniformMapper;

    @Autowired
    private UserUniformUploadRepo userUniformUploadRepo;

    @Autowired
    private UserUniformRepo userUniformRepo;

    @Override
    public List<UserUniformVo> getUserUniforms() {
        return this.hrUserUniformMapper.getUserUniforms();
    }

    @Override
    public UserUniform saveUserUniform(UserUniform userUniform) {
        return this.userUniformRepo.save(userUniform);
    }

    @Override
    public List<UserUniformTypeVo> getUserUniformTypes() {
        return this.hrUserUniformMapper.getUserUniformTypes();
    }

    @Override
    public List<UserVo> getUsers() {
        return this.hrUserUniformMapper.getUsers();
    }

    @Override
    @Transactional(transactionManager = "chainedTransactionManager", rollbackFor = Exception.class)
    public Map uploadExcel(MultipartFile file) throws IOException {

        /**
         * Tạo mã upload_id
         */
        int number = this.userUniformUploadRepo.getUploadIdsByCurDate().size();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int seqNum = number + 1;

        String uploadId = "UPD" + "-" + date + "-" + seqNum;

        /**
         * Insert dữ liệu lên bảng user_uniform_upload
         */
        List<UserUniformUpload> insertList1 = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        System.out.printf("AAA");

        for (int i = 5; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            UserUniformUpload uniformUpload = new UserUniformUpload();
            if (row.getCell(0).toString().equals("")) {
                continue;
            }

            uniformUpload.setUsername(row.getCell(0).getStringCellValue());
            uniformUpload.setUniformType((int) row.getCell(2).getNumericCellValue());
            uniformUpload.setQty((int) row.getCell(3).getNumericCellValue());
            uniformUpload.setDate(row.getCell(4).getDateCellValue());
            uniformUpload.setRemark(row.getCell(5).getStringCellValue());
            uniformUpload.setUploadId(uploadId);

            insertList1.add(uniformUpload);
        }

        List<UserUniformUpload> resultInsertList1 = this.userUniformUploadRepo.saveAll(insertList1);


        /**
         * Insert dữ liệu vào bảng user_uniform
         */
        List<UserUniformUploadVo> userUniformUploads = this.hrUserUniformMapper.getUserUniformUploads(uploadId);

        List<UserUniform> insertList2 = new ArrayList<>();

        for (UserUniformUploadVo item : userUniformUploads) {
            UserUniform obj = new UserUniform();
            obj.setUsername(item.getUsername().trim());
            obj.setUniformType(item.getUniformType());
            obj.setQty(item.getQty());
            obj.setDate(item.getDate());
            obj.setUploadId(uploadId);
            obj.setSize(item.getSize());
            obj.setRemark(item.getRemark());
            insertList2.add(obj);
        }

        List<UserUniform> resultInsertList2 = this.userUniformRepo.saveAll(insertList2);

        Map result = new HashMap();
        result.put("Result", resultInsertList2);

        return result;

    }

    @Override
    public ByteArrayInputStream downloadExcelTemplate() throws IOException {

        String sourcePath = "\\\\10.92.152.55\\pvg-data$\\PIDVN-Data\\Public Drive\\IS\\CanhHung\\FDCS\\HR & GA\\UserUniform\\UploadTemplate.xlsx";

        FileInputStream inputStream = new FileInputStream(sourcePath);

        //Creating workbook from input stream
        Workbook workbook = WorkbookFactory.create(inputStream);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream result = new ByteArrayInputStream(out.toByteArray());
        workbook.close();

        return result;

    }

    @Override
    public List<UserUniformVo> deleteUserUniforms(List<UserUniformVo> userUniformVos) {

        List<Integer> listId = new ArrayList<>();

        for (UserUniformVo item: userUniformVos) {
            listId.add(item.getId());
        }

        this.userUniformRepo.deleteAllById(listId);

        return userUniformVos;
    }

}
