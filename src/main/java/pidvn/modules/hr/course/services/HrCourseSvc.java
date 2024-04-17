package pidvn.modules.hr.course.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.HrCourseGroup;
import pidvn.entities.one.HrCourseHistory;
import pidvn.entities.one.Users;
import pidvn.mappers.one.hr.course.CourseMapper;
import pidvn.modules.hr.course.models.CourseVo;
import pidvn.modules.hr.course.models.CourseHistoryVo;
import pidvn.repositories.one.HrCourseGroupsRepo;
import pidvn.repositories.one.HrCourseHistoryRepo;
import pidvn.repositories.one.UsersRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HrCourseSvc implements IHrCourseSvc{

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private HrCourseGroupsRepo hrCourseGroupsRepo;

    @Autowired
    private HrCourseHistoryRepo hrCourseHistoryRepo;

    @Override
    public List<CourseVo> getCourse(CourseVo searchVo) {
        List<CourseVo> courses = this.courseMapper.getCourse(searchVo);
        return courses;

    }

    @Override
    public List<Users> getUsers() {
        return this.usersRepo.findAll();
    }

    @Override
    public List<HrCourseGroup> getCourseGroups() {
        return this.hrCourseGroupsRepo.findAll();
    }

    @Override
    public List<CourseHistoryVo> getCourseHistories() {
        return this.courseMapper.getCourseHistories();
    }

    @Override
    public Map uploadTrainingRecordData(MultipartFile file) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheet("Data");

        List<HrCourseHistory> arr = new ArrayList<>();

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            try {
                XSSFRow row = sheet.getRow(i);
                HrCourseHistory obj = new HrCourseHistory();
                obj.setUsername(row.getCell(0).getStringCellValue());
                obj.setTrainingCourse(row.getCell(4).getStringCellValue());
                obj.setTrainingDate(row.getCell(5).getDateCellValue());
                arr.add(obj);
            }catch (Exception e) {
                System.out.println("ERROR: " + e);
            }
        }

         List<HrCourseHistory> savedData = this.hrCourseHistoryRepo.saveAll(arr);

        Map result = new HashMap();
        result.put("savedData", savedData);

        return result;
    }
}
