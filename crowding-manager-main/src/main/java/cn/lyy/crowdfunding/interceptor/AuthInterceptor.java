package cn.lyy.crowdfunding.interceptor;

import cn.lyy.crowdfunding.bean.Permission;
import cn.lyy.crowdfunding.manager.service.PermissionService;
import cn.lyy.utils.Const;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Service
    PermissionService permissionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<Permission> permissionList = permissionService.queryAllPermission();

        Set<String> allUris = new HashSet<>();

        for (Permission p : permissionList) {
            allUris.add(p.getUrl());
        }

        String servletPath = request.getServletPath();

        if (allUris.contains(servletPath)) {

            Set<String> myURIs = (Set<String>) request.getSession().getAttribute(Const.MY_URLS);

            if (myURIs.contains(servletPath)) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath() + "/login.htm");
                return false;
            }
        } else {
            return true;
        }

    }
}
