package pidvn.modules.qa.qa_training_matrix.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.QaTrainingMatrixRecord;
import pidvn.mappers.one.qa.qa_training_matrix.QaTrainingMatrixMapper;
import pidvn.modules.qa.qa_training_matrix.models.CourseVo;
import pidvn.modules.qa.qa_training_matrix.models.RowExcelErrorVo;
import pidvn.modules.qa.qa_training_matrix.models.TrainingRecordVo;
import pidvn.repositories.one.QaTrainingMatrixCourseRepo;
import pidvn.repositories.one.QaTrainingMatrixRecordRepo;

import java.io.IOException;
import java.util.*;

@Service
public class QaTrainingMatrixSvc implements IQaTrainingMatrixSvc {

    @Autowired
    private QaTrainingMatrixMapper qaTrainingMatrixMapper;

    @Autowired
    private QaTrainingMatrixRecordRepo qaTrainingMatrixRecordRepo;

    @Autowired
    private QaTrainingMatrixCourseRepo qaTrainingMatrixCourseRepo;

    @Override
    public Map insertByExcel(MultipartFile file) {

        Map result = this.readExcel(file);

        List<QaTrainingMatrixRecord> data = (List<QaTrainingMatrixRecord>) result.get("data");

        this.qaTrainingMatrixRecordRepo.saveAll(data);

        return result;
    }

    @Override
    public List<CourseVo> getCourses() {
        return this.qaTrainingMatrixMapper.getCourses();
    }

    @Override
    public List<TrainingRecordVo> getTrainingRecords(Date trainingDate, Integer courseId) {
        return this.qaTrainingMatrixMapper.getTrainingRecords(trainingDate, courseId);
    }

    private Map readExcel(MultipartFile file) {

        XSSFWorkbook workbook;

        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();

        int START_ROW = 5;
        int COURSE_ID = (int) sheet.getRow(1).getCell(1).getNumericCellValue();
        Date TRAINING_DATE = sheet.getRow(2).getCell(1).getDateCellValue();
        int USERNAME_COL = 1;
        int TRAINING_TYPE_COL = 2;
        int TRAINING_STATUS_COL = 3;


        List<QaTrainingMatrixRecord> okData = new ArrayList<>();
        List<RowExcelErrorVo> ngData = new ArrayList<>();

        for (int i = START_ROW; i <= lastRowNum; i++) {

            QaTrainingMatrixRecord obj = new QaTrainingMatrixRecord();

            try {
                XSSFRow row = sheet.getRow(i);
                obj.setCourseId(COURSE_ID);
                obj.setTrainingDate(TRAINING_DATE);
                obj.setUsername(row.getCell(USERNAME_COL).getRawValue());
                obj.setTrainingType(row.getCell(TRAINING_TYPE_COL).getStringCellValue());
                obj.setTrainingStatus(row.getCell(TRAINING_STATUS_COL).getStringCellValue());
                okData.add(obj);
            } catch (Exception e) {
                Integer rowNum = i + 1;
                RowExcelErrorVo item = new RowExcelErrorVo(rowNum, e.toString());
                ngData.add(item);
            }

        }
        Map result = new HashMap();

        result.put("data", okData);
        result.put("error", ngData);

        return result;
    }
}
