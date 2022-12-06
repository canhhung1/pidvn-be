package pidvn.modules.relay.material_management.process_recording.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.RelayWhRecords;
import pidvn.modules.relay.material_management.process_recording.models.ReWhRecordSearchVo;
import pidvn.modules.relay.material_management.process_recording.models.ReWhRecordVo;
import pidvn.repositories.one.RelayWhRecordsRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReProcessRecordingSvc implements IReProcessRecodingSvc {

    @Autowired
    private RelayWhRecordsRepo relayWhRecordsRepo;

    @Override
    public Map checkScannedLot(ReWhRecordVo reWhRecordVo) {

        List<RelayWhRecords> records = this.relayWhRecordsRepo.findByLotNoOrderByIdDesc(reWhRecordVo.getLotNo());
        Map result = new HashMap();
        String message = "";

        if (records.size() == 0) {
            message = "Lot này chưa được SCAN nhập vào kho trung chuyển (RE-WH)";
            result.put("status", "ERROR");
            result.put("message", message);
            return result;
        }

        RelayWhRecords recordLatest = records.get(0);

        if ("RNP".equals(recordLatest.getRecordType())) {
            message = "Lot này chưa được SCAN nhập vào xe nguyên vật liệu (LINE-WH)";
            result.put("status", "ERROR");
            result.put("message", message);
            return result;
        } else if ("RDC".equals(recordLatest.getRecordType())) {

            // Trường hợp này NVL đang ở xe NVL (LINE-WH) nên có thể đổ vào line
            message = "Có thể nhập vào LINE";
            reWhRecordVo.setActualQty(reWhRecordVo.getQty());

            float importQty = 0;

            for (RelayWhRecords record: records) {
                if ("CDL".equals(record.getRecordType())) {
                    importQty+=record.getQty();
                }
            }
            float actualQty = reWhRecordVo.getQty() - importQty;
            reWhRecordVo.setActualQty(actualQty);
            reWhRecordVo.setQty(actualQty);

            result.put("status", "OK");
            result.put("message", message);
            result.put("data", reWhRecordVo);
            return result;

        } else if ("CDL".equals(recordLatest.getRecordType())) {
            /**
             * TH này có thể nhập NVL vào line nếu gói NVL vẫn còn
             * 1. Tính Qty thực tế còn lại của gói NVL
             */
            float importQty = 0;

            for (RelayWhRecords record: records) {
                if ("CDL".equals(record.getRecordType())) {
                    importQty+=record.getQty();
                }
            }
            float actualQty = reWhRecordVo.getQty() - importQty;
            reWhRecordVo.setActualQty(actualQty);
            reWhRecordVo.setQty(actualQty);

            message = "Có thể nhập vào LINE";
            result.put("status", "OK");
            result.put("message", message);
            result.put("data", reWhRecordVo);
            return result;
        }



        return result;
    }

    @Override
    public List<RelayWhRecords> saveAllReWhRecords(List<RelayWhRecords> relayWhRecordsList) {
        return this.relayWhRecordsRepo.saveAll(relayWhRecordsList);
    }

    @Override
    public List<ReWhRecordVo> getRelayWhRecords(ReWhRecordSearchVo searchVo) {
        return null;
    }


}
