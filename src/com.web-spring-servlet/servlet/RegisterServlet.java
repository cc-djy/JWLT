package servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userName");
        IUserService userService = null;
        //service由spring容器创建
//        开发中，项目只需要加载一次spring配置\
        userService = (IUserService) context.getBean("userService");
        userService.addUser(name);

//        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        System.out.println(context);
//        userService = (IUserService) context.getBean("userService");
//        userService.addUser(name);

        response.getWriter().write("register success");
    }
}
