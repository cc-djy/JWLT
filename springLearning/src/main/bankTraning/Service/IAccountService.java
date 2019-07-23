package Service;

public interface IAccountService {
    //转账
    public Object transferMoney(String outer,String inner,Integer money);
}
