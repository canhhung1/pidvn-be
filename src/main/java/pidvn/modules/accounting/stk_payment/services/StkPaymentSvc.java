package pidvn.modules.accounting.stk_payment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.PidvnStkPayment;
import pidvn.repositories.one.PidvnStkPaymentRepo;

import java.util.List;

@Service
public class StkPaymentSvc implements IStkPaymentSvc {

    @Autowired
    private PidvnStkPaymentRepo pidvnStkPaymentRepo;

    @Override
    public List<PidvnStkPayment> getStkPayments() {
        return this.pidvnStkPaymentRepo.findAll();
    }

    @Override
    public PidvnStkPayment createStkPayment(PidvnStkPayment pidvnStkPayment) {
        return this.pidvnStkPaymentRepo.save(pidvnStkPayment);
    }

    @Override
    public PidvnStkPayment updateStkPayment(PidvnStkPayment pidvnStkPayment) {

        PidvnStkPayment stkPayment = this.pidvnStkPaymentRepo.findById(pidvnStkPayment.getId()).get();

        stkPayment.setType(pidvnStkPayment.getType());
        stkPayment.setCode(pidvnStkPayment.getCode());
        stkPayment.setName(pidvnStkPayment.getName());
        stkPayment.setStk(pidvnStkPayment.getStk());
        stkPayment.setCurrency(pidvnStkPayment.getCurrency());
        stkPayment.setBank(pidvnStkPayment.getBank());
        stkPayment.setBranchName(pidvnStkPayment.getBranchName());
        stkPayment.setBenificiary(pidvnStkPayment.getBenificiary());

        return this.pidvnStkPaymentRepo.save(stkPayment);
    }
}
