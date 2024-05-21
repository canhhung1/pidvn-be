package pidvn.practice.email.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.practice.email.services.EmailService;

@RestController
@RequestMapping("Practice")
public class EmailController {

    @Autowired
    private EmailService emailService;

}
