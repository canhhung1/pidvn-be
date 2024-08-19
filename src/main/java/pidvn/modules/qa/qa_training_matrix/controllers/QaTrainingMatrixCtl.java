package pidvn.modules.qa.qa_training_matrix.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.qa.qa_training_matrix.models.TrainingRecordVo;
import pidvn.modules.qa.qa_training_matrix.services.QaTrainingMatrixSvc;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("Qa/TrainingMatrix")
public class QaTrainingMatrixCtl {

    @Autowired
    private QaTrainingMatrixSvc qaTrainingMatrixSvc;

    @PostMapping("InsertByExcel")
    public ResponseEntity<?> insertByExcel(@RequestBody MultipartFile file) {
        return new ResponseEntity<>(this.qaTrainingMatrixSvc.insertByExcel(file), HttpStatus.OK);
    }

    @GetMapping("Courses")
    public ResponseEntity<?> getCourses() {
        return new ResponseEntity<>(this.qaTrainingMatrixSvc.getCourses(), HttpStatus.OK);
    }

    @PostMapping("Records")
    public ResponseEntity<?> getTrainingRecords(@RequestBody TrainingRecordVo trainingRecordVo) {
        Date trainingDate = trainingRecordVo.getTrainingDate();
        Integer courseId = trainingRecordVo.getCourseId();
        return new ResponseEntity<>(this.qaTrainingMatrixSvc.getTrainingRecords(trainingDate, courseId), HttpStatus.OK);
    }

    @PostMapping("TemplateUpload")
    public ResponseEntity<byte[]> downloadTemplateUpload() {

        String fileUrl = "\\\\10.92.152.55\\pvg-data$\\PIDVN-Data\\Public Drive\\IS\\(C) Save File FDCS\\FDCS-Server-2\\QA\\TrainingMatrix\\training_matrix_upload_template.xlsx";

        byte[] file = new byte[0];

        try {
            file = FileUtils.readFileToByteArray(new File(fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(file);
    }

}
