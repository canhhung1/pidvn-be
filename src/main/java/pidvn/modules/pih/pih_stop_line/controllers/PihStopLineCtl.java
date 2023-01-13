package pidvn.modules.pih.pih_stop_line.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.StopTimes;
import pidvn.modules.pih.pih_stop_line.models.SearchVo;
import pidvn.modules.pih.pih_stop_line.services.PihStopLineSvc;

@RestController
@RequestMapping("PIH/StopLine")
public class PihStopLineCtl {

    @Autowired
    private PihStopLineSvc pihStopLineSvc;

    @GetMapping("ProductTypes")
    public ResponseEntity<?> getProductTypes(@RequestParam Integer productId) {
        return new ResponseEntity<>(this.pihStopLineSvc.getProductTypes(productId), HttpStatus.OK);
    }

    @GetMapping("StopTypes")
    public ResponseEntity<?> getStopTypes() {
        return new ResponseEntity<>(this.pihStopLineSvc.getStopTypes(), HttpStatus.OK);
    }

    @GetMapping("StopGroups")
    public ResponseEntity<?> getStopGroups() {
        return new ResponseEntity<>(this.pihStopLineSvc.getStopGroups(), HttpStatus.OK);
    }

    @GetMapping("StopItems")
    public ResponseEntity<?> getStopItems() {
        return new ResponseEntity<>(this.pihStopLineSvc.getStopItems(), HttpStatus.OK);
    }

    @GetMapping("Lines")
    public ResponseEntity<?> getLines() {
        return new ResponseEntity<>(this.pihStopLineSvc.getLines(), HttpStatus.OK);
    }

    @GetMapping("Shifts")
    public ResponseEntity<?> getShifts() {
        return new ResponseEntity<>(this.pihStopLineSvc.getShifts(), HttpStatus.OK);
    }

    @PostMapping("StopTime")
    public ResponseEntity<?> createStopTime(@RequestBody StopTimes stopTime) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.getAuthorities().stream().anyMatch(a -> (a.getAuthority().equals("create_stop_time")) || (a.getAuthority().equals("super_admin")))) {
            throw new Exception("Bạn không có quyền !");
        }

        return new ResponseEntity<>(this.pihStopLineSvc.createStopTime(stopTime), HttpStatus.OK);
    }

    @PutMapping("StopTime")
    public ResponseEntity<?> updateStopTime(@RequestBody StopTimes stopTime) {
        return new ResponseEntity<>(this.pihStopLineSvc.updateStopTime(stopTime), HttpStatus.OK);
    }

    @DeleteMapping("StopTime/{id}")
    public ResponseEntity<?> deleteStopTime(@PathVariable Integer id) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.getAuthorities().stream().anyMatch(a -> (a.getAuthority().equals("delete_stop_time")) || (a.getAuthority().equals("super_admin")))) {
            throw new Exception("Bạn không có quyền !");
        }
        return new ResponseEntity<>(this.pihStopLineSvc.deleteStopTime(id), HttpStatus.OK);
    }

    @PostMapping("StopTimes")
    public ResponseEntity<?> getStopTimes(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.pihStopLineSvc.getStopTimes(searchVo), HttpStatus.OK);
    }
}
