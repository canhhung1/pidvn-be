package pidvn.modules.hr.meal.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pidvn.auth.controller.AuthController;
import pidvn.entities.one.HrAttendanceDetail;
import pidvn.entities.one.HrLeaveDay;
import pidvn.entities.one.HrMealRecord;
import pidvn.entities.one.HrOvertimeData;
import pidvn.mappers.one.hr.e_meal.HrEMealMapper;
import pidvn.mappers.three.hr.meal.HrMealMapper;
import pidvn.modules.hr.meal.models.EmailDataVo;
import pidvn.modules.hr.meal.models.MealCouponVo;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.practice.email.services.EmailService;
import pidvn.repositories.one.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class HrMealSvc implements IHrMealSvc {

    private static final Logger LOGGER = LoggerFactory.getLogger(HrMealSvc.class);

    @Autowired
    private HrMealMapper hrMealMapper;

    @Autowired
    private HrEMealMapper hrEMealMapperDB1;

    @Autowired
    private EMealDataRepo eMealDataRepo;

    @Autowired
    private HrAttendanceDetailRepo hrAttendanceDetailRepo;

    @Autowired
    private HrOvertimeDataRepo hrOvertimeDataRepo;

    @Autowired
    private HrMealRecordRepo hrMealRecordRepo;

    @Autowired
    private HrLeaveDayRepo hrLeaveDayRepo;




    @Override
    public Map getMealRecords(SearchVo searchVo) {
        Map result = new HashMap();

        List<MealRecordVo> records = this.hrMealMapper.getMealRecords(searchVo);
        List<MealRecordVo> recordsSummary = this.hrMealMapper.getMealRecordsSummary(searchVo);

        result.put("records", records);
        result.put("recordsSummary", recordsSummary);

        return result;
    }

    @Override
    public List<MealCouponVo> getBalance(Date month) {
        return this.hrEMealMapperDB1.getMealCouponData(month);
    }


    @Override
    @Transactional(transactionManager = "transactionManagerOne")
    public Map timesheetConfirm(String table) {

        Map result = new HashMap();

        try {
            if (table.equals("attendance")) {
                // Lấy data từ PVG
                List<HrAttendanceDetail> attendanceDetails = this.hrMealMapper.getAttendanceDetails();

                // Xóa data
                this.hrAttendanceDetailRepo.deleteAttendanceDetailsPreviousMonth();

                // Lưu data vào MySQL
                List<HrAttendanceDetail> attendanceDetailResult = this.hrAttendanceDetailRepo.saveAll(attendanceDetails);

                result.put("table", "attendance");
                result.put("record", attendanceDetailResult.size());
            }

            if (table.equals("overtime")) {
                // Lấy data từ PVG
                List<HrOvertimeData> overtimeData = this.hrMealMapper.getOvertimeData();

                // Xóa data
                this.hrOvertimeDataRepo.deleteOvertimeDataPreviousMonth();

                // Lưu data vào MySQL
                List<HrOvertimeData> overtimeResult = this.hrOvertimeDataRepo.saveAll(overtimeData);
                result.put("table", "overtime");
                result.put("record", overtimeResult.size());
            }

            if (table.equals("meal_record")) {
                // Lấy data từ PVG
                List<HrMealRecord> mealRecords = this.hrMealMapper.getHrMealRecord();

                // Xóa data
                this.hrMealRecordRepo.deleteMealRecordsPreviousMonth();

                // Lưu data vào MySQL
                List<HrMealRecord> mealRecordResult = this.hrMealRecordRepo.saveAll(mealRecords);

                result.put("table", "meal_record");
                result.put("record", mealRecordResult.size());
            }

            if(table.equals("leave_day")) {
                // Lấy data từ PVG
                List<HrLeaveDay> leaveDays = this.hrMealMapper.getHrLeaveDay();

                // Xóa data
                this.hrLeaveDayRepo.deleteLeaveDayPreviousMonth();

                // Lưu data vào MySQL
                List<HrLeaveDay> leaveDaysResult = this.hrLeaveDayRepo.saveAll(leaveDays);

                result.put("table", "leave_day");
                result.put("record", leaveDaysResult.size());
            }
        }catch (Exception e) {
            LOGGER.debug("GET DATA TABLE : " + table);
            LOGGER.debug("ERROR GET DATA TABLE : " + e.toString());
        }



        return result;
    }

    @Override
    public Map getUserSendEmail(MultipartFile file) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        int cellTienChenhLech = 12;

        List<EmailDataVo> data = new ArrayList<>();

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

            try {

                XSSFRow row = sheet.getRow(i);

                if (row.getCell(cellTienChenhLech) == null) {
                    continue;
                }

                if (row.getCell(cellTienChenhLech).getNumericCellValue() < 0) {

                    EmailDataVo obj = new EmailDataVo();
                    obj.setUsername(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());
                    obj.setFullName(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());
                    obj.setEmail(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());
                    obj.setVeChinhThucTe(row.getCell(4) == null ? 0 : (int) row.getCell(4).getNumericCellValue());
                    obj.setVeChinhTimesheet(row.getCell(5) == null ? 0 : (int) row.getCell(5).getNumericCellValue());
                    obj.setVeChinhChenhLech(row.getCell(6) == null ? 0 : (int) row.getCell(6).getNumericCellValue());
                    obj.setVePhuThucTe(row.getCell(7) == null ? 0 : (int) row.getCell(7).getNumericCellValue());
                    obj.setVePhuTimesheet(row.getCell(8) == null ? 0 : (int) row.getCell(8).getNumericCellValue());
                    obj.setVePhuChenhLech(row.getCell(9) == null ? 0 : (int) row.getCell(9).getNumericCellValue());
                    obj.setTienVeChinh(row.getCell(10) == null ? 0 : (int) row.getCell(10).getNumericCellValue());
                    obj.setTienVePhu(row.getCell(11) == null ? 0 : (int) row.getCell(11).getNumericCellValue());
                    obj.setTienChenhLech(row.getCell(12) == null ? 0 : (int) row.getCell(12).getNumericCellValue());

                    data.add(obj);
                }

            } catch (Exception e) {
                System.out.printf("Error ROW:  " + i);
                System.out.println(e.getMessage());
            }

        }

        Map result = new HashMap();
        result.put("data", data);

        return result;

    }


    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public Map sendEmail(List<EmailDataVo> data) throws IOException, MessagingException {

        for (EmailDataVo obj : data) {
            this.passDataIntoMailTemplate(obj);
        }

        Map result = new HashMap();
        result.put("data", data);
        return result;
    }


    public void passDataIntoMailTemplate(EmailDataVo emailDataVo) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("it.pidvn", "PIDVN");
        helper.setTo(emailDataVo.getEmail());
        helper.setSubject("THÔNG TIN XÁC NHẬN TRỪ VÉ ĂN ");

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("user", emailDataVo);
        Context context = new Context();
        context.setVariables(templateModel);
        String html = templateEngine.process("client", context);
        helper.setText(html, true);
        mailSender.send(message);
    }


}
