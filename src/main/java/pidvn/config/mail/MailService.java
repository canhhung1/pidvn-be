package pidvn.config.mail;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
