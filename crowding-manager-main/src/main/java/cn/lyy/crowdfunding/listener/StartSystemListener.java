package cn.lyy.crowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartSystemListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute("APP_PATH", contextPath);
        System.out.println("APP_PATH...." + contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
