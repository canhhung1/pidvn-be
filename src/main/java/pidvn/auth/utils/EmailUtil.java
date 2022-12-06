package pidvn.auth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public  void sendMail(String subject, String to, String [] bcc, String body, String personal) throws MessagingException, UnsupportedEncodingException {
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
        System.out.println("======> SEND EMAIL DONE !");

    }
}
