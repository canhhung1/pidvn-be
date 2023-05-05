package pidvn.modules.pe.std_dummy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.PidvnStdDummy;
import pidvn.modules.pe.std_dummy.services.PeStdDummySvc;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("PE/StdDummy")
public class PeStdDummyCtrl {

    @Autowired
    private PeStdDummySvc peStdDummySvc;

    @GetMapping("Lines")
    public ResponseEntity<?> getLines() {
        List<Integer> productIdList = Arrays.asList(new Integer[] { 5 });
        return new ResponseEntity<>(this.peStdDummySvc.getLines(productIdList), HttpStatus.OK);
    }

    @GetMapping("StdDummies")
    public ResponseEntity<?> getStdDummies() {
        return new ResponseEntity<>(this.peStdDummySvc.getStdDummies(), HttpStatus.OK);
    }

    @PostMapping("StdDummy")
    public ResponseEntity<?> createStdDummy(@RequestBody PidvnStdDummy stdDummy) {
        return new ResponseEntity<>(this.peStdDummySvc.createStdDummy(stdDummy), HttpStatus.OK);
    }

    @DeleteMapping("StdDummy/{id}")
    public ResponseEntity<?> deleteStdDummy(@PathVariable Integer id) {
        return new ResponseEntity<>(this.peStdDummySvc.deleteStdDummy(id), HttpStatus.OK);
    }

    @PutMapping("StdDummies")
    public ResponseEntity<?> updateStdDummies(@RequestBody PidvnStdDummy pidvnStdDummy) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
