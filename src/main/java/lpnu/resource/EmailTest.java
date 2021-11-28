package lpnu.resource;

import lpnu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmailTest {
    @Autowired
    private EmailService emailService;


    public void sendSimpleMessage(String to, String subject, String text){
        emailService.sendSimpleMessage("s.ilchyshyn@ukr.net", "dwagga", "AWagawga");
    }
}
