package pidvn.share_module.relay_date_code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.RelayDateCode;
import pidvn.repositories.one.RelayDateCodeRepo;

import java.util.List;

@Service("RelayDateCodeService")
public class RelayDateCodeSvc implements IRelayDateCode {

    @Autowired
    private RelayDateCodeRepo relayDateCodeRepo;

    @Override
    public List<RelayDateCode> findAll() {
        return this.relayDateCodeRepo.findAll();
    }
}
