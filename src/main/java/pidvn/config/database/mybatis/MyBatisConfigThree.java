package pidvn.config.database.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = "pidvn.mappers.three",
        sqlSessionFactoryRef = "sqlSessionFactory3",
        sqlSessionTemplateRef = "sqlSessionTemplate3"
)
public class MyBatisConfigThree {

    @Autowired
    @Qualifier("dsThree")
    DataSource dsThree;

    @Bean
    SqlSessionFactory sqlSessionFactory3() {
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dsThree);
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate3() {
        return new SqlSessionTemplate(sqlSessionFactory3());
    }
}
