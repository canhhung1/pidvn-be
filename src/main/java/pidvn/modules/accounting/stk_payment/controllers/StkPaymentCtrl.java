package pidvn.modules.accounting.stk_payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.PidvnStkPayment;
import pidvn.modules.accounting.stk_payment.services.StkPaymentSvc;

@RestController
@RequestMapping("Accounting/StkPayment")
public class StkPaymentCtrl {

    @Autowired
    private StkPaymentSvc stkPaymentSvc;

    @GetMapping("StkPayments")
    public ResponseEntity<?> getStkPayments() {
        return new ResponseEntity<>(this.stkPaymentSvc.getStkPayments(), HttpStatus.OK);
    }

    @PostMapping("StkPayment")
    public ResponseEntity<?> createStkPayment(@RequestBody PidvnStkPayment stkPayment) {
        return new ResponseEntity<>(this.stkPaymentSvc.createStkPayment(stkPayment), HttpStatus.OK);
    }

    @PutMapping("StkPayment")
    public ResponseEntity<?> updateStkPayment(@RequestBody PidvnStkPayment stkPayment) {
        return new ResponseEntity<>(this.stkPaymentSvc.updateStkPayment(stkPayment), HttpStatus.OK);
    }
    

}
