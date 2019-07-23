package Service.impl;

import Dao.IAccountDao;
import Service.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
//整个类都使用这个事务
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class AccountServiceImpl2 implements IAccountService {
    private IAccountDao accountDao;//由spring注入

    public void setAcconutDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Object transferMoney(final String outer, final String inner, final Integer money) {
        //扣钱
        accountDao.out(outer,money);
        //进账
        accountDao.in(inner,money);
        String message = outer + "转了" + money + "元给" + inner;
        return message;
    }
}
