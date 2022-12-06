package pidvn.modules.relay.training_record.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.TrainingRecordDetail;
import pidvn.entities.one.TrainingRecordMaster;
import pidvn.modules.relay.training_record.models.TRCourseVo;
import pidvn.modules.relay.training_record.models.TrainingRecordDetailVo;
import pidvn.modules.relay.training_record.models.TrainingRecordMasterVo;
import pidvn.modules.relay.training_record.services.TrainingRecordSvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("Relay/TrainingRecord")
public class TrainingRecordController {

    @Autowired
    private TrainingRecordSvc trainingRecordSvc;

    @PostMapping("Masters")
    public ResponseEntity<?> getTrainingRecordMaster(@RequestBody TrainingRecordMasterVo masterVo) {
        return new ResponseEntity<>(this.trainingRecordSvc.getTrainingRecordMaster(masterVo), HttpStatus.OK);
    }

    @PostMapping("Master")
    public ResponseEntity<?> saveTrainingRecordMaster(@RequestBody TrainingRecordMasterVo masterVo, @RequestParam String action) {
        return new ResponseEntity<>(this.trainingRecordSvc.saveTrainingRecordMaster(masterVo, action), HttpStatus.OK);
    }

    @PostMapping("Details")
    public ResponseEntity<?> getTrainingRecordDetail(@RequestBody TrainingRecordDetailVo searchVo) {
        return new ResponseEntity<>(this.trainingRecordSvc.getTrainingRecordDetail(searchVo), HttpStatus.OK);
    }

    @PostMapping("Detail")
    public ResponseEntity<?> saveTrainingRecordDetail(@RequestBody TrainingRecordDetail detail, @RequestParam Integer master, @RequestParam String type, @RequestParam String scoreOfPass) {
        return new ResponseEntity<>(this.trainingRecordSvc.saveTrainingRecordDetail(detail, master, type, scoreOfPass), HttpStatus.OK);
    }

    @PostMapping("Approval")
    public ResponseEntity<?> approveTrainingRecord(@RequestParam Integer master, @RequestParam String username) {
        return new ResponseEntity<>(this.trainingRecordSvc.approveTrainingRecord(master, username), HttpStatus.OK);
    }

    @GetMapping("Types")
    public ResponseEntity<?> getTrainingRecordType() {
        return new ResponseEntity<>(this.trainingRecordSvc.getTrainingRecordType(), HttpStatus.OK);
    }

    @GetMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.trainingRecordSvc.getUsers(), HttpStatus.OK);
    }

    @PostMapping("UploadUsers")
    public ResponseEntity<?> uploadUsers(@RequestBody MultipartFile file, @RequestParam String master) throws IOException {
        return new ResponseEntity<>(this.trainingRecordSvc.uploadUsers(file, Integer.parseInt(master)), HttpStatus.OK);
    }

    @PostMapping("Attendance")
    public ResponseEntity<?> attendanceUser(@RequestBody TrainingRecordDetailVo searchVo) throws Exception {
        return new ResponseEntity<>(this.trainingRecordSvc.attendanceUser(searchVo), HttpStatus.OK);
    }

    @PostMapping("Course")
    public ResponseEntity<?> saveCourse(@RequestBody (required=false) TRCourseVo courseVo, @RequestParam String action) {
        return new ResponseEntity<>(this.trainingRecordSvc.saveCourse(courseVo, action), HttpStatus.OK);
    }

    @GetMapping("Courses")
    public ResponseEntity<?> getCourses() {
        return new ResponseEntity<>(this.trainingRecordSvc.getCourses(), HttpStatus.OK);
    }

    @GetMapping("Histories")
    public ResponseEntity<?> getHistories() {
        return new ResponseEntity<>(this.trainingRecordSvc.getHistories(), HttpStatus.OK);
    }




    @GetMapping(value = "GuideLines")
    public Object guideLines() {
        File file = new File("P:\\MA\\CanhHung\\TrainingRecord\\Guide.pdf");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
