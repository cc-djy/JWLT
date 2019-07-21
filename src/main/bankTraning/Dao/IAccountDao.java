package Dao;

public interface IAccountDao {
//    转出
    public void out(String outer, Integer money);
//    转进
    public void in(String inner, Integer money);
}
