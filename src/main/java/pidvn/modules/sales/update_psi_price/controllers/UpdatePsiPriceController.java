package pidvn.modules.sales.update_psi_price.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.sales.update_psi_price.services.UpdatePsiPriceSvc;

import java.io.IOException;

@RestController
@RequestMapping("Sales/UpdatePsiPrice")
public class UpdatePsiPriceController {

    @Autowired
    private UpdatePsiPriceSvc updatePsiPriceSvc;

    @GetMapping("ExportData")
    public ResponseEntity<?> updatePsiPrice() throws IOException {
        return new ResponseEntity<>(this.updatePsiPriceSvc.updatePsiPriceData(), HttpStatus.OK);
    }
}
