package dao;

import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserDao implements userDaoInterface{
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUserDao(User u){
        System.out.println("Dao添加用户" + u.toString());
    }

    @PostConstruct //自定义初始化
    public void myInit(){
        System.out.println("自定义初始化方法。。。。。");

    }


    public void addUser(Object user) {
        jdbcTemplate.update("insert into spring(userNAME, PASSWORD) value (?,?)","cc","123456");
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
