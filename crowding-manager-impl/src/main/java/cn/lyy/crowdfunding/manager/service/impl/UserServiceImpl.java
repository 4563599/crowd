package cn.lyy.crowdfunding.manager.service.impl;

import cn.lyy.crowdfunding.bean.Role;
import cn.lyy.crowdfunding.bean.User;
import cn.lyy.crowdfunding.manager.dao.UserMapper;
import cn.lyy.crowdfunding.manager.service.UserService;
import cn.lyy.exception.LoginFailException;
import cn.lyy.utils.Const;
import cn.lyy.utils.MD5Util;
import cn.lyy.utils.Page;
import cn.lyy.vo.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(Map<String, Object> paramMap) {
        User user = userMapper.queryUserlogin(paramMap);
        if (user == null) {
            throw new LoginFailException("用户账号或者密码不正确");
        }
        return user;
    }

//    @Override
//    public Page queryUserByPage(Integer pageno, Integer pagesize) {
//        Page page = new Page(pageno,pagesize);
//        Integer startIndex = page.getStartIndex();
//        List<User> datas = userMapper.queryList(startIndex,pagesize);
//
//        page.setDatas(datas);
//
//        Integer totalSize = userMapper.queryCount();
//        page.setTotalsize(totalSize);
//        return page;
//    }

    @Override
    public int saveUser(User user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());
        user.setCreatetime(createTime);
        user.setUserpswd(MD5Util.digest(Const.PSSWORD));
        return userMapper.insert(user);
    }

    @Override
    public Page queryUserByPage(Map<String, Object> paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<User> datas = userMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalSize = userMapper.queryCount(paramMap);
        page.setTotalsize(totalSize);
        return page;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteUsers(Integer[] ids) {
        int totalCount = 0;
        for (Integer i : ids) {
            int count = userMapper.deleteByPrimaryKey(i);
            totalCount += count;
        }
        if (totalCount != ids.length) {
            throw new RuntimeException("批量删除失败");
        }
        return totalCount;
    }

    @Override
    public int deleteUsersByvo(Data data) {
        return userMapper.deleteUsersByVO(data.getDatas());
    }

    @Override
    public List<Role> findAllListRoles() {
        return userMapper.queryAllRoles();
    }

    @Override
    public List<Integer> findALLroleIds(Integer id) {
        return userMapper.queryRoleById(id);
    }

    @Override
    public int saveUserRoleRelationShip(@Param("userid") Integer userid, @Param("data") Data data) {
        return userMapper.saveUserRoleRelationShip(userid, data);
    }



    @Override
    public int deleteUserRoleRelationship(Integer userid, Data data) {
        return userMapper.deleteUserRoleRelationship(userid,data);
    }
}
