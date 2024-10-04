package pidvn.modules.warehouse.iqc_recheck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.InoutLabels;
import pidvn.entities.one.Lots;
import pidvn.modules.warehouse.iqc_recheck.models.LabelDto;
import pidvn.repositories.one.InoutLabelsRepo;
import pidvn.repositories.one.LotsRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class WhIqcRecheckSvc implements IWhIqcRecheckSvc {

    @Autowired
    private InoutLabelsRepo inoutLabelsRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Override
    public List<Lots> scanLabel(LabelDto labelDto) {

        if (labelDto.getLabelType().equals("ParentLabel")) {
            return this.scanParentLabel(labelDto.getQrCode());
        }

        if (labelDto.getLabelType().equals("Elektrisola")) {
            return this.scanElektrisolaLabel(labelDto.getQrCode());
        }

        return this.scanChildrenLabel(labelDto.getQrCode());
    }


    /**
     * Scan tem thùng to (ParentLabel)
     * @return
     */
    private List<Lots> scanParentLabel(String qrCode) {

        String outerLotNo = qrCode.split(";")[2];

        List<InoutLabels> inoutLabels = this.inoutLabelsRepo.findByOuterLotNo(outerLotNo);

        List<String> lots = new ArrayList<>();

        for (InoutLabels item: inoutLabels) {
            lots.addAll(Arrays.asList(item.getInnerLabels().split(";")));
        }

        return this.lotsRepo.findByLotNoIn(lots);
    }

    /**
     * Scan tem ElektrisolaLabel
     * @param qrCode
     * @return
     */
    private List<Lots> scanElektrisolaLabel(String qrCode) {
        String[] value = qrCode.split(";");
        String lotNo = value[3] + value[5];
        Lots lot = this.lotsRepo.findByLotNo(lotNo);
        return Collections.singletonList(lot);
    }

    private List<Lots> scanChildrenLabel(String qrCode) {
        String[] value = qrCode.split(";");
        String lotNo = value[3];
        Lots lot = this.lotsRepo.findByLotNo(lotNo);
        return Collections.singletonList(lot);
    }



}
