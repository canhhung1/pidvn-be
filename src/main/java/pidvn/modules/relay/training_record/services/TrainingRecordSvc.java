package pidvn.modules.relay.training_record.services;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.mappers.one.relay.training_record.TrainingRecordMapper;
import pidvn.modules.relay.training_record.models.*;
import pidvn.repositories.one.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingRecordSvc implements ITrainingRecordSvc {

    @Autowired
    private TrainingRecordMasterRepo trainingRecordMasterRepo;

    @Autowired
    private TrainingRecordDetailRepo trainingRecordDetailRepo;

    @Autowired
    private TrainingRecordTypeRepo trainingRecordTypeRepo;

    @Autowired
    private TrainingRecordCourseRepo trainingRecordCourseRepo;

    @Autowired
    private TrainingRecordMapper trainingRecordMapper;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public List<UserVo> getUsers() {
        return this.trainingRecordMapper.getUsers();
    }

    @Override
    public TrainingRecordMaster createTrainingRecordMaster(TrainingRecordMaster master) {
        return this.trainingRecordMasterRepo.save(master);
    }

    @Override
    public List<TrainingRecordMasterVo> getTrainingRecordMaster(TrainingRecordMasterVo masterVo) {
        return this.trainingRecordMapper.getTrainingRecordMaster(masterVo);
    }

    @Override
    public TrainingRecordDetail saveTrainingRecordDetail(TrainingRecordDetail detail, Integer master, String type, String scoreOfPass) {
        detail.setTrainingRecordMaster(master);

        if (type.equals("remove")) {
            this.trainingRecordDetailRepo.deleteById(detail.getId());
            return detail;
        } else {
            detail.setTrainingRecordMaster(master);

            if (!scoreOfPass.equals("null")) {
                if (detail.getScore() != null && detail.getScore() < Integer.parseInt(scoreOfPass)) {
                    detail.setTestResult("FAIL");
                }else if (detail.getScore() != null && detail.getScore() >= Integer.parseInt(scoreOfPass)){
                    detail.setTestResult("PASS");
                }
            }
        }

        return this.trainingRecordDetailRepo.save(detail);
    }

    @Override
    public List<TrainingRecordDetailVo> getTrainingRecordDetail(TrainingRecordDetailVo searchVo) {
        return this.trainingRecordMapper.getTrainingRecordDetail(searchVo);
    }

    @Override
    public List<TrainingRecordType> getTrainingRecordType() {
        return this.trainingRecordTypeRepo.findAll();
    }

    @Override
    public TrainingRecordMaster approveTrainingRecord(Integer master, String username) {
        TrainingRecordMaster obj = this.trainingRecordMasterRepo.getOne(master);
        obj.setApprovalBy(username);
        return this.trainingRecordMasterRepo.save(obj);
    }

    @Override
    public List<TrainingRecordDetail> uploadUsers(MultipartFile file, Integer master) throws IOException {

        List<TrainingRecordDetail> detailList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);


        // TODO
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 1; j++) {
                TrainingRecordDetail obj = new TrainingRecordDetail();
                obj.setUsername(String.valueOf(row.getCell(j).getRawValue()));
                obj.setTrainingRecordMaster(master);
                obj.setAttendance("NO");
                detailList.add(obj);
            }
        }

        return this.trainingRecordDetailRepo.saveAll(detailList);
    }

    @Override
    public Users attendanceUser(TrainingRecordDetailVo detailVo) throws Exception {

        List<TrainingRecordDetail> details = this.trainingRecordDetailRepo.findByUsernameAndTrainingRecordMaster(detailVo.getUsername(), detailVo.getTrainingRecordMaster());
        if (details.size() <= 0) {
            throw new Exception("User " + detailVo.getUsername() + " không trong danh sách");
        }

        TrainingRecordDetail obj = details.get(0);
        obj.setAttendance("YES");
        this.trainingRecordDetailRepo.save(obj);

        return this.usersRepo.findByUsername(obj.getUsername());
    }

    @Override
    public List<TRCourseVo> getCourses() {
        return this.trainingRecordMapper.getCourses();
    }

    /**
     * CRUD training_record_course table
     * @param courseVo thông tin khóa học
     * @param action hành động: "insert", "update", "remove"
     * @return
     */
    @Override
    public TrainingRecordCourse saveCourse(TRCourseVo courseVo, String action) {
        TrainingRecordCourse result = null;
        switch (action) {
            case "insert" :
                result = this.insertCourse(courseVo);
                break;
            case "update" :
                result = this.updateCourse(courseVo);
                break;
            case "remove" :
                result = this.removeCourse(courseVo);
                break;
        }

        return result;
    }

    private TrainingRecordCourse insertCourse(TRCourseVo courseVo) {
        TrainingRecordCourse course = new TrainingRecordCourse();
        course.setName(courseVo.getName());
        course.setType(courseVo.getType());
        return this.trainingRecordCourseRepo.save(course);
    }

    private TrainingRecordCourse updateCourse(TRCourseVo courseVo) {
        TrainingRecordCourse course = this.trainingRecordCourseRepo.getById(courseVo.getId());
        course.setName(courseVo.getName());
        course.setType(courseVo.getType());
        return this.trainingRecordCourseRepo.save(course);
    }

    private TrainingRecordCourse removeCourse(TRCourseVo courseVo) {
        TrainingRecordCourse course = this.trainingRecordCourseRepo.findById(courseVo.getId()).get();
        this.trainingRecordCourseRepo.deleteById(courseVo.getId());
        return course;
    }

    /**
     * CRUD training_record_master table
     * @param masterVo
     * @param action
     * @return
     */
    @Override
    public TrainingRecordMaster saveTrainingRecordMaster(TrainingRecordMasterVo masterVo, String action) {
        TrainingRecordMaster result = null;
        switch (action) {
            case "insert" :
                result = this.insertTrainingRecordMaster(masterVo);
                break;
            case "update" :
                result = this.updateTrainingRecordMaster(masterVo);
                break;
            case "remove" :
                break;
        }

        return result;
    }

    @Override
    public List<HistoriesVo> getHistories() {
        return this.trainingRecordMapper.getHistories();
    }

    private TrainingRecordMaster insertTrainingRecordMaster(TrainingRecordMasterVo masterVo) {
        TrainingRecordMaster master = new TrainingRecordMaster();
        master.setCourse(masterVo.getCourse());
        master.setTrainer(masterVo.getTrainer());
        master.setDate(masterVo.getDate());
        master.setScoreOfMax(masterVo.getScoreOfMax());
        master.setScoreOfPass(masterVo.getScoreOfPass());

        return this.trainingRecordMasterRepo.save(master);
    }

    private TrainingRecordMaster updateTrainingRecordMaster(TrainingRecordMasterVo masterVo) {
        TrainingRecordMaster master = this.trainingRecordMasterRepo.findById(masterVo.getId()).get();
        master.setCourse(masterVo.getCourse());
        master.setTrainer(masterVo.getTrainer());
        master.setScoreOfMax(masterVo.getScoreOfMax());
        master.setScoreOfPass(masterVo.getScoreOfPass());
        master.setDate(masterVo.getDate());
        return this.trainingRecordMasterRepo.save(master);
    }

}
