package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserDao2 extends JdbcDaoSupport implements userDaoInterface{

    @Override
    public void addUserDao(User u) {

    }

    @Override
    public void addUser(User user) {
        getJdbcTemplate().update("insert into spring(userNAME, PASSWORD) value (?,?)",user.getName(),user.getPassword());
    }
}
