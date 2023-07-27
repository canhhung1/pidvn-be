package pidvn.modules.hr.meal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.three.hr.meal.HrMealMapper;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;

import java.util.List;

@Service
public class HrMealSvc implements IHrMealSvc {

    @Autowired
    private HrMealMapper hrMealMapper;

    @Override
    public List<MealRecordVo> getMealRecords(SearchVo searchVo) {
        return this.hrMealMapper.getMealRecords(searchVo);
    }
}
