package pidvn.modules.ie.drawing_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.config.mail.DataMailDTO;
import pidvn.config.mail.MailService;
import pidvn.entities.one.IeDrawing;
import pidvn.entities.one.IeProject;
import pidvn.modules.ie.drawing_management.models.SearchVo;
import pidvn.modules.ie.drawing_management.services.DrawingMngSvc;
import reactor.util.annotation.Nullable;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("IE/DrawingManagement")
public class DrawingMngCtl {

    @Autowired
    private MailService mailService;


    @Autowired
    private DrawingMngSvc drawingMngSvc;

    /**
     * API lấy danh sách các project
     * @param searchVo thông tin tìm kiếm project
     * @return list project
     */
    @PostMapping("Projects")
    public ResponseEntity<?> getProjects(@RequestBody @Nullable SearchVo searchVo) {
        return new ResponseEntity<>(this.drawingMngSvc.getProjects(searchVo), HttpStatus.OK);
    }

    /**
     * Api create project
     */
    @PostMapping("Project")
    public ResponseEntity<?> createProject() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * Api update project
     * @param ieProject obj
     */
    @PutMapping("Project")
    public ResponseEntity<?> updateProject(@RequestBody IeProject ieProject) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }




    @PostMapping("Drawing")
    public ResponseEntity<?> createDrawing(@RequestBody IeDrawing ieDrawing) {
        return new ResponseEntity<>(this.drawingMngSvc.saveIeDrawing(ieDrawing), HttpStatus.OK);
    }

    @GetMapping("Drawings")
    public ResponseEntity<?> getIeDrawings(@RequestParam Integer projectId, @RequestParam Integer progressId) {
        return new ResponseEntity<>(this.drawingMngSvc.getIeDrawings(projectId,progressId), HttpStatus.OK);
    }




    @PostMapping("Processes/{projectId}")
    public ResponseEntity<?> getProgress(@PathVariable Integer projectId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }






























    /*@GetMapping("Mail")
    public ResponseEntity<?> testEmail() {
        Map result = new HashMap();
        try {
            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo("canhhung.nguyen1@gmail.com");
            dataMail.setSubject("Test Send Email Spring boot");

            Map<String, Object> props = new HashMap<>();
            props.put("name", "Nguyễn Cảnh Hưng");
            props.put("username", "hungnc225.it");
            props.put("password", "pwd@123");
            dataMail.setProps(props);
            mailService.sendHtmlMail(dataMail, "client");

            result.put("result","Send OK");
        } catch (MessagingException e) {
            result.put("result","Send ERROR");
            result.put("error",e.toString());
            throw new RuntimeException(e);
        }


        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/


}
