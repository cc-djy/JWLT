package Service.impl;

import Dao.IAccountDao;
import Dao.impl.AccountDaoImpl;
import Service.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;//由spring注入

    public void setAcconutDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //spring配置事务模板
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void transferMoney(final String outer, final String inner, final Integer money) {
        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                //扣钱
                accountDao.out(outer,money);
                int i = 10/0;
                //进账
                accountDao.in(inner,money);
            }
        });
    }
}
