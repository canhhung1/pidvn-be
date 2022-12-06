package pidvn.modules.relay.datecode_management.services;

import pidvn.entities.one.RelayDateCode;
import pidvn.modules.relay.datecode_management.models.DateCodeVo;
import pidvn.modules.relay.datecode_management.models.QaCardVo;

import java.util.List;
import java.util.Map;

public interface IRelayDateCodeSvc {
    List<DateCodeVo> getDateCodes(String qaCard);
    List<DateCodeVo> getAllDateCode();
    RelayDateCode createDateCode(RelayDateCode dateCode) throws Exception;
    Map deleteDateCode(Integer id);
    List<QaCardVo> getQaCards();
}
