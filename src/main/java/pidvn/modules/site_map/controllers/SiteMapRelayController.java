package pidvn.modules.site_map.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pidvn.repositories.one.SiteMapRelayRepo;

@RestController
@RequestMapping("SiteMapRelay")
public class SiteMapRelayController {

    @Autowired
    private SiteMapRelayRepo siteMapRelayRepo;

    @GetMapping("")
    public ResponseEntity<?> findAll(@RequestParam String projectType) {



        Integer type = 0;

        if (projectType.equals("worker")) {
            type = 2;
        }else if (projectType.equals("manager")) {
            type = 1;
        }


        return new ResponseEntity<>(this.siteMapRelayRepo.findByTypeAndStatus(type, 1), HttpStatus.OK);
    }

}
