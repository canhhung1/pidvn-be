package pidvn.modules.warehouse.iqc_recheck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.InoutLabels;
import pidvn.entities.one.IqcDataMaster;
import pidvn.entities.one.IqcRequest;
import pidvn.entities.one.Lots;
import pidvn.modules.warehouse.iqc_recheck.models.LabelDto;
import pidvn.modules.warehouse.iqc_recheck.models.RequestDto;
import pidvn.repositories.one.InoutLabelsRepo;
import pidvn.repositories.one.IqcDataMasterRepo;
import pidvn.repositories.one.IqcRequestRepo;
import pidvn.repositories.one.LotsRepo;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WhIqcRecheckSvc implements IWhIqcRecheckSvc {

    @Autowired
    private InoutLabelsRepo inoutLabelsRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Autowired
    private IqcRequestRepo iqcRequestRepo;

    @Autowired
    private IqcDataMasterRepo iqcDataMasterRepo;

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

    @Override
    public IqcRequest createIqcRecheckRequest(RequestDto requestDto) {

        /**
         * B1: tạo request
         */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(new Date());
        int sequence = this.iqcRequestRepo.getTotalRequestRecheckInDay() + 1;

        IqcRequest obj = new IqcRequest();
        obj.setType(requestDto.getType());
        obj.setRequestedBy(requestDto.getRequestedBy());
        obj.setRequestNo("RC-" + date + "-" + sequence);

        IqcRequest request = this.iqcRequestRepo.save(obj);

        /**
         * B2: lưu dữ liệu vào iqc_data_master
         */
        List<IqcDataMaster> dataMasters = new ArrayList<>();
        for (Lots item: requestDto.getLots()) {
            IqcDataMaster master = new IqcDataMaster();
            master.setRequestNo(request.getRequestNo());
            master.setModel(item.getModel());
            master.setLotGroup(item.getLotGroup());
            master.setLotNo(item.getLotNo());
            dataMasters.add(master);
        }

        List<IqcDataMaster> masters = this.iqcDataMasterRepo.saveAll(dataMasters);

        return request;
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

        if (lot == null) {
            throw new RuntimeException("Lots: " + lotNo + " not found");
        }

        return Collections.singletonList(lot);
    }

    private List<Lots> scanChildrenLabel(String qrCode) {
        String[] value = qrCode.split(";");
        String lotNo = value[3];
        Lots lot = this.lotsRepo.findByLotNo(lotNo);
        if (lot == null) {
            throw new RuntimeException("Lots: " + lotNo + " not found");
        }
        return Collections.singletonList(lot);
    }



}
