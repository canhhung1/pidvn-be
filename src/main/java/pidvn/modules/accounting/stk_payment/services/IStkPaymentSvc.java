package pidvn.modules.accounting.stk_payment.services;

import pidvn.entities.one.PidvnStkPayment;

import java.util.List;

public interface IStkPaymentSvc {
    List<PidvnStkPayment> getStkPayments();

    PidvnStkPayment createStkPayment(PidvnStkPayment pidvnStkPayment);

    PidvnStkPayment updateStkPayment(PidvnStkPayment pidvnStkPayment) throws Exception;
}
