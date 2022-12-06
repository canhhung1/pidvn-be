package pidvn.modules.schedule_task.e_leave.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pidvn.auth.services.AuthService;
import pidvn.entities.one.PidvnRouterLeave;
import pidvn.mappers.one.hr.e_leave.ELeaveMapper;
import pidvn.modules.schedule_task.e_leave.models.EleaveVo;
import pidvn.modules.schedule_task.e_leave.models.EmailVo;
import pidvn.repositories.one.PidvnRouterLeaveRepo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ELeaveCron {

    Logger logger = LoggerFactory.getLogger(ELeaveCron.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ELeaveMapper eLeaveMapper;

    @Autowired
    private AuthService authService;

    /**
     * Thông báo phê duyệt request cần approve
     * Chạy cron vào ngày 1 và 4 hàng tháng vào lúc 6h sáng
     *
     * @throws Exception
     */
    //@Scheduled(cron = "0 0 6 1,4 * *")
    public void sendEmailInformRequest() throws Exception {

        logger.debug("EMAIL THÔNG BÁO CẦN APPROVE REQUEST");


        // Danh sách email cần thông báo
        List<EmailVo> emails = this.eLeaveMapper.getEmailInform();

        List<EmailVo> listEmailToSend = new ArrayList<>();

        for (EmailVo email : emails) {

            EmailVo obj1 = new EmailVo();
            obj1.setEmail(email.getEmail());
            String token = this.authService.generateJWT(email.getUsername()).get("token").toString();
            obj1.setContent(this.buildContentInformRequest(email.getType(), "email", token));
            if (email.getRn() == 1) {
                listEmailToSend.add(obj1);
            }

            EmailVo obj2 = new EmailVo();
            obj2.setEmail(email.getCcEmail());
            obj2.setContent(this.buildContentInformRequest(email.getType(), "ccEmail", null));
            listEmailToSend.add(obj2);
        }


        logger.debug("START PROCESS SEND EMAIL INFORM REQUEST");

        for (EmailVo item : listEmailToSend) {
            logger.debug(item.toString());
            this.sendMail("PIDVN thông báo", item.getEmail(), null, item.getContent(), "PIDVN");
        }

        logger.debug("END PROCESS SEND EMAIL INFORM REQUEST");
    }

    /**
     * Gửi email thông báo tự động reject nếu cấp trên không phê duyệt
     * chạy vào ngày 5 hàng tháng vào lúc 23h
     */
    //@Scheduled(cron = "0 0 23 5 * *")
    public void sendEmailAutoReject() {
        // TODO
    }

    /**
     * Gửi email thông báo cho các user bị lỗi Checkin Checkout
     * Chạy hàng ngày vào lúc 15:00
     */
    //@Scheduled(cron = "0 0 15 * * *")
    public void sendEmailInformErrorInOut() throws Exception {

        int count = 0;

        logger.debug("START SEND EMAIL IN / OUT");

        // B1: Get user miss inout
        List<EleaveVo> results = this.getUserMissInOut();

        // B2: Send email
        for (EleaveVo item : results) {
            String content = this.buildContentEmailMissInOut(item);
            this.sendMail("Thông báo thiếu dữ liệu quẹt thẻ", item.getEmail(), null, content, "PIDVN");
            logger.debug("SEND EMAIL TO: " + item.getCodeEmp());
            count++;
        }

        logger.debug("END SEND EMAIL IN / OUT SUCCESS : Qty = " + count + "(email)");
    }


    // Lấy các user bị miss checkin checkout
    private List<EleaveVo> getUserMissInOut() throws Exception {
        List<EleaveVo> results = this.eLeaveMapper.getUserMissInOut();
        return results;
    }

    private String buildContentEmailMissInOut(EleaveVo item) throws Exception {

        String token = this.authService.generateJWT(item.getCodeEmp()).get("token").toString();

        String linkInOut = "<a href='http://10.92.176.57:6969/Leave/Handle/confirm_in/request?accessToken=" + token + "'>Click here to view form </a>";

        String linkEleave = "<a href='http://10.92.176.57:6969/Leave/Handle/leave/request?accessToken=" + token + "'>Click here to view form </a>";

        StringBuilder content = new StringBuilder();
        content.append("Xin chào Anh/ Chị: <br />");
        content.append("Dữ liệu quẹt thẻ ngày công <b>").append(item.getDate()).append("</b> của Anh/ Chị bị lỗi như sau: <br />");
        content.append("Name: <b>").append(item.getProfileName()).append(" - ").append(item.getCodeEmp()).append("</b><br />");
        content.append("Ca làm việc: <b>").append(item.getShift()).append("</b><br />");
        content.append(item.getTime());
        content.append("<br />Trường hợp sai ca làm việc, vui lòng liên hệ Quản lý hoặc GA bộ phận để xác nhận lại ca làm việc <br />");

        if (item.getEmail().indexOf("@vn.panasonic.com") != -1) {
            content.append("-\tHãy bấm vào link bên dưới để xác nhận giờ in/ out trong trường hợp lỗi thẻ/ quên thẻ <br />");
            content.append(linkInOut).append("<br />");
            content.append("Thời hạn làm xác nhận: trong vòng 05 ngày từ ngày lỗi dữ liệu quẹt thẻ <br />");
            content.append("-\tHãy bấm vào link bên dưới để đăng ký nghỉ trong trường hợp chưa đăng ký nghỉ <br />");
            content.append(linkEleave).append("<br />");
        } else {
            content.append("-\tHãy vào hệ thống để làm xác nhận <br />");
        }

        content.append("# Đây là email tự động từ hệ thống, vui lòng không trả lời lại");

        return content.toString();
    }

    /**
     * Nội dung cho các email thông báo request sắp hết hạn, cần được approve
     *
     * @param type
     * @return
     */
    private String buildContentInformRequest(String type, String emailType, String token) {


        YearMonth thisMonth = YearMonth.now();
        YearMonth lastMonth = thisMonth.minusMonths(1);
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");

        String month = lastMonth.format(monthYearFormatter);

        StringBuilder content = new StringBuilder();
        // TH1: type == 'leave'
        if (type.equals("leave")) {

            String link = "<a href='http://10.92.176.57:6969/Leave/Handle/leave/approval?accessToken=" + token + "'>Click here to approval</a> <br />";

            if (emailType.equals("email")) {
                content.append("You have leave request in ");
                content.append(month);
                content.append(" pending to be approved. Please approval before the 5th of this month. <br />");
                content.append("If it is not approved on time, the system will automatically reject. <br />");
                content.append("Note: This month's leave can only be approved until the 5th of the next month, if it is not approved on time, the system will automatically reject. <br />");
                content.append(link);
                content.append("# Please do not reply to this message as it was sent from an unattended mailbox. <br />");
                content.append("-------------------------------------------------------------------------------- <br />");
                content.append("Bạn có yêu cầu xin nghỉ trong tháng ");
                content.append(month);
                content.append(" đang chờ được phê duyệt. Hãy phê duyệt trước mùng 5 tháng này. <br />");
                content.append("Nếu không phê duyệt đúng thời gian, hệ thống sẽ tự động từ chối yêu cầu. <br />");
                content.append("Lưu ý: Ngày nghỉ của tháng này chỉ được gia hạn phê duyệt đến ngày 05 của tháng sau, nếu không phê duyệt đúng hạn hệ thống sẽ tự động từ chối <br />");
                content.append(link);
                content.append("# Đây là email tự động từ hệ thống, vui lòng không trả lời lại. <br />");
                return content.toString();
            }

            if (emailType.equals("ccEmail")) {
                content.append("Bạn có yêu cầu xin nghỉ trong tháng ");
                content.append(month);
                content.append(" đang chưa được phê duyệt. Hãy thông báo với người quản lý phê duyệt trước mùng 5 tháng này. <br />");
                content.append("Nếu không phê duyệt đúng thời gian, hệ thống sẽ tự động từ chối yêu cầu. <br />");
                content.append("Lưu ý: Ngày nghỉ của tháng này chỉ được gia hạn phê duyệt đến ngày 05 của tháng sau, nếu không phê duyệt đúng hạn hệ thống sẽ tự động từ chối. <br />");
                content.append("# Đây là email tự động từ hệ thống, vui lòng không trả lời lại. <br />");
                return content.toString();
            }
        }

        // TH2: type == 'inout'
        if (type.equals("inout")) {
            String link = "<a href='http://10.92.176.57:6969/Leave/Handle/confirm_in/approval?accessToken=" + token + "'>Click here to approval</a> <br />";

            if (emailType.equals("email")) {
                content.append("You have in/out request in ");
                content.append(month);
                content.append(" pending to be approved. Please approval before the 5th of this month. <br />");
                content.append("If it is not approved on time, the system will automatically reject. <br />");
                content.append("Note: This month's leave can only be approved until the 5th of the next month, if it is not approved on time, the system will automatically reject <br />");
                content.append(link);
                content.append("# Please do not reply to this message as it was sent from an unattended mailbox. <br />");
                content.append("-------------------------------------------------------------------------------- <br />");
                content.append("Bạn có yêu cầu xác nhận quẹt thẻ trong tháng ");
                content.append(month);
                content.append(" đang chờ được phê duyệt. Hãy phê duyệt trước mùng 5 tháng này. <br />");
                content.append("Nếu không phê duyệt đúng thời gian, hệ thống sẽ tự động từ chối yêu cầu. <br />");
                content.append("Lưu ý: Ngày nghỉ của tháng này chỉ được gia hạn phê duyệt đến ngày 05 của tháng sau, nếu không phê duyệt đúng hạn hệ thống sẽ tự động từ chối <br />");
                content.append(link);
                content.append("# Đây là email tự động từ hệ thống, vui lòng không trả lời lại. <br />");

                return content.toString();
            }

            if (emailType.equals("ccEmail")) {
                content.append("Bạn có yêu cầu xác nhận quẹt thẻ trong tháng ");
                content.append(month);
                content.append(" đang chưa được phê duyệt. Hãy thông báo với người quản lý phê duyệt trước mùng 5 tháng này. <br />");
                content.append("Nếu không phê duyệt đúng thời gian, hệ thống sẽ tự động hủy yêu cầu. <br />");
                content.append("Lưu ý: Ngày nghỉ của tháng này chỉ được gia hạn phê duyệt đến ngày 05 của tháng sau, nếu không phê duyệt đúng hạn hệ thống sẽ tự động từ chối <br />");
                content.append("# Đây là email tự động từ hệ thống, vui lòng không trả lời lại. <br />");
                return content.toString();
            }

        }


        return content.toString();
    }


    public void sendMail(String subject, String to, String[] bcc, String body, String personal) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("it.pidvn", personal);
        helper.setSubject(subject);
        helper.setTo(to);

        if (bcc != null) {
            helper.setBcc(bcc);
        }
        helper.setText(body, true);
        this.mailSender.send(message);

        logger.debug("======> SEND EMAIL DONE !");

    }


    @Autowired
    private PidvnRouterLeaveRepo pidvnRouterLeaveRepo;

    /**
     * Cron chạy ngày 5 hàng tháng lúc 23h tối
     * Reject các request chưa được approve
     */
    //@Scheduled(cron = "0 0 23 5 * *")
    public void autoRejectRequest() throws MessagingException, UnsupportedEncodingException {

        logger.debug("Reject Request chua Approve");

        // B1: Get data
        List<EmailVo> emails = this.eLeaveMapper.getEmailRejectRequest();

        // B2: thực hiện update rồi gửi email
        for (EmailVo email : emails) {

            System.out.println("UPDATE TABLE");

            // 1. Update table
            PidvnRouterLeave routerLeave = this.pidvnRouterLeaveRepo.findById(email.getStt()).get();
            if (routerLeave == null) {
                continue;
            }

            routerLeave.setStatus("Rejected");
            routerLeave.setComment("Rejected by E-leave System");

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            routerLeave.setUpdatetime(dateFormat.format(date));

            this.pidvnRouterLeaveRepo.save(routerLeave);

            // 2. Send email
            String subject = "[Reject ] " + "Request [" + email.getLeaveId() + "] is rejected";
            this.sendMail(subject, email.getEmail(), null, this.buildContentRejectRequest(email.getLeaveId(), email.getType()), null);
        }
    }

    /**
     * Nội dung cho email reject request
     *
     * @param leaveId
     * @param type
     * @return
     */
    private String buildContentRejectRequest(String leaveId, String type) {

        String requestType = leaveId.split("-")[0];
        String link = "";
        if (type.equals("inout")) {
            link = "<a href='http://10.92.176.57:6969/Leave/Handle/confirm_in/request_his?pid=" + leaveId + "'>Click here to view form</a> <br />";
        } else if (type.equals("leave")) {
            link = "<a href='http://10.92.176.57:6969/Leave/Handle/leave/request_his?pid=" + leaveId + "'>Click here to view form</a> <br />";
        }

        StringBuilder content = new StringBuilder();

        content.append("The following request " + requestType + " Application have been rejected.<br />" +
                "Please click on the following link <br />" + link + "<br />" +
                "Note: This month's leave can only be approved until the 5th of the next month, if it is not approved on time, the system will automatically reject <br />" +
                "# Please do not reply to this message as it was sent from an unattended mailbox. <br />" +
                "-------------------------------------------------------------------------------- <br />" +
                "Xin chào a/c: <br />" +
                "Yêu cầu xin nghỉ " + requestType + " đã bị từ chối <br />" +
                "Hãy bấm vào link bên dưới để xem chi tiết  <br />" + link + "<br />" +
                "Lưu ý: Ngày nghỉ của tháng này chỉ được gia hạn phê duyệt đến ngày 05 của tháng sau, nếu không phê duyệt đúng hạn hệ thống sẽ tự động từ chối <br />" +
                "# Đây là email tự động từ hệ thống, vui lòng không trả lời lại.");

        return content.toString();
    }

}
