package pidvn.modules.relay.measurement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.relay.measurement.MeasureXbarRMapper;
import pidvn.modules.relay.measurement.models.xbar.XbarDataVo;
import pidvn.modules.relay.measurement.models.xbar.MeaSearchVo;
import pidvn.modules.relay.measurement.models.xbar.MeaDetailDataVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeaXbarRService implements IMeaXbarRService{

    @Autowired
    private MeasureXbarRMapper xbarRMapper;

    @Override
    public List<XbarDataVo> getXbarData(MeaSearchVo searchVo) {

        List<MeaDetailDataVo> dataList = this.xbarRMapper.getXbarData(searchVo);

        List<XbarDataVo> datasource = new ArrayList<>();

        if (searchVo.getItemType() == 1) {
            int count = 0;
            XbarDataVo obj = new XbarDataVo();
            for (MeaDetailDataVo item : dataList) {
                count++;
                if (count == 1) {
                    obj.setValue1(item.getA1());
                } else if (count == 2) {
                    obj.setValue2(item.getA1());
                } else if (count == 3) {
                    obj.setValue3(item.getA1());
                } else if (count == 4) {
                    obj.setValue4(item.getA1());
                } else if (count == 5) {
                    obj.setModel(item.getModel());
                    obj.setCreatedAt(item.getCreatedAt());
                    obj.setValue5(item.getA1());
                    datasource.add(obj);
                    obj = new XbarDataVo();
                    count = 0;
                }
            }
        } else if (searchVo.getItemType() == 2) {

            int count = 0;
            XbarDataVo obj1 = new XbarDataVo();
            XbarDataVo obj2 = new XbarDataVo();

            for (MeaDetailDataVo item : dataList) {
                count++;
                if (count == 1) {
                    obj1.setValue1(item.getB1());
                    obj2.setValue1(item.getB2());
                } else if (count == 2) {
                    obj1.setValue2(item.getB1());
                    obj2.setValue2(item.getB2());
                } else if (count == 3) {
                    obj1.setValue3(item.getB1());
                    obj2.setValue3(item.getB2());
                } else if (count == 4) {
                    obj1.setValue4(item.getB1());
                    obj2.setValue4(item.getB2());
                } else if (count == 5) {
                    obj1.setModel(item.getModel());
                    obj2.setModel(item.getModel());
                    obj1.setCreatedAt(item.getCreatedAt());
                    obj2.setCreatedAt(item.getCreatedAt());
                    obj1.setValue5(item.getB1());
                    obj2.setValue5(item.getB2());
                    datasource.add(obj1);
                    datasource.add(obj2);
                    obj1 = new XbarDataVo();
                    obj2 = new XbarDataVo();
                    count = 0;
                }
            }
        }

        return datasource;

    }
}
