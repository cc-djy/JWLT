package com.cc.service;

import com.cc.bean.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    /**
     * string存和取
     */
    String getUser(String key);

    /**
     *
     * @param uName 用户名
     * @param uPwd  密码
     * @return 返回用户
     */
    User login(String uName,String uPwd);

    /**
     * @param name 用户名
     * @return 给用户详细信息提示
     */
    String loginValData(String name);

    /**
     * 判断当前登录时候被限制
     * @param name
     * @return
     */
    Map<String,Object> loginLock(String name);

    /**
     * 往redis存储hash数据
     * @param user
     */
    void addUser(User user);

    /**
     * 通过id在redis 取对象
     * @param id
     * @return
     */
    User getUserById(String id);


    //list类型操作案例
    void listAdd();
    Object listRange(int pageNum,int pageSize);

    //订单消息队列
    void listQueueInit(String cardId);
    void listQueueTouch(String cardId);
    List<String> listQueueSucc(String cardId);
    List<String> listQueueWait(String cardId);
}
