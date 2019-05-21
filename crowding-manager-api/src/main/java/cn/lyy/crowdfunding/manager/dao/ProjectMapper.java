package cn.lyy.crowdfunding.manager.dao;

import cn.lyy.crowdfunding.bean.Project;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    Project selectByPrimaryKey(Integer id);

    List<Project> selectAll();

    int updateByPrimaryKey(Project record);
}