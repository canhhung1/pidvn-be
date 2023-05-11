package pidvn.modules.relay.datecode_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.Lots;
import pidvn.entities.one.RelayDateCode;
import pidvn.mappers.one.relay.datecode_management.RelayDateCodeMapper;
import pidvn.modules.relay.datecode_management.models.DateCodeVo;
import pidvn.modules.relay.datecode_management.models.QaCardVo;
import pidvn.repositories.one.LotsRepo;
import pidvn.repositories.one.RelayDateCodeRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service()
public class RelayDateCodeSvc implements IRelayDateCodeSvc {

    @Autowired
    private RelayDateCodeRepo relayDateCodeRepo;

    @Autowired
    private RelayDateCodeMapper dateCodeMapper;

    @Autowired
    private LotsRepo lotsRepo;

    @Override
    public List<DateCodeVo> getDateCodes(String qaCard) {
        return this.dateCodeMapper.getDateCodes(qaCard);
    }

    @Override
    public List<DateCodeVo> getAllDateCode() {
        return this.dateCodeMapper.getAllDateCode();
    }

    @Override
    public RelayDateCode createDateCode(RelayDateCode dateCode) throws Exception {
        List<RelayDateCode> relayDateCodes = this.relayDateCodeRepo.findByQaCardAndDateCodeAndCustomerCode(dateCode.getQaCard(), dateCode.getDateCode(), dateCode.getCustomerCode());
        if (relayDateCodes.size() > 0) {
            throw new Exception("DateCode: " + dateCode.getDateCode() + "và CustomerCode: " + dateCode.getCustomerCode() + " đã tồn tại !");
        }
        return this.relayDateCodeRepo.save(dateCode);
    }

    @Override
    public Map deleteDateCode(Integer id) {
        this.relayDateCodeRepo.deleteById(id);
        Map result = new HashMap();
        result.put("Response:","Xóa thành công");
        return result;
    }

    @Override
    public List<QaCardVo> getQaCards() {
        return this.dateCodeMapper.getQaCards();
    }

    @Override
    public Lots getQACardByValue(String qaCard) {
        return this.lotsRepo.findByLotNo(qaCard);
    }
}
