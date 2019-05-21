package cn.lyy.crowdfunding.manager.controller;

import cn.lyy.crowdfunding.bean.Permission;
import cn.lyy.crowdfunding.manager.service.PermissionService;
import cn.lyy.utils.AjaxResult;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index() {
        return "permission/index";
    }


    @RequestMapping("/loadData")
    @ResponseBody
    public Object loadData() {

        AjaxResult result = new AjaxResult();

        try {

            List<Permission> root = new ArrayList<Permission>();


            List<Permission> childredPermissons =  permissionService.queryAllPermission();


            Map<Integer,Permission> map = new HashMap<Integer,Permission>();//100

            for (Permission innerpermission : childredPermissons) {
                map.put(innerpermission.getId(), innerpermission);
            }


            for (Permission permission : childredPermissons) { //100
                //通过子查找父
                //子菜单
                Permission child = permission ; //假设为子菜单
                if(child.getPid() == null ){
                    root.add(permission);
                }else{
                    //父节点
                    Permission parent = map.get(child.getPid());
                    parent.getChildren().add(child);
                }
            }



            result.setSuccess(true);
            result.setData(root);

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("加载许可树数据失败!");
        }


        return result ;
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "permission/add";
    }

}
