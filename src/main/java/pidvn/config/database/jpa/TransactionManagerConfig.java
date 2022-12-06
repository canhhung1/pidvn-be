package pidvn.config.database.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {

    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager chainedTransactionManager (
            @Qualifier("transactionManagerOne") PlatformTransactionManager transactionManagerOne,
            @Qualifier("transactionManagerTwo") PlatformTransactionManager transactionManagerTwo,
            @Qualifier("transactionManagerThree") PlatformTransactionManager transactionManagerThree) {
        return new ChainedTransactionManager(transactionManagerOne, transactionManagerTwo, transactionManagerThree);
    }
}
