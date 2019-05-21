package cn.lyy.crowdfunding.manager.dao;

import cn.lyy.crowdfunding.bean.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> queryAllPermission();

}