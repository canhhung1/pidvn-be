package pidvn.modules.pe.std_dummy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.Line;
import pidvn.entities.one.PidvnStdDummy;
import pidvn.repositories.one.LineRepo;
import pidvn.repositories.one.PidvnStdDummyRepo;

import java.util.List;

@Service
public class PeStdDummySvc implements IPeStdDummySvc {

    @Autowired
    private PidvnStdDummyRepo pidvnStdDummyRepo;

    @Autowired
    private LineRepo lineRepo;

    @Override
    public List<Line> getLines(List<Integer> productIds) {
        return this.lineRepo.findByProductIdIn(productIds);
    }

    @Override
    public List<PidvnStdDummy> getStdDummies() {
        return this.pidvnStdDummyRepo.findByFlag(1);
    }

    @Override
    public PidvnStdDummy createStdDummy(PidvnStdDummy stdDummy) {
        return this.pidvnStdDummyRepo.save(stdDummy);
    }

    @Override
    public PidvnStdDummy deleteStdDummy(Integer id) {
        PidvnStdDummy stdDummy = this.pidvnStdDummyRepo.findById(id).get();
        stdDummy.setFlag(0);
        return this.pidvnStdDummyRepo.save(stdDummy);
    }
}
