package cn.lyy.crowdfunding.manager.dao;

import cn.lyy.crowdfunding.bean.Role;
import cn.lyy.crowdfunding.bean.User;
import cn.lyy.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User queryUserlogin(Map<String, Object> paramMap);

//    List<User> queryList(@Param("startIndex")Integer startIndex,@Param("pagesize") Integer pagesize);

//    Integer queryCount();

    List<User> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

//    int deleteUsersByVO(List<User> data);

    int deleteUsersByVO(@Param("userList") List<User> data);

    List<Role> queryAllRoles();

    List<Integer> queryRoleById(Integer id);

    int saveUserRoleRelationShip(@Param("userid") Integer userid, @Param("data") Data data);

    int deleteUserRoleRelationship(@Param("userid") Integer userid,@Param("data")  Data data);
}