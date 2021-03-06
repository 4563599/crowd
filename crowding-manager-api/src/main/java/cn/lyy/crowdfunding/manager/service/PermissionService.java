package cn.lyy.crowdfunding.manager.service;

import cn.lyy.crowdfunding.bean.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionService {
    List<Permission> queryAllPermission();

    int savePermission(Permission permission);

    Permission getPermissionById(Integer id);

    int deletePermission(Integer id);

    List<Integer> queryPermissionidsByRoleid(Integer roleid);
}
