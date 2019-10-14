package com.cc.service.impl;

import com.cc.bean.User;
import com.cc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, User> hashOperations; //相当于redisTemplate.opsForValue()

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;//相当于redisTemplate.opsForHash()

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOperations; //相当于redisTemplate.opsForList()

    /**
     * 通过key获取值
     * 如果redis不存在key就到数据库查询
     * 如果存在直接在redis上查询
     *
     * @param key
     * @return
     */
    @Override
    public String getUser(String key) {

        redisTemplate.expire(key, 10, TimeUnit.SECONDS);
        if (redisTemplate.hasKey(key)) {
            String result = valueOperations.get(key);
            System.out.println(this.getClass().getName() + "在redis中取出返回");
            return result;
        } else {
            String result = "dgut";
            valueOperations.set(key, result);
            System.out.println(this.getClass().getName() + "在mysql中取出返回");
            return result;
        }
    }

    /**
     * 登录限制
     *
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public User login(String name, String pwd) {
        return null;
    }

    /**
     * 登录不成功的操作
     *
     * @param name 用户名
     * @return
     */
    @Override
    public String loginValData(String name) {
        String key = User.getLoginFalseTimeKey(name);
        int num = 5;//设置最大的登录失败次数，超过该次数就锁定
        //记录当前登录错误的次数
        if (!redisTemplate.hasKey(key)) { //第一次登录失败 赋值1，设置失效期为2分钟
            //redisTemplate中在赋值时不能直接设置失效期（会设置失败）
            valueOperations.set(key, "1");

            redisTemplate.expire(key, 2, TimeUnit.MINUTES);

            return "登录失败，在2分钟内还允许输入错误" + (num - 1) + "次";
        } else { //不是第一次登录失败
            //查询登录失败次数的key的结果
            int count = Integer.parseInt(valueOperations.get(key));
            if (count < (num - 1)) { //如果当前登录失败次数小于4，还有资格继续登录
                valueOperations.increment(key, 1); //对指定key，增加指定数据
                long second = redisTemplate.getExpire(key, TimeUnit.SECONDS); //返回秒
                return "登录失败，在" + second + "秒内还允许输入错误" + (num - count - 1) + "次";
            } else { //超过指定登录次数
                valueOperations.set(User.getLoginTimeLockKey(name), "1");
                redisTemplate.expire(User.getLoginTimeLockKey(name), 1, TimeUnit.HOURS);
                return name + "登录失败次数超过5次，需要等待1个小时方能登录";
            }
        }
    }

    /**
     * 判断当前登录是否被限制登录
     * 如果key存在，代表被限制，就返回需要等待的时间
     * 如果key不存在，就不被限制
     *
     * @param name
     * @return
     */
    @Override
    public Map<String, Object> loginLock(String name) {
        String key = User.getLoginTimeLockKey(name);
        Map<String, Object> map = new HashMap<>();
        if (redisTemplate.hasKey(key)) {
            long lockTime = redisTemplate.getExpire(key, TimeUnit.MINUTES);//以分钟为单位
            map.put("flag", true);
            map.put("lockTime", lockTime);//还剩的时间解锁
        } else {
            map.put("flag", false);
        }
        return map;
    }

    /**
     * 往redis存储hash数据
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        hashOperations.put("user", user.getId(), user);
    }

    /**
     * 通过id在redis 取对象
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(String id) {
        if (hashOperations.hasKey("user", id)) { //redis存在key
            //在redis取值
            System.out.println(this.getClass().getName() + "---redis中查询的对象");
            return (User) hashOperations.get("user", id);
        } else { //不存在key
            //在Mysql取
            System.out.println(this.getClass().getName() + "---mysql中查询的对象");
            User user = new User();
            user.setId(id);
            user.setUsername("111");
            user.setSex("111");
            user.setAge("111");
            user.setPassword("111");
            this.addUser(user);
            return user;
        }
    }

    /**
     * 通过redisTemplate List类型的存和取
     */
    @Override
    public void listAdd() {
        String key = "newslist:top5";
        listOperations.leftPush(key, "习近平将出访两邻国 亮点看点都在这");
        listOperations.leftPushAll(key, "美称中方逼美企弃自己价值观 耿爽回俩数字", "国产飞碟型武装直升机亮相(图)", "追求低级趣味的省部级 被降为四级调研员", "江苏无锡一高架桥侧翻 一货车掉下有车被压");
    }

    /**
     * 分页查找
     *
     * @param pageNum  第几页
     * @param pageSize 每一页多少条
     * @return
     */
    @Override
    public Object listRange(int pageNum, int pageSize) {
        String key = "newslist:top5";
        System.out.println("新闻条数" + listOperations.size(key));
        List<String> list = listOperations.range(key, (pageNum - 1) * pageSize, pageNum * pageSize - 1);
        return list;
    }


    /**
     * 购买商品初始化
     * @param cardId 商品id
     */
    public void listQueueInit(String cardId){
        String key = "prod:" + cardId ;
        listOperations.leftPushAll(key,"1.出货","2.收件完成","3.运输中","4.正在派送","5.已签收");

    }

    /**
     * 触发事件，每运行一次，代表完成一个任务
     * @param cardId
     */
    public void listQueueTouch(String cardId){
        String key = "prod:" + cardId +":finish";
        listOperations.rightPopAndLeftPush("prod:"+cardId,key);
    }

    //查询：客户查询，我的快递到哪了
    public List<String> listQueueSucc(String cardId){
        String key = "prod:" + cardId +":finish";
        return listOperations.range(key,0,-1);
    }

    //物流查询，当前还剩多少任务待完成
    public List<String> listQueueWait(String cardId){
        String key = "prod:" + cardId ;
        return listOperations.range(key,0,-1);
    }

}
