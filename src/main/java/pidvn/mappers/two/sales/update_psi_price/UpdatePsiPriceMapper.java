package pidvn.mappers.two.sales.update_psi_price;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.sales.update_psi_price.modes.PsiPriceVo;

import java.util.List;

@Mapper
public interface UpdatePsiPriceMapper {
    List<PsiPriceVo> getPsiPriceData(String type);
}
