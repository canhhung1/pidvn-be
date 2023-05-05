package pidvn.modules.pe.std_dummy.services;

import pidvn.entities.one.Line;
import pidvn.entities.one.PidvnStdDummy;

import java.util.List;

public interface IPeStdDummySvc {

    List<Line> getLines(List<Integer> productIds);
    List<PidvnStdDummy> getStdDummies();
    PidvnStdDummy createStdDummy(PidvnStdDummy stdDummy);
    PidvnStdDummy deleteStdDummy(Integer id);
}
