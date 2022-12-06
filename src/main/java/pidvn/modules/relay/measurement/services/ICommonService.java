package pidvn.modules.relay.measurement.services;

import pidvn.entities.one.MeaModel;
import pidvn.entities.one.MeaReason;
import pidvn.entities.one.Users;
import pidvn.modules.relay.measurement.models.QaCardVo;

import java.util.List;

public interface ICommonService {
    List<MeaReason> getReasons();
    List<MeaModel> getModels();
    List<Users> getUsers();
    List<QaCardVo> getQaCards();
}
