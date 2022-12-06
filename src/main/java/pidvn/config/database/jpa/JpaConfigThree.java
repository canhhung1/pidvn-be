package pidvn.config.database.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "pidvn.repositories.three",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanThree",
        transactionManagerRef = "transactionManagerThree"
)
public class JpaConfigThree {

    @Autowired
    @Qualifier("dsThree")
    DataSource dsThree;

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanThree(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dsThree)
                .packages("pidvn.entities.three")
                .properties(jpaProperties.getProperties())
                .persistenceUnit("pu3")
                .build();
    }

    @Bean
    PlatformTransactionManager transactionManagerThree(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBeanThree(builder).getObject());
    }
}
