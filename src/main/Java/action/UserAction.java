package action;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.userServiceInterface;

@Controller
@Scope("prototype")
public class UserAction {
    /**
     * Autowired根据类型注入值
     * 如果是一个接口，从容器找接口实现类
     * 如果是一个实现类，就找类
     */
    @Autowired
    private userServiceInterface us;

    public void save(User u){
        System.out.println("action添加用户" + u);
        us.addUserService(u);
    }
}
