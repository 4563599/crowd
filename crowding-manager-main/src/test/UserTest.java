import cn.lyy.crowdfunding.bean.User;
import cn.lyy.crowdfunding.manager.service.UserService;
import cn.lyy.utils.MD5Util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring*.xml");
        UserService service = (UserService) applicationContext.getBean(UserService.class);

        for (int i = 0; i <100; i++) {
            User user =new User();
            user.setCreatetime("2019-04-25 11:30");
            user.setEmail("4563599@qq.com");
            user.setId(i+2);
            user.setLoginacct("lyy"+(i+2));
            user.setUsername("lyy"+(i+2));
            user.setUserpswd(MD5Util.digest("123"));
            service.saveUser(user);
        }
    }
}
