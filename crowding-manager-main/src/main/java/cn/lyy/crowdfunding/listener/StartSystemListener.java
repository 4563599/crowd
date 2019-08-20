package cn.lyy.crowdfunding.listener;

import cn.lyy.crowdfunding.bean.Permission;
import cn.lyy.crowdfunding.manager.service.PermissionService;
import cn.lyy.utils.Const;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartSystemListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute("APP_PATH", contextPath);
        System.out.println("APP_PATH...." + contextPath);

        //2.加载所有许可路径

        ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        PermissionService permissionService = ioc.getBean(PermissionService.class);
        List<Permission> queryAllpermission = permissionService.queryAllPermission();

        Set<String> allURIs = new HashSet<>();

        for (Permission permission : queryAllpermission) {
            allURIs.add("/" + permission.getUrl());
        }

        servletContext.setAttribute(Const.ALL_PERMISSION_URI, allURIs);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
