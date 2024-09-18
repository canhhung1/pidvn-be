package pidvn.modules.hr.meal.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.EMealData;
import pidvn.modules.hr.meal.models.EmailDataVo;
import pidvn.modules.hr.meal.models.MealCouponVo;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IHrMealSvc {

    Map getMealRecords(SearchVo searchVo) ;

    List<MealCouponVo> getBalance(Date month);

    Map timesheetConfirm(String table);

    Map getUserSendEmail(MultipartFile file) throws IOException;

    Map sendEmail(List<EmailDataVo> data) throws IOException, MessagingException;
}
