package pidvn.modules.relay.material_management.relay_warehouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.RelayWhRecords;
import pidvn.mappers.one.relay.material_management.relay_warehouse.RelayWarehouseMapper;
import pidvn.modules.relay.material_management.relay_warehouse.models.LotVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.PhieuXuatKhoVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.ReWhRecordSearchVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.ReWhRecordVo;
import pidvn.repositories.one.RelayWhRecordsRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReWhSvc implements IReWhSvc {

    @Autowired
    private RelayWarehouseMapper reWhMapper;

    @Autowired
    private RelayWhRecordsRepo reWhRepo;

    @Override
    public List<PhieuXuatKhoVo> getListPXK() {
        return this.reWhMapper.getListPXK();
    }

    @Override
    public List<LotVo> getLotsXuatKhoBySlipNo(String slipNo) {
        return this.reWhMapper.getLotsXuatKhoBySlipNo(slipNo);
    }

    @Override
    public Map receiveLotsFromWH(List<RelayWhRecords> records, String slipNo) {
        List<RelayWhRecords> dataList = this.reWhRepo.findBySlipNoAndRecordType(slipNo, "RNP");

        List<RelayWhRecords> insertList = new ArrayList<>();
        List<RelayWhRecords> errorList = new ArrayList<>();

        /**
         * Kiểm tra lots đã được RE-WH trước đó chưa
         */
        for (RelayWhRecords record: records) {
            boolean isInsert = true;
            for (RelayWhRecords data: dataList) {
                /**
                 * Kiểm tra: nếu lotNo và slipNo trùng thì lot đã được RE-WH trước đó <br />
                 * => nên sẽ không insert vào bảng relay_records
                 */
                if (record.getLotNo().equals(data.getLotNo()) && record.getSlipNo().equals(data.getSlipNo())) {
                    errorList.add(data);
                    isInsert = false;
                    break;
                }
            }
            if (isInsert) {
                record.setRecordType("RNP");
                insertList.add(record);
            }
        }

        this.reWhRepo.saveAll(insertList);

        HashMap<String, List<RelayWhRecords>> result = new HashMap();
        result.put("insertList", insertList);
        result.put("errorList", errorList);

        return result;
    }

    @Override
    public List<ReWhRecordVo> getRelayWhRecords(ReWhRecordSearchVo searchVo) {
        return this.reWhMapper.getRelayWhRecords(searchVo);
    }

    /**
     * Chuyển NVL từ RE-WH vào LINE-WH (xe để NVL)
     * @param scannedRecords các lots đã scan
     * @return
     */
    @Override
    public Map sendMaterialToLineWh(List<RelayWhRecords> scannedRecords) {

        List<RelayWhRecords> insertList = new ArrayList<>();
        List<ReWhRecordVo> errorList = new ArrayList<>();

        // List LotNo đã scan
        List<String> lotNos = scannedRecords.stream().map(RelayWhRecords::getLotNo).collect(Collectors.toList());
        ReWhRecordSearchVo searchVo = new ReWhRecordSearchVo();
        searchVo.setLotNos(lotNos);

        // List record thực tế có trong database
        List<ReWhRecordVo> actualRecords = this.reWhMapper.getNewestRelayWhRecords(searchVo);

        /**
         * Kiểm tra các lotNo đã scan
         * 1. Nếu record_type == 'RNP' hoặc record_type == 'CTR' lưu vào database
         */
        for (RelayWhRecords record: scannedRecords) {
            boolean isExist = false;
            for (ReWhRecordVo actual: actualRecords) {
                if (record.getLotNo().equals(actual.getLotNo())) {
                    isExist = true;
                    if (actual.getRecordType().equals("RDC")) {
                        ReWhRecordVo obj = new ReWhRecordVo();
                        obj.setLotNo(record.getLotNo());
                        obj.setRemark("Đã được nhập vào QA Card: " + actual.getQaCard());
                        errorList.add(obj);
                    } else if (actual.getRecordType().equals("RNP") || actual.getRecordType().equals("CTR")) {
                        insertList.add(record);
                    }
                    break;
                }
            }
            if (!isExist) {
                ReWhRecordVo obj = new ReWhRecordVo();
                obj.setLotNo(record.getLotNo());
                obj.setRemark("Chưa được nhập vào kho trung chuyển (RE-WH)");
                errorList.add(obj);
            }
        }

        this.reWhRepo.saveAll(insertList);

        Map<String, Object> result = new HashMap<>();
        result.put("insertList", insertList);
        result.put("errorList", errorList);

        return result;
    }


}
