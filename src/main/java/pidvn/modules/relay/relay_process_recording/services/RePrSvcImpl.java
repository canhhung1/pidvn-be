package pidvn.modules.relay.relay_process_recording.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.entities.one.Lots;
import pidvn.entities.one.PurWhRecords;
import pidvn.mappers.one.relay.relay_process_recording.RePrMapper;
import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;
import pidvn.modules.relay.relay_process_recording.models.SearchDto;
import pidvn.repositories.one.LotsRepo;
import pidvn.repositories.one.PurWhRecordsRepo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RePrSvcImpl implements RePrSvc {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RePrMapper rePrMapper;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Override
    public List<RequestDto> getRequests(SearchDto searchDto) {
        return this.rePrMapper.getRequests(searchDto);
    }

    @Override
    public List<LotDto> getRequestDetail(String requestNo) {

        List<LotDto> result = this.rePrMapper.getRequestDetail(requestNo);

        return result;
    }

    @Autowired
    private EntityManager entityManager;

    /**
     * Nhận NVL từ kho WH
     * @param lots danh sach NVL
     * @return
     */
    @Override
    public List<LotDto> receiveMaterials(List<LotDto> lots) {
        /**
         * Insert vào bảng pur_wh_record với record_type = 'RNP'
         */
        List<PurWhRecords> data = lots.stream().map(item -> modelMapper.map(item, PurWhRecords.class)).collect(Collectors.toList());
        this.purWhRecordsRepo.saveAll(data);

        /**
         * Cập nhật vào bảng pur_wh_record với record_type = 'XPA'
         * (cập nhật cột receiver để đánh dấu là lot đã nhận)
         */
        List<String> lotNos = new ArrayList<>();
        for (LotDto item : lots) {
            lotNos.add(item.getLotNo());
        }


        String jpql = "SELECT p FROM PurWhRecords p WHERE p.slipNo = :slipNo and  p.lotNo IN :lotNos and p.recordType = :recordType";
        TypedQuery<PurWhRecords> query = entityManager.createQuery(jpql, PurWhRecords.class);
        query.setParameter("lotNos", lotNos);
        query.setParameter("slipNo", lots.get(0).getReqNo());
        query.setParameter("recordType" ,"XPA");

        List<PurWhRecords> purWhRecords = query.getResultList();

        for (PurWhRecords item : purWhRecords) {
            item.setReceiver(lots.get(0).getReceiver());
        }


        this.purWhRecordsRepo.saveAll(purWhRecords);

        return lots;
    }

    @Override
    public LotDto validateLotReceive(LotDto lotDto) {
        Lots obj = this.lotsRepo.findByLotNo(lotDto.getLotNo());
        LotDto lot = this.modelMapper.map(obj, LotDto.class);
        lot.setReqNo(lotDto.getReqNo());
        lot.setRecordType(lotDto.getRecordType());
        lot.setFlag(lotDto.getFlag());
        lot.setRemainQty(obj.getQty());
        lot.setReceiver(lotDto.getReceiver());
        lot.setQrCode(lotDto.getQrCode());
        return lot;
    }

    /**
     * Xóa lot đã nhận
     * 1. Thực hiện xóa khỏi bảng pur_wh_record (record_type = 'RNP')
     * 2. Thực hiện update cột receiver = null trong bảng pur_wh_record (record_type = 'XPA')
     * @param lotDto
     * @return
     */
    @Override
    @Transactional(transactionManager = "transactionManagerOne")
    public Map<String, Object> deleteLotReceived(LotDto lotDto) {

        Map<String, Object> result = new HashMap<>();

        // 1. Xóa record đã nhận
        this.purWhRecordsRepo.deleteById(lotDto.getId());


        // 2. Update receiver = null
        String jpql = "SELECT p FROM PurWhRecords p WHERE p.slipNo = :slipNo and  p.lotNo = :lotNo and p.recordType = :recordType";
        TypedQuery<PurWhRecords> query = entityManager.createQuery(jpql, PurWhRecords.class);
        query.setParameter("lotNo", lotDto.getLotNo());
        query.setParameter("slipNo", lotDto.getReqNo());
        query.setParameter("recordType" ,"XPA");

        PurWhRecords purWhRecord = query.getSingleResult();
        purWhRecord.setReceiver(null);

        PurWhRecords data = this.purWhRecordsRepo.save(purWhRecord);

        result.put("Id Deleted", lotDto.getId());
        result.put("Lot updated", data);
        return result;
    }


}
