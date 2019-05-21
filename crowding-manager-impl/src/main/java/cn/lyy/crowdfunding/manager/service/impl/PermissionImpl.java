package cn.lyy.crowdfunding.manager.service.impl;

import cn.lyy.crowdfunding.bean.Permission;
import cn.lyy.crowdfunding.manager.dao.PermissionMapper;
import cn.lyy.crowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> queryAllPermission() {
        return permissionMapper.queryAllPermission();
    }
}
