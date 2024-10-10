package pidvn.modules.warehouse.iqc_recheck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.mappers.one.warehouse.iqc_recheck.WhIqcRecheckMapper;
import pidvn.modules.warehouse.iqc_recheck.models.LabelDto;
import pidvn.modules.warehouse.iqc_recheck.models.LotDto;
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
    private WhIqcRecheckMapper whIqcRecheckMapper;

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
        obj.setRemark("IT test");

         IqcRequest request = this.iqcRequestRepo.save(obj);

        /**
         * B2: Tạo dữ liệu bảng master
         */

        List<LotDto> lots = this.whIqcRecheckMapper.getLotsIqcOver6Month(requestDto.getLotGroups());

        if (requestDto.getGoodsType().equals("OUTSIDE")) {
            List<IqcDataMaster> iqcDataMasters = new ArrayList<>();
            for (LotDto lot : lots) {
                IqcDataMaster obj1 = new IqcDataMaster();
                obj1.setRequestNo(request.getRequestNo());
                obj1.setModel(lot.getModel());
                obj1.setLotGroup(lot.getLotGroup());
                obj1.setLotNo(lot.getLotNo());
                obj1.setCreatedAt(new Date());
                obj1.setUpdatedAt(new Date());
                iqcDataMasters.add(obj1);
            }
            this.iqcDataMasterRepo.saveAll(iqcDataMasters);

        } else if (requestDto.getGoodsType().equals("INSIDE")) {

            List<IqcResults> iqcResults = new ArrayList<>();

        }







        return request;
    }


    @Override
    public List<LotDto> getLotGroupsIqcOver6Month() {
        return this.whIqcRecheckMapper.getLotGroupsIqcOver6Month();
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
