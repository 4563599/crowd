package cn.lyy.crowdfunding.interceptor;

import cn.lyy.crowdfunding.bean.User;
import cn.lyy.utils.Const;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Set<String> uri = new HashSet<>();
        uri.add("/user/reg.do");
        uri.add("/user/reg.htm");
        uri.add("/login.htm");
        uri.add("/doLogin.do");
        uri.add("/logout.do");

        String servletPath = request.getServletPath();
        System.out.println("servletPath:" + servletPath);

        if (uri.contains(servletPath)) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        if (user != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/login.htm");
            return false;
        }
    }
}
