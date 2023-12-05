package pidvn.modules.spare_part.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.spare_part.services.SparePartCronSvc;

@RestController
@RequestMapping("SparePartCron")
public class SparePartCronCtrl {

    @Autowired
    private SparePartCronSvc sparePartCronSvc;

    @GetMapping("InsertDataOutputSparePart")
    public ResponseEntity<?> insertDataOutputSparePart(@RequestParam @Nullable String factory ) {
        return new ResponseEntity<>(this.sparePartCronSvc.insertDataOutputSparePart(), HttpStatus.OK);
    }




}
