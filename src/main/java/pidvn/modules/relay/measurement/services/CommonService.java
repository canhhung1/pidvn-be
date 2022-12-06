package pidvn.modules.relay.measurement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.MeaModel;
import pidvn.entities.one.MeaReason;
import pidvn.entities.one.Users;
import pidvn.mappers.one.relay.measurement.MeasureMapper;
import pidvn.modules.relay.measurement.models.QaCardVo;
import pidvn.repositories.one.MeaModelRepo;
import pidvn.repositories.one.MeaReasonRepo;
import pidvn.repositories.one.UsersRepo;

import java.util.List;

@Service
public class CommonService implements ICommonService{

    @Autowired
    private MeaReasonRepo reasonRepo;

    @Autowired
    private MeaModelRepo modelRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private MeasureMapper measureMapper;

    @Override
    public List<MeaReason> getReasons() {
        return this.reasonRepo.findAll();
    }

    @Override
    public List<MeaModel> getModels() {
        return this.modelRepo.findAll();
    }

    @Override
    public List<Users> getUsers() {
        return this.usersRepo.findAll();
    }

    @Override
    public List<QaCardVo> getQaCards() {
        return this.measureMapper.getQaCard();
    }


}
