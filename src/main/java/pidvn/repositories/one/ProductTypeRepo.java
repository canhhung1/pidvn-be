package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.ProductType;

import java.util.List;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType, Integer> {

    List<ProductType> findByProductId(Integer productId);

}
