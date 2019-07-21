package Dao.impl;

import Dao.IAccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

//spring-jdbc
public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {
    @Override
    public void out(String outer, Integer money) {
        getJdbcTemplate().update("update account set money = money - ? where username = ?",money,outer);
    }

    @Override
    public void in(String inner, Integer money) {
        getJdbcTemplate().update("update account set money = money + ? where username = ?",money,inner);
    }
}
